package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("simulate_pet_picture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulatePetPicture {


    // 虚拟宠物编号
    private String spid;

    /**
     * 虚拟宠物图片
     * 每个虚拟宠物在数据库中提前存放好多张图片
     * 宠物的每个状态用不同的图片来表现
     */
    private String picture;
}
