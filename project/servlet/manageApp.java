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
			//������������ɾ��
			skim_del_App(request,response);
		}

	}
	
	private void skim_del_App(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("account_del");
		// �������
		checkadmin app = new checkadmin(account, null, null, null, null);

		// ���ø��º���
		int flag = manageAppImpl.deleteApp(app);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			//skimApp(request, response);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"������Ա��ɾ����\");window.location.href='./manageApp?action=skim';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"������Աɾ��ʧ�ܣ����Ժ����ԣ�\");window.location='manage_approval/skim_app.jsp';</script>");
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
		// ��ȡ����ֵ
		String account = request.getParameter("retrieve_account");

		// System.out.println(account);

		List<checkadmin> list = new ArrayList<checkadmin>();
		list = manageAppImpl.getMessage(account);

		if (!list.isEmpty()) {
			// ����������
			String part = list.get(0).getDepartMent();
			switch (part) {
			case "create":
				part = "����";
				break;
			case "check":
				part = "����";
				break;
			case "gain":
				part = "�ɹ�";
				break;
			case "reward":
				part = "����";
				break;
			default:
				break;
			}
			if (action.equals("retrieve"))
				dot="_mod";
			else
				dot="_del";
			// ����Ϣ����ػ�Session
			session.setAttribute("app_account"+dot, list.get(0).getAccount());
			session.setAttribute("app_name"+dot, list.get(0).getName());
			session.setAttribute("app_sex"+dot, list.get(0).getSex());
			session.setAttribute("app_part"+dot, part);
			// ҳ���ض���
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
						"<script langua=\"JavaScript\" type=\"text/javascript\">;alert(\"�˻������ڣ�\");window.location='manage_approval/modify_app.jsp';</script>");
			} else
				response.getWriter().write(
						"<script langua=\"JavaScript\" type=\"text/javascript\">;alert(\"�˻������ڣ�\");window.location='manage_approval/delete_app.jsp';</script>");
		}

	}

	private void addApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("app_account");
		String name = request.getParameter("app_name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		String sex = request.getParameter("app_sex");
		sex = new String(sex.getBytes("iso-8859-1"), "utf-8");
		String part = request.getParameter("app_part");
		// part = new String(part.getBytes("iso-8859-1"), "utf-8");

		System.out.println(name);
		// �ж���û����ͬ���˺�
		List<checkadmin> list = new ArrayList<checkadmin>();
		list = manageAppImpl.getMessage(account);
		if (list.isEmpty()) {
			// ����checkadmin����
			checkadmin app = new checkadmin(account, account, name, sex, part);
			// System.out.println(sAdmin.getAdmin_sex());

			// ���ø��º���
			int flag = manageAppImpl.addApp(app);
			// ���ݷ���ֵ��ʾ�û�
			// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
			if (flag > 0) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"��ӳɹ���\");window.location='manage_approval/add_app.jsp';</script>");
			}
			// false��ʾʧ�ܣ�����������ҳ�治��
			else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���ʧ�ܣ�\");window.location='manage_approval/add_app.jsp';</script>");
			}
		} else {
			// ������ͻ
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"�˻��Ѵ��ڣ�\");window.location='manage_approval/add_app.jsp';</script>");

		}

	}

	private void updateApp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// ��ȡ����ֵ
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
		// �������
		checkadmin app = new checkadmin(account, null, name, sex, part);

		// System.out.println(name);

		// ���ø��º���
		int flag = manageAppImpl.updateApp(app);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			session.setAttribute("app_name_mod", name);
			session.setAttribute("app_sex_mod", sex);
			session.setAttribute("app_part_mod", part);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"������Ա�����޸ĳɹ���\");window.location='manage_approval/modify_app.jsp';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"������Ա�����޸�ʧ�ܣ����Ժ����ԣ�\");window.location='manage_approval/modify_app.jsp';</script>");
		}
	}

	private void deleteApp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageAppDaoImpl manageAppImpl = new manageAppDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("app_account_del");
		// �������
		checkadmin app = new checkadmin(account, null, null, null, null);

		// ���ø��º���
		int flag = manageAppImpl.deleteApp(app);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			session.setAttribute("app_account_del", "");
			session.setAttribute("app_name_del", "");
			session.setAttribute("app_sex_del", "");
			session.setAttribute("app_part_del", "");
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"������Աɾ���ɹ���\");window.location='manage_approval/delete_app.jsp';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"������Աɾ��ʧ�ܣ����Ժ����ԣ�\");window.location='manage_approval/delete_app.jsp';</script>");
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
