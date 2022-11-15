package cwbb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwbb.POJO.Dto.CwCommunityDto;
import cwbb.POJO.doMain.CwCommunity;
import cwbb.POJO.doMain.CwSupply;
import cwbb.POJO.doMain.UserAndCommunity;
import cwbb.dao.*;
import cwbb.service.CwCommunityService;
import cwbb.service.CwPersonalService;
import cwbb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CwPersonalServiceImpl extends ServiceImpl<UserAndCommunityDao, UserAndCommunity> implements CwPersonalService {


    @Autowired
    private UserAndCommunityDao userAndCommunityDao;

    @Autowired
    private CwCommunityDao communityDao;

    @Autowired
    private CommunityPictureDao communityPictureDao;

    @Autowired
    private CollectionCommunityDao collectionCommunityDao;

    @Autowired
    private CollectionSupplyDao collectionSupplyDao;

    @Autowired
    private CwSupplyDao supplyDao;

    /**
     * 获取 我的帖子
     * @param uid
     * @return
     */
    @Override
    public Result findPersonalCommunity(int uid) {
        /**
         * 获取用户所有发布的帖子的cyid
         * 遍历获取每个帖子
         */
        List<String> cyidList = userAndCommunityDao.findPersonalCommunity(uid);

        List<CwCommunityDto> cwCommunityDtoList = new ArrayList<>();
        for (String cyid : cyidList) {
            CwCommunity cwCommunity = communityDao.selectById(cyid);
            List<String> pictureList = communityPictureDao.findByCyid(cyid);

            /**
             * 装载返回值
             */
            CwCommunityDto cwCommunityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);

            cwCommunityDtoList.add(cwCommunityDto);
        }

        return Result.success("获取成功",cwCommunityDtoList);
    }



    /**
     * 获取 我收藏的帖子
     * @param uid
     * @return
     */
    @Override
    public Result findPersonalCollectionCommunity(int uid) {
        /**
         * 获取帖子id列表
         * 获取相应帖子信息
         */
        List<String> cyidList = collectionCommunityDao.findAll(uid);

        List<CwCommunityDto> cwCommunityDtoList = new ArrayList<>();
        for (String cyid : cyidList) {
            CwCommunity cwCommunity = communityDao.selectById(cyid);
            List<String> pictureList = communityPictureDao.findByCyid(cyid);
            CwCommunityDto cwCommunityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);
            cwCommunityDtoList.add(cwCommunityDto);
        }
        return Result.success("获取成功",cwCommunityDtoList);
    }


    /**
     * 获取点赞量
     * @param uid
     * @return
     */
    @Override
    public Result findAllIncense(int uid) {
        List<String> cyidList = userAndCommunityDao.findPersonalCommunity(uid);

        int incenseNumber = 0;
        for (String cyid : cyidList) {
            incenseNumber = incenseNumber + communityDao.findAllIncense(cyid);
        }
        return Result.success("获取点赞量",incenseNumber);
    }


    /**
     * 获取 我收藏的宠物用品
     * @param uid
     * @return
     */
    @Override
    public Result findPersonalCollectionSupply(int uid) {
        List<Integer> suidList = collectionSupplyDao.findPersonalCollectionSupply(uid);
        List<CwSupply> supplyList = new ArrayList<>();
        for (Integer suid : suidList) {
            CwSupply supply = supplyDao.selectById(suid);
            supplyList.add(supply);
        }

        return Result.success("获取收藏的宠物用品",supplyList);
    }


    /**
     * 获取个人发布的总帖子量
     * @param uid
     * @return
     */
    @Override
    public Result personalCommunityTotal(int uid) {
        int communityNumber = userAndCommunityDao.CommunityTotal(uid);
        return Result.success("获取发帖数",communityNumber);
    }


    /**
     * 获取收藏帖子的总量
     * @param uid
     * @return
     */
    @Override
    public Result collectionCommunityTotal(int uid) {
        int collectionCommunityNumber = collectionCommunityDao.collectionCommunityTotal(uid);
        return Result.success("获取收藏帖子数",collectionCommunityNumber);
    }


    /**
     * 获取物品收藏总量
     * @param uid
     * @return
     */
    @Override
    public Result collectionSupplyTotal(int uid) {
        int collectionSupplyNumber = collectionSupplyDao.collectionSupplyTotal(uid);
        return Result.success("获取收藏物品数",collectionSupplyNumber);
    }
}
