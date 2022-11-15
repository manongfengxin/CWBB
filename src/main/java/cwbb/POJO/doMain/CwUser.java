package cwbb.POJO.doMain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cw_user")
public class CwUser {


    // 用户编号
    @TableId(type = IdType.INPUT)
    private int uid;

    // 昵称
    private String nickname;

    // 性别
    private String gender;

    // 头像
    private String headshot;

    // 真实姓名
    private String realname;

    // 手机号
    private String phonenumber;

    // 电子邮箱
    private String email;

    // 用户所在地
    private String place;

    // 身份
    private String identity;

}
