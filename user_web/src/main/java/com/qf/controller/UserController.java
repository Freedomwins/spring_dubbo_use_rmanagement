package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private IUserService userService;
    @Reference
    private IEmailService emailService;

    @RequestMapping("toRegister")
    public String toRegister(){
        return "registered";
    }

    @RequestMapping("sendEmail")
    @ResponseBody
    public boolean sendEmail(String address, HttpServletRequest request){
        Random ran = new Random();
        int code= ran.nextInt(8999)+1000;
        Email email = new Email();
        email.setAddress(address);
        email.setCode(code);
        System.out.println("---------------------------------------------------------------------------------------"+email.getCode());
        request.getSession().setAttribute("codeEmail",code+"");
        emailService.sendCodeEmail(email);
        return true;
    }

    @RequestMapping("addUser")
    public String insertUser(User user, HttpServletRequest request){
        System.out.println(user);
        System.out.println(request.getSession().getAttribute("codeEmail"));
        if(user.getCode().equals(request.getSession().getAttribute("codeEmail"))){
            userService.addUser(user);
            return "login";
        }else {
            return "erron";
        }
    }

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(User user){
      if (userService.userLogin(user)){
          return "loginSucess";
      }
        return "loginFailure";
    }

    @RequestMapping("toFindPassword")
    public String toFindPassword(){
        return "findPassword";
    }

    @RequestMapping("findPassword")
    public String findPassword(User user){
        User user1 = userService.getUserByName(user.getUserName());
        emailService.sendFindPasswordeEmail(user1);
        return "login";
    }

    @RequestMapping("toResetPassword")
    public String toResetPassword(String userName, Model model){
        User user = userService.getUserByName(userName);
        model.addAttribute("user",user);
        return "resetPassword";
    }

    @RequestMapping("resetPassword")
    public String toResetPassword(User user){
        int i = userService.updateUserById(user);
        if (i>0){
            return "login";
        }else {
            return "resetPasswrodErron";
        }
    }

    @RequestMapping("check")
    @ResponseBody
    public boolean registerCheck(String userName) {
        if (userService.getUserByName(userName) != null) {
            return true;
        }
        return false;
    }
}
