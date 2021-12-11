package by.lab4.back.repository.impl;

import by.lab4.back.builder.UserBuilder;
import by.lab4.back.entity.User;
import by.lab4.back.exception.RepositoryException;
import by.lab4.back.repository.AbstractRepository;
import by.lab4.back.specification.Specification;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User, Integer> {
    private final UserBuilder builder = new UserBuilder();

    public UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(User item) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("id", item.getId());
        values.put("username", item.getUsername());
        values.put("password", item.getPassword());
        values.put("role", item.getRole());
        return values;
    }

    @Override
    public String getTableName() {
        return "user";
    }

    @Override
    public Optional<User> query(Specification specification) throws RepositoryException {
        String query = "SELECT * FROM user " + specification.toSql();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<User> queryAll(Specification specification) throws RepositoryException {
        String query = "SELECT * FROM user " + specification.toSql();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }

}
