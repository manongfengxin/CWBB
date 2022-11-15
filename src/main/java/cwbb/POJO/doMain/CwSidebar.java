package cwbb.POJO.doMain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@Data
@TableName("cw_sidebar")
@AllArgsConstructor
@NoArgsConstructor
public class CwSidebar {

    @TableId(type = IdType.AUTO)
    private int sideid;

    private String sidebarname;
}
