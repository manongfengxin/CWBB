package cwbb;

import cwbb.POJO.doMain.*;
import cwbb.dao.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CwbbApplicationTests {


    @Autowired
    private CwSupplyDao supplyDao;

    @Autowired
    private CwPetDao petDao;

    @Autowired
    private CwShopDao shopDao;

    @Autowired
    private CwUserDao userDao;

    @Autowired
    private CwCommunityLeftDao communityLeftDao;

    @Test
    void contextLoads() {
        CwCommunity cwCommunity = new CwCommunity("12121", "21112", "asda", "sawda", "asda", 2, 2, 2, null, "sda");
        CwCommunityLeft cwCommunityLeft = new CwCommunityLeft();
        BeanUtils.copyProperties(cwCommunity, cwCommunityLeft);
        log.info("cwCommunity ==> {}", cwCommunity);
        log.info("cwCommunityLeft ==> {}", cwCommunityLeft);
    }


    @Test
    void test1() {
        int integer = communityLeftDao.selectCount(null);
        log.info("integer ==> {}", integer);
    }

    @Test
    void test2() {
        if (communityLeftDao.selectById("620221004130114") == null){
            log.info("无数据");
        }else {
            log.info("有数据");
        }
    }

}
