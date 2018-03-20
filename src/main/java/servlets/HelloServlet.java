package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String creditAmmountString = request.getParameter("creditAmmount");
		String creditPeriodString = request.getParameter("creditPeriod");
		String creditInterestRateString = request.getParameter("creditInterestRate");
		if (!creditAmmountString.matches("[0-9].+")||!creditPeriodString.matches("[0-9].+")||creditInterestRateString.matches("[^0-9]")) {
			response.sendRedirect("/");
		}
				
		double creditAmmount = Double.parseDouble(creditAmmountString);
		int creditPeriod = Integer.parseInt(creditPeriodString);
		double creditInterestRate = Double.parseDouble(creditInterestRateString);
		
			if(creditAmmount<=0 || creditPeriod<=0 || creditInterestRate<=0){
				response.sendRedirect("/");
			}
		
		String list = request.getParameter("list");
		
		PrintWriter out = response.getWriter();
						
		if(list.equals("rata_stala")){	
			response.setContentType("text/html");
			out.println( "<h3> Wybrano: " + "wnioskowana kwota kredytu: "+ creditAmmount + ""
					+ ", ilosc rat : " + creditPeriod*12 + " (w latach:  "+ creditPeriod +")"  + ", oprocentowanie: " + creditInterestRate + " % " 
				+ ", rodzaj rat: "+ request.getParameter("list") + "</h3>");
		
		double q = 1+(creditInterestRate/1200);
		double instalment = (creditAmmount * (Math.pow(q, (creditPeriod*12)))*(q-1)/(Math.pow(q, (creditPeriod*12))-1));
		
			out.println("<h3>Calkowita kwota do splaty to: "
				+ Math.round(instalment*creditPeriod*12*100)/100.0
				+ " PLN.</h3>");
					
		out.println("<table border='1'> <tr> <th>Nr raty</th><th>Kwota kapitalu</th> <th>Kwota odsetek</th><th>Calkowita kwota raty</th> </tr>");
		
		for (int i = 1;i<=(creditPeriod*12);i++){
			double interst = creditAmmount*creditInterestRate/1200;
			double creditCapital = instalment-interst;
			creditAmmount = creditAmmount - creditCapital;
			out.println(" <tr> <td align='center'>"+i+"</td> <td align='center'>"+Math.round(interst*100)/100.0+"</td> <td align='center'>"+Math.round(creditCapital*100)/100.0+"</td> <td align='center'>"+Math.round(instalment*100)/100.0+"</td> </tr>");
		}
		}
		
		else {	out.println( "<h3> Wybrano: " + "wnioskowana kwota kredytu: "+ creditAmmount + ""
				+ ", ilosc rat : " + creditPeriod*12 + " (w latach:  "+ creditPeriod +")"  + ", oprocentowanie: " + creditInterestRate + " % " 
				+ ", rodzaj rat: "+ request.getParameter("list") + "</h3>");
		
			double kapital = creditAmmount/(creditPeriod*12);
			double sum = 0;
			out.println("<table border='1'> <tr> <th>Nr raty</th><th>Kwota kapitalu</th> <th>Kwota odsetek</th><th>Calkowita kwota raty</th> </tr>");

		for (int i = 1;i<=(creditPeriod*12);i++){
			double odsetki= (creditAmmount-(i-1)*kapital)*creditInterestRate/(100*12);
			out.println(" <tr> <td align='center'>"+i+"</td> <td align='center'>"+Math.round(odsetki*100)/100.0+"</td> <td align='center'>"+Math.round(kapital*100)/100.0+"</td> <td align='center'>"+Math.round((kapital+odsetki)*100)/100.0+"</td> </tr>");
			sum+=(kapital+odsetki);
		}
		
		out.println("<h3>Calkowita kwota do splaty to: "
				+ Math.round(sum*100)/100.0
				+ " PLN.</h3>");
		}		
		}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}


}
