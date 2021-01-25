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
import air.test.login.database.LoginDao;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
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
        
        String title = request.getParameter("title");
        String question_text = request.getParameter("question_text");
        
        
        System.out.println(title);
        System.out.println(question_text);
        
        LoginBean user = new LoginBean();
        
        HttpSession session = request.getSession();
        user =(LoginBean) session.getAttribute("user");
        System.out.println(user.getUserId());
        
        int user_id = user.getUserId();

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
           
            String destPage = "addQuestion.jsp";
            String resultString="";
            
            String sql = "INSERT INTO questions (title, question_text, user_id) VALUES (\""+title+"\", \""+question_text+"\", "+user_id+");";

            if(!title.equals("") && !question_text.equals("")) {
            	System.out.println("WOlne pole");
                if (stmt.executeUpdate(sql)>0) {
//                	resultString = "Added new question";
                	destPage = "addedQuestionSuccess.jsp";
                }
                else
                {
//                  resultString = "Wrong query type";
                	destPage = "addedQuestionFailure.jsp";
                }
            } else {
            	System.out.println("Empty inputs");
            	destPage = "addedQuestionFailure.jsp";
            }
            
//            if (stmt.executeUpdate(sql)>0) {
////            	resultString = "Added new question";
//            	destPage = "addedQuestionSuccess.jsp";
//            }
//            else
//            {
////              resultString = "Wrong query type";
//            	destPage = "addedQuestionFailure.jsp";
//            }
            
            //sending response to the client:
//            response.getWriter().append(resultString);
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
           
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            response.sendRedirect("addedQuestionFailure.jsp");
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
