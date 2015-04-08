package org.qiugui.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.qiugui.service.IUserService;
import cn.edu.qiugui.service.User;
import cn.edu.qiugui.service.UserException_Exception;
import cn.edu.qiugui.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService port;
	private UserService userService;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		userService = new UserService();
		port = userService.getUserServicePort();
		if (method == null || "".equals(method)) {
			list(request, response);
		} else if ("add".equals(method)) {
			add(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		} else if ("login".equals(method)) {
			login(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User user = port.login(username, password);
			request.getSession().setAttribute("loginUser", user);
			response.sendRedirect("user.do");
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {

		try {
			WebUtil.addLicenseHeader(port, request);
			String username = request.getParameter("username");
			port.delete(username);
			response.sendRedirect("user.do");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UserException_Exception e) {
			 System.out.println(e.getMessage());
			 
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		
		WebUtil.addLicenseHeader(port, request);
		
		String username = request.getParameter("username");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");

		try {
			try {
				User user = new User();
				user.setUsername(username);
				user.setNickname(nickname);
				user.setPassword(password);
				port.add(user);
			} catch (UserException_Exception e) {
				System.out.println(e.getMessage());
			}
			response.sendRedirect("user.do");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("users", port.list());
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
