package air.test.login.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import air.test.login.bean.LoginBean;

/**
 * Servlet implementation class AddAnswer
 */
@WebServlet("/AddTags")
public class AddTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String _contextPath="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTags() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		_contextPath=request.getContextPath();
		String qid = request.getParameter("qid");
		String page = getTagsForm(qid);
		page = CommonFunctions.getCommonStyleTag() + page + CommonFunctions.getCommonBackButton();
		response.getWriter().append(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		_contextPath=request.getContextPath();
		String qid = request.getParameter("qid");
		String[] tag_id_array=request.getParameterValues("tag_id_array");
		TagHandler th = new TagHandler();
		th.addTagsToQuestion(tag_id_array, qid);
		if(th.isError())
		{
			response.sendRedirect("errorTag.jsp");
		} else {
//			th.releaseError();
			response.sendRedirect(_contextPath+"/ShowQuestion?id=" + qid);
		}
	}
	
	private String getTagsForm(String qid) {
		String form = "";
		String invisible= "<input type=\"hidden\" name=\"qid\" value=\""+ qid + "\">";
		TagHandler tagHandler = new TagHandler();
		LinkedList<TagHandler.Tag> tags = tagHandler.getAllTags();
		tagHandler.releaseError();
		
		for (TagHandler.Tag tag : tags) {
			form+="<p><input class='checkbox' type=\"checkbox\" id=\"tag-"+ tag.name +"\" name=\"tag_id_array\" value=\""+tag.id+"\">\r\n"
					+ "  <label for=\"tag-"+ tag.name +"\"> "+ tag.name +"</label></p>";
		}
		return "<form class='formCheckbox' action=\""+_contextPath+"/AddTags\" method=\"post\">" + invisible + form + "<input class='back' type=\"submit\" value=\"Submit\"></form>";
	}
}
