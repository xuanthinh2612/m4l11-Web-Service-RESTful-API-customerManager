package CustomerManagerRESTfullWebservice.repository;

import java.util.List;

public interface IRepository <T> {
    List<T> findAll();

    T findById(Long id);

    void save(T model);

    void remove(Long id);
}