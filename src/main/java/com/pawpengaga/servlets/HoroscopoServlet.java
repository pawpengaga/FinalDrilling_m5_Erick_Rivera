package com.pawpengaga.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pawpengaga.modelo.Usuario;

/**
 * Servlet implementation class HoroscopoServlet
 */
@WebServlet("/horoscopo")
public class HoroscopoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoroscopoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Al llegar por get aqui, se hacen una serie de base de comprobaciones en torno a la session para mostrar el animal
		HttpSession session = request.getSession(false);
		Usuario current_user = (Usuario) session.getAttribute("current_user");

		String animal = current_user.getAnimal().toLowerCase();
		
		switch (animal) {
			case "NO POSEE":
				request.setAttribute("message", "Usted no posee un animal");
				request.setAttribute("animal", "none");
				break;
			case "dragón":
				request.setAttribute("animal", "dragon");
				break;
			default:
				request.setAttribute("animal", animal);
				break;
		}
		request.setAttribute("animalReal", current_user.getAnimal());
		request.getRequestDispatcher("yourHoroscope.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
