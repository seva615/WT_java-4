package by.lab4.back.repository;

import by.lab4.back.exception.RepositoryException;
import by.lab4.back.specification.Specification;

import java.util.List;
import java.util.Optional;


public interface Repository<T> {


    Optional<T> query(Specification specification) throws RepositoryException;


    List<T> queryAll(Specification specification) throws RepositoryException;


    void save(T item) throws RepositoryException;

    String getTableName();
}
