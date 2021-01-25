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
import air.test.login.web.TagHandler.Tag;

/**
 * Servlet implementation class AddAnswer
 */
@WebServlet("/CreateTag")
public class CreateTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String _contextPath="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTag() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		_contextPath=request.getContextPath();
		TagHandler th = new TagHandler();
		String page = "";
		LinkedList<Tag> tags = th.getAllTags();
		for (TagHandler.Tag tag : tags) {
			page+=" #"+tag.name;
		}
		page = "<p>"+page+"</p>";
		page += getSimpleForm();
		page = CommonFunctions.getCommonStyleTag() + page + CommonFunctions.getCommonBackButtonMenu();
		response.getWriter().append(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid = CommonFunctions.getUserIdFromRequest(request);
		if(uid==1) {
			_contextPath=request.getContextPath();
			TagHandler th = new TagHandler();
			String tagName = request.getParameter("tag_name");
			th.insertTag(tagName);
			th.releaseError();
			this.doGet(request, response);
		}
		else
			response.getWriter().append("<p>Please login as admin</p>"+ CommonFunctions.getCommonBackButton());
	}
	
	private String getSimpleForm() {
		String form = "<input type=\"text\" name=\"tag_name\">";
		return "<form action=\""+_contextPath+"/CreateTag\" method=\"post\">" + form + "<input type=\"submit\" value=\"Submit\"></form>";
	}
}
