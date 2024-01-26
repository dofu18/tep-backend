package com.main.timeshareexchangeplatform_backend.controller;


import com.main.timeshareexchangeplatform_backend.entity.User;
import com.main.timeshareexchangeplatform_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes("user")
public class LoginController {
    private UserService userService;

    @ModelAttribute("user")
    public User user(){
        return new User();
    }
    @GetMapping("/login")
    public String showloginForm(){
        return "/login";
    }
    @PostMapping("/login")
    public String Login(@ModelAttribute("user") User user){
        if(!userService.checkUserByUsername(user.getUser_name())){
            return "redirect:/login?emailwrong";
        }

        if(user.getRole().equals("ADMIN")){
            return  "redirect:/admin_home";
        }
        if(userService.checkPasswordByUsername(user.getUser_name(), user.getPassword())){
            return "redirect:/home?success";
        }
        return "redirect:/login?passwordwrong";
    }
}
