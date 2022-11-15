package cwbb.controller;


import cwbb.POJO.doMain.UserAndCommunity;
import cwbb.service.CwPersonalService;
import cwbb.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/CwPersonal")
public class CwPersonalController {


    @Resource
    private CwPersonalService personalService;


    /**
     * 获取 我的帖子
     * @param uid
     * @return
     */
    @GetMapping("/findPersonalCommunity")
    public Result findPersonalCommunity(@Param("uid") int uid){
        return personalService.findPersonalCommunity(uid);
    }


    /**
     * 获取点赞量
     * @param uid
     * @return
     */
    @GetMapping("/findAllIncense")
    public Result findAllIncense(@Param("uid") int uid){
        return personalService.findAllIncense(uid);
    }


    /**
     * 获取 我收藏的帖子
     * @param uid
     * @return
     */
    @GetMapping("/findPersonalCollectionCommunity")
    public Result findPersonalCollectionCommunity(@Param("uid") int uid){
        return personalService.findPersonalCollectionCommunity(uid);
    }


    /**
     * 获取 我收藏的宠物用品
     * @param uid
     * @return
     */
    @GetMapping("/findPersonalCollectionSupply")
    public Result findPersonalCollectionSupply(@Param("uid") int uid){
        return personalService.findPersonalCollectionSupply(uid);
    }


    /**
     * 获取个人发布的总帖子量
     * @param uid
     * @return
     */
    @GetMapping("/personalCommunityTotal")
    public Result personalCommunityTotal(@Param("uid") int uid){
        return personalService.personalCommunityTotal(uid);
    }


    /**
     * 获取收藏帖子的总量
     * @param uid
     * @return
     */
    @GetMapping("/collectionCommunityTotal")
    public Result collectionCommunityTotal(@Param("uid") int uid){
        return personalService.collectionCommunityTotal(uid);
    }


    /**
     * 获取物品收藏总量
     * @param uid
     * @return
     */
    @GetMapping("/collectionSupplyTotal")
    public Result collectionSupplyTotal(@Param("uid") int uid){
        return personalService.collectionSupplyTotal(uid);
    }
}
