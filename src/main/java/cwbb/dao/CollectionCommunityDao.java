package cwbb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CollectionCommunity;
import cwbb.POJO.doMain.CwCommunity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionCommunityDao extends BaseMapper<CollectionCommunity> {

    /**
     * 获取 我收藏的帖子
     * @param uid
     * @return
     */
    @Select("SELECT cyid FROM collection_community WHERE uid = #{uid}")
    List<String> findAll(@Param("uid") int uid);


    /**
     * 收藏帖子
     * @param uid
     * @param cyid
     */
    @Insert("INSERT INTO collection_community VALUES(#{uid},#{cyid})")
    void addCollection(@Param("uid") int uid,@Param("cyid") String cyid);


    /**
     * 获取收藏帖子的总量
     * @param uid
     * @return
     */
    @Select("SELECT COUNT(cyid) FROM collection_community WHERE uid = #{uid}")
    int collectionCommunityTotal(@Param("uid") int uid);


    /**
     * 删除帖子
     * @param cyid
     */
    @Delete("DELETE FROM collection_community WHERE cyid = #{cyid}")
    void deleteCommunity(@Param("cyid") String cyid);
}
