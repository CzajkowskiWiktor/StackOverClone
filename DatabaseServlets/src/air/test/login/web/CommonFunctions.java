package air.test.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import air.test.login.bean.LoginBean;


public class CommonFunctions {
	public static class HtmlTagWrapper{
		public HtmlTagWrapper() {
			super();
		}
		public String tag(String text, String tag) {
			return "<"+tag+">" + text + "</" + tag + ">";
		}
		public String tag(String text, String tag, String htmlClass) {
			return "<"+tag+" class=\""+ htmlClass +"\">" + text + "</" + tag + ">";
		}
	}
	
	public static String getLink(String text, String adress) {
		return"<a class='link' href=\""+ adress +"\">" + text + "</a>";
	}
	
	public static String getLinkR(String text, String adress) {
		return"<a class='rating' href=\""+ adress +"\">" + text + "</a>";
	}
	
	public static String getCommonBackButton() {
		return "<button class='back' onclick='history.back()'>Go Back</button>";
	}
	
	public static String getCommonBackButtonQuestions() {
		return "<div class='back_question'><a class=\"back\" href=\"../DatabaseServlets/ShowQuestions\">Go Back</a></div>";
	}
	
	public static String getCommonBackButtonMenu() {
		return "<div class='back_question'><a class=\"back\" href=\"../DatabaseServlets/adminMain.jsp\">Go Back</a></div>";
	}
	
	public static String wrapTitle(String title) {
		return "<div class='title' style=\"font-size: 30px;\"><p>" + title + "</p></div>";
		//return "<div class=\"title\"><p>" + title + "</p></div>";
	}
	
	public static String wrapDate(String date) {
		return "<div class='date' style=\"color:black; text-align:right; font-style: italic;\"><p>" + date + "</p></div>";
		//return "<div class=\"date\"><p>" + date + "</p></div>";
	}
	
	public static String wrapAuthor(String author) {
		return "<div class='date' style=\"color:black; text-align:right; font-weight:bold;\"><p>" + "posted by: " + author + "</p></div>";
		//return "<div class=\"date\"><p>" + date + "</p></div>";
	}
	
	public static String wrapText(String text) {
		return "<div class=\"text\" style=\"font-size: 18px;\"><p>" + text + "</p></div>";
	}
	
	public static int getUserIdFromRequest(HttpServletRequest request) {
        LoginBean user = new LoginBean();
        HttpSession session = request.getSession();
        user =(LoginBean) session.getAttribute("user");
        System.out.println(user.getUserId());
        return user.getUserId();
        
	}
	
