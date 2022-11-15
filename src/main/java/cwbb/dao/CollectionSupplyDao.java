package cwbb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CollectionSupply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface CollectionSupplyDao extends BaseMapper<CollectionSupply> {

    /**
     * 获取 我收藏的宠物用品
     * @param uid
     * @return
     */
    @Select("SELECT suid FROM collection_supply WHERE uid = #{uid}")
    List<Integer> findPersonalCollectionSupply(@Param("uid") int uid);


    /**
     * 收藏 宠物用品
     * @param uid
     * @param suid
     */
    @Insert("INSERT INTO collection_supply VALUES(#{uid},#{suid})")
    void collectionSupply(@Param("uid") int uid,@Param("suid") int suid);


    /**
     * 获取物品收藏总量
     * @param uid
     * @return
     */
    @Select("SELECT COUNT(suid) FROM collection_supply WHERE uid = #{uid}")
    int collectionSupplyTotal(@Param("uid") int uid);
}
