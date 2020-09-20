package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanQin
 * @version v1.0.0
 * @Description : TODO
 * @Create on : 2020/9/19 16:39
 **/
@Controller
public class LoginController {

    @RequestMapping("/login-view")
    public String viewTest() {
        return "login";
    }

    @RequestMapping("/login-success")
    public String loginControl() {
        return "login-success";
    }

    @GetMapping("/r/r1")
    @ResponseBody
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return getUserName() + "r1访问资源";
    }

    @GetMapping("/r/r2")
    @ResponseBody
    @PreAuthorize("hasAuthority('p2')")
    public String r2() {
        return getUserName() + "r2访问资源";
    }


    //获取当前用户
    private String getUserName() {
        String username = null;
        //认证过的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户信息
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        //如果principal是UserDetails
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
