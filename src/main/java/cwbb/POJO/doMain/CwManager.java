package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("cw_manager")
@AllArgsConstructor
@NoArgsConstructor
public class CwManager {

    private String username;

    private String password;
}
