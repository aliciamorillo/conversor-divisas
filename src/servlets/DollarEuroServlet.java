package servlets;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.ConversorTextoNumero;
import factoria.Factoria;


@WebServlet("/DollarEuroServlet")
public class DollarEuroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		procesarPeticion(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		procesarPeticion(request, response);
	}

	protected void procesarPeticion(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DecimalFormat formato = new DecimalFormat("#.00");
		
		System.out.println("SEVLET - CONVERSOR DOLLAR A EURO");
		
		String cantidad = request.getParameter("cajaCantidad");
		double cantidadNumero = ConversorTextoNumero.conversorTextoNumero(cantidad);
		
		double resultado = Factoria.obtenerCalculadoraDivisas().convertirDollarsEuros(cantidadNumero);
		System.out.println("\n- LA CANTIDAD EN DOLARES -> " + resultado);
		
		String resultadoCorto = formato.format(resultado);
		
		request.setAttribute("resultado", resultadoCorto);
		
		response.sendRedirect("index.jsp?resultado=" + cantidad + " Dolares son: " +
				resultadoCorto + " Euros.");			
	}

}
