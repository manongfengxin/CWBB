package cwbb.controller;


import cwbb.POJO.doMain.*;
import cwbb.dao.*;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 搜索、发现 页面接口
 */
@RestController
@RequestMapping("/CwSearch")
@Slf4j
public class CwSearchController {

    @Autowired
    private CwPetDao petDao;

    @Autowired
    private CwSupplyDao supplyDao;

    @Autowired
    private CollectionSupplyDao collectionSupplyDao;

    @Autowired
    private CwPetSortDao petSortDao;

    @Autowired
    private CwSupplySortDao supplySortDao;


    /**
     * 通过宠物名称搜索
     * @param petName
     * @return
     */
    @GetMapping("/findPetByName")
    public Result findPetByName(@RequestParam("petName")String petName){
        List<CwPet> petList = petDao.findPetByName(petName);
        if (petList == null){
            return Result.fail("未查询到相关结果");
        }else {
            return Result.success("查询成功",petList);
        }
    }


    /**
     * 通过宠物用品名搜索
     * @param supplyName
     * @return
     */
    @GetMapping("/findSupplyByName")
    public Result findSupplyByName(@RequestParam("supplyName")String supplyName){
        List<CwSupply> supplyList = supplyDao.findSupplyByName(supplyName);
        if (supplyList == null){
            return Result.fail("未查询到相关结果");
        }else {
            return Result.success("查询成功",supplyList);
        }
    }


    /**
     * 收藏 宠物用品
     * @param collectionSupply
     * @return
     */
    @PutMapping("/collectionSupply")
    public Result collectionSupply(@RequestBody CollectionSupply collectionSupply){
        collectionSupplyDao.collectionSupply(collectionSupply.getUid(),collectionSupply.getSuid());
        return Result.success("收藏成功");
    }


    /**
     * 获得宠物分类信息
     * @return
     */
    @GetMapping("/getPetSort")
    public Result getPetSort(){
        List<CwPetSort> petSortList = petSortDao.selectList(null);
        return Result.success("获取宠物分类表单成功",petSortList);
    }


    /**
     * 获取宠物用品分类信息
     * @return
     */
    @GetMapping("/getSupplySort")
    public Result getSupplySort(){
        List<CwSupplySort> supplySortList = supplySortDao.selectList(null);
        return Result.success("获取宠物用品分类表单成功",supplySortList);
    }
}
