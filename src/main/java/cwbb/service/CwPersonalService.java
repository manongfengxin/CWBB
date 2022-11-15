package cwbb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwbb.POJO.doMain.UserAndCommunity;
import cwbb.utils.Result;

public interface CwPersonalService extends IService<UserAndCommunity> {

    /**
     * 获取 我的帖子
     * @param uid
     * @return
     */
    Result findPersonalCommunity(int uid);


    /**
     * 获取 我收藏的帖子
     * @param uid
     * @return
     */
    Result findPersonalCollectionCommunity(int uid);


    /**
     * 获取点赞量
     * @param uid
     * @return
     */
    Result findAllIncense(int uid);


    /**
     * 获取 我收藏的宠物用品
     * @param uid
     * @return
     */
    Result findPersonalCollectionSupply(int uid);


    /**
     * 获取个人发布的总帖子量
     * @param uid
     * @return
     */
    Result personalCommunityTotal(int uid);


    /**
     * 获取收藏帖子的总量
     * @param uid
     * @return
     */
    Result collectionCommunityTotal(int uid);


    /**
     * 获取物品收藏总量
     * @param uid
     * @return
     */
    Result collectionSupplyTotal(int uid);
}