	public static String getCommonStyleTag() {
		return "<style> #questions {font-family: Arial, Helvetica, sans-serif;"
        		+ "  border-collapse: collapse;"
        		+ "  min-width: 30%;"
        		+ "	 box-shadow: 5px 5px 5px black;"
        		+ "}"
        
                + "#questions td, #customers th {"
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
        		+ "} "
        
        		+ "body {"
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
        		+ ".menu a, .menu a:visited, .menu a:link {"
        		+ "		color: white;"
        		+ "		text-transform: uppercase;"
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
        		+ ""
        		+ ".back_question a{"
        		+ " 	color: white;"
        		+ "}"
        		+ ""
        		+ ".post {"
        		+ "		background: white;"
        		+ " 	padding: 10px;"
        		+ " 	width: 450px;"
        		+ " 	border-radius: 10px;"
        		+ "}"
        		+ ""
        		+ " .link {"
        		+ "	   text-transform: uppercase;"
        		+ "	   text-decoration: none;"
        		+ "    text-align: center;"
        		+ "    display: block;"
        		+ "    padding: 10px 10px;"
        		+ "    margin-left: 0px;"
        		+ "    margin-top: 10px;"
        		+ "    width: 100px;"
        		+ "    color: white;"
        		+ "    font-size: 13px;"
        		+ "    background-color: rgb(0,30,255);"
        		+ "    border: none;"
        		+ "    border-radius: 10px;"
        		+ "    cursor: pointer;"
        		+ "    transition: background-color .5s;"
        		+ "  }"
        		+ ""
        		+ "  .link:hover{"
        		+ "      background-color: darkblue;"
        		+ " }"
        		+ ""
        		+ ".tags {"
        		+ " 	margin-top: 5px;"
        		+ "		margin-left: 0;"
        		+ "		text-align: right;"
        		+ "}"
        		+ ""
        		+ ".tag_links a {"
        		+ " 	color: white;"
        		+ "}"
        		+ ""
        		+ ".post a:link {"
        		+ "	 	color: white;"
        		+ "  	text-decoration: none;"
        		+ "}"
        		+ ""
        		+ ".post a:visited {"
        		+ "  	text-decoration: none;"
        		+ "	 	color: white;"
        		+ "}"
        		+ ""
        		+ ".ratingBoxAnswer, .ratingBoxComment {"
        		+ " 	color:black;"
        		+ "		font-weight: bold;"
        		+ "}"
        		+ ".dateBoxAnswer, .usernameBoxAnswer {"
        		+ "		text-align: right;"
        		+ "}"
        		+ ".dateBoxAnswer {"
        		+ "		margin-right: 10px;"
        		+ "}"
        		+ ""
        		+ ".usernameBoxAnswer, .usernameBoxComment {"
        		+ "		font-weight: bold;"
        		+ "}"
        		+ ""
        		+ ".usernameBoxAnswer {"
        		+ " 	margin-right: 10px;"
        		+ "}"
        		+ ".contentAnswer {"
        		+ "		font-size: 18px;"
        		+ "}"
        		+ ""
        		+ ".dateBoxComment {"
        		+ " 	font-size: 12px;"
        		+ "}"
        		+ ""
        		+ ".dateBoxAnswer {"
        		+ "		font-size: 15px;"
        		+ "}"
        		+ ".contentComment {"
        		+ "		font-style: italic;"
        		+ "}"
        		+ ""
        		+ ".answerContener {"
        		+ "		background: white;"
        		+ " 	padding: 10px;"
        		+ "		color: black;"
        		+ " 	width: 380px;"
        		+ "		margin-top: 12px;"
        		+ " 	border-radius: 10px;"
        		+ "}"
        		+ ".allCommentsContener {"
        		+ "text-align: right;"
        		+ " 	padding: 10px;"
        		+ "		color: black;"
        		+ " 	width: 250px;"
        		+ "		margin-top: 12px;"
        		+ "	 	transform: translateX(110px);"
        		+ " 	border-radius: 10px;"
        		+ "}"
        		+ ""
        		+ ".answerContener a:link {"
        		+ "	 color: white;"
        		+ "  text-decoration: none;"
        		+ "}"
        		+ ""
        		+ ".answerContener a:visited {"
        		+ "  text-decoration: none;"
        		+ "	 color: white;"
        		+ "}"
        		+ ""
        		+ ".ratingBoxComment, .ratingBoxAnswer {"
        		+ " 	display: flex;"
        		+ " 	flex-direction: row;"
        		+ "		align-items: center;"
        		+ "		justify-content: flex-end;"
        		+ "}"
        		+ ""
        		+ ".ratingBoxAnswer {"
        		+ "		justify-content: flex-start;"
        		+ "}"
        		+ " .ratingBoxComment .rating, .ratingBoxAnswer .rating {"
        		+ "	   text-transform: uppercase;"
        		+ "	   text-decoration: none;"
        		+ "    text-align: center;"
        		+ "	   font-weight: bold;"
        		+ "    display: block;"
        		+ "    padding: 5px 5px;"
        		+ "    margin-left: 0px;"
        		+ "    margin-top: 5px;"
        		+ "    width: 20px;"
        		+ "    color: black;"
        		+ "    font-size: 13px;"
        		+ "    background-color: none;"
        		+ "    border: none;"
        		+ "	   border-radius: 8px;"
        		+ "    cursor: pointer;"
        		+ "    transition: background-color .5s;"
        		+ "  }"
        		+ ""
        		+ ".ratingBoxComment  .rating:hover, .ratingBoxAnswer .rating:hover{"
        		+ "		 background-color: #ddd;"
        		+ " }"
        		+ ".ratingBoxComment a:link, .ratingBoxAnswer a:link {"
        		+ "	 	color: black;"
        		+ "  	text-decoration: none;"
        		+ "}"
        		+ ""
        		+ ".ratingBoxComment a:visited, .ratingBoxAnswer a:visited {"
        		+ "  	text-decoration: none;"
        		+ "	 	color: black;"
        		+ "}"
        		+ ".ratingBoxComment p, .ratingBoxAnswer p {"
        		+ "		margin-right: 15px;"
        		+ "}"
        		+ ""
        		+ ".checkbox {"
        		+ "		cursor: pointer;"
        		+ "}"
        		+ ""
        		+ ".formCheckbox {"
        		+ "		display: flex;"
        		+ "	    flex-direction: column;"
        		+ "     justify-content: center;"
        		+ "     align-items: left;"
        		+ "}"
        		+ "</style>"
        		+ "<script src=\"https://kit.fontawesome.com/2496dcef5a.js\" crossorigin=\"anonymous\"></script>";
	}
}
