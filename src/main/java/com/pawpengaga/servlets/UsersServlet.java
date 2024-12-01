package com.pawpengaga.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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

			try {
				
				List<Usuario> searchResults = usuarioDAO.getAllUsuarios();

				if (searchResults.size() > 0) {
					request.setAttribute("usuarios", searchResults);
					request.getRequestDispatcher("listUsers.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "No tenemos registros de usuarios al parecer...");
					request.getRequestDispatcher("login.jsp").forward(request, response); // Broma cibernetica
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ("search".equals(accion)){
			String query = request.getParameter("myquery");
			
			try {
				List<Usuario> searchResults = usuarioDAO.getUserByName(query);
				
				if(searchResults.size() > 0){
					request.setAttribute("usuarios", searchResults);
				} else {
					request.setAttribute("message", "No se han encontrado resultados...");
				}

				request.getRequestDispatcher("searchUsers.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			response.sendRedirect("index.jsp");
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
				request.getRequestDispatcher("login.jsp").forward(request, response);
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

		} else if ("edit".equals(accion)) {

			int userId = Integer.parseInt(request.getParameter("userId"));
			String nombre = request.getParameter("nombre");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fecha_nacimiento"));

			String newPassword = request.getParameter("password");
			String currentPassword = request.getParameter("currentPassword");

			try {

				Usuario tempUser = new Usuario(nombre, username, email, fechaNacimiento, newPassword);
				tempUser.setId(userId);

				if (usuarioDAO.updateUser(tempUser, currentPassword)) {
					HttpSession session = request.getSession(false);
					session.removeAttribute("current_user");
					session.invalidate();
					request.setAttribute("message", "Usuario actualizado! Vuelva a iniciar sesion para ver los cambios.");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
				request.setAttribute("message", "Hubo un error al hacer la actualizacion... intente nuevamente.");
				request.getRequestDispatcher("edituser.jsp").forward(request, response);
				}
				
				} catch (Exception e) {
					e.printStackTrace();
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

		} else {
			request.setAttribute("message", "Ha ocurrido algo extraño...");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
