package com.myvueshopproject.login.controller;

import com.myvueshopproject.login.model.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-vue-shop-project/")
public class LoginController {

    @PostMapping("/login")
    public BaseResult success(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {
        if (username.equals("goudan") && password.equals("111111")) {
            return new BaseResult(1, 0, "狗蛋", "110", "goudan@gd.com", "token", true);
        }
        else return new BaseResult(0, 0, "", "", "", "", false);
    }
}
