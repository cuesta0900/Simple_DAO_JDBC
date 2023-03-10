package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller department);
    void update(Seller department);
    void deleteById(Integer id);
    Seller selectById(Integer id);
    List<Seller> selectAll();
    List<Seller> findByDepartment(Department department);
}
