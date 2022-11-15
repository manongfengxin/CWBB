package cwbb.POJO.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CwCommunityDto {

    // 帖子id
    private String cyid;

    // 文字内容
    private String content;

    // 发帖人昵称
    private String nickname;

    // 发帖人头像
    private String headshot;

    // 发帖人所在地
    private String place;

    // 点赞数
    private int incense;

    // 收藏数
    private int collection;

    // 转发数
    private int repost;

    // 发帖时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    // 标题
    private String title;

    // 文件名
    private List<String> fileList;
}
