package cwbb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.UserIncense;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

@Mapper
public interface UserIncenseDao extends BaseMapper<UserIncense> {

    /**
     * 查询该用户是否点赞
     * @param uid
     * @param cyid
     * @return
     */
    @Select("SELECT * FROM user_incense WHERE uid = #{uid} AND cyid = #{cyid}")
    UserIncense whetherIncense(@Param("uid") int uid,@Param("cyid") String cyid);


    /**
     * 删除点赞数据
     * @param uid
     * @param cyid
     */
    @Delete("DELETE FROM user_Incense WHERE uid = #{uid} AND cyid = #{cyid}")
    void cancelIncense(@Param("uid") int uid,@Param("cyid") String cyid);


    /**
     * 新增点赞
     * @param uid
     * @param cyid
     */
    @Insert("INSERT INTO user_Incense VALUES(#{uid},#{cyid})")
    void addIncense(@Param("uid") int uid,@Param("cyid") String cyid);
}
