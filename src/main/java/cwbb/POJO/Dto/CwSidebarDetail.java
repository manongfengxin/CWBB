package cwbb.POJO.Dto;

import cwbb.POJO.doMain.CwSidebar;
import cwbb.POJO.doMain.CwSupplySort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CwSidebarDetail{

    private int sideid;

    private String sidebarname;

    /**
     * 给 宠物用品类 做下拉表单
     */
    private List<CwSupplySort> detail;
}
