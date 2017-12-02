package com.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.manageSysDaoImpl;
import com.project.model.admin;

/**
 * Servlet implementation class manageSys
 */
public class manageSys extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageSys() {
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

		// System.out.println(action);

		if (action.equals("add")) {
			addSys(request, response);
		} else if (action.equals("retrieve")) {
			getMessage(request, response);
		} else if (action.equals("update")) {
			update_sys(request, response);
		} else if (action.equals("retrieve_d")) {
			getMessage(request, response);
		} else if (action.equals("delete")) {
			deleteSys(request, response);
		} else if (action.equals("skim")) {
			skimSys(request, response);
		} else if (action.equals("skim_delete")) {
			// 在浏览界面删除
			skim_delete_Sys(request, response);
		}

	}

	private void skim_delete_Sys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");
		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// 获取表单的值
		String account = request.getParameter("account_del");
		// 存入对象
		admin sAdmin = new admin(account, null, null, null, null);

		// 调用更新函数
		int flag = manageSysImpl.deleteSys(sAdmin);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			//skimSys(request, response);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"管理员已删除！\");window.location.href='./manageSys?action=skim';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"管理员删除失败，请稍候重试！\");window.location='./manage_sys/skim_sys.jsp';</script>");
		}
	}

	protected void addSys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// 获取表单的值
		String account = request.getParameter("admin_account");
		String name = request.getParameter("admin_name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("admin_sex");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String phone = request.getParameter("admin_phone");
		// 判断有没有相同的账号
		List<admin> list = new ArrayList<admin>();
		list = manageSysImpl.getMessage(account);
		if (list.isEmpty()) {
			// 存入admin对象
			admin sAdmin = new admin(account, account, name, sex, phone);
			// System.out.println(sAdmin.getAdmin_sex());

			// 调用更新函数
			int flag = manageSysImpl.addSys(sAdmin);
			// 根据返回值提示用户
			// true表示成功，给出弹窗，刷新本页信息
			if (flag > 0) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加成功！\");window.location='manage_sys/add_sys.jsp';</script>");
			}
			// false表示失败，给出弹窗，页面不变
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加失败！\");window.location='manage_sys/add_sys.jsp';</script>");
			}
		} else {
			// 主键冲突
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"账户已存在！\");window.location='manage_sys/add_sys.jsp';</script>");

		}

	}

	private void getMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String dot = new String();

		response.setCharacterEncoding("gbk");

		String action = new String();
		action = request.getParameter("action");

		HttpSession session = request.getSession();

		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// 获取表单的值
		String account = request.getParameter("retrieve_account");

		List<admin> list = new ArrayList<admin>();
		list = manageSysImpl.getMessage(account);
		if (!list.isEmpty()) {
			if (action.equals("retrieve"))
				dot = "mod";
			else
				dot = "del";
			// 将信息存入回话Session
			session.setAttribute("sys_account_" + dot, list.get(0).getAdmin_account());
			session.setAttribute("sys_name_" + dot, list.get(0).getAdmin_name());
			session.setAttribute("sys_sex_" + dot, list.get(0).getAdmin_sex());
			session.setAttribute("sys_phone_" + dot, list.get(0).getAdmin_phone());
			// request.setAttribute("admin_name", list.get(0).getAdmin_name());
			// 页面重定向
			if (action.equals("retrieve"))
				response.sendRedirect("manage_sys/modify_sys.jsp");
			else
				response.sendRedirect("manage_sys/delete_sys.jsp");
		} else {
			if (action.equals("retrieve")) {
				session.setAttribute("sys_account_" + dot, "");
				session.setAttribute("sys_name_" + dot, "");
				session.setAttribute("sys_sex_" + dot, "");
				session.setAttribute("sys_phone_" + dot, "");
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">;alert(\"账户不存在！\");window.location='manage_sys/modify_sys.jsp';</script>");
			} else
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">;alert(\"账户不存在！\");window.location='manage_sys/delete_sys.jsp';</script>");
		}

	}

	private void update_sys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// 获取表单的值
		String account = request.getParameter("sys_account_mod");
		String name = request.getParameter("sys_name_mod");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("sys_sex_mod");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String phone = request.getParameter("sys_phone_mod");
		// 存入对象
		admin sAdmin = new admin(account, null, name, sex, phone);

		System.out.println(name);

		// 调用更新函数
		int flag = manageSysImpl.updateSys(sAdmin);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			session.setAttribute("sys_name_mod", name);
			session.setAttribute("sys_sex_mod", sex);
			session.setAttribute("sys_phone_mod", phone);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"个人资料修改成功！\");window.location='manage_sys/modify_sys.jsp';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"个人资料修改失败，请稍候重试！\");window.location='manage_sys/modify_sys.jsp';</script>");
		}

	}

	private void deleteSys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// 获取表单的值
		String account = request.getParameter("sys_account_del");
		if (account.equals("111111")) {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"帐号错误！\");window.location='manage_sys/delete_sys.jsp';</script>");
		} else {
			// 存入对象
			admin sAdmin = new admin(account, null, null, null, null);

			// 调用更新函数
			int flag = manageSysImpl.deleteSys(sAdmin);
			// 根据返回值提示用户
			// true表示成功，给出弹窗，刷新本页信息
			if (flag > 0) {
				session.setAttribute("sys_account_del", "");
				session.setAttribute("sys_name_del", "");
				session.setAttribute("sys_sex_del", "");
				session.setAttribute("sys_phone_del", "");
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"管理员删除成功！\");window.location='manage_sys/delete_sys.jsp';</script>");
			}
			// false表示失败，给出弹窗，页面不变
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"管理员删除失败，请稍候重试！\");window.location='manage_sys/delete_sys.jsp';</script>");
			}
		}

	}

	private void skimSys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		List<admin> list = new ArrayList<admin>();
		list = manageSysImpl.getAllMessage();

		HttpSession session = request.getSession();
		session.setAttribute("list", list);

		response.sendRedirect("manage_sys/skim_sys.jsp");

	}

}
