package com.myvueshopproject.login.controller;

import com.myvueshopproject.login.model.*;
import com.myvueshopproject.login.result.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        PermResult.Perm goodsList = new PermResult.Perm(302, "权限列表", "1", 0, "goods");
        PermResult.Perm orderList = new PermResult.Perm(303, "商品列表", "1", 0, "order");
        PermResult.Perm dataList = new PermResult.Perm(402, "订单列表", "1", 0, "data");
        PermResult.Perm statsList = new PermResult.Perm(502, "统计列表", "1", 0, "stats");

        PermResult.Perm addUser = new PermResult.Perm(111, "添加用户", "2", 0, "user");
        PermResult.Perm editUser = new PermResult.Perm(121, "修改用户", "2", 0, "user");
        PermResult.Perm delUser = new PermResult.Perm(311, "删除商品", "2", 0, "user");
        PermResult.Perm addGoods = new PermResult.Perm(312, "添加商品", "2", 0, "goods");
        PermResult.Perm editGoods = new PermResult.Perm(313, "修改商品", "2", 0, "goods");
        PermResult.Perm delGoods = new PermResult.Perm(314, "删除商品", "2", 0, "goods");

        ArrayList<PermResult.Perm> perms = new ArrayList<>();
        perms.add(userManager);
        perms.add(permManager);
        perms.add(goodsManager);
        perms.add(orderManager);
        perms.add(dataStats);

        perms.add(userList);
        perms.add(permList);
        perms.add(goodsList);
        perms.add(orderList);
        perms.add(dataList);
        perms.add(statsList);

        perms.add(addUser);
        perms.add(editUser);
        perms.add(delUser);
        perms.add(addGoods);
        perms.add(editGoods);
        perms.add(delGoods);

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
        secondPerms1.add(secondPerm1);
        secondPerms1.add(secondPerm2);

        RolesResult.FirstPerm firstPerm1 = new RolesResult.FirstPerm(101, "用户管理", "user",secondPerms1);
        RolesResult.FirstPerm firstPerm2 = new RolesResult.FirstPerm(301, "商品管理", "goods", secondPerms1);
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
}
