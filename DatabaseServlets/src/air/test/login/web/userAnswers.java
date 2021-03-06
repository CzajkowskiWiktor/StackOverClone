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
 * Servlet implementation class userAnswers
 */
@WebServlet("/userAnswers")
public class userAnswers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userAnswers() {
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
        
        
        //catching logged user's id
        LoginBean user = new LoginBean();
        HttpSession session = request.getSession();
        user =(LoginBean) session.getAttribute("user");
        System.out.println(user.getUserId());
        int user_id = user.getUserId();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
           
            String id = request.getParameter("id");
            String resultString="";
            if (id==null) {
            	if (stmt.execute("SELECT id, answer_text, created_at, question_id FROM answers WHERE user_id=\""+user_id+"\";")) {
                    rs = stmt.getResultSet();
                    boolean link = true;
                    resultString = getHtmlTableFromResultSet(rs, link);
                }
                else
                    resultString = "Wrong query type";
            } else {
            	if (stmt.execute("SELECT title, question_text, created_at FROM questions WHERE id="+id)) {
                    rs = stmt.getResultSet();
                    boolean link = false;
                    resultString = getHtmlTableFromResultSet(rs, link);
                }
                else
                    resultString = "Wrong query type";
            }
            
           System.out.println(resultString);
            //sending response to the client:
            response.getWriter().append(resultString);
           
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
//            response.getWriter().append("Internal error");
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
	
	private String getHtmlTableFromResultSet(ResultSet rs, boolean link) throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();
//        String htmlTable = "<table border=\"1\">";
        
        String htmlTable = "<style> #questions {font-family: Arial, Helvetica, sans-serif;"
        		+ "  border-collapse: collapse;"
        		+ "  min-width: 30%;"
        		+ "	 box-shadow: 5px 5px 5px black;"
        		+ "}";
        
        htmlTable += "#questions td, #customers th {"
        		+ "  border: 3px solid #ddd;"
        		+ "  padding: 15px;"
        		+ "}"
        		+ ""
        		+ "#questions tr {transition: .3s;}"
        		+ "#questions tr:nth-child(even){background-color: #f2f2f2; transition: .3s;}"
        		+ ""
        		+ "#questions tr:hover {background-color: #ddd;}"
        		+ ""
        		+ "#questions th {"
        		+ "  padding-top: 12px;"
        		+ "  padding-bottom: 12px;"
        		+ "	 text-transform: uppercase;"
        		+ "  text-align: center;"
        		+ "  background-color: rgb(0,30,255);"
        		+ "  color: white;"
        		+ "	 border: 3px solid #ddd;"
        		+ "} ";
        
        htmlTable += "body {"
        		+ "    display: flex;"
        		+ "	   flex-direction: column;"
        		+ "    justify-content: center;"
        		+ "    align-items: center;"
        		+ "    min-height: 100vh;"
        		+ "    background: linear-gradient(-90deg,  rgb(228, 228, 228) 0%, #3d3d3d 100%, #d8e6d2 100%);"
        		+ "}"
        		+ "a:link {"
        		+ "	 color: rgb(0,30,255);"
        		+ "  text-decoration: none;"
        		+ "}"
        		+ ""
        		+ "a:visited {"
        		+ "  text-decoration: none;"
        		+ "	 color: rgb(0,30,255);"
        		+ "}"
        		+ ""
        		+ "  .back {"
        		+ "    text-align: center;"
        		+ "    display: block;"
        		+ "    padding: 10px 0;"
        		+ "    margin-left: 20px;"
        		+ "    margin-top: 10px;"
        		+ "    width: 200px;"
        		+ "    font-size: 16px;"
        		+ "    background-color: rgb(0,30,255);"
        		+ "    color: white;"
        		+ "    border: none;"
        		+ "    border-radius: 10px;"
        		+ "    cursor: pointer;"
        		+ "    transition: background-color .5s;"
        		+ "  }"
        		+ ""
        		+ "  .back:hover{"
        		+ "      background-color: darkblue;"
        		+ " }"
        		+ "p {"
        		+ "		font-size: 32px;"
        		+ "		font-weight: bold;"
        		+ "}"
        		+ "</style>";
        
        htmlTable += "<table id='questions' border=\"1\">";
        
//        if(!rs.next()) {
//        	htmlTable += "<p> You have not add any Answer in StackOverClone! </p>";
//        	htmlTable += "<button class='back' onclick='history.back()'>Go Back</button>";
//        } else {
//        	//header row:
//            htmlTable +="<tr>";
//            for (int col=1; col <= colCount; col++)
//            {
//                htmlTable +="<th>";
//                htmlTable +=meta.getColumnLabel(col);
//                htmlTable +="</th>";
//            }
//            
//            if(link) {
//            	//creating question's link column
//                htmlTable +="<th>";
//                htmlTable +="Link to question";
//                htmlTable +="</th>";
//            }
//            
//            
//            htmlTable +="</tr>";
//            
//            //data rows:
//            while(rs.next()) {
//                
//                htmlTable +="<tr>";
//                // auction id
//                String id="";
//                
//                for (int col=1; col <= colCount; col++)
//                {
//                    Object value = rs.getObject(col);
//                    htmlTable +="<td>";
//                    if (value != null)
//                    {
//                        htmlTable +=value.toString();
//                        if(col==4) {
//                        	id = value.toString();
//                        }
//                    }
//                    htmlTable +="</td>";
//                }
//                if(link) {
//                	//creating question rows
//                    htmlTable +="<td>";
//                    String urlAuction = "<a style='font-weight:bold;' href=\"../DatabaseServlets/ShowQuestion?id="+id+"\">Go to Question</a>";
//                    htmlTable += urlAuction;
//                    htmlTable +="</td>";
//                }
//                htmlTable +="</tr>";
//            }
//            htmlTable +="</table><br>";
//            
//          //added back button
//            htmlTable += "<button class='back' onclick='history.back()'>Go Back</button>";
//        }
        
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
                    if(col==4) {
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
        
      //added back button
        htmlTable += "<button class='back' onclick='history.back()'>Go Back</button>";
        
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
