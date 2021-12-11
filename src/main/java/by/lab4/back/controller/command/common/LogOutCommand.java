package by.lab4.back.controller.command.common;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("id");
        session.removeAttribute("username");
        session.removeAttribute("role");
        return CommandResult.forward("/WEB-INF/pages/login.jsp");
    }
}
