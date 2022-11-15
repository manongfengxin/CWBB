package cwbb.service.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cwbb.POJO.Dto.AddSimulatePetDto;
import cwbb.POJO.doMain.CwSimulatePet;
import cwbb.dao.CwSimulatePetDao;
import cwbb.service.CwSimulatePetService;
import cwbb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CwSimulatePetServiceImpl extends ServiceImpl<CwSimulatePetDao, CwSimulatePet> implements CwSimulatePetService {



    @Autowired
    private CwSimulatePetDao simulatePetDao;



    /**
     * 用户添加新的虚拟宠物
     * @param addSimulatePetDto
     * @return
     */
    @Override
    public Result addNewSimulatePet(AddSimulatePetDto addSimulatePetDto) {
        /**
         * 用当前时间+用户uid 拼接成虚拟宠物id
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

        int uid = addSimulatePetDto.getUid();
        String spid = uid + dateString;

        /**
         * 填充数据库和返回值
         */
        CwSimulatePet cwSimulatePet = new CwSimulatePet(spid,
                addSimulatePetDto.getPetname(),
                addSimulatePetDto.getPetsort(),
                addSimulatePetDto.getPetage(),
                100,
                100,
                100,
                "健康"
                );
        if (simulatePetDao.insert(cwSimulatePet) == 0){
            return Result.fail("添加失败");
        }else {
            return Result.success("添加成功",cwSimulatePet);
        }
    }
}
