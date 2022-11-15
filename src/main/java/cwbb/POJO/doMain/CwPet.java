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
@TableName("cw_pet")
public class CwPet {


    // 宠物编号
    @TableId(type = IdType.AUTO)
    private int pid;

    // 品种
    private int variety;

    // 宠物种类名
    private String petname;

    // 图片
    private String picture;

    // 简介
    private String introduce;

    // 形态特征
    private String feature;

    // 饲养方法
    private String nurture;
}
