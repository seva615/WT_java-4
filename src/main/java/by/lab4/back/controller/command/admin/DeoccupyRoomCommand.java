package by.lab4.back.controller.command.admin;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.entity.Room;
import by.lab4.back.exception.ServiceException;
import by.lab4.back.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeoccupyRoomCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String roomId = request.getParameter("roomId");

        RoomService roomService = new RoomService();
        roomService.changeStatus(Integer.valueOf(roomId), false);

        List<Room> roomList = roomService.findAll();
        request.setAttribute("roomList", roomList);

        return CommandResult.redirect("controller?command=showRooms");
    }
}
