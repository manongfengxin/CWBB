package cwbb.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cwbb.POJO.doMain.CwManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CwManagerDao extends BaseMapper<CwManager> {

    @Select("SELECT * FROM cw_manager WHERE username=#{username} AND password=#{password}")
    CwManager login(@Param("username") String username,@Param("password") String password);

}
