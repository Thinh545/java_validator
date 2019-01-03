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
import hoami.java_validator.Validator.ValidatorRegistry;
import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Messages.EngMessagesFactory;
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
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		String username = (String) request.getParameter("username");
		int userage = -1;
		Date userdate = null;
		try {
			userage = Integer.parseInt(request.getParameter("userage"));
			userdate = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("userdate")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String useremail = (String) request.getParameter("useremail");
		String userpassword = (String) request.getParameter("userpassword");
		
		User user = new User(username, userage, userdate, useremail, userpassword);
		
		PrintWriter pw = response.getWriter();
		EngMessagesFactory.create();
		Date minDate = new Date(), maxDate = new Date();
		minDate.setTime(0);
		
		Validator emailValidator = rules(stringRule("email").notEmpty().minLength(5).matches(EMAIL_PATTERN).notMatches("admin@gmail.com")).build();
		
		Validator userValidator = ValidatorRegistry.register("user_validator", rules(
				stringRule("user.name").notEmpty().maxLength(50),
				cmpRule("user.age").notNull().greatherEqualsThan(1),
				cmpRule("user.birthday").notNull().range(minDate, maxDate),
				stringRule("user.email").notNull(),
				stringRule("user.password").notEmpty().notContains("!").notContains("&").notContains("*").minLength(8).maxLength(50)
		).include(emailValidator, "user"));
				
		try {
			Selector userSelector = userValidator.validate_selector(user);
			String userNameResult = userSelector.select("user.name", String.class);
			int userAgeResult = userSelector.select("user.age", Integer.class);
			Date userBirthdayResult = userSelector.select("user.birthday", Date.class);
			String userEmailResult = userSelector.select("user.email", String.class);
			String userPasswordResult = userSelector.select("user.password", String.class);
	        
			System.out.println("");
			System.out.println(userNameResult + " -- " + userAgeResult + " -- " + userBirthdayResult + " -- " + userEmailResult + " -- " + userPasswordResult);
			pw.println("Your user data is valid: " + userNameResult + " -- " + userAgeResult + " -- " + userBirthdayResult + " -- " + userEmailResult + " -- " + userPasswordResult);
		}
		catch(Exception e) {
			System.out.println("");
			ErrorManager errors = userValidator.validate_error_manager(user);
			e.printStackTrace();
			pw.write(errors.getResult());
		}
	}

}
