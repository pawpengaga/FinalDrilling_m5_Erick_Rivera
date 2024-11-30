package com.pawpengaga.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.pawpengaga.dao.UsuarioDAO;
import com.pawpengaga.modelo.Usuario;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
  
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersServlet() {
			super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		// La bifuracion de acciones
		if ("register".equals(accion)) {
			
			String nombre = request.getParameter("nombre");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));
			String password = request.getParameter("password");

			try {
				if (usuarioDAO.registrarUsuario(new Usuario(nombre, username, email, fechaNacimiento, password))) {
					request.setAttribute("mensaje", "Usuario registrado");
				} else {
				request.setAttribute("mensaje", "Error al registrar usuario...");
				}
					System.out.println(request.getAttribute("mensaje"));
				} catch (Exception e) {
					e.printStackTrace();
			}
		
		}

	}

}
