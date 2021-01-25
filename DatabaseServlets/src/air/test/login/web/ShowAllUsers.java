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

/**
 * Servlet implementation class ShowAllUsers
 */
@WebServlet("/ShowAllUsers")
public class ShowAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllUsers() {
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
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
           
            String resultString="";
            if (stmt.execute("SELECT username, password FROM users WHERE username NOT LIKE 'delete user%';")) {
                rs = stmt.getResultSet();
                resultString = getHtmlTableFromResultSet(rs);
            }
            else
                resultString = "Wrong query type";
           
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
	
	private String getHtmlTableFromResultSet(ResultSet rs) throws SQLException
    {
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();
//        String htmlTable = "<table border=\"1\">";
        
        String htmlTable = "<style> #users {font-family: Arial, Helvetica, sans-serif;"
        		+ "  border-collapse: collapse;"
        		+ "  min-width: 30%;"
        		+ "	 box-shadow: 5px 5px 5px black;"
        		+ "}";
        
        htmlTable += "#users td, #customers th {"
        		+ "  border: 3px solid #ddd;"
        		+ "  padding: 15px;"
        		+ "}"
        		+ ""
        		+ "#users tr {transition: .3s;}"
        		+ "#users tr:nth-child(even){background-color: #f2f2f2; transition: .3s;}"
        		+ ""
        		+ "#users tr:hover {background-color: #ddd;}"
        		+ ""
        		+ "#users th {"
        		+ "  padding-top: 12px;"
        		+ "  padding-bottom: 12px;"
        		+ "	 text-transform: uppercase;"
        		+ "  text-align: center;"
        		+ "  background-color: rgb(0,30,255);"
        		+ "  color: white;"
        		+ "	 border: 3px solid #ddd;"
        		+ "}";
        
        htmlTable += "body {"
        		+ "    display: flex;"
        		+ "	   flex-direction: column;"
        		+ "    justify-content: center;"
        		+ "    align-items: center;"
        		+ "    height: 100vh;"
        		+ "    background: linear-gradient(-90deg,  rgb(228, 228, 228) 0%, #3d3d3d 100%, #d8e6d2 100%);"
        		+ "}"
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
        		+ "</style>";
        
        htmlTable += "<table id='users' border=\"1\">";
        
        //header row:
        htmlTable +="<tr>";
        for (int col=1; col <= colCount; col++)
        {
            htmlTable +="<th>";
            htmlTable +=meta.getColumnLabel(col);
            htmlTable +="</th>";
        }
        //creating user's auctions column
//        htmlTable +="<th>";
//        htmlTable +="Show user's auctions";
//        htmlTable +="</th>";
        
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
//            //creating user's auctions rows
//            htmlTable +="<td>";
//            String urlAuction = "<a href=\"../DatabaseServlets/ShowAuctions?id="+id+"\">Show Auctions</a>";
//            htmlTable += urlAuction;
//            htmlTable +="</td>";
            
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
