package ua.com.domains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.domains.dao.UserDao;
import ua.com.domains.dto.DtoUtilMapper;
import ua.com.domains.dto.UserDTO;
import ua.com.domains.entity.User;
import ua.com.domains.service.MailSenderService;
import ua.com.domains.service.UserService;
import ua.com.domains.validator.UserValidationMessages;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private MailSenderService mailSenderService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
    public String newUser(@PathVariable int id) {

        userService.delete(id);

        return "redirect:/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user, Model model) {

        String uuid = UUID.randomUUID().toString();

        user.setUuid(uuid);

        try {
            userService.save(user);
        } catch (Exception e) {
            if (e.getMessage().equals(UserValidationMessages.EMPTY_USERNAME_FIELD)) {
                model.addAttribute("usernameException", e.getMessage());
            } else if (e.getMessage().equals(UserValidationMessages.EMPTY_EMAIl_FIELD)
                    || e.getMessage().equals(UserValidationMessages.EMAIL_ALREADY_EXIST)) {
                model.addAttribute("emailException", e.getMessage());
            } else if (e.getMessage().equals(UserValidationMessages.EMPTY_PASSWORD_FIELD)){
                model.addAttribute("passwordException", e.getMessage());
            }
            return "registration";
        }

        String theme = "thank's for registration";
        String mailBody = "gl & hf       http://localhost:8080/Topalov/confirm/" + uuid;

        mailSenderService.sendMail(theme, mailBody, user.getEmail());

        return "redirect:/";

    }

    @RequestMapping(value = "/confirm/{uuid}", method = RequestMethod.GET)
    public String confirm(@PathVariable String uuid) {

        User user = userService.findByUUID(uuid);
        user.setEnabled(true);

        userService.update(user);

        return "redirect:/";
    }

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/all")
    private List<User> getAll(){
        return userDao.findAll();
    }

    @PostMapping(value = "/load")
    private List<User> persist(@RequestBody final User user){
        userDao.save(user);
        return userDao.findAll();
    }
}
