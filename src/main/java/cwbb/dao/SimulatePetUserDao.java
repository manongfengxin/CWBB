package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.SimulatePetUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SimulatePetUserDao extends BaseMapper<SimulatePetUser> {

    @Insert("INSERT INTO simulate_pet_user VALUES(#{uid},#{spid})")
    void addNewSimulate(@Param("uid") int uid, @Param("spid") String spid);
}
