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
 * Servlet implementation class ShowUsers
 */
@WebServlet("/ShowUsers")
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String databaseSchema = "pzw_lab";
        String username = "wiktor";
        String password = "Student12";
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            stmt = conn.createStatement();
           
            String resultString="";
            if (stmt.execute("SELECT * FROM user")) {
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
        String htmlTable = "<table border=\"1\">";
        
        //header row:
        htmlTable +="<tr>";
        for (int col=1; col <= colCount; col++)
        {
            htmlTable +="<th>";
            htmlTable +=meta.getColumnLabel(col);
            htmlTable +="</th>";
        }
        //creating user's auctions column
        htmlTable +="<th>";
        htmlTable +="Show user's auctions";
        htmlTable +="</th>";
        
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
            //creating user's auctions rows
            htmlTable +="<td>";
            String urlAuction = "<a href=\"../DatabaseServlets/ShowAuctions?id="+id+"\">Show Auctions</a>";
            htmlTable += urlAuction;
            htmlTable +="</td>";
            
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
