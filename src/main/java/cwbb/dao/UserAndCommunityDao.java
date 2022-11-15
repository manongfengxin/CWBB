package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.UserAndCommunity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAndCommunityDao extends BaseMapper<UserAndCommunity> {


    /**
     * 删除帖子关联
     * @param cyid
     */
    @Delete("DELETE FROM user_and_community WHERE cyid = #{cyid}")
    void deleteCommunity(@Param("cyid") String cyid);


    /**
     * 获取个人的所有帖子
     * @param uid
     * @return
     */
    @Select("SELECT cyid FROM user_and_community WHERE uid = #{uid}")
    List<String> findPersonalCommunity(@Param("uid") int uid);


    /**
     * 获取个人发布的总帖子量
     * @param uid
     * @return
     */
    @Select("SELECT COUNT(cyid) FROM user_and_community WHERE uid = #{uid}")
    int CommunityTotal(@Param("uid") int uid);
}
