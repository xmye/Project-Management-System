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
			// ���������ɾ��
			skim_delete_Sys(request, response);
		}

	}

	private void skim_delete_Sys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");
		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("account_del");
		// �������
		admin sAdmin = new admin(account, null, null, null, null);

		// ���ø��º���
		int flag = manageSysImpl.deleteSys(sAdmin);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			//skimSys(request, response);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"����Ա��ɾ����\");window.location.href='./manageSys?action=skim';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"����Աɾ��ʧ�ܣ����Ժ����ԣ�\");window.location='./manage_sys/skim_sys.jsp';</script>");
		}
	}

	protected void addSys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("admin_account");
		String name = request.getParameter("admin_name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("admin_sex");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String phone = request.getParameter("admin_phone");
		// �ж���û����ͬ���˺�
		List<admin> list = new ArrayList<admin>();
		list = manageSysImpl.getMessage(account);
		if (list.isEmpty()) {
			// ����admin����
			admin sAdmin = new admin(account, account, name, sex, phone);
			// System.out.println(sAdmin.getAdmin_sex());

			// ���ø��º���
			int flag = manageSysImpl.addSys(sAdmin);
			// ���ݷ���ֵ��ʾ�û�
			// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
			if (flag > 0) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"��ӳɹ���\");window.location='manage_sys/add_sys.jsp';</script>");
			}
			// false��ʾʧ�ܣ�����������ҳ�治��
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���ʧ�ܣ�\");window.location='manage_sys/add_sys.jsp';</script>");
			}
		} else {
			// ������ͻ
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"�˻��Ѵ��ڣ�\");window.location='manage_sys/add_sys.jsp';</script>");

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
		// ��ȡ����ֵ
		String account = request.getParameter("retrieve_account");

		List<admin> list = new ArrayList<admin>();
		list = manageSysImpl.getMessage(account);
		if (!list.isEmpty()) {
			if (action.equals("retrieve"))
				dot = "mod";
			else
				dot = "del";
			// ����Ϣ����ػ�Session
			session.setAttribute("sys_account_" + dot, list.get(0).getAdmin_account());
			session.setAttribute("sys_name_" + dot, list.get(0).getAdmin_name());
			session.setAttribute("sys_sex_" + dot, list.get(0).getAdmin_sex());
			session.setAttribute("sys_phone_" + dot, list.get(0).getAdmin_phone());
			// request.setAttribute("admin_name", list.get(0).getAdmin_name());
			// ҳ���ض���
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
						"<script language=\"JavaScript\" type=\"text/javascript\">;alert(\"�˻������ڣ�\");window.location='manage_sys/modify_sys.jsp';</script>");
			} else
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">;alert(\"�˻������ڣ�\");window.location='manage_sys/delete_sys.jsp';</script>");
		}

	}

	private void update_sys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("sys_account_mod");
		String name = request.getParameter("sys_name_mod");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("sys_sex_mod");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String phone = request.getParameter("sys_phone_mod");
		// �������
		admin sAdmin = new admin(account, null, name, sex, phone);

		System.out.println(name);

		// ���ø��º���
		int flag = manageSysImpl.updateSys(sAdmin);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			session.setAttribute("sys_name_mod", name);
			session.setAttribute("sys_sex_mod", sex);
			session.setAttribute("sys_phone_mod", phone);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���������޸ĳɹ���\");window.location='manage_sys/modify_sys.jsp';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���������޸�ʧ�ܣ����Ժ����ԣ�\");window.location='manage_sys/modify_sys.jsp';</script>");
		}

	}

	private void deleteSys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageSysDaoImpl manageSysImpl = new manageSysDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("sys_account_del");
		if (account.equals("111111")) {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"�ʺŴ���\");window.location='manage_sys/delete_sys.jsp';</script>");
		} else {
			// �������
			admin sAdmin = new admin(account, null, null, null, null);

			// ���ø��º���
			int flag = manageSysImpl.deleteSys(sAdmin);
			// ���ݷ���ֵ��ʾ�û�
			// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
			if (flag > 0) {
				session.setAttribute("sys_account_del", "");
				session.setAttribute("sys_name_del", "");
				session.setAttribute("sys_sex_del", "");
				session.setAttribute("sys_phone_del", "");
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"����Աɾ���ɹ���\");window.location='manage_sys/delete_sys.jsp';</script>");
			}
			// false��ʾʧ�ܣ�����������ҳ�治��
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"����Աɾ��ʧ�ܣ����Ժ����ԣ�\");window.location='manage_sys/delete_sys.jsp';</script>");
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
