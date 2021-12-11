package by.lab4.back.controller.command.admin;

import by.lab4.back.controller.command.Command;
import by.lab4.back.controller.command.CommandResult;
import by.lab4.back.entity.Room;
import by.lab4.back.exception.ServiceException;
import by.lab4.back.service.RoomService;
import by.lab4.back.util.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddRoomCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String roomNumber = request.getParameter("roomNumber");

        Validation validation = new Validation();
        Map<String, String> values = new HashMap<>();
        values.put("roomNumber", roomNumber);
        if (!validation.isValid(values)) {
            return CommandResult.redirect("controller?command=showRooms&message=" + "invalidRoom");
        }

        RoomService roomService = new RoomService();
        roomService.saveRoom(null, roomNumber, false);

        List<Room> roomList = roomService.findAll();
        request.setAttribute("roomList", roomList);

        return CommandResult.redirect("controller?command=showRooms&message=" + "added");
    }
}
