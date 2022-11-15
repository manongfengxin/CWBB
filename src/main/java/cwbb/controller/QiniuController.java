package cwbb.controller;

import cwbb.utils.QiniuUtil;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件连接云存储上传下载
 */

@RestController
@RequestMapping("/qiniu")
@Slf4j
public class QiniuController {

    @Autowired
    private QiniuUtil qiniuUtils;

    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String url = qiniuUtils.upload(file.getInputStream(), file.getOriginalFilename());
        return Result.success(url,"文件上传成功");
    }

    @GetMapping("/download/{fileName}")
    public Result download(@PathVariable String fileName){
        String url = qiniuUtils.download(fileName);
        return Result.success(url,"文件下载成功");
    }
}
