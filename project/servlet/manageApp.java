package com.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.manageAppDaoImpl;
import com.project.model.checkadmin;

/**
 * Servlet implementation class manageApp
 *
 */
public class manageApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageApp() {
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
		if (action.equals("add")) {
			addApp(request, response);
		} else if (action.equals("retrieve")) {
			getMessage(request, response);
		} else if (action.equals("retrieve_d")) {
			getMessage(request, response);
		} else if (action.equals("update")) {
			updateApp(request, response);
		} else if (action.equals("delete")) {
			deleteApp(request, response);
		}else if(action.equals("skim")){
			skimApp(request,response);
		}else if(action.equals("skim_delete")){
			//在浏览界面进行删除
			skim_del_App(request,response);
		}

	}
	
	private void skim_del_App(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// 获取表单的值
		String account = request.getParameter("account_del");
		// 存入对象
		checkadmin app = new checkadmin(account, null, null, null, null);

		// 调用更新函数
		int flag = manageAppImpl.deleteApp(app);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			//skimApp(request, response);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"审批人员已删除！\");window.location.href='./manageApp?action=skim';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"审批人员删除失败，请稍候重试！\");window.location='manage_approval/skim_app.jsp';</script>");
		}
		
	}

	private void getMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");
		String dot=new String();
		String action = new String();
		action = request.getParameter("action");

		HttpSession session = request.getSession();

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// 获取表单的值
		String account = request.getParameter("retrieve_account");

		// System.out.println(account);

		List<checkadmin> list = new ArrayList<checkadmin>();
		list = manageAppImpl.getMessage(account);

		if (!list.isEmpty()) {
			// 处理部门名称
			String part = list.get(0).getDepartMent();
			switch (part) {
			case "create":
				part = "立项";
				break;
			case "check":
				part = "验收";
				break;
			case "gain":
				part = "成果";
				break;
			case "reward":
				part = "奖励";
				break;
			default:
				break;
			}
			if (action.equals("retrieve"))
				dot="_mod";
			else
				dot="_del";
			// 将信息存入回话Session
			session.setAttribute("app_account"+dot, list.get(0).getAccount());
			session.setAttribute("app_name"+dot, list.get(0).getName());
			session.setAttribute("app_sex"+dot, list.get(0).getSex());
			session.setAttribute("app_part"+dot, part);
			// 页面重定向
			if (action.equals("retrieve"))
				response.sendRedirect("manage_approval/modify_app.jsp");
			else
				response.sendRedirect("manage_approval/delete_app.jsp");
		} else {
			if (action.equals("retrieve")) {
				session.setAttribute("app_account"+dot, "");
				session.setAttribute("app_name"+dot, "");
				session.setAttribute("app_sex"+dot, "");
				session.setAttribute("app_part"+dot, "");
				response.getWriter().write(
						"<script langua=\"JavaScript\" type=\"text/javascript\">;alert(\"账户不存在！\");window.location='manage_approval/modify_app.jsp';</script>");
			} else
				response.getWriter().write(
						"<script langua=\"JavaScript\" type=\"text/javascript\">;alert(\"账户不存在！\");window.location='manage_approval/delete_app.jsp';</script>");
		}

	}

	private void addApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// 获取表单的值
		String account = request.getParameter("app_account");
		String name = request.getParameter("app_name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("app_sex");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String part = request.getParameter("app_part");
		// part = new String(part.getBytes("iso-8859-1"), "utf-8");

		System.out.println(name);
		// 判断有没有相同的账号
		List<checkadmin> list = new ArrayList<checkadmin>();
		list = manageAppImpl.getMessage(account);
		if (list.isEmpty()) {
			// 存入checkadmin对象
			checkadmin app = new checkadmin(account, account, name, sex, part);
			// System.out.println(sAdmin.getAdmin_sex());

			// 调用更新函数
			int flag = manageAppImpl.addApp(app);
			// 根据返回值提示用户
			// true表示成功，给出弹窗，刷新本页信息
			if (flag > 0) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加成功！\");window.location='manage_approval/add_app.jsp';</script>");
			}
			// false表示失败，给出弹窗，页面不变
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加失败！\");window.location='manage_approval/add_app.jsp';</script>");
			}
		} else {
			// 主键冲突
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"账户已存在！\");window.location='manage_approval/add_app.jsp';</script>");

		}

	}

	private void updateApp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// 获取表单的值
		String account = request.getParameter("app_account_mod");
		String name = request.getParameter("app_name_mod");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("app_sex_mod");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String part = request.getParameter("choose_part_mod");
		if(part=="")
			part=request.getParameter("app_part_mod");

		// String part = request.getParameter("choose_part");
		// part = new String(part.getBytes("iso-8859-1"), "utf-8");
		// 存入对象
		checkadmin app = new checkadmin(account, null, name, sex, part);

		// System.out.println(name);

		// 调用更新函数
		int flag = manageAppImpl.updateApp(app);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			session.setAttribute("app_name_mod", name);
			session.setAttribute("app_sex_mod", sex);
			session.setAttribute("app_part_mod", part);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"审批人员资料修改成功！\");window.location='manage_approval/modify_app.jsp';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"审批人员资料修改失败，请稍候重试！\");window.location='manage_approval/modify_app.jsp';</script>");
		}
	}

	private void deleteApp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// 获取表单的值
		String account = request.getParameter("app_account_del");
		// 存入对象
		checkadmin app = new checkadmin(account, null, null, null, null);

		// 调用更新函数
		int flag = manageAppImpl.deleteApp(app);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			session.setAttribute("app_account_del", "");
			session.setAttribute("app_name_del", "");
			session.setAttribute("app_sex_del", "");
			session.setAttribute("app_part_del", "");
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"审批人员删除成功！\");window.location='manage_approval/delete_app.jsp';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"审批人员删除失败，请稍候重试！\");window.location='manage_approval/delete_app.jsp';</script>");
		}
	}
	
	private void skimApp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		manageAppDaoImpl manageAppImpl=new manageAppDaoImpl();
		List<checkadmin> list=new ArrayList<checkadmin>();
		list=manageAppImpl.getAllMessage();
		
		HttpSession session=request.getSession();
		session.setAttribute("app_list", list);
		
		response.sendRedirect("manage_approval/skim_app.jsp");
		
	}
	
}
