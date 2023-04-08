package com.dbmsproject.upsideavenue.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dbmsproject.upsideavenue.models.Photo;
import com.dbmsproject.upsideavenue.models.Post;
import com.dbmsproject.upsideavenue.models.Property;
import com.dbmsproject.upsideavenue.models.Role;
import com.dbmsproject.upsideavenue.models.SearchPost;
import com.dbmsproject.upsideavenue.models.User;
import com.dbmsproject.upsideavenue.repositories.PhotoRepository;
import com.dbmsproject.upsideavenue.repositories.PostRepository;
import com.dbmsproject.upsideavenue.repositories.PropertyRepository;
import com.dbmsproject.upsideavenue.repositories.UserRepository;

@Controller
@RequestMapping("")
@Secured({ "USER" })
public class UserController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("properties")
    public String properties(Model model) {
        model.addAttribute("properties", propertyRepository
                .findAllPropertyByOwner(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "properties";
    }

    @GetMapping("properties/add")
    public String addProperty(Model model) {
        model.addAttribute("property", new Property());
        return "addProperty";
    }

    @PostMapping("properties/add")
    public String addProperty(Property property, @RequestParam("images") MultipartFile[] images, Model model)
            throws SerialException, SQLException, IOException {

        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        property.setOwner(owner);

        propertyRepository.save(property);

        for (MultipartFile image : images) {
            Photo photo = new Photo();
            photo.setPropertyId(property);
            photo.setPhoto(image.getBytes());

            photoRepository.save(photo);
        }

        return properties(model);
    }

    @GetMapping("properties/{propertyID}")
    public String propertyDetails(@PathVariable UUID propertyID, Model model) {
        Property property = propertyRepository.findById(propertyID).orElse(null);
        model.addAttribute("property", property);
        return "propertyDetails";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("agents", userRepository.findAllUserByRole(Role.AGENT));
        model.addAttribute("properties", propertyRepository
                .findAllPropertyByOwner(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "createPost";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postRepository
                .findAllPostByOwner(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "posts";
    }

    @PostMapping("/posts/create")
    public String createPost(Post post, Model model) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        post.setPostDate(date);
        postRepository.save(post);

        return posts(model);
    }

    @GetMapping("/purchase")
    public String purchase(Model model) {
        model.addAttribute("posts", postRepository
                .findAllPostNotOwned(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("search", new SearchPost());
        return "purchase";
    }

    @PostMapping("/purchase")
    public String purchaseFilter(SearchPost search, Model model) {
        model.addAttribute("posts", postRepository
                .findAllPostNotOwned(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("search", search);
        return "purchase";
    }

}
