package cwbb.POJO.doMain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("wx_user")
public class WxUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String username;

    private String password;

    private String gender;

    // 头像
    private String portrait;

    // 背景图片
    private String background;

    private String openid;

    // 联合id
    private String wxunionid;


}
