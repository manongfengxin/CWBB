package cwbb.utils;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class QiniuUtil {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Value("${qiniu.domainOfBucket}")
    private String domainOfBucket;

    @Autowired
    private UploadManager uploadManager;

    public String UploadToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }

    /**
     * 根据文件路径上传文件
     * @param filePath
     * @param fileName
     * @return
     */
    public String upload(String filePath,String fileName){
        String name = this.genName(fileName);
        try {
            Response response = uploadManager.put(filePath, name, this.UploadToken());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("文件上传成功==> key:{} <==> hash:{}",putRet.key,putRet.hash);
            return name;
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.error("文件上传失败==>{}",r.bodyString());
            } catch (QiniuException e2) {

            }
            return null;
        }
    }


    /**
     * 根据字节上传文件
     * @param bytes
     * @param fileName
     * @return
     */
    public String upload(byte[] bytes,String fileName){
        String name = this.genName(fileName);
        try {
            Response response = uploadManager.put(bytes, name, this.UploadToken());
            return name;
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.error("文件上传失败==>{}",r.bodyString());
            } catch (QiniuException e2) {

            }
            return null;
        }
    }




    /**
     * 根据流上传文件
     * @param inputStream
     * @param fileName
     * @return
     */
    public String upload(InputStream inputStream, String fileName){
        String name = this.genName(fileName);
        try {
            Response response = uploadManager.put(inputStream, name, this.UploadToken(),null,null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("文件上传成功==>{}",putRet);
            return name;
        } catch (QiniuException e) {
            Response r = e.response;
            try {
                log.error("文件上传失败==>{}",r.bodyString());
            } catch (QiniuException e2) {

            }
            return null;
        }
    }





    public String download(String fileName){
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {

        }
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        System.out.println(finalUrl);
        return finalUrl;
    }


    /**
     * 删除文件
     * @param fileName
     */
    public void delete(String fileName){
        // 构建一个指定Region对象的配置类
        Configuration configuration = new Configuration(Region.region2());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth,configuration);
        try {
            bucketManager.delete(bucket,fileName);
            log.info("删除文件成功");
        } catch (QiniuException e) {
            // 出现异常说明删除失败
            log.error("删除失败==>{}",e.code());
            log.error(e.response.toString());
        }
    }


    /**
     * 根据时间生成唯一文件名
     * @param fileName
     * @return
     */
    public String genName(String fileName){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date()) + fileName;
    }

}
