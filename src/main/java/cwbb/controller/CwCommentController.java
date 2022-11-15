package cwbb.controller;


import cwbb.POJO.Dto.AddCommentDto;
import cwbb.dao.CwCommunityDao;
import cwbb.service.CwCommentService;
import cwbb.service.CwCommunityService;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CwComment")
@Slf4j
public class CwCommentController {

    @Autowired
    private CwCommentService cwCommentService;


    /**
     * 获取某帖子的所有评论
     * 一次修改
     * @param cyid
     * @return
     */
    @GetMapping("/findAllComment")
    public Result findAllComment(@Param("cyid") String cyid){
        return cwCommentService.findAllComment(cyid);
    }


    /**
     * 新增评论
     * @param addCommentDto
     * @return
     */
    @PutMapping("/addNewComment")
    public Result addNewComment(@RequestBody AddCommentDto addCommentDto){
        return cwCommentService.addNewComment(addCommentDto);
    }


    /**
     * 删除评论
     * @param ctid
     * @return
     */
    @DeleteMapping("/deleteComment/{ctid}")
    public Result deleteComment(@PathVariable("ctid") String ctid){
        return cwCommentService.deleteComment(ctid);
    }
}
