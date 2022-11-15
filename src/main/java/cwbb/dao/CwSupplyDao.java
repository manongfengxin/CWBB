package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CwSupply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CwSupplyDao extends BaseMapper<CwSupply> {

    @Select("SELECT * FROM cw_supply WHERE category=#{susortid}")
    List<CwSupply> findSupplyBySort(@Param("susortid") int susortid);


    @Select("SELECT * FROM cw_supply WHERE supplyname LIKE CONCAT('%',#{supplyName},'%')")
    List<CwSupply> findSupplyByName(@Param("supplyName") String supplyName);
}
