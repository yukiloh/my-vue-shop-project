package com.myvueshopproject.login.controller;

import com.myvueshopproject.login.model.*;
import com.myvueshopproject.login.result.AddUsersResult;
import com.myvueshopproject.login.result.LoginResult;
import com.myvueshopproject.login.result.MenuResult;
import com.myvueshopproject.login.result.UsersResult;
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
        children2.add(new MenuResult.Data(205,"权限管理管理","perm",new ArrayList<>()));

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


    //添加用户
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
}
