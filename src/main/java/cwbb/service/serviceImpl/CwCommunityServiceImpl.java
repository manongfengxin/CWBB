package cwbb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwbb.POJO.Dto.AddCommunityDto;
import cwbb.POJO.Dto.CwCommunityDto;
import cwbb.POJO.Dto.ModifyCommunityDto;
import cwbb.POJO.Dto.UserIncenseDto;
import cwbb.POJO.doMain.*;
import cwbb.POJO.doMain.UserAndCommunity;
import cwbb.dao.*;
import cwbb.service.CwCommunityService;
import cwbb.service.CwPersonalService;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CwCommunityServiceImpl extends ServiceImpl<CwCommunityDao, CwCommunity> implements CwCommunityService {


    @Autowired
    private CwCommunityDao communityDao;

    @Autowired
    private CommunityPictureDao communityPictureDao;

    @Autowired
    private CwUserDao userDao;

    @Autowired
    private UserAndCommunityDao userAndCommunityDao;

    @Autowired
    private CwCommentDao commentDao;

    @Autowired
    private CollectionCommunityDao collectionCommunityDao;

    @Autowired
    private UserIncenseDao userIncenseDao;

    @Autowired
    private CwCommunityLeftDao communityLeftDao;

    @Autowired
    private CwCommunityRightDao communityRightDao;

    /**
     * 填充返回值 CwCommunityDto
     * @param cwCommunity
     * @param pictureList
     * @return
     */
    public static CwCommunityDto fillReturnValue(CwCommunity cwCommunity,List<String> pictureList){
        CwCommunityDto cwCommunityDto = new CwCommunityDto(
                cwCommunity.getCyid(),
                cwCommunity.getContent(),
                cwCommunity.getNickname(),
                cwCommunity.getHeadshot(),
                cwCommunity.getPlace(),
                cwCommunity.getIncense(),
                cwCommunity.getCollection(),
                cwCommunity.getRepost(),
                cwCommunity.getTime(),
                cwCommunity.getTitle(),
                pictureList
        );
        return cwCommunityDto;
    }


    /**
     * 获取所有帖子
     *
     * @return
     */
    @Override
    public Result findAllCommunity() {

        List<CwCommunity> communityList = communityDao.selectList(null);

        /**
         * 填充返回值
         */
        List<CwCommunityDto> communityDtoList = new ArrayList<>();

        for (CwCommunity cwCommunity : communityList) {
            CwCommunityDto communityDto = new CwCommunityDto();

            List<String> pictureList = communityPictureDao.findByCyid(cwCommunity.getCyid());
            if (pictureList != null) {
                communityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);
            } else {
                communityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,null);
            }
            communityDtoList.add(communityDto);
        }

        return Result.success("获取所有帖子", communityDtoList);
    }


    /**
     * 新增帖子
     * @param addCommunityDto
     * @return
     */
    @Override
    public Result addNewCommunity(AddCommunityDto addCommunityDto) {

        /**
         * 发帖先存入帖子总表
         * 查询左、右表帖子数量，当 右表总数 > 左表总数 时
         * 将帖子再存入左表，否则存入右表
         */


        /**
         * 获取当前时间设置为任务的创建时间
         * 将 用户uid + 当前时间time 组合成帖子cyid
         *
         * Date date = new Date(); 获得当前时间
         * Timestamp t = new Timestamp(date.getTime()); 将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
         *
         *
         * 将 Date 转为 String
         * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         * String dateString = formatter.format(currentTime);
         *
         *
         * 将 String转为 Date
         * String dateString = "2020-02-12 20:55:09";
         * DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         * Date date = null;
         * try {
         * 	date = df.parse(dateString);
         * } catch (ParseException e) {
         * 	e.printStackTrace();
         * }
         *
         */

        /**
         * 获取当前时间
         */
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());

        /**
         * 将 Date 转为 String
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);

        int uid = addCommunityDto.getUid();
        String cyid = uid + dateString;

        /**
         * 根据uid查询发帖用户信息
         */
        CwUser cwUser = userDao.selectById(uid);

        /**
         * 填入数据库
         */
        CwCommunity cwCommunity = new CwCommunity(
                cyid,
                addCommunityDto.getContent(),
                cwUser.getNickname(),
                cwUser.getHeadshot(),
                "地球",
                0,
                0,
                0,
                time,
                addCommunityDto.getTitle()
        );
        communityDao.insert(cwCommunity);

        UserAndCommunity userAndCommunity = new UserAndCommunity(uid, cyid);
        userAndCommunityDao.insert(userAndCommunity);

        /**
         * 填充返回信息
         */
        CwCommunityDto cwCommunityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,addCommunityDto.getFiles());
        /**
         * 确认是否有文件上传
         */
        if (addCommunityDto.getFiles() != null) {
            cwCommunityDto.setFileList(addCommunityDto.getFiles());
            for (String file : addCommunityDto.getFiles()) {
                CommunityPicture communityPicture = new CommunityPicture(cyid, file);
                communityPictureDao.insert(communityPicture);
            }
        }

        /**
         * 查询左右表总数，确认存入哪一列表
         */
        int leftCount = communityLeftDao.selectCount(null);
        int rightCount = communityRightDao.selectCount(null);
        if (rightCount > leftCount){
            CwCommunityLeft cwCommunityLeft = new CwCommunityLeft();
            BeanUtils.copyProperties(cwCommunity,cwCommunityLeft);
            communityLeftDao.insert(cwCommunityLeft);
        }else {
            CwCommunityRight cwCommunityRight = new CwCommunityRight();
            BeanUtils.copyProperties(cwCommunity,cwCommunityRight);
            communityRightDao.insert(cwCommunityRight);
        }


        return Result.success("发帖成功", cwCommunityDto);
    }


    /**
     * 修改帖子内容
     *
     * @param modifyCommunityDto
     * @return
     */
    @Override
    public Result modifyCommunity(ModifyCommunityDto modifyCommunityDto) {
        /**
         * 获取原帖内容
         */
        CwCommunity cwCommunity = communityDao.selectById(modifyCommunityDto.getCyid());

        /**
         * 修改原帖
         */
        cwCommunity.setContent(modifyCommunityDto.getContent());
        cwCommunity.setTitle(modifyCommunityDto.getTitle());

        List<String> pictureList = modifyCommunityDto.getFileList();

        /**
         * 更新数据库
         * 存入帖子总表
         * 确认该贴在左右哪列表，再存入
         */
        communityDao.updateById(cwCommunity);


        if (communityLeftDao.selectById(cwCommunity.getCyid()) == null){
            CwCommunityRight cwCommunityRight = new CwCommunityRight();
            BeanUtils.copyProperties(cwCommunity,cwCommunityRight);
            communityRightDao.updateById(cwCommunityRight);
        }else {
            CwCommunityLeft cwCommunityLeft = new CwCommunityLeft();
            BeanUtils.copyProperties(cwCommunity,cwCommunityLeft);
            communityLeftDao.updateById(cwCommunityLeft);
        }
        /**
         * 更新图片
         */
        communityPictureDao.deleteCommunity(modifyCommunityDto.getCyid());
        if (pictureList != null){
            for (String picture : pictureList) {
                CommunityPicture communityPicture = new CommunityPicture(modifyCommunityDto.getCyid(), picture);
                communityPictureDao.insert(communityPicture);
            }
        }
        /**
         * 填充返回值
         */
        CwCommunityDto cwCommunityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);

        return Result.success("成功修改帖子", cwCommunityDto);
    }


    /**
     * 根据cyid 获取帖子
     *
     * @param cyid
     * @return
     */
    @Override
    public Result findCommunityByCyid(String cyid) {
        CwCommunity cwCommunity = communityDao.selectById(cyid);

        if (cwCommunity != null) {
            List<String> pictureList = communityPictureDao.findByCyid(cyid);
            CwCommunityDto cwCommunityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);
            return Result.success("获取帖子", cwCommunityDto);
        } else {
            return Result.fail("请输入正确的cyid");
        }
    }


    /**
     * 通过标题关键字搜索相关帖子
     *
     * @param keyword
     * @return
     */
    @Override
    public Result findCommunityByTitle(String keyword) {
        List<CwCommunity> communityList = communityDao.findCommunityByTitle(keyword);

        if (communityList != null) {
            List<CwCommunityDto> communityDtoList = new ArrayList<>();

            for (CwCommunity cwCommunity : communityList) {
                List<String> pictureList = communityPictureDao.findByCyid(cwCommunity.getCyid());
                CwCommunityDto cwCommunityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);
                communityDtoList.add(cwCommunityDto);
            }
            return Result.success("获取到相关内容",communityDtoList);
        } else {
            return Result.fail("未查询到相关内容");
        }
    }


    /**
     * 删除帖子
     * @param cyid
     * @return
     */
    @Override
    public Result deleteCommunity(String cyid) {
        /**
         * 删除Community CommunityPicture UserAndCommunity Comment collectionCommunity 五个表的相关内容
         */
        communityDao.deleteCommunity(cyid);
        communityPictureDao.deleteCommunity(cyid);
        userAndCommunityDao.deleteCommunity(cyid);
        commentDao.deleteCommunity(cyid);
        collectionCommunityDao.deleteCommunity(cyid);

        if (communityLeftDao.selectById(cyid) == null){
            communityRightDao.deleteById(cyid);
        }else {
            communityLeftDao.deleteById(cyid);
        }

        return Result.success("成功删除帖子");
    }


    /**
     * 收藏帖子
     * @param collectionCommunity
     * @return
     */
    @Override
    public Result collectionCommunity(CollectionCommunity collectionCommunity) {
        /**
         * 收藏数增加
         * 添加帖子收藏数据库
         */
        communityDao.addCollection(collectionCommunity.getCyid());

        if (communityLeftDao.selectById(collectionCommunity.getCyid()) == null){
            communityRightDao.addCollection(collectionCommunity.getCyid());
        }else {
            communityLeftDao.addCollection(collectionCommunity.getCyid());
        }

        collectionCommunityDao.addCollection(collectionCommunity.getUid(),collectionCommunity.getCyid());
        return Result.success("收藏成功");
    }


    /**
     * 给帖子点赞
     * @param userIncenseDto
     * @return
     */
    @Override
    public Result addIncense(UserIncenseDto userIncenseDto) {
        /**
         * 查询该用户是否点赞过该帖子
         * 点赞过则将数据删除，取消点赞，返回 -1
         * 未点赞过则增加点赞数据，返回 1
         */
        if (userIncenseDao.whetherIncense(userIncenseDto.getUid(),userIncenseDto.getCyid()) != null){
            /**
             * 有数据
             */
            userIncenseDao.cancelIncense(userIncenseDto.getUid(),userIncenseDto.getCyid());
            communityDao.cancelIncense(userIncenseDto.getCyid());

            if (communityLeftDao.selectById(userIncenseDto.getCyid()) == null){
                communityRightDao.cancelIncense(userIncenseDto.getCyid());
            }else {
                communityLeftDao.cancelIncense(userIncenseDto.getCyid());
            }

            return Result.success("取消点赞",-1);
        }else if (userIncenseDao.whetherIncense(userIncenseDto.getUid(),userIncenseDto.getCyid()) == null){
            /**
             * 无数据
             */
            userIncenseDao.addIncense(userIncenseDto.getUid(),userIncenseDto.getCyid());
            communityDao.addIncense(userIncenseDto.getCyid());

            if (communityLeftDao.selectById(userIncenseDto.getCyid()) == null){
                communityRightDao.addIncense(userIncenseDto.getCyid());
            }else {
                communityLeftDao.addIncense(userIncenseDto.getCyid());
            }

            return Result.success("点赞成功",1);
        }
        return Result.fail("点赞操作异常");

    }


    /**
     * 帖子转发
     * @param cyid
     * @return
     */
    @Override
    public Result addRepost(String cyid) {
        communityDao.addRepost(cyid);

        if (communityLeftDao.selectById(cyid) == null){
            communityRightDao.addRepost(cyid);
        }else {
            communityLeftDao.addRepost(cyid);
        }
        return Result.success("转发成功");
    }


    /**
     * 获取左边列表帖子
     * @return
     */
    @Override
    public Result findAllLeftCommunity() {
        List<CwCommunityLeft> cwCommunityLeftList = communityLeftDao.selectList(null);

        List<CwCommunity> communityList = new ArrayList<>();

        for (CwCommunityLeft cwCommunityLeft : cwCommunityLeftList) {
            CwCommunity cwCommunity = new CwCommunity();
            BeanUtils.copyProperties(cwCommunityLeft,cwCommunity);
            communityList.add(cwCommunity);
        }
        /**
         * 填充返回值
         */
        List<CwCommunityDto> communityDtoList = new ArrayList<>();

        for (CwCommunity cwCommunity : communityList) {
            CwCommunityDto communityDto = new CwCommunityDto();

            List<String> pictureList = communityPictureDao.findByCyid(cwCommunity.getCyid());
            if (pictureList != null) {
                communityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);
            } else {
                communityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,null);
            }
            communityDtoList.add(communityDto);
        }

        return Result.success("获取所有帖子", communityDtoList);
    }

    /**
     * 获取右边列表帖子
     * @return
     */
    @Override
    public Result findAllRightCommunity() {
        List<CwCommunityRight> cwCommunityRightList = communityRightDao.selectList(null);

        List<CwCommunity> communityList = new ArrayList<>();

        for (CwCommunityRight cwCommunityRight : cwCommunityRightList) {
            CwCommunity cwCommunity = new CwCommunity();
            BeanUtils.copyProperties(cwCommunityRight,cwCommunity);
            communityList.add(cwCommunity);
        }
        /**
         * 填充返回值
         */
        List<CwCommunityDto> communityDtoList = new ArrayList<>();

        for (CwCommunity cwCommunity : communityList) {
            CwCommunityDto communityDto = new CwCommunityDto();

            List<String> pictureList = communityPictureDao.findByCyid(cwCommunity.getCyid());
            if (pictureList != null) {
                communityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,pictureList);
            } else {
                communityDto = CwCommunityServiceImpl.fillReturnValue(cwCommunity,null);
            }
            communityDtoList.add(communityDto);
        }

        return Result.success("获取所有帖子", communityDtoList);
    }

}
