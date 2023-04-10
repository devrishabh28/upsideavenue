package com.dbmsproject.upsideavenue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbmsproject.upsideavenue.repositories.PostRepository;

@Controller
@RequestMapping("")
@Secured({ "AGENT" })
public class AgentController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/myposts")
    public String posts(Model model) {
        model.addAttribute("posts", postRepository
                .findAllPostByAgent(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "posts";
    }
}
