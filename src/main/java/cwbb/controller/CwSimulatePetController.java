package cwbb.controller;


import cwbb.POJO.Dto.AddSimulatePetDto;
import cwbb.POJO.doMain.CwSimulatePet;
import cwbb.service.CwSimulatePetService;
import cwbb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CwSimulatePet")
public class CwSimulatePetController {


    @Autowired
    private CwSimulatePetService cwSimulatePetService;


    /**
     * 用户添加新的虚拟宠物
     * @param addSimulatePetDto
     * @return
     */
    @PutMapping("/addNewSimulatePet")
    public Result addNewSimulatePet(@RequestBody AddSimulatePetDto addSimulatePetDto){
        return cwSimulatePetService.addNewSimulatePet(addSimulatePetDto);
    }


    /**
     * 根据编号获取虚拟宠物信息
     * @param spid
     * @return
     */
    @GetMapping("/findSimulateBySpid")
    public Result findSimulateBySpid(@RequestParam("spid") String spid){
        return Result.success("获取虚拟宠物信息",cwSimulatePetService.getById(spid));
    }
}
