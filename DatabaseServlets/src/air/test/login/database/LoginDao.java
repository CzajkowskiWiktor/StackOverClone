package air.test.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import air.test.login.bean.LoginBean;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;
        String databaseSchema = "pzw_stackoverflow";
        String username = "admin";
        String password = "admin";

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
        		.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT * FROM users WHERE username = ? and password = ?")) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            
            System.out.println(rs.getInt("id"));
            
            LoginBean user = null;            
            if (rs.next()) {
                user = new LoginBean();
                user.setUserId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
            }
     
            connection.close();
//            return user;
            

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }
    
    public LoginBean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
    	String databaseSchema = "pzw_stackoverflow";
        String username_sql = "admin";
        String password_sql = "admin";

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager
        		.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username_sql,password_sql);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement("SELECT * FROM users WHERE username = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet result = preparedStatement.executeQuery();
            
            LoginBean loginbean = null;
     
            if (result.next()) {
                loginbean = new LoginBean();
                loginbean.setUserId(result.getInt("id"));
                loginbean.setUsername(result.getString("username"));
            }
     
            connection.close();
            
            return loginbean;

//        return status;
        
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
