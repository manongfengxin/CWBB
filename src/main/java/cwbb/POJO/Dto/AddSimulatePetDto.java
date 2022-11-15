package cwbb.POJO.Dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSimulatePetDto {


    // 领养虚拟宠物的用户id
    private int uid;

    // 虚拟宠物名
    private String petname;

    // 虚拟宠物品种
    private int petsort;

    // 宠物年龄
    private float petage;

}
