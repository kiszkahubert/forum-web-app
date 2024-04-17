package com.kiszka.forumapp.controllers;

import com.kiszka.forumapp.dataretrieval.dbmanipulation.response.Response;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.response.ResponseService;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.Thread;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadDto;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadService;
import com.kiszka.forumapp.dataretrieval.validation.ForumUser;
import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
public class pageController {
    private final ThreadService threadService;
    private final ForumUserService forumUserService;
    private final ResponseService responseService;

    @Autowired
    public pageController(ThreadService threadService, ForumUserService forumUserService, ResponseService responseService) {
        this.threadService = threadService;
        this.forumUserService = forumUserService;
        this.responseService = responseService;
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
    @GetMapping("/thread/{id}")
    public String showThread(@PathVariable("id") int id, Model model){
        Thread thread = threadService.returnCurrentThread(id);
        model.addAttribute("thread",thread);
        return "threadPage";
    }
    @PostMapping("/add/response")
    public String addResponse(@RequestParam("threadId") int threadId, @RequestParam("responseText") String responseText){
        ForumUser currentUser = forumUserService.getCurrentUser();
        Thread thread = threadService.returnCurrentThread(threadId);
        Response response = Response.builder()
                .responseText(responseText)
                .responseDatetime(new Timestamp(System.currentTimeMillis()))
                .likeCounter(0)
                .forumUser(currentUser)
                .thread(thread)
                .build();
        responseService.saverResponse(response);
        return "redirect:/thread/"+threadId;
    }
}
