package com.kiszka.forumapp.controllers;

import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadDto;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class pageController {
    private final ThreadService threadService;

    @Autowired
    public pageController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/get/thread")
    public String getThreads(Model model){
        model.addAttribute("threads",threadService.getAllThreads());
        return "/home";
    }
    @GetMapping("/add/thread")
    public String addThread(){
        return "addThread";
    }
    @PostMapping("/post/thread")
    public String postThread(@ModelAttribute ThreadDto threadDto){
        threadService.createThread(threadDto);
        return "redirect:/home";
    }
}
