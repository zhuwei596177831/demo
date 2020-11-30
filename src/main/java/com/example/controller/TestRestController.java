package com.example.controller;

import com.example.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 朱伟伟
 * @date 2020-09-22 15:22:07
 * @description
 */
@RestController
@SessionAttributes(value = {"user"}, types = {User.class})
public class TestRestController {

    @GetMapping(value = {"/test", "/test1"})
    public String test() {
        return null;
    }

//    @ModelAttribute
//    public void testModelAttribute(Integer id, Model model) {
//        System.out.println("testModelAttribute");
//        model.addAttribute("id", id);
//    }

    @ModelAttribute("paramModelValue")
    public String paramModelValue(String name) {
        System.out.println("paramModelValue");
        return name;
    }

    @GetMapping("/getTest")
    public String getTest(Integer id,
                          @ModelAttribute("paramModelValue") String paramModelValue, Model model) {
        System.out.println("TestRestController getTest");
        System.out.println("id：" + model.getAttribute("id"));
        System.out.println("paramModelValue：" + paramModelValue);
        return "zww";
    }

    @PostMapping("/getTest")
    public String getTest(String name) {
        return null;
    }

    @InitBinder
    public void testInitBinder(WebDataBinder webDataBinder, HttpServletRequest request, String name) {
        System.out.println("testInitBinder");
    }

//    @ModelAttribute("user")
//    public User user(User user) {
//        return user;
//    }

    @GetMapping("/addSessionAttribute")
    public void testSessionAttribute(Model model, User user) {
//        User user = new User();
//        user.setName("朱伟伟");
        model.addAttribute("user", user);
        System.out.println("testSessionAttribute");
    }

    @GetMapping("/getSessionAttribute")
    public User getSessionAttribute(@SessionAttribute(value = "user", required = false) User user) {
        return user;
    }

}
