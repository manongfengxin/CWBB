package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("collection_supply")
public class CollectionSupply {

    /**
     * 用户 id
     */
    private int uid;

    /**
     * 宠物用品 id
     */
    private int suid;
}
