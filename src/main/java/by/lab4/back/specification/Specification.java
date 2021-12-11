package by.lab4.back.specification;

import java.util.List;

public interface Specification {
    String toSql();

    List<Object> getParameters();
}
