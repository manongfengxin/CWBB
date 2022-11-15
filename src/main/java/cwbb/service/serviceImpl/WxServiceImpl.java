package cwbb.service.serviceImpl;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cwbb.service.WxService;
import cwbb.utils.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

@Service
@Slf4j
public class WxServiceImpl implements WxService {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String WxDecrypt(String encryptedData, String vi, String sessionId) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        encryptedData.replaceAll(" ","+");
        vi.replaceAll(" ","+");
        sessionId.replaceAll(" ","+");
        // 开始解密
        String json = stringRedisTemplate.opsForValue().get(RedisKey.WX_SESSION_ID + sessionId);
        JSONObject jsonObject = JSON.parseObject(json);
        String sessionKey = (String) jsonObject.get("session_key");
        byte[] encData = Base64.decode(encryptedData);
        byte[] iv = Base64.decode(vi);
        byte[] key = Base64.decode(sessionKey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key,"AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
        return new String(cipher.doFinal(encData),"UTF-8");
    }
}
