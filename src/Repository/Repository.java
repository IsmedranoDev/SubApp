/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.List;

/**
 *
 * @author israelmedrano
 */
public interface Repository<T> {
    
    List<T> findAll();
    T findById(int id);
    void save(T entity);
    void delete(int id);
    
}
