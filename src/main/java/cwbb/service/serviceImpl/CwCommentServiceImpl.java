package cwbb.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwbb.POJO.Dto.AddCommentDto;
import cwbb.POJO.doMain.CwComment;
import cwbb.POJO.doMain.CwUser;
import cwbb.dao.CwCommentDao;
import cwbb.dao.CwUserDao;
import cwbb.service.CwCommentService;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CwCommentServiceImpl extends ServiceImpl<CwCommentDao, CwComment> implements CwCommentService {


    @Autowired
    private CwCommentDao commentDao;

    @Autowired
    private CwUserDao userDao;


    /**
     * 获取该贴所有评论
     * @param cyid
     * @return
     */
    @Override
    public Result findAllComment(String cyid) {
        List<CwComment> cwCommentList = commentDao.findAllComment(cyid);
        return Result.success("获取评论",cwCommentList);
    }


    /**
     * 新增评论
     * @param addCommentDto
     * @return
     */
    @Override
    public Result addNewComment(AddCommentDto addCommentDto) {
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

        /**
         * 创建帖子的 ctid
         */
        int uid = addCommentDto.getUid();
        String ctid = uid + dateString;

        /**
         * 获取发帖人信息
         */
        CwUser cwUser = userDao.selectById(addCommentDto.getUid());

        /**
         * 填充数据库和返回值
         */
        CwComment cwComment = new CwComment(
                ctid,
                addCommentDto.getCyid(),
                addCommentDto.getUid(),
                cwUser.getNickname(),
                cwUser.getHeadshot(),
                cwUser.getPlace(),
                addCommentDto.getContent(),
                time
                );
        commentDao.addNewComment(
                ctid,
                cwComment.getCyid(),
                cwComment.getUid(),
                cwComment.getNickname(),
                cwComment.getHeadshot(),
                cwComment.getPlace(),
                cwComment.getContent(),
                time
        );
        return Result.success("发表评论成功",cwComment);
    }


    /**
     * 删除帖子
     * @param ctid
     * @return
     */
    @Override
    public Result deleteComment(String ctid) {
        commentDao.deleteComment(ctid);
        return Result.success("删除评论成功");
    }
}
