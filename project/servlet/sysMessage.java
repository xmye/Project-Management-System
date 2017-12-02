package com.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.AdminDaoImpl;
import com.project.model.admin;

/**
 * Servlet implementation class sysMessage
 */
public class sysMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sysMessage() {
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
		if (action.equals("update")) {
			updateSys(request, response);
		} else if (action.equals("update_pwd")) {
			update_pwd(request, response);
		}

	}

	protected void showMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("gbk");
		// ��ûػ�Session
		HttpSession session = request.getSession();
		// ����account���admin��Ϣ
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();

		// ��Session�еõ�account
		 String account=(String) session.getAttribute("account");
		// ע���滻account

		List<admin> list = adminDaoImpl.getMessage(account);
		// ����Ϣ����ػ�Session
		session.setAttribute("admin_account", list.get(0).getAdmin_account());
		session.setAttribute("admin_password", list.get(0).getAdmin_pwd());
		session.setAttribute("admin_name", list.get(0).getAdmin_name());
		session.setAttribute("admin_sex", list.get(0).getAdmin_sex());
		session.setAttribute("admin_phone", list.get(0).getAdmin_phone());

		// request.setAttribute("admin_name", list.get(0).getAdmin_name());
		// ҳ���ض���
		response.sendRedirect("privacy_sys/sys_message.jsp");
		// ҳ��ת����ͣ����Servlet��ַ
		// request.getRequestDispatcher("privacy_super/admin_message.jsp").forward(request,response);

	}

	private void updateSys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("admin_account");
		String name = request.getParameter("admin_name");
		String sex = request.getParameter("admin_sex");
		String phone = request.getParameter("admin_phone");
		
		// String account=(String) session.getAttribute("account");

		// ʹ��account�����滻����

		admin sAdmin = new admin(account, null, name, sex, phone);
		// ���ø��º���
		int flag = adminDaoImpl.updateAdmin(sAdmin);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			session.setAttribute("admin_name", name);
			session.setAttribute("admin_sex", sex);
			session.setAttribute("admin_phone", phone);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���������޸ĳɹ���\");window.location='privacy_sys/sys_message.jsp';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���������޸�ʧ�ܣ����Ժ����ԣ�\");window.location='privacy_super/admin_message.jsp';</script>");
		}

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("admin_account");
		String name = request.getParameter("admin_name");
		String sex = request.getParameter("admin_sex");
		String phone = request.getParameter("admin_phone");

		//ʹ��account�����滻
		admin sAdmin = new admin(account, null, name, sex, phone);
		// ���ø��º���
		int flag = adminDaoImpl.updateAdmin(sAdmin);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			session.setAttribute("admin_name", name);
			session.setAttribute("admin_sex", sex);
			session.setAttribute("admin_phone", phone);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���������޸ĳɹ���\");window.location='privacy_super/admin_message.jsp';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���������޸�ʧ�ܣ����Ժ����ԣ�\");window.location='privacy_super/admin_message.jsp';</script>");
		}
	}

	private void update_pwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		response.setCharacterEncoding("gbk");
		AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
		// ��ȡ����ֵ
		String old_pwd = request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd");
		// String cofirm_pwd = request.getParameter("confirm_pwd");;
		List<admin> slist = new ArrayList<admin>();
		
		//��ȡ��account��ֵ
		String account=new String();
		account=(String) session.getAttribute("account");
		
		//ʹ��account�滻
		slist = adminDaoImpl.getMessage(account);
		if (slist.get(0).getAdmin_pwd().equals(old_pwd)) {
			admin sAdmin = new admin(account, new_pwd, null, null, null);
			// ���ø��º���
			int flag = adminDaoImpl.updateAdminPwd(sAdmin);
			// ���ݷ���ֵ��ʾ�û�
			// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
			if (flag > 0) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"�����޸ĳɹ���\");alert(\"���ص�½���棡\");window.location='./Exit';</script>");

			}
			// false��ʾʧ�ܣ�����������ҳ�治��
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"�����޸�ʧ�ܣ�\");window.location='privacy_sys/sys_pwd.jsp';</script>");
			}
		} else {
			// �������ĺ��
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"ԭ�������\");window.location='privacy_sys/sys_pwd.jsp';</script>");
		}

	}
}

