package air.test.login.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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
@WebServlet("/ShowQuestion")
public class ShowQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection _conn = null;
    String _contextPath;
    String _questionId;
    int _user_id;
    //Statement _stmt = null;  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @SuppressWarnings("deprecation")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            

	        LoginBean user = new LoginBean();
	        HttpSession session = request.getSession();
	        user =(LoginBean) session.getAttribute("user");
	        System.out.println(user.getUserId());
	        _user_id = user.getUserId();
    	
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            _contextPath=request.getContextPath();
            String databaseSchema = "pzw_stackoverflow";
            String username = "admin";
            String password = "admin";
            
            String question = request.getParameter("search_quest");
//            System.out.println(question);
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                _conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
                conn=_conn;
                stmt = conn.createStatement();
               
                String id = request.getParameter("id");
                _questionId=id;
                String resultString="";
            	if (stmt.execute("SELECT title, question_text, created_at, id, rating_sum, username, user_id FROM readable_questions WHERE question_id="+id)) {
                    rs = stmt.getResultSet();
                    resultString = getPostFromResultSet(rs);

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
                    stmt = conn.createStatement();
                	//if (stmt.execute("SELECT username, answer_text, created_at, rating_sum FROM readable_answers WHERE question_id="+id)) {
                    String sql = "SELECT username, answer_text, created_at, rating_sum, id, user_id FROM readable_answers WHERE question_id="
                	+id+" AND answer_text NOT LIKE 'deleted_perma_answer' ORDER BY rating_sum DESC";
                    if (stmt.execute(sql)) {
                        rs = stmt.getResultSet();
                        resultString += getAnswerBoxesFromResultSet(rs);
                        //resultString = getPostFromResultSet(rs);
                    }
                    resultString = wrapResult(resultString);
                }
                else resultString = "Wrong query type";
                
            	//System.out.println(resultString);
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

    	private static String wrapResult(String result) {
    		return CommonFunctions.getCommonStyleTag() + result + CommonFunctions.getCommonBackButtonQuestions();
    	}
        
    	private String getPostFromResultSet(ResultSet rs) throws SQLException
    	{
    		CommonFunctions.HtmlTagWrapper w = new CommonFunctions.HtmlTagWrapper();
    		if(!rs.next()) {
    			return "<p class=> There is no question </p>";
    		}
    		String title = rs.getObject(1).toString();
    		String text = rs.getObject(2).toString();
    		String date = rs.getObject(3).toString();
    		String id = rs.getObject(4).toString();
    		String rating_sum = rs.getObject(5).toString();
    		String username = rs.getObject(6).toString();
    		int author_id = Integer.parseInt(rs.getObject(7).toString());
    		
    		System.out.println(rating_sum);
    		System.out.println(username);
    		System.out.println(author_id);
    		String tags ="";
    		
    		String ratingContent = w.tag(rating_sum, "p");
        	String plus = CommonFunctions.getLinkR("<i class=\"far fa-thumbs-up\"></i>", _contextPath+"/RateQuestion?rating=1&questionId="+_questionId);
        	String x = CommonFunctions.getLinkR("<i class=\"fas fa-times\"></i>", _contextPath+"/RateQuestion?rating=0&questionId="+_questionId);
        	String minus = CommonFunctions.getLinkR("<i class=\"far fa-thumbs-down\"></i>", _contextPath+"/RateQuestion?rating=-1&questionId="+_questionId);
        	if(_user_id == 1 || author_id == _user_id) {
        		String update = CommonFunctions.getLinkR("<i class=\"fas fa-edit\"></i>", _contextPath+"/editQuestion.jsp?id="+id);
        		tags = CommonFunctions.getLinkR("<i class=\"fas fa-hashtag\"></i>", _contextPath+"/AddTags?qid="+id);
        		ratingContent+=plus+x+minus+update+tags;
        	} else {
        		ratingContent+=plus+x+minus;
        	}
        	ratingContent = w.tag(ratingContent,  "div", "ratingBoxAnswer");
        	String deleteButton ="";
//        	if(_user_id == 1 || author_id == _user_id)
//        		deleteButton = CommonFunctions.getLink("delete", _contextPath+"/deleteQuestionUser.jsp?id="+id);
        	//tags
        	if(_user_id == 1 || author_id == _user_id) {
        		deleteButton = CommonFunctions.getLink("delete", _contextPath+"/deleteQuestionUser.jsp?id="+id);
//        		tags = CommonFunctions.getLink("TAGS", _contextPath+"/AddTags?qid="+id);
        	}
        	{
        		TagHandler th = new TagHandler();
        		LinkedList<TagHandler.Tag> tagvals = th.getTagsByQuestion(id);
        		for (TagHandler.Tag tag : tagvals) {
        			tags+=" #"+tag.name;
				}
        	}
        	tags = "<div class=\"tags\">" + tags + "</div>";
        	
    		title = CommonFunctions.wrapTitle(title);
    		text = CommonFunctions.wrapText(text);
    		date = CommonFunctions.wrapDate(date);
    		username = CommonFunctions.wrapAuthor(username);
    		String addAnsw = CommonFunctions.getLink("add answer", _contextPath+"/AddAnswer?id="+id);
    		return "<div class=\"post\">" + ratingContent + date + title + text + username + tags + addAnsw + deleteButton + "</div>";
    	}
    	
        private String getHtmlTableFromResultSet(ResultSet rs) throws SQLException
        {
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();
//            String htmlTable = "<table border=\"1\">";
            
            String htmlTable = "";
            //String htmlTable = CommonFunctions.getCommonStyleTag();
            
            htmlTable += "<table id='questions' border=\"1\">";
            
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
                // auction id
                //String id="";
                
                for (int col=1; col <= colCount; col++)
                {
                    Object value = rs.getObject(col);
                    htmlTable +="<td>";
                    if (value != null)
                    {
                        htmlTable +=value.toString();
                        //if(col==1) {
                        	//id = value.toString();
                        //}
                    }
                    htmlTable +="</td>";
                }
                htmlTable +="</tr>";
            }
            htmlTable +="</table><br>";
            
          //added back button
            //htmlTable += "<button class='back' onclick='history.back()'>Go Back</button>";
            
            return htmlTable;
        }


        private String getCommentBoxesFromResultSet(ResultSet rs) throws SQLException {
        	//SELECT username, comment_text, created_at, rating_sum, id FROM readable_comments WHERE answer_id={$answerId}
        	CommonFunctions.HtmlTagWrapper w = new CommonFunctions.HtmlTagWrapper();
            
            String content = "";
            while(rs.next()) {
            	String commentBox = "";
            	String username = rs.getObject(1).toString();
            	String comment_text = rs.getObject(2).toString();
            	String date = rs.getObject(3).toString();
            	String rating = rs.getObject(4).toString();
            	String id = rs.getObject(5).toString();
            	int author_id = Integer.parseInt(rs.getObject(6).toString());
            	
            	String ratingContent = w.tag(rating, "p");
            	String plus = CommonFunctions.getLinkR("<i class=\"far fa-thumbs-up\"></i>", _contextPath+"/RateComment?rating=1&questionId="+_questionId+"&id="+id);
            	String x = CommonFunctions.getLinkR("<i class=\"fas fa-times\"></i>", _contextPath+"/RateComment?rating=0&questionId="+_questionId+"&id="+id);
            	String minus = CommonFunctions.getLinkR("<i class=\"far fa-thumbs-down\"></i>", _contextPath+"/RateComment?rating=-1&questionId="+_questionId+"&id="+id);
            	if(_user_id == 1 || author_id == _user_id) {
            		String update = CommonFunctions.getLinkR("<i class=\"fas fa-edit\"></i>", _contextPath+"/editComment.jsp?id="+id);
            		ratingContent+=plus+x+minus+update;
            	} else {
            		ratingContent+=plus+x+minus;
            	}
            	
            	commentBox += w.tag(ratingContent,  "div", "ratingBoxComment");
            	commentBox += w.tag(w.tag(date, "p"),  "div", "dateBoxComment");            	
            	commentBox += w.tag(w.tag(username, "p"),  "div", "usernameBoxComment");
            	commentBox += w.tag(w.tag(comment_text, "p"),  "div", "contentComment");
            	if(_user_id == 1 || author_id == _user_id)
            		commentBox += CommonFunctions.getLink("delete", _contextPath+"/deleteComment.jsp?id="+id);
            	commentBox = w.tag(commentBox, "div", "commentContener");
            	content+=commentBox;
    		}
            content = w.tag(content, "div", "allCommentsContener");
            return content;
        }
        
        private String getCommentBoxesFromId(String answerId) {
        	Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            //String databaseSchema = "pzw_stackoverflow";
            //String username = "admin";
            //String password = "admin";
            String commentContener="";
            CommonFunctions.HtmlTagWrapper w = new CommonFunctions.HtmlTagWrapper();
            
            
            try {
                //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseSchema+"?serverTimezone=UTC",username,password);
            	conn=_conn;
            	stmt = conn.createStatement();
               
                String resultString="";
                String sql = "SELECT username, comment_text, created_at, rating_sum, id, user_id FROM readable_comments WHERE answer_id="
            	+answerId+" AND comment_text NOT LIKE 'deleted_perma_comment' ORDER BY rating_sum DESC";
            	if (stmt.execute(sql)) {
                    rs = stmt.getResultSet();
                    resultString = getCommentBoxesFromResultSet(rs);
                }
                else resultString = "Wrong query type";
                
            	commentContener=resultString;
               
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
                commentContener="Internal error";
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
        	return w.tag(commentContener, "div", "commentContener");
        }
        
        private String getAnswerBoxesFromResultSet(ResultSet rs) throws SQLException
        {
        	//SELECT username, answer_text, created_at, rating_sum, id FROM readable_answers WHERE question_id= {$id}
        	CommonFunctions.HtmlTagWrapper w = new CommonFunctions.HtmlTagWrapper();
            
            String content = "";
            LinkedList<String[]> commentlessAnswers = new LinkedList<>();
            while(rs.next()) {
            	String answerBox = "";
            	String username = rs.getObject(1).toString();
            	String answer_text = rs.getObject(2).toString();
            	String date = rs.getObject(3).toString();
            	String rating = rs.getObject(4).toString();
            	String id = rs.getObject(5).toString();
            	int author_id = Integer.parseInt(rs.getObject(6).toString());
            	String ratingContent = w.tag(rating, "p");
            	String plus = CommonFunctions.getLinkR("<i class=\"far fa-thumbs-up\"></i>", _contextPath+"/RateAnswer?rating=1&questionId="+_questionId+"&id="+id);
            	String x = CommonFunctions.getLinkR("<i class=\"fas fa-times\"></i>", _contextPath+"/RateAnswer?rating=0&questionId="+_questionId+"&id="+id);
            	String minus = CommonFunctions.getLinkR("<i class=\"far fa-thumbs-down\"></i>", _contextPath+"/RateAnswer?rating=-1&questionId="+_questionId+"&id="+id);
            	if(_user_id == 1 || author_id == _user_id) {
            		String update = CommonFunctions.getLinkR("<i class=\"fas fa-edit\"></i>", _contextPath+"/editAnswer.jsp?id="+id);
            		ratingContent+=plus+x+minus+update;
            	} else {
            		ratingContent+=plus+x+minus;
            	}
            	//ratingContent+=upvote+downvote;?
            	answerBox += w.tag(ratingContent,  "div", "ratingBoxAnswer");
            	answerBox += w.tag(w.tag(date, "p"),  "div", "dateBoxAnswer");
            	answerBox += w.tag(w.tag(username, "p"),  "div", "usernameBoxAnswer");
            	answerBox += w.tag(w.tag(answer_text, "p"),  "div", "contentAnswer");
            	answerBox += CommonFunctions.getLink("add comment", _contextPath+"/AddComment?id="+id);
            	if(_user_id == 1 || author_id == _user_id)
            		answerBox += CommonFunctions.getLink("delete", _contextPath+"/deleteAnswer.jsp?id="+id);
            	//To prevent deadlock, next half of the work has to be done 
            	//answerBox += getCommentBoxesFromId(id);
            	//answerBox = w.tag(answerBox, "div", "answerContener");
            	//System.out.println(id+":  "+username+answer_text+date+rating);
            	//System.out.println(answerBox);
            	//content+=answerBox;
            	String[] commentlessAnswer = {id, answerBox};
            	commentlessAnswers.add(commentlessAnswer);
            }
            for (String[] commentlessAnswer : commentlessAnswers) {
    			String id = commentlessAnswer[0];
    			String answerBox = commentlessAnswer[1];
            	answerBox += getCommentBoxesFromId(id);
            	answerBox = w.tag(answerBox, "div", "answerContener");
            	content+=answerBox;
    		}
            content = w.tag(content, "div", "allAnswersContener");
            return content;
        }

    	/**
    	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    	 */
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		doGet(request, response);
    	}

}