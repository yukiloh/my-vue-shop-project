package com.myvueshopproject.login.controller;

import com.myvueshopproject.login.model.*;
import com.myvueshopproject.login.result.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/my-vue-shop-project")
public class MainController {

    //登陆
    @PostMapping("/login")
    //通过RequestBody来接收json类型的对象,完全忘记了
    public LoginResult login(@RequestBody LoginParam loginParam) {
        if (loginParam.getUsername().equals("goudan") && loginParam.getPassword().equals("111111")) {
            return new LoginResult(1, 0, "狗蛋", "110", "goudan@gd.com", "token", true);
        }
        else return new LoginResult(0, 0, "", "", "", "", false);
    }


    //菜单
    @GetMapping("/menu")
    public MenuResult menu() {

        ArrayList<MenuResult.Data> children1 = new ArrayList<>();
        children1.add(new MenuResult.Data(104,"用户列表","users",new ArrayList<>()));



        ArrayList<MenuResult.Data> children2 = new ArrayList<>();
        children2.add(new MenuResult.Data(204,"角色管理","roles",new ArrayList<>()));
        children2.add(new MenuResult.Data(205,"权限列表","perm",new ArrayList<>()));

        ArrayList<MenuResult.Data> children3 = new ArrayList<>();
        children3.add(new MenuResult.Data(304,"商品列表","goods",new ArrayList<>()));

        ArrayList<MenuResult.Data> children4 = new ArrayList<>();
        children4.add(new MenuResult.Data(404,"订单列表","orders",new ArrayList<>()));

        ArrayList<MenuResult.Data> children5 = new ArrayList<>();
        children5.add(new MenuResult.Data(504,"统计列表","stats",new ArrayList<>()));


        ArrayList<MenuResult.Data> parent = new ArrayList<>();
        parent.add(new MenuResult.Data(101,"用户管理",null,children1));
        parent.add(new MenuResult.Data(201,"权限管理",null,children2));
        parent.add(new MenuResult.Data(301,"商品管理",null,children3));
        parent.add(new MenuResult.Data(401,"订单管理",null,children4));
        parent.add(new MenuResult.Data(501,"数据统计",null,children5));

        return new MenuResult(parent);
    }


    //用户
    @GetMapping("/users")
    public UsersResult users(
            @RequestParam(name="query", required=false) String query,
            @RequestParam(name="currentPage", required=false) Integer currentPage,
            @RequestParam(name="pageSize", required=false) Integer pageSize
    ) {

        UsersResult.Users admin = new UsersResult.Users(777, "admin", "777", 1, "admin@admin.com", new Date(), true, "超级管理员");
        UsersResult.Users manager = new UsersResult.Users(666, "manager", "666", 2, "mng@mng.com", new Date(), true, "管理员");
        ArrayList<UsersResult.Users> list = new ArrayList<>();
        list.add(admin);
        list.add(manager);

        //手动实现分页...
        if (pageSize != null && currentPage != null) {
            if (pageSize == 1) {
                if (currentPage == 1) list.remove(manager);
                else if (currentPage == 2) list.remove(admin);
            }
        }
        return new UsersResult( new UsersResult.Data(list.size(), 1, list));
    }


    //添加用户,没数据库
    @PostMapping("/addUser")
    public AddUsersResult addUser(@RequestBody AddUserParam addUserParam) {

        AddUsersResult addUsersResult = new AddUsersResult(
                new AddUsersResult.Data(
                        11,
                        addUserParam.getUsername(),
                        addUserParam.getMobile(),
                        3,
                        "",
                        addUserParam.getMobile(),
                        new Date(),
                        null,
                        false,
                        false
                )
        );
        return addUsersResult;
    }


