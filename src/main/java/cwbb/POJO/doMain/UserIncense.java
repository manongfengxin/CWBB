package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("user_incense")
@AllArgsConstructor
@NoArgsConstructor
public class UserIncense {


    // 点赞用户id
    private int uid;

    // 被点赞帖子id
    private String cyid;
}
