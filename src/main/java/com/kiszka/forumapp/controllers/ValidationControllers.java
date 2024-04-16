package com.kiszka.forumapp.controllers;

import com.kiszka.forumapp.entity.ForumUser;
import com.kiszka.forumapp.validation.ForumUserDto;
import com.kiszka.forumapp.validation.ForumUserService;
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
    private final ForumUserService forumUserService;
    @Autowired
    public ValidationControllers(ForumUserService forumUserService){
        this.forumUserService = forumUserService;
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        ForumUserDto userDto = new ForumUserDto();
        model.addAttribute("user",userDto);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") ForumUserDto forumUserDto, BindingResult result, Model model){
        ForumUser existingUser = forumUserService.findUserByEmail(forumUserDto.getEmail());
        if(existingUser!=null && existingUser.getEmail()!=null &&!existingUser.getEmail().isEmpty()){
            result.rejectValue("email","400","Konto z podanym adresem email już istnieje");
        }
        if(result.hasErrors()){
            model.addAttribute("user",forumUserDto);
            return "register";
        }
        forumUserDto.setRole("USER");
        forumUserService.saveUser(forumUserDto);
        return "redirect:/login";
    }
}