    //权限管理>权限列表
    @GetMapping("/perm")
    public PermResult permission() {

        PermResult.Perm userManager = new PermResult.Perm(101, "用户管理", "0", 0, null);
        PermResult.Perm permManager = new PermResult.Perm(201, "权限管理", "0", 0, null);
        PermResult.Perm goodsManager = new PermResult.Perm(301, "商品管理", "0", 0, null);
        PermResult.Perm orderManager = new PermResult.Perm(401, "订单管理", "0", 0, null);
        PermResult.Perm dataStats = new PermResult.Perm(501, "数据统计", "0", 0, null);

        PermResult.Perm userList = new PermResult.Perm(102, "用户列表", "1", 0, "user");
        PermResult.Perm permList = new PermResult.Perm(202, "角色管理", "1", 0, "perm");
        PermResult.Perm goodsList = new PermResult.Perm(203, "权限列表", "1", 0, "goods");
        PermResult.Perm orderList1 = new PermResult.Perm(302, "商品列表", "1", 0, "order");
        PermResult.Perm orderList2 = new PermResult.Perm(303, "分类参数", "1", 0, "order");
        PermResult.Perm orderList3 = new PermResult.Perm(304, "商品分类", "1", 0, "order");
        PermResult.Perm dataList = new PermResult.Perm(402, "订单列表", "1", 0, "data");
        PermResult.Perm statsList = new PermResult.Perm(502, "统计列表", "1", 0, "stats");

        PermResult.Perm p01 = new PermResult.Perm(111, "添加用户","2",0, "user");
        PermResult.Perm p02 = new PermResult.Perm(121, "修改用户","2",0, "user");
        PermResult.Perm p03 = new PermResult.Perm(131, "删除用户","2",0, "user");

        PermResult.Perm p04 = new PermResult.Perm(211, "添加角色","2",0, "roles");
        PermResult.Perm p05 = new PermResult.Perm(221, "修改角色","2",0, "roles");
        PermResult.Perm p06 = new PermResult.Perm(231, "删除角色","2",0, "roles");

        PermResult.Perm p07 = new PermResult.Perm(241, "查看权限","2",0, "roles");

        PermResult.Perm p08 = new PermResult.Perm(311, "添加商品", "2", 0, "goods");
        PermResult.Perm p09 = new PermResult.Perm(312, "修改商品", "2", 0, "goods");
        PermResult.Perm p10 = new PermResult.Perm(313, "删除商品", "2", 0, "goods");
        PermResult.Perm p11 = new PermResult.Perm(321, "更新商品图片", "2", 0, "goods");
        PermResult.Perm p12 = new PermResult.Perm(322, "更新商品属性", "2", 0, "goods");
        PermResult.Perm p13 = new PermResult.Perm(323, "更新商品状态", "2", 0, "goods");

        PermResult.Perm p14 = new PermResult.Perm(331, "添加分类", "2", 0, "categories");
        PermResult.Perm p15 = new PermResult.Perm(332, "修改分类", "2", 0, "categories");
        PermResult.Perm p16 = new PermResult.Perm(333, "删除分类", "2", 0, "categories");

        PermResult.Perm p17 = new PermResult.Perm(341, "修改三级分类", "2", 0, "categories");

        PermResult.Perm p18 = new PermResult.Perm(411, "添加订单", "2", 0, "orders");
        PermResult.Perm p19 = new PermResult.Perm(412, "修改订单", "2", 0, "orders");
        PermResult.Perm p20 = new PermResult.Perm(413, "查看订单", "2", 0, "orders");

        PermResult.Perm p21 = new PermResult.Perm(511, "查看数据", "2", 0, "reports");


        ArrayList<PermResult.Perm> perms = new ArrayList<>();
        perms.add(userManager);
        perms.add(permManager);
        perms.add(goodsManager);
        perms.add(orderManager);
        perms.add(dataStats);

        perms.add(userList);
        perms.add(permList);
        perms.add(goodsList);
        perms.add(orderList1);
        perms.add(orderList2);
        perms.add(orderList3);
        perms.add(dataList);
        perms.add(statsList);

        Collections.addAll(perms,p01,p02,p03,p04,p05,p06,p07,p08,p09,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21);

        return new PermResult(new PermResult.Data(perms));
    }


    //权限管理>角色列表
    @GetMapping("/roles")
    public RolesResult roles() {

        RolesResult.ThirdPerm thirdPerm1 = new RolesResult.ThirdPerm(111, "添加用户", "user");
        RolesResult.ThirdPerm thirdPerm2 = new RolesResult.ThirdPerm(121, "修改用户", "user");
        RolesResult.ThirdPerm thirdPerm3 = new RolesResult.ThirdPerm(131, "删除用户", "user");
        RolesResult.ThirdPerm thirdPerm4 = new RolesResult.ThirdPerm(311, "添加商品", "goods");
        RolesResult.ThirdPerm thirdPerm5 = new RolesResult.ThirdPerm(312, "修改商品", "goods");
        RolesResult.ThirdPerm thirdPerm6 = new RolesResult.ThirdPerm(313, "删除商品", "goods");

        ArrayList<RolesResult.ThirdPerm> thirdPerms1 = new ArrayList<>();
        thirdPerms1.add(thirdPerm1);
        thirdPerms1.add(thirdPerm2);
        thirdPerms1.add(thirdPerm3);

        ArrayList<RolesResult.ThirdPerm> thirdPerms2 = new ArrayList<>();
        thirdPerms2.add(thirdPerm4);
        thirdPerms2.add(thirdPerm5);
        thirdPerms2.add(thirdPerm6);

        RolesResult.SecondPerm secondPerm1 = new RolesResult.SecondPerm(102,"用户列表","user",thirdPerms1);
        RolesResult.SecondPerm secondPerm2 = new RolesResult.SecondPerm(303,"商品列表","goods",thirdPerms2);

        ArrayList<RolesResult.SecondPerm> secondPerms1 = new ArrayList<>();
        ArrayList<RolesResult.SecondPerm> secondPerms2 = new ArrayList<>();
        secondPerms1.add(secondPerm1);
        secondPerms2.add(secondPerm2);

        RolesResult.FirstPerm firstPerm1 = new RolesResult.FirstPerm(101, "用户管理", "user",secondPerms1);
        RolesResult.FirstPerm firstPerm2 = new RolesResult.FirstPerm(301, "商品管理", "goods", secondPerms2);
        ArrayList<RolesResult.FirstPerm> firstPerms = new ArrayList<>();
        firstPerms.add(firstPerm1);
        firstPerms.add(firstPerm2);

        RolesResult.Role role1 = new RolesResult.Role(1, "管理员1号", null, "第1个管理员", firstPerms);
        RolesResult.Role role2 = new RolesResult.Role(2, "管理员2号", null, "第2个管理员", firstPerms);

        ArrayList<RolesResult.Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);

