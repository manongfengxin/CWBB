package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CwPet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CwPetDao extends BaseMapper<CwPet> {

    @Select("SELECT * FROM cw_pet WHERE variety=#{psid}")
    List<CwPet> getPetBySort(@Param("psid") int psid);


    @Select("SELECT * FROM cw_pet WHERE petname LIKE CONCAT('%',#{petName},'%')")
    List<CwPet> findPetByName(@Param("petName") String petName);
}
