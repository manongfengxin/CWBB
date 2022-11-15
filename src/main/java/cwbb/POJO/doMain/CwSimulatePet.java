package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("cw_simulate_pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CwSimulatePet {


    // 虚拟宠物编号(用户uid+当前时间time)
    @TableId(type = IdType.INPUT)
    private String spid;

    // 虚拟宠物名
    private String petname;

    // 虚拟宠物品种
    private int petsort;

    // 宠物年龄
    private float petage;

    // 饥饿值
    private int hunger;

    // 饥渴值
    private int thirsty;

    // 健康值
    private int health;

    // 状态
    private String state;
}
