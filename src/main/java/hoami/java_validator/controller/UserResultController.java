package hoami.java_validator.controller;

import static hoami.java_validator.Validator.Rules.StringRuleBuilder.stringRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hoami.java_validator.Validator.Validator;
import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Messages.MessageFactory;
import hoami.java_validator.Validator.Util.User;

import static hoami.java_validator.Validator.Rules.ComparableRuleBuilder.cmpRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;
import static hoami.java_validator.Validator.Rules.StringRuleBuilder.stringRule;

/**
 * Servlet implementation class UserResultController
 */
public class UserResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserResultController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String username = (String) request.getParameter("username");
		int userage = Integer.parseInt(request.getParameter("userage"));
		Date userdate = null;
		try {
			userdate = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("userdate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String useremail = (String) request.getParameter("useremail");
		String userpassword = (String) request.getParameter("userpassword");
		
		User user = new User(username, userage, userdate, useremail, userpassword);
		
		PrintWriter pw = response.getWriter();
		MessageFactory.create();
		Date minDate = new Date(), maxDate = new Date();
		minDate.setTime(0);
		
		Validator userValidator = rules(
				stringRule("username").notEmpty().maxLength(50),
				cmpRule("userage").notNull().greatherEqualsThan(1),
				cmpRule("userbirthday").notNull().range(minDate, maxDate),
				stringRule("useremail").notEmpty().notStartsWith("@").contains("@gmail.com").endsWith("com"),
				stringRule("userpassword").notEmpty().notContains("!").notContains("&").notContains("*").minLength(8).maxLength(50)
		).build();
		
		try {
			Selector userSelector = userValidator.validate_selector(username, userage, userdate, useremail, userpassword);
			String userNameResult = userSelector.select("username", String.class);
			int userAgeResult = userSelector.select("userage", Integer.class);
			Date userBirthdayResult = userSelector.select("userbirthday", Date.class);
			String userEmailResult = userSelector.select("useremail", String.class);
			String userPasswordResult = userSelector.select("userpassword", String.class);
	        
			System.out.println("");
			System.out.println(userNameResult + " -- " + userAgeResult + " -- " + userBirthdayResult + " -- " + userEmailResult + " -- " + userPasswordResult);
			pw.write("<h1>Your user data is valid</h1>");
		}
		catch(Exception e) {
			System.out.println("");
			ErrorManager errors = userValidator.validate_error_manager(username, userage, userdate, useremail, userpassword);
			e.printStackTrace();
			pw.write("<h1>" + errors.getResult() + "</h1>");
		}
	}

}
