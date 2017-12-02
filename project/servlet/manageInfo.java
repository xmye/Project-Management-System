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
		// 获取表单的值
		int info_id=Integer.parseInt(request.getParameter("info_id"));
	    String content=request.getParameter("content");
	    //获取当前时间为更新时间
		java.util.Date currenttime = new java.util.Date();
		Date update_time = new Date(currenttime.getYear(), currenttime.getMonth(), currenttime.getDay());
		// 存入对象
	    info information=new info(null, null, null, update_time, null, content, info_id);;

		// 调用更新函数
		int flag = manageInfoImpl.updateInfo(information);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			session.setAttribute("content", content);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"更新成功！\");window.location='manage_message/detail.jsp';</script>");
		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"更新失败，请稍候重试！\");window.location='manage_message/detail.jsp';</script>");
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
		// 获取表单的值
		List<info> list = new ArrayList<info>();
		list = manageInfoImpl.getMessage(id);
		// 将信息存入回话Session
		session.setAttribute("info_id", id);
		session.setAttribute("title", list.get(0).getTitle());
		session.setAttribute("publisher", list.get(0).getPublishPson());
		session.setAttribute("type", list.get(0).getType());
		session.setAttribute("content", list.get(0).getContent());
		// 页面重定向
		response.sendRedirect("manage_message/detail.jsp");

	}

	private void addInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("GBK");
		// request.setCharacterEncoding("GBK");
		manageInfoDaoImpl manageInfoImpl = new manageInfoDaoImpl();
		// 获取表单的值
		String title = request.getParameter("title");
		// title = new String(title.getBytes("iso-8859-1"), "utf-8");
		String publisher = request.getParameter("publisher");
		// publisher = new String(publisher.getBytes("iso-8859-1"), "utf-8");
		String type = request.getParameter("type");
		if (type.equals("通知通告"))
			type = "inform";
		if (type.equals("科技动态"))
			type = "technology";
		// type = new String(type.getBytes("iso-8859-1"), "utf-8");
		String content = request.getParameter("content");
		// content = new String(content.getBytes("iso-8859-1"), "utf-8");

		System.out.println(title);

		// 得到当前时间为发布时间
		java.util.Date currenttime = new java.util.Date();
		Date pub_time = new Date(currenttime.getYear(), currenttime.getMonth(), currenttime.getDay());
		Date update_time = new Date(currenttime.getYear(), currenttime.getMonth(), currenttime.getDay());
		///////////
		// 将信息存入对象
		info information = new info(title, publisher, pub_time, update_time, type, content, null);
		// 调用添加函数
		int flag = manageInfoImpl.addInfo(information);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			if (type.equals("inform")) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加成功！\");window.location='manage_message/announce_inform.jsp';</script>");
			} else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加成功！\");window.location='manage_message/announce_tech.jsp';</script>");

			}
		}
		// false表示失败，给出弹窗，页面不变
		else {
			if (type.equals("inform")) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加失败！\");window.location='manage_message/announce_inform.jsp';</script>");
			} else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"添加失败！\");window.location='manage_message/announce_tech.jsp';</script>");
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
		// 获取id
		int id = Integer.parseInt(request.getParameter("id"));
		// 调用函数
		List<info> information = new ArrayList<info>();
		information = manageInfoImpl.getMessage(id);

		int flag = manageInfoImpl.deleteInfo(id);
		// 根据返回值提示用户
		// true表示成功，给出弹窗，刷新本页信息
		if (flag > 0) {
			if (information.get(0).getType().equals("inform")) {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"通知通告已删除！\");window.location.href='./manageInfo?action=skim_inform';</script>");
				// skimInform(request, response);
			} else {
				response.getWriter().write(
						"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"科技动态已删除！\");window.location.href='./manageInfo?action=skim_tech';</script>");
				skimTech(request, response);
			}

		}
		// false表示失败，给出弹窗，页面不变
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"删除失败，请稍候重试！\");window.location='manage_approval/delete_app.jsp';</script>");
		}
	}
}
