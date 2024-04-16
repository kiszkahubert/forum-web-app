package com.kiszka.forumapp.controllers;

import com.kiszka.forumapp.entity.ForumUser;
import com.kiszka.forumapp.validation.ForumUserDto;
import com.kiszka.forumapp.validation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValidationControllers { ;
    private final UserService userService;
    @Autowired
    public ValidationControllers(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(){
        return "validation/login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        ForumUserDto userDto = new ForumUserDto();
        model.addAttribute("user",userDto);
        return "validation/register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") ForumUserDto forumUserDto, BindingResult result, Model model){
        ForumUser existingUser = userService.findUserByEmail(forumUserDto.getEmail());
        if(existingUser!=null && existingUser.getEmail()!=null &&!existingUser.getEmail().isEmpty()){
            result.rejectValue("email","400","Konto z podanym adresem email już istnieje");
        }
        if(result.hasErrors()){
            model.addAttribute("user",forumUserDto);
            return "validation/register";
        }
        forumUserDto.setRole("USER");
        userService.saveUser(forumUserDto);
        return "redirect:/login";
    }
}