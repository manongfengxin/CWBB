package cwbb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwbb.POJO.Dto.WxUserDto;
import cwbb.POJO.Dto.WxAuth;
import cwbb.POJO.doMain.CwUser;
import cwbb.utils.Result;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface CwUserService extends IService<CwUser> {



    /**
     * 登录
     * @param userDto
     * @return
     */
    Result login(WxUserDto userDto);


    /**
     * 注册
     * @param userDto
     * @return
     */
    Result register(WxUserDto userDto);

    /**
     * 获取Session
     * @param code
     * @return
     */
    Result getSessionId(String code);


    Result authLogin(WxAuth wxAuth) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException;


    /**
     * 获取用户信息
     * @param refresh
     * @return
     */
    Result userinfo(boolean refresh);
}
