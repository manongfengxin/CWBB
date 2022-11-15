package cwbb.controller;


import cwbb.POJO.Dto.AddCommunityDto;
import cwbb.POJO.Dto.CwCommunityDto;
import cwbb.POJO.Dto.ModifyCommunityDto;
import cwbb.POJO.Dto.UserIncenseDto;
import cwbb.POJO.doMain.CollectionCommunity;
import cwbb.POJO.doMain.CommunityPicture;
import cwbb.POJO.doMain.CwCommunity;
import cwbb.dao.CollectionCommunityDao;
import cwbb.dao.CommunityPictureDao;
import cwbb.dao.CwCommunityDao;
import cwbb.service.CwCommunityService;
import cwbb.utils.Result;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/CwCommunity")
public class CwCommunityController {



    @Autowired
    private CwCommunityService communityService;



    /**
     * 获取所有帖子
     * @return
     */
    @GetMapping("/findAllCommunity")
    public Result findAllCommunity(){
        return communityService.findAllCommunity();
    }


    /**
     * 获取所有左边列表的帖子
     * @return
     */
    @GetMapping("/findAllLeftCommunity")
    public Result findAllLeftCommunity(){
        return communityService.findAllLeftCommunity();
    }


    /**
     * 获取所有右边列表的帖子
     * @return
     */
    @GetMapping("/findAllRightCommunity")
    public Result findAllRightCommunity(){
        return communityService.findAllRightCommunity();
    }


    /**
     * 发帖
     * @param addCommunityDto
     * @return
     */
    @PutMapping("/addNewCommunity")
    public Result addNewCommunity(@RequestBody AddCommunityDto addCommunityDto){
        return communityService.addNewCommunity(addCommunityDto);
    }


    /**
     * 修改帖子内容
     * @param modifyCommunityDto
     * @return
     */
    @PostMapping("/modifyCommunity")
    public Result modifyCommunity(@RequestBody ModifyCommunityDto modifyCommunityDto){
        return communityService.modifyCommunity(modifyCommunityDto);
    }


    /**
     * 根据 cyid 获取帖子内容
     * @param cyid
     * @return
     */
    @GetMapping("/findCommunityByCyid")
    public Result findCommunityByCyid(@Param("cyid") String cyid){
        return communityService.findCommunityByCyid(cyid);
    }


    /**
     * 通过标题关键字搜索相关帖子
     * @param keyword
     * @return
     */
    @GetMapping("/findCommunityByLike")
    public Result findCommunityByTitle(@Param("keyword") String keyword){
        return communityService.findCommunityByTitle(keyword);
    }


    /**
     * 删除帖子
     * @param cyid
     * @return
     */
    @DeleteMapping("/deleteCommunity/{cyid}")
    public Result deleteCommunity(@PathVariable("cyid") String cyid){
        return communityService.deleteCommunity(cyid);
    }


    /**
     * 收藏帖子
     * @param collectionCommunity
     * @return
     */
    @PutMapping("/collectionCommunity")
    public Result collectionCommunity(@RequestBody CollectionCommunity collectionCommunity){
        return communityService.collectionCommunity(collectionCommunity);
    }


    /**
     * 给帖子点赞
     * @param userIncenseDto
     * @return
     */
    @PostMapping("/addIncense")
    public Result addIncense(@RequestBody UserIncenseDto userIncenseDto){
        return communityService.addIncense(userIncenseDto);
    }


    /**
     * 帖子转发
     * @param cyid
     * @return
     */
    @PostMapping("/addRepost")
    public Result addRepost(@Param("cyid") String cyid){
        return communityService.addRepost(cyid);
    }
}
