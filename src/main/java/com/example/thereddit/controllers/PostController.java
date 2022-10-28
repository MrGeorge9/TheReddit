package com.example.thereddit.controllers;

import com.example.thereddit.models.Post;
import com.example.thereddit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/mainPage/{name}")
    public String mainPage (@PathVariable String name, Model model){
        model.addAttribute("posts", postService.get10TopPosts());
        model.addAttribute("nameOfUser", name);
        return "mainPage";
    }

    @GetMapping("/allPosts/{name}")
    public String allPosts (@PathVariable String name, Model model){
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("nameOfUser", name);
        return "mainPage";
    }

    @GetMapping("/newPost/{name}")
    public String newPost (Model model,@PathVariable String name){
        model.addAttribute("nameOfUser", name);
        return "newPost";
    }

    @PostMapping("/newPost/{name}")
    public String newPost (@PathVariable String name, @ModelAttribute Post post){
        postService.savePost(name,post);
        return "redirect:/mainPage/"+name;
    }

    @GetMapping("/raiseRating/{name}/{id}")
    public String raiseRatingByID (@PathVariable String name, @PathVariable Integer id) {
        postService.raiseRating(name,id);
        return postService.correctRedirect(name,id);
    }

    @GetMapping("/lowerRating/{name}/{id}")
    public String lowerRatingByID (@PathVariable String name, @PathVariable Integer id) {
        postService.lowerRating(name,id);
        return postService.correctRedirect(name,id);
    }

}
