package org.qiugui.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.qiugui.service.IUserService;
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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		userService = new UserService();
		port = userService.getUserServicePort();
		if (method==null || "".equals(method)){
			list(request,response);
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
