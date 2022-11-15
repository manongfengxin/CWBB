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
@TableName("cw_shop")
public class CwShop {


    // 店家编号
    @TableId(type = IdType.AUTO)
    private int shid;

    // 店面名称
    private String shopname;

    // 头像
    private String picture;

    // 详细位置信息
    private String position;

    // 联系电话
    private String phonenumber;

    // 主营业务
    private String business;

    // 营业执照认证信息(图片)
    private String licence;
}
