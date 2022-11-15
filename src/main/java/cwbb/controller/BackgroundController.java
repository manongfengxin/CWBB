package cwbb.controller;


import cwbb.POJO.Dto.CwPetSortDto;
import cwbb.POJO.Dto.CwSidebarDetail;
import cwbb.POJO.doMain.*;
import cwbb.dao.*;
import cwbb.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * web后台管理系统接口
 */

@RestController
@Slf4j
@RequestMapping("/Background")
public class BackgroundController {

    @Autowired
    private CwManagerDao managerDao;

    @Autowired
    private CwUserDao userDao;

    @Autowired
    private CwPetDao petDao;

    @Autowired
    private CwShopDao shopDao;

    @Autowired
    private CwSupplyDao supplyDao;

    @Autowired
    private CwSidebarDao sidebarDao;

    @Autowired
    private CwSupplySortDao supplySortDao;

    @Autowired
    private CwPetSortDao petSortDao;


    /**
     * 管理员登录后台管理
     *
     * @param manager
     * @return
     */
    @PostMapping("/managerLogin")
    public Result managerLogin(@RequestBody CwManager manager) {
        try {
            if (managerDao.login(manager.getUsername(), manager.getPassword()) == null) {
                return Result.fail("登录失败，请检查用户名密码是否正确");
            } else {
                return Result.success("登录成功，管理员" + manager.getUsername());
            }
        } catch (Exception e) {
            return Result.fail("登录异常");
        }
    }


