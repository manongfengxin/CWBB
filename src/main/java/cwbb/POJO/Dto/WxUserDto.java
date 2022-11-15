package cwbb.POJO.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class WxUserDto implements Serializable {

    private Long id;

    private String nickname;

    private String username;

    private String password;

    private String gender;

    private String phoneNumber;

    // 头像
    private String portrait;

    // 背景图片
    private String background;

    private String openid;

    // 联合id
    private String wxUnionId;

    // dto拓展属性
    private String token;
    List<String> permission;
    List<String> roles;
    // 验证码
    private String code;

    public void from(WxUserInfo wxUserInfo){
        this.nickname = wxUserInfo.getNickName();
        this.portrait = wxUserInfo.getAvatarUrl();
        this.username = "";
        this.password = "";
        this.phoneNumber = "";
        this.gender = wxUserInfo.getGender();
        this.openid = wxUserInfo.getOpenid();
        this.wxUnionId = wxUserInfo.getUnionId();

    }
}
