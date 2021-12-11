package by.lab4.back.controller.command.common;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.forward("/index.jsp");
    }
}
