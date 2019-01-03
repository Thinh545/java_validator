package hoami.java_validator.controller;

import static hoami.java_validator.Validator.Rules.ComparableRuleBuilder.cmpRule;
import static hoami.java_validator.Validator.ValidatorBuilder.rules;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hoami.java_validator.Validator.Validator;
import hoami.java_validator.Validator.Core.ErrorManager;
import hoami.java_validator.Validator.Messages.MessageFactory;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

/**
 * Servlet implementation class DateResultController
 */
public class DateResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateResultController() {
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
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse((request.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		MessageFactory.create();
		
		Date min = new Date();
		Date max = new Date();
		min.setTime(0);
		
        Validator nameValidator = rules(cmpRule("date").notNull().lessThan(max).greatherThan(min)).build();
        ErrorManager errors = nameValidator.validate_error_manager(date);
        System.out.println(errors.getResult());
        if(errors.getResult().length() > 0) {
        	 pw.write("<h1>" + errors.getResult() + "</h1>");
        }
        else {
        	 pw.write("<h1>Your date is legal</h1>");
        }
	}

}
