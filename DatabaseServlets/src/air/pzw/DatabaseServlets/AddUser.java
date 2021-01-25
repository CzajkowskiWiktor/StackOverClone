package air.pzw.DatabaseServlets;

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
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
        String databaseSchema = "pzw_lab";
        String username = "wiktor";
        String password = "Student12";
        
        int id = Integer.parseInt(request.getParameter("id"));
        String f_name = request.getParameter("name");
        String l_name = request.getParameter("last_name");
        
        System.out.println(id);
        System.out.println(f_name);
        System.out.println(l_name);
        
        
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
           
            String resultString="";
            
            String sql = "INSERT INTO user (id, first_name, last_name) VALUES ("+id+", \""+ f_name+"\", \""+ l_name+"\");";

            if (stmt.executeUpdate(sql)>0) {
            	resultString = "User added";
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
	     String htmlTable = "<table border=\"1\">";
	     
	     //header row:
	     htmlTable +="<tr>";
	     for (int col=1; col <= colCount; col++)
	     {
	         htmlTable +="<th>";
	         htmlTable +=meta.getColumnLabel(col);
	         htmlTable +="</th>";
	     }
	     htmlTable +="</tr>";
	     
	     //data rows:
	     while(rs.next()) {
	         
	         htmlTable +="<tr>";
	         
	         for (int col=1; col <= colCount; col++)
	         {
	             Object value = rs.getObject(col);
	             htmlTable +="<td>";
	             if (value != null)
	             {
	                 htmlTable +=value.toString();
	             }
	             htmlTable +="</td>";
	         }
	         htmlTable +="</tr>";
	     }
	     htmlTable +="</table>";
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
