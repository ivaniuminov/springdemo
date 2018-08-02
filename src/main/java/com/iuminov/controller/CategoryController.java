package com.iuminov.controller;

import com.iuminov.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ModelAndView getyAll() {
        ModelAndView vm = new ModelAndView("categories", "categories", categoryService.getAll());
        return vm;
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam(name = "c_id") Long id) {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("category");
        vm.addObject("category", categoryService.getById(id));
        System.out.println();
        return vm;
    }
}
