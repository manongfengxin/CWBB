package cwbb.service.serviceImpl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwbb.POJO.Dto.WxUserDto;
import cwbb.POJO.Dto.WxAuth;
import cwbb.POJO.Dto.WxUserInfo;
import cwbb.POJO.doMain.CwUser;
import cwbb.POJO.doMain.WxUser;
import cwbb.dao.CwUserDao;
import cwbb.dao.WxUserDao;
import cwbb.handler.UserThreadLocal;
import cwbb.service.CwUserService;
import cwbb.service.WxService;
import cwbb.utils.JwtUtil;
import cwbb.utils.RedisKey;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CwUserServiceImpl extends ServiceImpl<CwUserDao, CwUser> implements CwUserService {

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private WxService wxService;

    @Autowired
    private CwUserDao cwUserDao;


    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;




    @Override
    public Result login(WxUserDto userDto) {
        String token = jwtUtil.sign(userDto.getId());
        userDto.setToken(token);
        userDto.setOpenid(null);
        userDto.setWxUnionId(null);
        // 需要把token 存入redis，value存为userDto，下次用户访问登录资源时，可以根据token拿到用户的详细信息
        stringRedisTemplate.opsForValue().set(RedisKey.TOKEN + token, JSON.toJSONString(userDto),7, TimeUnit.DAYS);
        /**
         * 检查CwUser表中是否有该用户数据
         */
        int uid = userDto.getId().intValue();
        if (cwUserDao.selectById(uid) == null){
            /**
             * 空数据则存入
             */
            CwUser cwUser = new CwUser(userDto.getId().intValue(),userDto.getNickname(),null,userDto.getPortrait(),null,null,null,null,"会员");
            cwUserDao.insert(cwUser);
        }
        return Result.success("登录成功",userDto);
    }

    @Override
    public Result register(WxUserDto userDto) {
        WxUser user = new WxUser();
        BeanUtils.copyProperties(userDto,user);
        /**
         * BeanUtils.copyProperties(a, b);
         *
         * b中的存在的属性，a中一定要有，但是a中可以有多余的属性；
         * a中与b中相同的属性都会被替换，不管是否有值；
         * a、 b中的属性要名字相同，才能被赋值，不然的话需要手动赋值；
         * Spring的BeanUtils的CopyProperties方法需要对应的属性有getter和setter方法；
         * 如果存在属性完全相同的内部类，但是不是同一个内部类，即分别属于各自的内部类，则spring会认为属性不同，不会copy；
         * spring和apache的copy属性的方法源和目的参数的位置正好相反，所以导包和调用的时候都要注意一下。
         */
        wxUserDao.insert(user);
        /**
         * 存入cw_user
         */
        CwUser cwUser = new CwUser(user.getId().intValue(),user.getNickname(),null,user.getPortrait(),null,null,null,null,"会员");
        cwUserDao.insert(cwUser);
        userDto.setId(user.getId());
        return this.login(userDto);
    }

    @Override
    public Result getSessionId(String code) {
        /**
         * 1. 拼接url，微信登录凭证校验接口
         * 2. 发起http调用，获取微信的返回结构
         * 3. 存到redis
         * 4. 生成sessionId返回给前端，作为用户登录的标识
         * 5. 生成sessionId，当用户点击登录时，可以标识用户身份
         */

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+
                appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String res = HttpUtil.get(url);
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(RedisKey.WX_SESSION_ID + uuid,res,30, TimeUnit.MINUTES);
        Map<String,String> map = new HashMap();
        map.put("sessionId",uuid);
        return Result.success("session获取成功",map);
    }

    @Override
    public Result authLogin(WxAuth wxAuth) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        /**
         * 1. 通过wxAuth中的值,对它进行解密
         * 2. 解密完成后，会获取到微信用户信息，其中包含openId 、性别、 昵称、 头像等信息
         * 3. openId 是唯一的，需要去user表中查询openId是否存在（存在则登录成功，不存在则先注册）
         * 4. 使用jwt技术，生成token 提供给前端 token令牌，用户下次将携带token访问
         * 5. 后端通过对token的验证，知道是 哪个用户是否处于登录状态
         */

        try {
            String wxRes = wxService.WxDecrypt(wxAuth.getEncryptedData(),wxAuth.getVi(),wxAuth.getSessionId());
            String json = stringRedisTemplate.opsForValue().get(RedisKey.WX_SESSION_ID + wxAuth.getSessionId());
            JSONObject jsonObject = JSON.parseObject(json);
            String openid = (String) jsonObject.get("openid");
            WxUserInfo wxUserInfo = JSON.parseObject(wxRes, WxUserInfo.class);
            wxUserInfo.setOpenid(openid);
            WxUser wxUser = wxUserDao.selectOne(Wrappers.<WxUser>lambdaQuery().eq(WxUser::getOpenid, openid));
            WxUserDto userDto = new WxUserDto();
            userDto.from(wxUserInfo);
            if (wxUser == null){
                // 注册
                return this.register(userDto);
            }else {
                userDto.setId(wxUser.getId());
                // 登录
                return this.login(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail("登录失败");
    }

    @Override
    public Result userinfo(boolean refresh) {
        WxUserDto wxUserDto = UserThreadLocal.get();
        if (refresh){
            String token = jwtUtil.sign(wxUserDto.getId());
            wxUserDto.setToken(token);
            stringRedisTemplate.opsForValue().set(RedisKey.TOKEN + token,JSON.toJSONString(wxUserDto),7,TimeUnit.DAYS);
        }
        return Result.success("获取用户信息",wxUserDto);
    }
}
