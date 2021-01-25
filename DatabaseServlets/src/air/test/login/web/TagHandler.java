package air.test.login.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;


public class TagHandler {
	public static class Tag{
		public String name;
		public String id;
	}
	
	private boolean errorState=false;
	private String errorMessage;
	
	private LinkedList<Tag> getTagsFromRS(ResultSet rs) throws SQLException {
		LinkedList<Tag> tags= new LinkedList<Tag>();
		while(rs.next()) {
			Tag tag = new Tag();
			tag.id = rs.getObject(1).toString();
			tag.name = rs.getObject(2).toString();
			tags.add(tag);
		}
		return tags;
	}

	@SuppressWarnings("deprecation")
	public LinkedList<Tag> getTagsByQuestion(String qid){
		LinkedList<Tag> tags = null;
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
           
            if (stmt.execute("SELECT tag_id, tag_name FROM tags_to_question WHERE question_id = "+qid+";")) {
                rs = stmt.getResultSet();
                tags= getTagsFromRS(rs);
            }
            else
    	    	errorState=true;
    	    	errorMessage="Wrong query type";
           
           
        } catch (Exception ex) {
	    	errorState=true;
	    	errorMessage="Exception: " + ex.getMessage();
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
		return tags;
	}

	@SuppressWarnings("deprecation")
	public LinkedList<Tag> getAllTags(){
		LinkedList<Tag> tags = null;
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
           
            if (stmt.execute("SELECT id, name FROM tags;")) {
                rs = stmt.getResultSet();
                tags= getTagsFromRS(rs);
            }
            else
    	    	errorState=true;
    	    	errorMessage="Wrong query type";
           
           
        } catch (Exception ex) {
	    	errorState=true;
	    	errorMessage="Exception: " + ex.getMessage();
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
		return tags;
	}
	
	@SuppressWarnings("deprecation")
	public void insertTag(String tagName) {
		Connection conn = null;
	    Statement stmt = null;
	    String databaseSchema = "pzw_stackoverflow";
	    String username = "admin";
	    String password = "admin";
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
	        stmt = conn.createStatement();
	        
	        String sql = "INSERT INTO tags(name) VALUES('"+ tagName +"');";
	        if(stmt.executeUpdate(sql)<1) {
		    	errorState=true;
		    	errorMessage="insertTagError: stmt.executeUpdate(sql)<1";
	        }
	       
	    } catch (Exception ex) {
	    	errorState=true;
	    	errorMessage="insertTagError: " + ex.getMessage();
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

	public void addTagsToQuestion(String[] tag_ids, String qid) {
		for (String tag_id : tag_ids) {
			addTagToQuestion(tag_id, qid);
		}
	}

	@SuppressWarnings("deprecation")
	private void addTagToQuestion(String tag_id, String qid) {
		Connection conn = null;
	    Statement stmt = null;
	    String databaseSchema = "pzw_stackoverflow";
	    String username = "admin";
	    String password = "admin";
	    String sql="";
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
	        stmt = conn.createStatement();
	        
	        sql = "INSERT INTO question_tags(tag_id, question_id) VALUES('"+ tag_id + "', '"+ qid +"');";
	        if(stmt.executeUpdate(sql)<1) {
		    	errorState=true;
		    	errorMessage="addTagToQuestionError: stmt.executeUpdate(sql)<1";
	        }
	       
	    } catch (Exception ex) {
	    	errorState=true;
	    	errorMessage=sql+"   |||   addTagToQuestionError: " + ex.getMessage();
//	    	System.out.println("blad dodawania");
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
	
	public boolean isError() {
		return errorState;
	}
	
	public String getErrorMessage() {
		return errorMessage.toString();
	}

	/**
	 * If there is an error, it throws it
	 */
	public void releaseError() {
		if(this.isError()) {
			String e = this.getErrorMessage();
			System.out.println(e);
			throw new Error(e);
		}
	}
}

