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
@TableName("cw_community")
public class CwCommunity {

    // 帖子id
    @TableId(type = IdType.INPUT)
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
}
