package com.iuminov.controller;

import com.iuminov.controller.ajax.AjaxResponseBody;
import com.iuminov.controller.ajax.EmailPayload;
import com.iuminov.model.User;
import com.iuminov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("login");
        vm.addObject("user", new User());
        return vm;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("user") User user) {
        ModelAndView vm = new ModelAndView();
        vm.addObject("user", userService.getByEmail(user));
        vm.setViewName("welcome");
        return vm;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView doSignup() {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("signup");
        vm.addObject("user", new User());
        return vm;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@ModelAttribute("user")User user) {
        userService.addUser(user);

        ModelAndView vm = new ModelAndView();
        vm.setViewName("welcome");
        vm.addObject("user", user);
        return vm;
    }

    @RequestMapping(value = "/activation/{token}", method = RequestMethod.GET)
    public ModelAndView activation(@PathVariable String token) {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("welcome");
        return vm;
    }

    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public AjaxResponseBody emailCheck(@RequestBody EmailPayload email) {
        AjaxResponseBody resp = new AjaxResponseBody();
        User user = new User();
        user.setEmail(email.getEmail());

        if (userService.getByEmail(user) != null) {
            resp.setMsg("NOK");
            resp.setResult("false");
        } else {
            resp.setMsg("OK");
            resp.setResult("true");
        }

        return resp;
    }
}
