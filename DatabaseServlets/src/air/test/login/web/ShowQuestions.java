package air.test.login.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import air.test.login.bean.LoginBean;

/**
 * Servlet implementation class ShowQuestions
 */
@WebServlet("/ShowQuestions")
public class ShowQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
@SuppressWarnings("deprecation")
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _contextPath;
	    String _questionId;
	    int _user_id;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String databaseSchema = "pzw_stackoverflow";
        String username = "admin";
        String password = "admin";
        
        String question = request.getParameter("search_quest");
//        System.out.println(question);
        
        LoginBean user = new LoginBean();
        HttpSession session = request.getSession();
        user =(LoginBean) session.getAttribute("user");
        System.out.println(user.getUserId());
        _user_id = user.getUserId();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
           
            String id = request.getParameter("id");
            String resultString="";
            if (id==null) {
            	if (stmt.execute("SELECT id, title FROM questions WHERE question_text NOT LIKE 'deleted_perma_question'")) {
                    rs = stmt.getResultSet();
                    boolean link = true;
                    resultString = getHtmlTableFromResultSet(rs, link, _user_id);
                }
                else
                    resultString = "Wrong query type";
            } else {
            	if (stmt.execute("SELECT title, question_text, created_at FROM questions WHERE id="+id)) {
                    rs = stmt.getResultSet();
                    boolean link = false;
                    resultString = getHtmlTableFromResultSet(rs, link, _user_id);
                }
                else
                    resultString = "Wrong query type";
            }
            
//           System.out.println(resultString);
            //sending response to the client:
            response.getWriter().append(resultString);
           
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
    
    private String getHtmlTableFromResultSet(ResultSet rs, boolean link, int _user_id) throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();
//        String htmlTable = "<table border=\"1\">";
        
        String htmlTable = CommonFunctions.getCommonStyleTag();
        
        htmlTable += "<table id='questions' border=\"1\">";
        
        //header row:
        htmlTable +="<tr>";
        for (int col=1; col <= colCount; col++)
        {
            htmlTable +="<th>";
            htmlTable +=meta.getColumnLabel(col);
            htmlTable +="</th>";
        }
        
        if(link) {
        	//creating question's link column
            htmlTable +="<th>";
            htmlTable +="Link to question";
            htmlTable +="</th>";
        }
        
        
        htmlTable +="</tr>";
        
        //data rows:
        while(rs.next()) {
            
            htmlTable +="<tr>";
            // auction id
            String id="";
            
            for (int col=1; col <= colCount; col++)
            {
                Object value = rs.getObject(col);
                htmlTable +="<td>";
                if (value != null)
                {
                    htmlTable +=value.toString();
                    if(col==1) {
                    	id = value.toString();
                    }
                }
                htmlTable +="</td>";
            }
            if(link) {
            	//creating question rows
                htmlTable +="<td>";
                String urlAuction = "<a style='font-weight:bold;' href=\"../DatabaseServlets/ShowQuestion?id="+id+"\">Go to Question</a>";
                htmlTable += urlAuction;
                htmlTable +="</td>";
            }
            htmlTable +="</tr>";
        }
        htmlTable +="</table><br>";
        
      //added main menu button
//        htmlTable += "<button class='back' onclick='history.back()'>Go Back</button>";
        htmlTable += "<div class='menu'><a class='back' href=\"../DatabaseServlets/SearchByTag\">Search By Tag</a></div>";
        if(_user_id == 1 ) {
        	htmlTable += "<div class='menu'><a class='back' href=\"../DatabaseServlets/adminMain.jsp\">Main Menu</a></div>";
        }
        else {
        	htmlTable += "<div class='menu'><a class='back' href=\"../DatabaseServlets/loginsuccess.jsp\">Main Menu</a></div>";
        }
        	
        
        return htmlTable;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

