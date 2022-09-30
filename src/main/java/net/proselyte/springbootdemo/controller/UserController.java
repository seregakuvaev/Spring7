package net.proselyte.springbootdemo.controller;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcome(){
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model m){
        m.addAttribute("user", new User());
        m.addAttribute("listUsers", userService.list());
        return "show";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping(value="/adduser")
    public String addUser(@ModelAttribute("user") User users, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-user";
        }
        userService.add(users);
        return "redirect:/users";
    }

    @RequestMapping(value="/remove/{id}",method = RequestMethod.GET)
    public String removeUser(@PathVariable("id") int id){
        System.out.println(id);
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Validated User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userService.add(user);
        return "redirect:/users";
    }

}
