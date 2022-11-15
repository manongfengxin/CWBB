package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CwComment;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CwCommentDao extends BaseMapper<CwComment> {

    /**
     * 删除帖子评论
     * @param cyid
     */
    @Delete("DELETE FROM cw_comment WHERE cyid = #{cyid}")
    void deleteCommunity(@Param("cyid") String cyid);


    /**
     * 获取某帖子的所有评论
     * @param cyid
     * @return
     */
    @Select("SELECT * FROM cw_comment WHERE cyid = #{cyid}")
    List<CwComment> findAllComment(@Param("cyid") String cyid);


    /**
     * 新增评论
     * @param ctid
     * @param cyid
     * @param uid
     * @param nickname
     * @param headshot
     * @param place
     * @param content
     * @param time
     */
    @Insert("INSERT INTO cw_comment VALUES(#{ctid},#{cyid},#{uid},#{nickname},#{headshot},#{place},#{content},#{time})")
    void addNewComment(@Param("ctid") String ctid,@Param("cyid") String cyid,@Param("uid") int uid,@Param("nickname") String nickname,@Param("headshot") String headshot,@Param("place") String place,@Param("content") String content,@Param("time") Timestamp time);


    /**
     * 删除帖子
     * @param ctid
     */
    @Delete("DELETE FROM cw_comment WHERE ctid = #{ctid}")
    void deleteComment(@Param("ctid") String ctid);
}
