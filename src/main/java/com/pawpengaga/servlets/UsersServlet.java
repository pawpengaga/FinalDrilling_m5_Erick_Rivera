package com.pawpengaga.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		String accion = request.getParameter("accion");
		
		if ("list".equals(accion)) {
			// TODO: Aqui va la lista de usuarios
		} else if ("search".equals(accion)){
			// TODO: Aqui va la busqueda de usuarios
		}
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
					request.setAttribute("message", "Usuario registrado");
				} else {
				request.setAttribute("message", "Hubo un error al registrar el usuario...");
				}
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
			}
		
		} else if ("login".equals(accion)){

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {
				Usuario user = usuarioDAO.getUsuariobyCredentials(email, password);
				if (user != null) {
					HttpSession session = request.getSession();

					// Se manda el objeto completo de user
					session.setAttribute("current_user", user);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Usuario y Clave invalidos!!!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				request.setAttribute("message", "Error: " + e.getMessage());
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		} else if ("logout".equals(accion)){
			
			HttpSession session = request.getSession(false);
			session.removeAttribute("current_user");
			session.invalidate();
			request.setAttribute("message", "Sesion cerrada exitosamente...");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} else if ("delete".equals(accion)) {

			int userId = Integer.parseInt(request.getParameter("userId"));

			if (usuarioDAO.deleteUser(userId) == true) {
				HttpSession session = request.getSession(false);
				session.removeAttribute("current_user");
				session.invalidate();
				request.setAttribute("message", "Cuenta eliminada exitosamente, gracias por usar nuestra aplicación.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Ha ocurrido un problema con la eliminacion de su cuenta...");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		}

	}

}
