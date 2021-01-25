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
@WebServlet("/SearchByTag")
public class SearchByTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String _contextPath="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByTag() {
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
			String text="#"+tag.name;
			String adress = request.getContextPath() + "/FilterByTag?tag_id="+tag.id;
			page+=CommonFunctions.getLink(text, adress);
		}
		page = "<div class=\"tag_links\">"+page+"</div>";
		page = CommonFunctions.getCommonStyleTag() + page + CommonFunctions.getCommonBackButton();
		response.getWriter().append(page);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private String getSimpleForm() {
		String form = "<input type=\"text\" name=\"tag_name\">";
		return "<form action=\""+_contextPath+"/CreateTag\" method=\"post\">" + form + "<input type=\"submit\" value=\"Submit\"></form>";
	}
}