    /**
     * 给前端返回下拉表单
     *
     * @return
     */
    @GetMapping("/getSidebar")
    public Result getSidebar() {
        List<CwSupplySort> supplySortList = supplySortDao.selectList(null);
        List<CwSidebar> sidebarsList = sidebarDao.selectList(null);
        List<CwSidebarDetail> sidebarDetailList = new ArrayList<CwSidebarDetail>();
        for (CwSidebar cwSidebar : sidebarsList) {
            CwSidebarDetail sidebarDetail = new CwSidebarDetail();
            sidebarDetail.setSideid(cwSidebar.getSideid());
            sidebarDetail.setSidebarname(cwSidebar.getSidebarname());
            if (cwSidebar.getSideid() == 4) {
                sidebarDetail.setDetail(supplySortList);
            }
            sidebarDetailList.add(sidebarDetail);
        }
        return Result.success("返回表单", sidebarDetailList);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @GetMapping("/findAllUser")
    public Result findAllUser() {
        List<CwUser> userList = userDao.selectList(null);
        return Result.success("获取所有用户信息", userList);
    }


    /**
     * 根据id查询用户信息
     *
     * @param uid
     * @return
     */
    @GetMapping("/findUserById")
    public Result findUserById(@Param("uid") int uid) {
        if (uid >= 0 && userDao.selectById(uid) != null) {
            CwUser cwUser = userDao.selectById(uid);
            return Result.success("成功获取用户信息", cwUser);
        } else {
            return Result.fail("请输入正确的的用户id");
        }
    }

    /**
     * 增加新用户
     */
    @PutMapping("/addNewUser")
    public Result addNewUser(@RequestBody CwUser user) {
        try {
            userDao.insert(user);
            return Result.success("添加成功", user);
        } catch (Exception e) {
            return Result.fail("添加失败，请检查提交数据是否合法");
        }
    }


    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/deleteUser/{uid}")
    public Result deleteUser(@PathVariable("uid") int uid) {
        try {
            if (userDao.deleteById(uid) > 0) {
                return Result.success("成功删除用户");
            } else {
                return Result.fail("删除用户失败");
            }

        } catch (Exception e) {
            return Result.fail("删除用户失败");
        }
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/modifyUser")
    public Result modifyUser(@RequestBody CwUser user) {
        try {
            if (userDao.updateById(user) > 0) {
                return Result.success("成功修改用户信息");
            } else {
                return Result.fail("修改用户信息失败");
            }

        } catch (Exception e) {
            return Result.fail("修改用户信息失败");
        }
    }


    /**
     * 查询所有宠物信息
     *
     * @return
     */
    @GetMapping("/findAllPet")
    public Result findAllPet() {
        List<CwPet> petList = petDao.selectList(null);
        return Result.success("获取所有用户信息", petList);
    }


    /**
     * 根据id查询宠物
     *
     * @param pid
     * @return
     */
    @GetMapping("/findPetById")
    public Result findPetById(@Param("pid") int pid) {
        if (pid >= 0 && petDao.selectById(pid) != null) {
            CwPet cwPet = petDao.selectById(pid);
            return Result.success("成功获取用户信息", cwPet);

        } else {
            return Result.fail("请输入正确的的id");
        }
    }

    /**
     * 获取宠物分类单
     * @return
     */
    @GetMapping("/petSort")
    public Result petSort(){
        List<CwPetSort> petSortList = petSortDao.selectList(null);
        List<CwPetSortDto> cwPetSortDtos = new ArrayList<CwPetSortDto>();
        for (CwPetSort cwPetSort : petSortList) {
            CwPetSortDto cwPetSortDto = new CwPetSortDto();
            cwPetSortDto.setPsid(cwPetSort.getPsid());
            cwPetSortDto.setPsname(cwPetSort.getPsname());
            cwPetSortDto.setMsg(null);
            cwPetSortDtos.add(cwPetSortDto);
        }
        return Result.success("成功获取分类信息",cwPetSortDtos);
    }


    /**
     * 获取一个品种的宠物
     * @param psid
     * @return
     */
    @GetMapping("/getPetSort")
    public Result getPetBySort(@Param("psid") int psid){
        List<CwPet> petList = petDao.getPetBySort(psid);
        return Result.success("成功获取宠物信息",petList);
    }


    /**
     * 增加新宠物
     */
    @PutMapping("/addNewPet")
    public Result addNewPet(@RequestBody CwPet pet) {
        try {
            petDao.insert(pet);
            return Result.success("添加成功", pet);
        } catch (Exception e) {
            return Result.fail("添加失败，请检查提交数据是否合法");
        }
    }


    /**
     * 删除宠物
     *
     * @param pid
     * @return
     */
    @DeleteMapping("/deletePet/{pid}")
    public Result deletePet(@PathVariable("pid") int pid) {
        try {
            if (petDao.deleteById(pid) > 0) {
                return Result.success("成功删除宠物");
            } else {
                return Result.fail("删除宠物失败");
            }
        } catch (Exception e) {
            return Result.fail("删除宠物失败");
        }
    }


    /**
     * 修改宠物信息
     *
     * @param pet
     * @return
     */
    @PostMapping("/modifyPet")
    public Result modifyPet(@RequestBody CwPet pet) {
        try {
            if (petDao.updateById(pet) > 0) {
                return Result.success("成功修改宠物信息");
            } else {
                return Result.fail("修改宠物信息失败");
            }
        } catch (Exception e) {
            return Result.fail("修改宠物信息失败");
        }
    }


    /**
     * 查询所有商户信息
     *
     * @return
     */
    @GetMapping("/findAllShop")
    public Result findAllShop() {
        List<CwShop> shopList = shopDao.selectList(null);
        return Result.success("获取所有用户信息", shopList);
    }


    /**
     * 根据id查询商户
     *
     * @param shid
     * @return
     */
    @GetMapping("/findShopById")
    public Result findShopById(@Param("shid") int shid) {
        if (shid >= 0 && shopDao.selectById(shid) != null) {
            CwShop cwShop = shopDao.selectById(shid);
            return Result.success("成功获取用户信息", cwShop);

        } else {
            return Result.fail("请输入正确的的id");
        }
    }

    /**
     * 增加新商户
     */
    @PutMapping("/addNewShop")
    public Result addNewShop(@RequestBody CwShop shop) {
        try {
            shopDao.insert(shop);
            return Result.success("添加成功", shop);
        } catch (Exception e) {
            return Result.fail("添加失败，请检查提交数据是否合法");
        }
    }


    /**
     * 删除商户
     *
     * @param shid
     * @return
     */
    @DeleteMapping("/deleteShop/{shid}")
    public Result deleteShop(@PathVariable("shid") int shid) {
        try {
            if (shopDao.deleteById(shid) > 0) {
                return Result.success("成功删除商户");
            } else {
                return Result.fail("删除商户失败");
            }
        } catch (Exception e) {
            return Result.fail("删除商户失败");
        }
    }


    /**
     * 修改商户信息
     *
     * @param shop
     * @return
     */
    @PostMapping("/modifyShop")
    public Result modifyShop(@RequestBody CwShop shop) {
        try {
            if (shopDao.updateById(shop) > 0) {
                return Result.success("成功修改商户信息");
            } else {
                return Result.fail("修改商户信息失败");
            }
        } catch (Exception e) {
            return Result.fail("修改商户信息失败");
        }
    }

    /**
     * 查询所有宠物用品信息
     *
     * @return
     */
    @GetMapping("/findAllSupply")
    public Result findAllSupply() {
        List<CwSupply> supplyList = supplyDao.selectList(null);
        return Result.success("获取所有用户信息", supplyList);
    }

    /**
     * 根据id查询宠物用品
     *
     * @param suid
     * @return
     */
    @GetMapping("/findSupplyById")
    public Result findSupplyById(@Param("suid") int suid) {
        if (suid >= 0 && supplyDao.selectById(suid) != null) {
            CwSupply cwSupply = supplyDao.selectById(suid);
            return Result.success("成功获取用户信息", cwSupply);
        } else {
            return Result.fail("请输入正确的的id");
        }
    }


    /**
     * 根据类别获取宠物用品
     * @param susortid
     * @return
     */
    @GetMapping("/findSupplyBySort")
    public Result findSupplyBySort(@Param("susortid") int susortid){
        List<CwSupply> supplyList = supplyDao.findSupplyBySort(susortid);
        return Result.success("获取宠物用品信息",supplyList);
    }


    /**
     * 增加新宠物用品
     */
    @PutMapping("/addNewSupply")
    public Result addNewSupply(@RequestBody CwSupply supply) {
        try {
            supplyDao.insert(supply);
            return Result.success("添加成功", supply);
        } catch (Exception e) {
            return Result.fail("添加失败，请检查提交数据是否合法");
        }
    }


    /**
     * 删除宠物用品
     *
     * @param suid
     * @return
     */
    @DeleteMapping("/deleteSupply/{suid}")
    public Result deleteSupply(@PathVariable("suid") int suid) {
        try {
            if (supplyDao.deleteById(suid) > 0) {
                return Result.success("成功删除宠物用品");
            } else {
                return Result.fail("删除宠物用品失败");
            }
        } catch (Exception e) {
            return Result.fail("删除宠物用品失败");
        }
    }


    /**
     * 修改宠物用品信息
     *
     * @param supply
     * @return
     */
    @PostMapping("/modifySupply")
    public Result modifyShop(@RequestBody CwSupply supply) {
        try {
            if (supplyDao.updateById(supply) > 0) {
                return Result.success("成功修改宠物用品信息");
            } else {
                return Result.fail("修改宠物用品信息失败");
            }
        } catch (Exception e) {
            return Result.fail("修改宠物用品信息失败");
        }
    }

}
