package by.lab4.back.controller.command.common;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.entity.User;
import by.lab4.back.exception.ServiceException;
import by.lab4.back.service.UserService;
import by.lab4.back.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class SignUpCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> signUpData = new HashMap<>();
        signUpData.put("username", login);
        Validation validation = new Validation();
        if (!validation.isValid(signUpData)) {
            String errorName = validation.getInvalidData();
            request.setAttribute("signUpError", errorName);
            return CommandResult.forward("/WEB-INF/pages/login.jsp");
        }

        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findByUsername(login);
        if (optionalUser.isPresent()) {
            request.setAttribute("signUpError", "username");
            return CommandResult.forward("/WEB-INF/pages/login.jsp");
        }
        userService.signUpUser(null, login, password);
        return CommandResult.redirect("controller?command=startLogin");
    }
}
