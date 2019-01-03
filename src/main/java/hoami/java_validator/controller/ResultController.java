package hoami.java_validator.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static hoami.java_validator.Validator.Rules.StringRuleBuilder.stringRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;

import hoami.java_validator.Validator.Validator;
import hoami.java_validator.Validator.Core.ErrorManager;
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
		String value = (String) request.getParameter("email");
		
		System.out.println("Entered servlet email result");
		PrintWriter pw = response.getWriter();
		MessageFactory.create();
        Validator nameValidator = rules(stringRule("email").notEmpty().minLength(5).notStartsWith("@").notMatches("admin@gmail.com")).build();
        ErrorManager errors = nameValidator.validate_error_manager(value);
        System.out.println(errors.getResult());
        if(errors.getResult().length() > 0) {
        	 pw.write("<h1>" + errors.getResult() + "</h1>");
        }
        else {
        	 pw.write("<h1>Your email is legal</h1>");
        }
	}

}
