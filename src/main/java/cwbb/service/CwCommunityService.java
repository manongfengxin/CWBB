package cwbb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwbb.POJO.Dto.AddCommunityDto;
import cwbb.POJO.Dto.CwCommunityDto;
import cwbb.POJO.Dto.ModifyCommunityDto;
import cwbb.POJO.Dto.UserIncenseDto;
import cwbb.POJO.doMain.CollectionCommunity;
import cwbb.POJO.doMain.CwCommunity;
import cwbb.utils.Result;

public interface CwCommunityService extends IService<CwCommunity> {

    /**
     * 获取所有帖子
     * @return
     */
    Result findAllCommunity();


    /**
     * 发帖
     * @param addCommunityDto
     * @return
     */
    Result addNewCommunity(AddCommunityDto addCommunityDto);


    /**
     * 修改帖子内容
     * @param modifyCommunityDto
     * @return
     */
    Result modifyCommunity(ModifyCommunityDto modifyCommunityDto);


    /**
     * 根据 cyid获取帖子
     * @param cyid
     * @return
     */
    Result findCommunityByCyid(String cyid);


    /**
     * 通过标题关键字搜索相关帖子
     * @param keyword
     * @return
     */
    Result findCommunityByTitle(String keyword);


    /**
     * 删除帖子
     * @param cyid
     * @return
     */
    Result deleteCommunity(String cyid);


    /**
     * 收藏帖子
     * @param collectionCommunity
     * @return
     */
    Result collectionCommunity(CollectionCommunity collectionCommunity);


    /**
     * 给帖子点赞
     * @param userIncenseDto
     * @return
     */
    Result addIncense(UserIncenseDto userIncenseDto);


    /**
     * 帖子转发
     * @param cyid
     * @return
     */
    Result addRepost(String cyid);


    /**
     * 获取所有左边列表的帖子
     * @return
     */
    Result findAllLeftCommunity();


    /**
     * 获取右边列表帖子
     * @return
     */
    Result findAllRightCommunity();
}
