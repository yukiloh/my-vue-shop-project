package com.myvueshopproject.login.controller;

import com.myvueshopproject.login.model.LoginResult;
import com.myvueshopproject.login.model.LoginParam;
import com.myvueshopproject.login.model.MenuResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/my-vue-shop-project")
public class LoginController {

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
        children1.add(new MenuResult.Data(104,"用户列表",null,new ArrayList<>()));



        ArrayList<MenuResult.Data> children2 = new ArrayList<>();
        children2.add(new MenuResult.Data(204,"角色管理",null,new ArrayList<>()));
        children2.add(new MenuResult.Data(205,"权限管理管理",null,new ArrayList<>()));

        ArrayList<MenuResult.Data> children3 = new ArrayList<>();
        children3.add(new MenuResult.Data(304,"商品列表",null,new ArrayList<>()));

        ArrayList<MenuResult.Data> children4 = new ArrayList<>();
        children4.add(new MenuResult.Data(404,"订单列表",null,new ArrayList<>()));

        ArrayList<MenuResult.Data> children5 = new ArrayList<>();
        children5.add(new MenuResult.Data(504,"商品列表",null,new ArrayList<>()));


        ArrayList<MenuResult.Data> parent = new ArrayList<>();
        parent.add(new MenuResult.Data(101,"用户管理",null,children1));
        parent.add(new MenuResult.Data(201,"权限管理",null,children2));
        parent.add(new MenuResult.Data(301,"商品管理",null,children3));
        parent.add(new MenuResult.Data(401,"订单管理",null,children4));
        parent.add(new MenuResult.Data(501,"数据统计",null,children5));

        return new MenuResult(parent);
    }
}
