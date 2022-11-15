package cwbb.controller;


import cwbb.POJO.Dto.WxAuth;
import cwbb.service.CwUserService;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/CwUserController")
@Slf4j
public class CwUserController {


    @Autowired
    private CwUserService userService;


    @GetMapping("/getSessionId")
    public Result getSessionId(@RequestParam String code){
        return userService.getSessionId(code);
    }

    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WxAuth wxAuth) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Result result = userService.authLogin(wxAuth);
        return result;
    }

    @GetMapping("/userinfo")
    public Result userinfo(boolean refresh){
        return userService.userinfo(refresh);
    }
}
