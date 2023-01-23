package model.dao;

import model.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department department);
    void update(Department department);
    void deleteById(Integer id);
    Department selectById(Integer id);
    List<Department> selectAll();
}
