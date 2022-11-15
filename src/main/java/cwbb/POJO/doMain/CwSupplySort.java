package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("cw_supplysort")
@AllArgsConstructor
@NoArgsConstructor
public class CwSupplySort {

    @TableId(type = IdType.AUTO)
    private int susortid;

    private String susoname;

    private String picture;
}
