package air.test.login.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteComment
 */
@WebServlet("/RateComment")
public class RateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        Statement stmt = null;
        String databaseSchema = "pzw_stackoverflow";
        String username = "admin";
        String password = "admin";
        int user_id = CommonFunctions.getUserIdFromRequest(request);
        String rating = request.getParameter("rating");
        String questionId = request.getParameter("questionId");
        
        //question's ID from deleteQuestion.jsp form
        int comment_id = Integer.parseInt(request.getParameter("id"));
//        int question_id = Integer.parseInt(id);
        System.out.println(comment_id);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
            
            //setting destination Page
            String destPage = "deleteComment.jsp";
            String sql = "DELETE FROM comment_ratings WHERE comment_id = '"+comment_id+"' AND user_id = '"+user_id+"';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            try {
                stmt.close();
            } catch (SQLException sqlEx) { }
            stmt = conn.createStatement();
            sql = "INSERT INTO comment_ratings( rating, comment_id, user_id) VALUES ( "+rating+", "+comment_id+", "+user_id+");";
            System.out.println(stmt.executeUpdate(sql));
            response.sendRedirect(request.getContextPath()+"/ShowQuestion?id="+questionId);
           
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            response.getWriter().append("Internal error");
            response.sendRedirect("internalError.jsp");
        }
        finally {
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