        RolesResult rolesResult = new RolesResult(roles);

        return rolesResult;
    }


    //所有权限列表
    @GetMapping("/allPerm")
    public RolesResult allPerm() {

        //三级权限
        RolesResult.ThirdPerm thirdPerm1 = new RolesResult.ThirdPerm(111, "添加用户", "user");
        RolesResult.ThirdPerm thirdPerm2 = new RolesResult.ThirdPerm(121, "修改用户", "user");
        RolesResult.ThirdPerm thirdPerm3 = new RolesResult.ThirdPerm(131, "删除用户", "user");

        RolesResult.ThirdPerm thirdPerm4 = new RolesResult.ThirdPerm(211, "添加角色", "roles");
        RolesResult.ThirdPerm thirdPerm5 = new RolesResult.ThirdPerm(221, "修改角色", "roles");
        RolesResult.ThirdPerm thirdPerm6 = new RolesResult.ThirdPerm(231, "删除角色", "roles");

        RolesResult.ThirdPerm thirdPerm7 = new RolesResult.ThirdPerm(232, "查看权限", "perm");

        RolesResult.ThirdPerm thirdPerm8 = new RolesResult.ThirdPerm(311, "添加商品", "goods");
        RolesResult.ThirdPerm thirdPerm9 = new RolesResult.ThirdPerm(312, "修改商品", "goods");
        RolesResult.ThirdPerm thirdPerm10 = new RolesResult.ThirdPerm(313, "删除商品", "goods");
        RolesResult.ThirdPerm thirdPerm101 = new RolesResult.ThirdPerm(321, "更新商品图片", "goods");
        RolesResult.ThirdPerm thirdPerm102 = new RolesResult.ThirdPerm(322, "更新商品属性", "goods");
        RolesResult.ThirdPerm thirdPerm103 = new RolesResult.ThirdPerm(323, "更新商品状态", "goods");


        RolesResult.ThirdPerm thirdPerm11 = new RolesResult.ThirdPerm(331, "添加分类", "categories");
        RolesResult.ThirdPerm thirdPerm12 = new RolesResult.ThirdPerm(332, "修改分类", "categories");
        RolesResult.ThirdPerm thirdPerm13 = new RolesResult.ThirdPerm(333, "删除分类", "categories");

        RolesResult.ThirdPerm thirdPerm14 = new RolesResult.ThirdPerm(341, "修改三级分类", "categories");

        RolesResult.ThirdPerm thirdPerm15 = new RolesResult.ThirdPerm(411, "添加订单", "orders");
        RolesResult.ThirdPerm thirdPerm16 = new RolesResult.ThirdPerm(412, "修改订单", "orders");
        RolesResult.ThirdPerm thirdPerm17 = new RolesResult.ThirdPerm(413, "查看订单", "orders");

        RolesResult.ThirdPerm thirdPerm18 = new RolesResult.ThirdPerm(511, "查看数据", "reports");


        ArrayList<RolesResult.ThirdPerm> thirdPerms1 = new ArrayList<>();
        thirdPerms1.add(thirdPerm1);
        thirdPerms1.add(thirdPerm2);
        thirdPerms1.add(thirdPerm3);

        ArrayList<RolesResult.ThirdPerm> thirdPerms2 = new ArrayList<>();
        thirdPerms2.add(thirdPerm4);
        thirdPerms2.add(thirdPerm5);
        thirdPerms2.add(thirdPerm6);


        ArrayList<RolesResult.ThirdPerm> thirdPerms3 = new ArrayList<>();
        thirdPerms3.add(thirdPerm7);

        ArrayList<RolesResult.ThirdPerm> thirdPerms4 = new ArrayList<>();
        thirdPerms4.add(thirdPerm8);
        thirdPerms4.add(thirdPerm9);
        thirdPerms4.add(thirdPerm10);
        thirdPerms4.add(thirdPerm101);
        thirdPerms4.add(thirdPerm102);
        thirdPerms4.add(thirdPerm103);

        ArrayList<RolesResult.ThirdPerm> thirdPerms5 = new ArrayList<>();
        thirdPerms5.add(thirdPerm11);
        thirdPerms5.add(thirdPerm12);
        thirdPerms5.add(thirdPerm13);

        ArrayList<RolesResult.ThirdPerm> thirdPerms55 = new ArrayList<>();
        thirdPerms55.add(thirdPerm14);

        ArrayList<RolesResult.ThirdPerm> thirdPerms6 = new ArrayList<>();
        thirdPerms6.add(thirdPerm15);
        thirdPerms6.add(thirdPerm16);
        thirdPerms6.add(thirdPerm17);

        ArrayList<RolesResult.ThirdPerm> thirdPerms7 = new ArrayList<>();
        thirdPerms7.add(thirdPerm18);

        //二级
        RolesResult.SecondPerm secondPerm1 = new RolesResult.SecondPerm(102,"用户列表","user",thirdPerms1);

        RolesResult.SecondPerm secondPerm2 = new RolesResult.SecondPerm(202,"角色列表","roles",thirdPerms2);
        RolesResult.SecondPerm secondPerm3 = new RolesResult.SecondPerm(203,"权限列表","perm",thirdPerms3);

        RolesResult.SecondPerm secondPerm4 = new RolesResult.SecondPerm(302,"商品列表","goods",thirdPerms4);
        RolesResult.SecondPerm secondPerm5 = new RolesResult.SecondPerm(303,"分类参数","categories",thirdPerms5);
        RolesResult.SecondPerm secondPerm6 = new RolesResult.SecondPerm(304,"商品分类","categories",thirdPerms55);

        RolesResult.SecondPerm secondPerm7 = new RolesResult.SecondPerm(402,"订单列表","orders",thirdPerms6);

        RolesResult.SecondPerm secondPerm8 = new RolesResult.SecondPerm(502,"数据报表","orders",thirdPerms7);



        ArrayList<RolesResult.SecondPerm> secondPerms1 = new ArrayList<>();
        secondPerms1.add(secondPerm1);

        ArrayList<RolesResult.SecondPerm> secondPerms2 = new ArrayList<>();
        secondPerms2.add(secondPerm2);
        secondPerms2.add(secondPerm3);

        ArrayList<RolesResult.SecondPerm> secondPerms3 = new ArrayList<>();
        secondPerms3.add(secondPerm4);
        secondPerms3.add(secondPerm5);
        secondPerms3.add(secondPerm6);

        ArrayList<RolesResult.SecondPerm> secondPerms4 = new ArrayList<>();
        secondPerms4.add(secondPerm7);

        ArrayList<RolesResult.SecondPerm> secondPerms5 = new ArrayList<>();
        secondPerms5.add(secondPerm8);


        //一级
        RolesResult.FirstPerm firstPerm1 = new RolesResult.FirstPerm(101, "用户管理", "user",secondPerms1);
        RolesResult.FirstPerm firstPerm2 = new RolesResult.FirstPerm(201, "权限管理", "user",secondPerms2);
        RolesResult.FirstPerm firstPerm3 = new RolesResult.FirstPerm(301, "商品管理", "goods", secondPerms3);
        RolesResult.FirstPerm firstPerm4 = new RolesResult.FirstPerm(401, "订单管理", "goods", secondPerms4);
        RolesResult.FirstPerm firstPerm5 = new RolesResult.FirstPerm(501, "数据统计", "goods", secondPerms5);

        ArrayList<RolesResult.FirstPerm> firstPerms = new ArrayList<>();
        firstPerms.add(firstPerm1);
        firstPerms.add(firstPerm2);
        firstPerms.add(firstPerm3);
        firstPerms.add(firstPerm4);
        firstPerms.add(firstPerm5);


        //这里偷懒不想再定义新的result了
        RolesResult.Role role1 = new RolesResult.Role(1, "权限列表", null, "查看权限", firstPerms);

        ArrayList<RolesResult.Role> roles = new ArrayList<>();
        roles.add(role1);

        RolesResult rolesResult = new RolesResult(roles);

        return rolesResult;

    }
}
