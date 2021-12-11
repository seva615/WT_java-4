package by.lab4.back.builder;

import by.lab4.back.entity.Room;
import by.lab4.back.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomBuilder implements Builder<Room> {
    @Override
    public Room build(ResultSet resultSet) throws RepositoryException {
        try {
            Integer id = resultSet.getInt("id");
            String roomNumber = resultSet.getString("room_number");
            Boolean occupied = resultSet.getBoolean("occupied");

            return new Room(id, roomNumber, occupied);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
