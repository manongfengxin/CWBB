package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("simulate_pet_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulatePetUser {


    // 虚拟宠物所有者的用户id
    private int uid;

    // 虚拟宠物id
    private String spid;
}
