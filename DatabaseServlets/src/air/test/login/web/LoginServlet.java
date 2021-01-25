package air.test.login.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.org.apache.xerces.internal.util.Status;

import air.test.login.bean.LoginBean;
import air.test.login.database.LoginDao;

/**
 * @email Ramesh Fadatare
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        LoginDao logindao = new LoginDao();
//        loginBean.setUsername(username);
//        loginBean.setPassword(password);

        try {
        	LoginBean user = logindao.checkLogin(username, password);
            String destPage = "login.jsp";
            
            if (user != null) {
            	//printing data about user logged in
            	System.out.println(user.getUserId());
            	System.out.println(user.getUsername());
                HttpSession session = request.getSession();
                session.setAttribute("user",user);
                if(user.getUserId() == 1) {
                	destPage = "adminMain.jsp";
                } else {
                	destPage = "loginsuccess.jsp";
                }
//                response.sendRedirect("loginsuccess.jsp");
            } else {
            	destPage = "loginfailure.jsp";
//                response.sendRedirect("loginfailure.jsp");
//            	String message = "Invalid email/password";
//                request.setAttribute("message", message);
            }
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
