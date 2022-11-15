package cwbb.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.CwUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CwUserDao extends BaseMapper<CwUser> {
}
