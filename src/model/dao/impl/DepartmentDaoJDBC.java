package model.dao.impl;

import db.DB;
import db.dbException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("Insert into Department (Name) Values (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, department.getName());
            int row = st.executeUpdate();
            if(row > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new dbException("Unexpected error: no lines were affected");
            }
        }catch (SQLException e){
            throw new dbException(e.getMessage());
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Department SET NAME = ? WHERE ID = ?");
            st.setString(1, department.getName());
            st.setInt(2, department.getId());
            st.executeUpdate();
        }catch (SQLException e){
            throw new dbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE from Department WHERE ID = ?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new dbException(e.getMessage());
        }
    }

    @Override
    public Department selectById(Integer id) {
        try{
            PreparedStatement st = conn.prepareStatement("SELECT* FROM DEPARTMENT WHERE ID = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                return dep;
            }
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return null;
        }catch (SQLException e) {
            throw new dbException(e.getMessage());
        }
    }

    @Override
    public List<Department> selectAll() {
        List<Department> list = new ArrayList<>();
        try{
            PreparedStatement st = conn.prepareStatement("SELECT* FROM DEPARTMENT");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Department dep = instantiateDepartment(rs);
                list.add(dep);
            }
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;
        }catch (SQLException e) {
            throw new dbException(e.getMessage());
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
