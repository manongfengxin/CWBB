package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_and_community")
public class UserAndCommunity {

    private int uid;

    private String cyid;
}
