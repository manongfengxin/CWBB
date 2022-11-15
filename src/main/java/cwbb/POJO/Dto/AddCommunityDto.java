package cwbb.POJO.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommunityDto {

    private int uid;

    private String content;

    private String title;

    // 文件名
    private List<String> files;
}
