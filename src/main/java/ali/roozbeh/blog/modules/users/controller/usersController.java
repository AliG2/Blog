package ali.roozbeh.blog.modules.users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
@Controller
@RequestMapping(value = "/users")
public class usersController {

        private UsersService usersService;

        @Autowired
        public UsersController(UsersService usersService) {
            this.usersService = usersService;
        }

        @RequestMapping(value = "", method = RequestMethod.GET)
        public String users(Model model) {
            model.addAttribute("users", usersService.findAllUsers());
            return "users/users";
        }

        @RequestMapping(value = "/register", method = RequestMethod.GET)
        public String registerPage(Model model) {
            model.addAttribute("user", new Users());
            return "users/registerUser";
        }

        @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
        public String registerPage(Model model, @PathVariable("id") Long id) {
            model.addAttribute("user", usersService.findById(id));
            return "users/registerUser";
        }

        @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
        public String delete(@PathVariable("id") Long id) {
            usersService.deleteById(id);
            return "redirect:/users";
        }

        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public String register(@ModelAttribute(name = "user") @Valid Users users, BindingResult bindingResult) throws IOException, InvocationTargetException, IllegalAccessException {
            if (bindingResult.hasErrors())
                return "users/registerUser";

            usersService.registerUser(users);
            return "redirect:/users";
        }

    }

