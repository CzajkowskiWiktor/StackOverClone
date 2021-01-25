package air.test.login.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import air.test.login.bean.LoginBean;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Servlet implementation class deleteUser
 */
@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String databaseSchema = "pzw_stackoverflow";
        String username = "admin";
        String password = "admin";
        
        //username from deleteUser.jsp form
        String user_name = request.getParameter("username");
        System.out.println(user_name);
//        
//        LoginBean user = new LoginBean();
//        
//        HttpSession session = request.getSession();
//        user =(LoginBean) session.getAttribute("user");
//        System.out.println(user.getUserId());
//        
//        int user_id = user.getUserId();

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
            
          //setting destination Page
            String destPage = "deleteUser.jsp";
//            String resultString="";
            int id = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
            //update deleted user with "delete user" + random id to dodge unique username
            String sql = "UPDATE users SET username='delete user"+id+"', password='delete' WHERE username=\""+user_name+"\";";
            if (stmt.executeUpdate(sql)>0) {
//            	resultString = "User has been deleted from StackOverFlow";
            	destPage = "deleteUserSuccess.jsp";
            }
            else {
//            	resultString = "Wrong query type";
            	destPage = "deleteUserFailure.jsp";
            }
                
           
            //sending response to the client:
//            response.getWriter().append(resultString);
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
           
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            response.getWriter().append("Internal error");
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { }

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }

                stmt = null;
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
