package com.project.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.manageInfoDaoImpl;
import com.project.model.info;

/**
 * Servlet implementation class manageInfo
 */
public class manageInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageInfo() {
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
		if (action.equals("add_inform")) {
			addInfo(request, response);
		} else if (action.equals("add_tech")) {
			addInfo(request, response);
		} else if (action.equals("skim_inform")) {
			skimInform(request, response);
		} else if (action.equals("skim_tech")) {
			skimTech(request, response);
		} else if (action.equals("detail")) {
			showInformation(request, response);
		} else if (action.equals("inform")) {
			response.sendRedirect("manage_message/inform.jsp");
		} else if (action.equals("technology")) {
			response.sendRedirect("manage_message/technology.jsp");
		} else if (action.equals("delete")) {
			deleteInfo(request, response);
		} else if (action.equals("update")) {
			updateInfo(request, response);
		}
	}

	private void updateInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		// ��ȡ����ֵ
		int info_id=Integer.parseInt(request.getParameter("info_id"));
	    String content=request.getParameter("content");
	    //��ȡ��ǰʱ��Ϊ����ʱ��
		java.util.Date currenttime = new java.util.Date();
		Date update_time = new Date(currenttime.getYear(), currenttime.getMonth(), currenttime.getDay());
		// �������
	    info information=new info(null, null, null, update_time, null, content, info_id);;

		// ���ø��º���
		int flag = manageInfoImpl.updateInfo(information);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			session.setAttribute("content", content);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���³ɹ���\");window.location='manage_message/detail.jsp';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"����ʧ�ܣ����Ժ����ԣ�\");window.location='manage_message/detail.jsp';</script>");
		}
	}

	private void showInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("gbk");

		HttpSession session = request.getSession();

		int id = 0;
		id = Integer.parseInt(request.getParameter("id"));

		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		// ��ȡ����ֵ
		List<info> list = new ArrayList<info>();
		list = manageInfoImpl.getMessage(id);
		// ����Ϣ����ػ�Session
		session.setAttribute("info_id", id);
		session.setAttribute("title", list.get(0).getTitle());
		session.setAttribute("publisher", list.get(0).getPublishPson());
		session.setAttribute("type", list.get(0).getType());
		session.setAttribute("content", list.get(0).getContent());
		// ҳ���ض���
		response.sendRedirect("manage_message/detail.jsp");

	}

	private void addInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("GBK");
		// request.setCharacterEncoding("GBK");
		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		// ��ȡ����ֵ
		String title = request.getParameter("title");
		// title = new String(title.getBytes("iso-8859-1"), "utf-8");
		String publisher = request.getParameter("publisher");
		// publisher = new String(publisher.getBytes("iso-8859-1"), "utf-8");
		String type = request.getParameter("type");
		if (type.equals("֪ͨͨ��"))
			type = "inform";
		if (type.equals("�Ƽ���̬"))
			type = "technology";
		// type = new String(type.getBytes("iso-8859-1"), "utf-8");
		String content = request.getParameter("content");
		// content = new String(content.getBytes("iso-8859-1"), "utf-8");

		System.out.println(title);

		// �õ���ǰʱ��Ϊ����ʱ��
		java.util.Date currenttime = new java.util.Date();
		Date pub_time = new Date(currenttime.getYear(), currenttime.getMonth(), currenttime.getDay());
		Date update_time = new Date(currenttime.getYear(), currenttime.getMonth(), currenttime.getDay());
		///////////
		// ����Ϣ�������
		info information = new info(title, publisher, pub_time, update_time, type, content, null);
		// ������Ӻ���
		int flag = manageInfoImpl.addInfo(information);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			if (type.equals("inform")) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"��ӳɹ���\");window.location='manage_message/announce_inform.jsp';</script>");
			} else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"��ӳɹ���\");window.location='manage_message/announce_tech.jsp';</script>");

			}
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			if (type.equals("inform")) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���ʧ�ܣ�\");window.location='manage_message/announce_inform.jsp';</script>");
			} else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"���ʧ�ܣ�\");window.location='manage_message/announce_tech.jsp';</script>");
			}
		}
	}

	private void skimInform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		List<info> list = new ArrayList<info>();
		list = manageInfoImpl.getAllMessage("inform");

		HttpSession session = request.getSession();
		session.setAttribute("inform_list", list);

		response.sendRedirect("manage_message/inform.jsp");
	}

	private void skimTech(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		List<info> list = new ArrayList<info>();
		list = manageInfoImpl.getAllMessage("technology");

		HttpSession session = request.getSession();
		session.setAttribute("tech_list", list);

		response.sendRedirect("manage_message/technology.jsp");
	}

	private void deleteInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("gbk");

		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		// ��ȡid
		int id = Integer.parseInt(request.getParameter("id"));
		// ���ú���
		List<info> information = new ArrayList<info>();
		information = manageInfoImpl.getMessage(id);

		int flag = manageInfoImpl.deleteInfo(id);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			if (information.get(0).getType().equals("inform")) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"֪ͨͨ����ɾ����\");window.location.href='./manageInfo?action=skim_inform';</script>");
				// skimInform(request, response);
			} else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"�Ƽ���̬��ɾ����\");window.location.href='./manageInfo?action=skim_tech';</script>");
				skimTech(request, response);
			}

		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"ɾ��ʧ�ܣ����Ժ����ԣ�\");window.location='manage_approval/delete_app.jsp';</script>");
		}
	}
}
