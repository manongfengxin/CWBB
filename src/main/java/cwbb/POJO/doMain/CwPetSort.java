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
@TableName("cw_petsort")
public class CwPetSort {


    @TableId(type = IdType.AUTO)
    private int psid;

    private String psname;

    private String picture;
}
