package com.dbmsproject.upsideavenue.controllers;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dbmsproject.upsideavenue.models.Photo;
import com.dbmsproject.upsideavenue.models.Property;
import com.dbmsproject.upsideavenue.models.User;
import com.dbmsproject.upsideavenue.repositories.PhotoRepository;
import com.dbmsproject.upsideavenue.repositories.PropertyRepository;

@Controller
@RequestMapping("")
@Secured({ "USER" })
public class UserController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PhotoRepository photoRepository;

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
    public String addProperty(Property property, @RequestParam("images") MultipartFile[] images)
            throws SerialException, SQLException, IOException {

        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        property.setOwner(owner);

        propertyRepository.save(property);

        for (MultipartFile image : images) {
            Photo photo = new Photo();
            photo.setPropertyId(property);
            Blob imageBlob = new SerialBlob(image.getBytes());
            photo.setPhoto(imageBlob);

            photoRepository.save(photo);
        }

        return "properties";
    }
}
