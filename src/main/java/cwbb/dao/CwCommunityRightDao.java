package cwbb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CwCommunityRight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CwCommunityRightDao extends BaseMapper<CwCommunityRight> {


    /**
     * 取消点赞
     * @param cyid
     */
    @Select("UPDATE cw_community_right SET incense = incense-1 WHERE cyid = #{cyid}")
    void cancelIncense(@Param("cyid") String cyid);


    /**
     * 给帖子点赞
     * @param cyid
     */
    @Update("UPDATE cw_community_right SET incense = incense+1 WHERE cyid = #{cyid}")
    void addIncense(@Param("cyid") String cyid);


    /**
     * 帖子转发
     * @param cyid
     */
    @Update("UPDATE cw_community_right SET repost = repost+1 WHERE cyid = #{cyid}")
    void addRepost(@Param("cyid") String cyid);


    /**
     * 帖子收藏
     * @param cyid
     */
    @Update("UPDATE cw_community_right SET collection = collection+1 WHERE cyid = #{cyid}")
    void addCollection(@Param("cyid") String cyid);
}
