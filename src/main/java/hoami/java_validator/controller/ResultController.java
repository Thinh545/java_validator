package hoami.java_validator.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static hoami.java_validator.Validator.Rules.ComparableRuleBuilder.cmpRule;
import static hoami.java_validator.Validator.Rules.StringRuleBuilder.stringRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;

import hoami.java_validator.Validator.Validator;
import hoami.java_validator.Validator.ValidatorRegistry;
import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Messages.EngMessagesFactory;
import hoami.java_validator.Validator.Messages.MessageFactory;
/**
 * Servlet implementation class ResultController
 */
public class ResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultController() {
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
		
		String value = (String) request.getParameter("email");
		
		System.out.println("Entered servlet email result");
		PrintWriter pw = response.getWriter();
		EngMessagesFactory.create();
		
		Validator emailValidator = ValidatorRegistry.register("email", rules(stringRule("email").notEmpty().minLength(5).matches(EMAIL_PATTERN).notMatches("admin@gmail.com")));
		
		try {
			Selector userSelector = emailValidator.validate_selector(value);
			String emailResult = userSelector.select("email", String.class);
	        
			System.out.println("");
			System.out.println(emailResult);
			pw.println("<h1>Your user data is valid: " + emailResult + "</h1>");
		}
		catch(Exception e) {
			System.out.println("");
			ErrorManager errors = emailValidator.validate_error_manager(value);
			e.printStackTrace();
			pw.println("<h1>" + errors.getResult() + "</h1>");
		}
	}

}
