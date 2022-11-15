package cwbb.handler;



import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import cwbb.POJO.Dto.WxUserDto;
import cwbb.utils.JwtUtil;
import cwbb.utils.RedisKey;
import cwbb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandler implements HandlerInterceptor {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 方法执行之前进行拦截
        /**
         * 1. 判断请求是否是请求controller方法
         * 2. 拿到token并认证 redis认证 ，获得user信息
         * 3. 如果token通过就放行，不通过则返回未登录
         * 4. 得到了用户信息，放入ThreadLocal中
         */


        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String token = request.getHeader("Authorization");
        token = token.replace("Bearer","");
        boolean verify = jwtUtil.verify(token);
        if (!verify){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(Result.fail("未登录")));
            return false;
        }
        String userJson = stringRedisTemplate.opsForValue().get(RedisKey.TOKEN + token);
        if (StringUtils.isBlank(userJson)){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(Result.fail("未登录")));
            return false;
        }
        WxUserDto userDto = JSON.parseObject(userJson, WxUserDto.class);
        cwbb.handler.UserThreadLocal.put(userDto);
        return true;

    }
}
