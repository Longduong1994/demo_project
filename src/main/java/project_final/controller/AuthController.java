package project_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project_final.exception.RegisterException;
import project_final.model.dto.request.UserRequest;
import project_final.model.service.impl.mail.IMailService;
import project_final.model.service.impl.user.IUserService;

import javax.validation.Valid;

@Controller
@RequestMapping
public class AuthController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IMailService mailService;
    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRequest userRequest) throws RegisterException {
        userService.save(userRequest);
        String emailContent = "<p style=\"color: red; font-size: 18px;\">\n" + "Registered successfully</p>";
        mailService.sendMail(userRequest.getEmail(), "RegisterSuccess", emailContent);
        return "login";
    }
}
