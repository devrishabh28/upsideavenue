package com.dbmsproject.upsideavenue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbmsproject.upsideavenue.models.Property;
import com.dbmsproject.upsideavenue.models.User;
import com.dbmsproject.upsideavenue.repositories.PropertyRepository;

@Controller
@RequestMapping("")
@Secured({ "USER" })
public class UserController {

    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping("properties")
    public String properties() {
        return "properties";
    }

    @GetMapping("properties/add")
    public String addProperty(Model model) {
        model.addAttribute("property", new Property());
        return "addProperty";
    }

    @PostMapping("properties/add")
    public String addProperty(Property property) {

        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        property.setOwner(owner);

        propertyRepository.save(property);

        return "properties";
    }
}
