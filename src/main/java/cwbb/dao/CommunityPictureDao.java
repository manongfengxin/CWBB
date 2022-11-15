package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CommunityPicture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityPictureDao extends BaseMapper<CommunityPicture> {


    /**
     * 获取帖子的图片
     * @param cyid
     * @return
     */
    @Select("SELECT picture FROM community_picture WHERE cyid = #{cyid}")
    List<String> findByCyid(@Param("cyid") String cyid);


    /**
     * 删除帖子图片
     * @param cyid
     */
    @Delete("DELETE FROM community_picture WHERE cyid = #{cyid}")
    void deleteCommunity(@Param("cyid") String cyid);
}
