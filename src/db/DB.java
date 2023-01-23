package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;
    private static String link = "jdbc:mysql://localhost:3306/coursejdbc";
    private static final String usuario = "root";
    private static final String senha = "pedro123";
    public static Connection getConnection(){
        if(conn == null){
            try{
                conn = DriverManager.getConnection(link, usuario, senha);
            }catch (SQLException e){
                throw new dbException(e.getMessage());
            }
        }
        return conn;
    }


    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch (IOException e){
            throw new dbException(e.getMessage());
        }
    }

    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                throw new dbException(e.getMessage());
            }
        }
    }


    public static void closeStatement(Statement st){
        try{
            if(st!=null ) {
                st.close();
            }
        }catch (SQLException e){
            throw new dbException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet st){
        try{
            if(st!=null ) {
                st.close();
            }
        }catch (SQLException e){
            throw new dbException(e.getMessage());
        }
    }
}
