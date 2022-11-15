package cwbb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cwbb.POJO.doMain.WxUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxUserDao extends BaseMapper<WxUser> {
}
