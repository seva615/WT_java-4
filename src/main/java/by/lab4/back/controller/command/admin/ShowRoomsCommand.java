package by.lab4.back.controller.command.admin;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.entity.Room;
import by.lab4.back.exception.ServiceException;
import by.lab4.back.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowRoomsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RoomService roomService = new RoomService();
        List<Room> fullRoomList = roomService.findAll();
        request.setAttribute("roomList", fullRoomList);

        String notifyMessage = request.getParameter("message");
        if (notifyMessage != null) {
            request.setAttribute("notifyMessage", notifyMessage);
        }

        return CommandResult.forward("/WEB-INF/pages/admin/rooms.jsp");
    }
}
