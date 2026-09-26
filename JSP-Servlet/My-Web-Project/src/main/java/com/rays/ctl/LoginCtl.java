package com.rays.ctl;

import java.io.IOException;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginCtl")
public class LoginCtl extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		HttpSession session = request.getSession();

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		try {
			bean = model.authenticate(loginId, password);

			if (bean != null) {
				session.setAttribute("user", bean);
				response.sendRedirect("WelcomeCtl");
				return;
			} else {
				request.setAttribute("errorMsg", "invalid login or password");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
		rd.forward(request, response);

	}

}
