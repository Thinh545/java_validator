package hoami.java_validator.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hoami.java_validator.Validator.Validator;
import hoami.java_validator.Validator.ValidatorRegistry;
import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Core.Selector;
import hoami.java_validator.Validator.Messages.MessageFactory;

import static hoami.java_validator.Validator.Rules.StringRuleBuilder.stringRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;

/**
 * Servlet implementation class PasswordResultController
 */
public class PasswordResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordResultController() {
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
		final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{0,}$";
		//This matches the presence of at least one lowercase letter.
		//This matches the presence of at least one digit i.e. 0-9.
		//This matches the presence of at least one special character.
		//This matches the presence of at least one capital letter.
		
		String value = (String) request.getParameter("password");
		
		System.out.println("Entered servlet email result");
		PrintWriter pw = response.getWriter();
		MessageFactory.create();
		
		Validator passwordValidator = ValidatorRegistry.register("password", rules(stringRule("password").notEmpty().minLength(5).maxLength(20).matches(PASSWORD_PATTERN)));
		
		try {
			Selector userSelector = passwordValidator.validate_selector(value);
			String passwordResult = userSelector.select("password", String.class);
	        
			System.out.println("");
			System.out.println(passwordResult);
			pw.write("Your user data is valid: " + passwordResult);
		}
		catch(Exception e) {
			System.out.println("");
			ErrorManager errors = passwordValidator.validate_error_manager(value);
			e.printStackTrace();
			pw.write(errors.getResult());
		}
	}

}
