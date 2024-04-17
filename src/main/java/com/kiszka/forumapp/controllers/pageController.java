package com.kiszka.forumapp.controllers;

import com.kiszka.forumapp.dataretrieval.dbmanipulation.response.Response;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.response.ResponseService;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.Thread;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadDto;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadService;
import com.kiszka.forumapp.dataretrieval.validation.ForumUser;
import com.kiszka.forumapp.dataretrieval.validation.ForumUserRepository;
import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class pageController {
    private final ThreadService threadService;
    private final ForumUserService forumUserService;
    private final ResponseService responseService;
    private final ForumUserRepository forumUserRepository;
    @Autowired
    public pageController(ThreadService threadService, ForumUserService forumUserService, ResponseService responseService, ForumUserRepository forumUserRepository) {
        this.threadService = threadService;
        this.forumUserService = forumUserService;
        this.responseService = responseService;
        this.forumUserRepository = forumUserRepository;
    }
    @GetMapping("/get/thread")
    public String getThreads(Model model, @PageableDefault(size = 5) Pageable pageable){
        List<ForumUser> mostActiveUsers = getMostActiveUsers();
        model.addAttribute("users",mostActiveUsers);
        Page<Thread> threads = threadService.getAllPageThreads(pageable);
        model.addAttribute("threads",threads);
        return "/home";
    }
    @GetMapping("/add/thread")
    public String addThread(){
        return "addThread";
    }
    @PostMapping("/post/thread")
    public String postThread(@ModelAttribute ThreadDto threadDto){
        threadService.createThread(threadDto);
        return "redirect:/get/thread";
    }
    @GetMapping("/thread/{id}")
    public String showThread(@PathVariable("id") int id, Model model){
        Thread thread = threadService.returnCurrentThread(id);
        model.addAttribute("thread",thread);
        return "threadPage";
    }
    @PostMapping("/add/response/{threadId}")
    public String addResponse(@PathVariable("threadId") int threadId, @RequestParam("responseText") String responseText){
        ForumUser currentUser = forumUserService.getCurrentUser();
        Thread thread = threadService.returnCurrentThread(threadId);
        Response response = Response.builder()
                .responseText(responseText)
                .responseDatetime(new Timestamp(System.currentTimeMillis()))
                .likeCounter(0)
                .forumUser(currentUser)
                .thread(thread)
                .build();
        responseService.saveResponse(response);
        return "redirect:/thread/"+threadId;
    }
    @GetMapping("/add/response/{threadId}")
    public String addResponse(@PathVariable("threadId") int threadId, Model model){
        model.addAttribute("threadId",threadId);
        return "responseForm";
    }
    public List<ForumUser> getMostActiveUsers(){
        List<ForumUser> allUsers = forumUserRepository.findAll();
        allUsers.sort((u1,u2)->{
            int u1Activity = u1.getResponses().size() + u1.getThreads().size();
            int u2Activity = u2.getResponses().size() + u2.getThreads().size();
            return Integer.compare(u2Activity,u1Activity);
        });
        return allUsers.subList(0,Math.min(5, allUsers.size()));
    }
}
