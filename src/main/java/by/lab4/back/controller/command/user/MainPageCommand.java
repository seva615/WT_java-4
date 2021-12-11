package by.lab4.back.controller.command.user;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.entity.Room;
import by.lab4.back.exception.ServiceException;
import by.lab4.back.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RoomService roomService = new RoomService();
        List<Room> freeRoomList = roomService.findFree();
        request.setAttribute("roomList", freeRoomList);
        return CommandResult.forward("/WEB-INF/pages/makeOrder.jsp");
    }
}
