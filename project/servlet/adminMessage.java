package com.project.servlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.AdminDaoImpl;
import com.project.model.admin;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class adminMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public adminMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = new String();
		action = request.getParameter("action");
		if(action.equals("show"))
			showMessage(request, response);
		if (action.equals("update"))
			update(request, response);
		if (action.equals("update_pwd"))
			update_pwd(request, response);

	}

	protected void showMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("gbk");
		// 获得回话Session
		HttpSession session = request.getSession();
		// 根据account获得admin信息
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		// 从Session中得到account
	    String account=(String) session.getAttribute("account");
		List<admin> list = adminDaoImpl.getMessage(account);
		System.out.println(account+list.size());
		// 将信息存入回话Session
		session.setAttribute("admin_account", list.get(0).getAdmin_account());
		session.setAttribute("admin_password", list.get(0).getAdmin_pwd());
		session.setAttribute("admin_name", list.get(0).getAdmin_name());
		session.setAttribute("admin_sex", list.get(0).getAdmin_sex());
		session.setAttribute("admin_phone", list.get(0).getAdmin_phone());
		
		// request.setAttribute("admin_name", list.get(0).getAdmin_name());
		// 页面重定向
		response.sendRedirect("privacy_super/admin_message.jsp");
		// 页面转发，停留在Servlet地址
		// request.getRequestDispatcher("privacy_super/admin_message.jsp").forward(request,response);

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		// 获取表单的值
		String account = request.getParameter("admin_account");
		String name = request.getParameter("admin_name");
		String sex = request.getParameter("admin_sex");
		String phone = request.getParameter("admin_phone");

		//使用account变量替换
		admin sAdmin = new admin(account, null, name, sex, phone);
		// 调用更新函数
		int flag = adminDaoImpl.updateAdmin(sAdmin);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			session.setAttribute("admin_name", name);
			session.setAttribute("admin_sex", sex);
			session.setAttribute("admin_phone", phone);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"个人资料修改成功！\");window.location='privacy_super/admin_message.jsp';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"个人资料修改失败，请稍候重试！\");window.location='privacy_super/admin_message.jsp';</script>");
		}
	}

	private void update_pwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		// 获取表单的值
		String old_pwd = request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd");
		// String cofirm_pwd = request.getParameter("confirm_pwd");;
		// 根据account获得admin信息
		// 从Session中得到account
		HttpSession session = request.getSession();
		String account=(String) session.getAttribute("account");
		List<admin> slist = new ArrayList<admin>();
		slist = adminDaoImpl.getMessage(account);
		if (slist.get(0).getAdmin_pwd().equals(old_pwd)) {
			admin sAdmin = new admin(account, new_pwd, null, null, null);
			// 调用更新函数
			int flag = adminDaoImpl.updateAdminPwd(sAdmin);
			// 根据返回值提示用户
			// true表示成功，给出弹窗，刷新本页信息
			if (flag > 0) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"密码修改成功！\");window.location='privacy_super/admin_pwd.jsp';</script>");

			}
			// false表示失败，给出弹窗，页面不变
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"密码修改失败！\");window.location='privacy_super/admin_pwd.jsp';</script>");
			}
		} else {
			// 密码错误的后果
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"原密码错误！\");window.location='privacy_super/admin_pwd.jsp';</script>");
		}
	}
}
