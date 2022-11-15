package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cw_comment")
public class CwComment {

    // 该评论id
    private String ctid;

    // 所属帖子id
    private String cyid;

    // 评论人id
    private int uid;

    // 评论人昵称
    private String nickname;

    // 评论人头像
    private String headshot;

    // 评论人所在地
    private String place;

    // 评论内容
    private String content;

    // 评论时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;
}
