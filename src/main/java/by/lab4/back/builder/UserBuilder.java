package by.lab4.back.builder;

import by.lab4.back.entity.Role;
import by.lab4.back.entity.User;
import by.lab4.back.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            Integer id = resultSet.getInt("id");
            String login = resultSet.getString("username");
            String password = resultSet.getString("password");
            Role role = Role.valueOf(resultSet.getString("role").toUpperCase());

            return new User(id, login, password, role);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
