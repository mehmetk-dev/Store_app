package dao;

import model.Product;

import java.util.List;

public interface BaseDAO<T> {

    void save(T t);

    T findById(long id);

    List<T> findAll(int page);

    void update(T t);

    void delete(long id);

}
