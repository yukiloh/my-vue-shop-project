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

    @PostMapping("/login")
    //通过RequestBody来接收json类型的对象,完全忘记了
    public LoginResult login(@RequestBody LoginParam loginParam) {
        if (loginParam.getUsername().equals("goudan") && loginParam.getPassword().equals("111111")) {
            return new LoginResult(1, 0, "狗蛋", "110", "goudan@gd.com", "token", true);
        }
        else return new LoginResult(0, 0, "", "", "", "", false);
    }

    @GetMapping("/menu")
    //通过RequestBody来接收json类型的对象,完全忘记了
    public MenuResult menu() {
        ArrayList<MenuResult.Data> children = new ArrayList<>();
        children.add(new MenuResult.Data(104,"商品列表",null,new ArrayList<>()));
        return new MenuResult(101,"商品管理",null,children);
    }
}
