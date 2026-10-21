package com.training.demosecurity.controller;

import java.util.logging.Logger;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.demosecurity.entity.User;
import com.training.demosecurity.service.UserService;
import com.training.demosecurity.user.WebUser;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller 
@RequestMapping ("/registration")
public class RegistrationController {
    private Logger logger = Logger.getLogger(getClass().getName());

    private final UserService _userService;

    public RegistrationController(UserService _userService) {
        this._userService = _userService;
    }

    @InitBinder 
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationPageString(Model model) {
        model.addAttribute("webUser", new WebUser());
        return "registration/registration-form";
    }
    

    @PostMapping("/processRegistrationForm")
    public String processRegistratinFormString(
        @Valid @ModelAttribute("webUser") WebUser theWebUser,
        BindingResult bindingResult,
        HttpSession session, Model model
    ) {
        String userName = theWebUser.getUserName();
        logger.info("Processing registration form for: " + userName);

        String registrationForm = "registration/registration-form";
        if (bindingResult.hasErrors()) {
            logger.warning("Form has errors");
            return registrationForm;
        }

        User existing = this._userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("webUser", new WebUser());
            model.addAttribute("registrationError", "User name already exists");

            logger.warning("User name already exists.");
            return registrationForm;
        }

        this._userService.save(theWebUser);

        logger.info("Successfully created user: " + userName);

        session.setAttribute("user", theWebUser);
        
        return "registration/registration-confirmation";
    }
    
}
