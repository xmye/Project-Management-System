package com.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.ApplyPersonDaoImpl;
import com.project.model.applyperson;

/**
 * Servlet implementation class applyPerson
 */
public class applyPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applyPerson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=new String();
		action=request.getParameter("action");
		if(action.equals("skim")){
			skimAlyPson(request,response);
		}else if (action.equals("delete")) {
			deleteReg(request, response);
		}
	}

	private void skimAlyPson(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		ApplyPersonDaoImpl applyPersonDaoImpl=new ApplyPersonDaoImpl();
		List<applyperson> list=new ArrayList<applyperson>();
		list=applyPersonDaoImpl.getAllMessage();
		
		HttpSession session=request.getSession();
		session.setAttribute("list", list);
		
		response.sendRedirect("query_sys/register_inquire.jsp");
		
	}
	
	private void deleteReg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setCharacterEncoding("gbk");
		ApplyPersonDaoImpl applyPersonDaoImpl=new ApplyPersonDaoImpl();
		// ��ȡ����ֵ
		String account = request.getParameter("account");
		// ���ø���
		int flag = applyPersonDaoImpl.deleteReg(account);
		// ���ݷ���ֵ��ʾ�û�
		// true��ʾ�ɹ�������������ˢ�±�ҳ��Ϣ
		if (flag > 0) {
			//skimSys(request, response);
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"ע����Ա��ɾ����\");window.location.href='./getApplyPerson?action=skim';</script>");
		}
		// false��ʾʧ�ܣ�����������ҳ�治��
		else {
			response.getWriter().write(
					"<script language=\"JavaScript\" type=\"text/javascript\">alert(\"ע����Աɾ��ʧ�ܣ����Ժ����ԣ�\");window.location='query_sys/register_inquire.jsp';</script>");
		}

	}

}
