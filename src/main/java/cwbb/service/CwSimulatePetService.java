package cwbb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwbb.POJO.Dto.AddSimulatePetDto;
import cwbb.POJO.doMain.CwSimulatePet;
import cwbb.utils.Result;

public interface CwSimulatePetService extends IService<CwSimulatePet> {


    /**
     * 用户添加新的虚拟宠物
     * @param addSimulatePetDto
     * @return
     */
    Result addNewSimulatePet(AddSimulatePetDto addSimulatePetDto);
}
