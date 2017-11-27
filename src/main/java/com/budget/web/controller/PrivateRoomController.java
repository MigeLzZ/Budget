package com.budget.web.controller;

import com.budget.dao.entities.User;
import com.budget.services.IRecordService;
import com.budget.services.IUserDetailsService;
import com.budget.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/privateRoom")
public class PrivateRoomController {
    private final IUserService userService;
    private final IRecordService recordService;
    private final IUserDetailsService userDetailsService;

    @Autowired
    public PrivateRoomController(IUserService userService, IRecordService recordService, IUserDetailsService iUserDetailsService) {
        this.userService = userService;
        this.recordService = recordService;
        this.userDetailsService = iUserDetailsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/*")
    public String getPrivateRoom(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap modelMap){
        if(user == null){
            modelMap.addAttribute("message", "Авторизируйтесь!");
            return "login";
        }

        modelMap.addAttribute("user", user);
        return "Person_page";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/changeUser")
    public String changeUser(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap modelMap){
        return "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/action")
    public String changeUserData(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap modelMap){
        String message = null;
        switch (request.getParameter("action")){
            case "changeUsername":
                if(userService.checkIfUserExists(request.getParameter("p_name"))){
                    message = "Пользователь с таким логином существует!";
                    break;
                }
                user.setUsername(request.getParameter("p_name"));
                break;
            case "changeLimit":
                user.setMounthlyLimit(new Float(request.getParameter("p_limit")));
                break;
            case "changeMail":
                if(userService.checkIfMailExists(request.getParameter("p_email"))){
                    message = "Пользователь с таким e-mail уже существует!";
                    break;
                }
                user.setMail(request.getParameter("p_email"));
                break;
        }

        user = userService.saveCurrentUserWithDetailsUpdate(user);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("message", message);
        return "Person_page";
    }

}
