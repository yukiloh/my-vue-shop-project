package com.myvueshopproject.login.controller;

import com.myvueshopproject.login.model.BaseResult;
import com.myvueshopproject.login.model.LoginParam;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/my-vue-shop-project")
public class LoginController {

    @PostMapping("/login")
    //通过RequestBody来接收json类型的对象,完全忘记了
    public BaseResult login(@RequestBody LoginParam loginParam) {
        if (loginParam.getUsername().equals("goudan") && loginParam.getPassword().equals("111111")) {
            return new BaseResult(1, 0, "狗蛋", "110", "goudan@gd.com", "token", true);
        }
        else return new BaseResult(0, 0, "", "", "", "", false);
    }
}
