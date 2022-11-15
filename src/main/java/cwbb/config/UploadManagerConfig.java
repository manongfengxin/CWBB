package cwbb.config;


import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadManagerConfig {

    // 七牛云上传配置文件
    @Bean
    public UploadManager uploadManager(){
        com.qiniu.storage.Configuration configuration = new com.qiniu.storage.Configuration(Region.region2());
        return new UploadManager(configuration);
    }
}
