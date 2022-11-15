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
@TableName("cw_supply")
public class CwSupply {


    // 宠物用品编号
    @TableId(type = IdType.AUTO)
    private int suid;

    // 类别
    private int category;

    // 名称
    private String supplyname;

    // 基本信息
    private String information;

    // 用途或使用说明
    private String illustrate;

    // 推荐价格
    private float price;

    // 用品图片
    private String picture;
}
