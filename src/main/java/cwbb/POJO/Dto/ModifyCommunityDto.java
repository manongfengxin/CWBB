package cwbb.POJO.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyCommunityDto {

    private String cyid;


    /**
     * 修改后的 内容 标题 文件
     */
    private String content;

    private String title;

    private List<String> fileList;
}
