package cwbb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cwbb.POJO.Dto.AddCommentDto;
import cwbb.POJO.doMain.CwComment;
import cwbb.utils.Result;


public interface CwCommentService extends IService<CwComment> {


    /**
     * 获取某帖子的所有评论
     * @param cyid
     * @return
     */
    Result findAllComment(String cyid);


    /**
     * 新增评论
     * @param addCommentDto
     * @return
     */
    Result addNewComment(AddCommentDto addCommentDto);


    /**
     * 删除评论
     * @param ctid
     * @return
     */
    Result deleteComment(String ctid);
}
