package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("collection_community")
public class CollectionCommunity {

    /**
     * 用户 id
     */
    private int uid;

    /**
     * 帖子 id
     */
    private String cyid;
}
