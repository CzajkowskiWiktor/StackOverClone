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

/**
 * Servlet implementation class editAnswer
 */
@WebServlet("/editAnswer")
public class editAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String destPage = "editAnswer.jsp?id="+request.getParameter("id");
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String databaseSchema = "pzw_stackoverflow";
        String username = "admin";
        String password = "admin";
        
        //getting answer text string
        String answer_text = request.getParameter("question_text");
//        int question_id = Integer.parseInt(id);
//        System.out.println(comment_text);
        
        //getting info about user
        LoginBean user = new LoginBean();  
        HttpSession session = request.getSession();
        user =(LoginBean) session.getAttribute("user");
//        System.out.println(user.getUserId());
        int user_id = user.getUserId();
        
        //getting value of answer ID
        int question_id = Integer.parseInt(request.getParameter("questionID"));
//        System.out.println(test);
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
            
            //setting destination Page
            String destPage = "editAnswer.jsp";
//            String resultString="";
//            int rand = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
          //update deleted question with "delete question" + random id to dodge unique title
            String sql = "UPDATE answers SET answer_text=\""+answer_text+"\" WHERE id=\""+question_id+"\";";
            
            if(!answer_text.equals("")) {
            	if (stmt.executeUpdate(sql)>0) {
                	destPage = "editAnswerSuccess.jsp";
                }
                else {
                	destPage = "editAnswerFailure.jsp";
                }
            } else {
            	destPage = "editAnswerFailure.jsp";
            }
            
                
            //sending response to the client:
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
           
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            response.getWriter().append("Internal error");
            response.sendRedirect("internalError.jsp");
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

}
