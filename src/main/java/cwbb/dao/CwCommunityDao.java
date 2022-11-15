package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CwCommunity;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CwCommunityDao extends BaseMapper<CwCommunity> {


    /**
     * 根据标题关键字查询相关帖子
     * @param keyword
     * @return
     */
    @Select("SELECT * FROM cw_community WHERE title LIKE CONCAT('%',#{keyword},'%');")
    List<CwCommunity> findCommunityByTitle(@Param("keyword") String keyword);


    /**
     * 删除帖子
     * @param cyid
     */
    @Delete("DELETE FROM cw_community WHERE cyid = #{cyid}")
    void deleteCommunity(@Param("cyid") String cyid);


    /**
     * 帖子收藏
     * @param cyid
     */
    @Update("UPDATE cw_community SET collection = collection+1 WHERE cyid = #{cyid}")
    void addCollection(@Param("cyid") String cyid);


    /**
     * 给帖子点赞
     * @param cyid
     */
    @Update("UPDATE cw_community SET incense = incense+1 WHERE cyid = #{cyid}")
    void addIncense(@Param("cyid") String cyid);


    /**
     * 帖子转发
     * @param cyid
     */
    @Update("UPDATE cw_community SET repost = repost+1 WHERE cyid = #{cyid}")
    void addRepost(@Param("cyid") String cyid);


    /**
     * 获取点赞量
     * @param cyid
     * @return
     */
    @Select("SELECT incense FROM cw_community WHERE cyid = #{cyid}")
    int findAllIncense(@Param("cyid") String cyid);


    /**
     * 取消点赞
     * @param cyid
     */
    @Update("UPDATE cw_community SET incense = incense-1 WHERE cyid = #{cyid}")
    void cancelIncense(@Param("cyid") String cyid);


}
