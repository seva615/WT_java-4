package by.lab4.back.controller.command.common;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.entity.Role;
import by.lab4.back.entity.User;
import by.lab4.back.exception.ServiceException;
import by.lab4.back.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserService service = new UserService();
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> optionalUser = service.findByUsernameAndPassword(login, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Role role = user.getRole();

            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", role);

            return Role.ADMIN.equals(role) ?
                    CommandResult.redirect("controller?command=showRooms") :
                    CommandResult.redirect("controller?command=mainPage");
        } else {
            request.setAttribute("errorMessage", "Wrong login or password");
            return CommandResult.forward("/WEB-INF/pages/login.jsp");
        }
    }
}
