package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("community_picture")
public class CommunityPicture {

    private String cyid;

    private String picture;
}
