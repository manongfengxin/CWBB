package cwbb.POJO.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentDto {

    private int uid;

    private String cyid;

    private String content;
}
