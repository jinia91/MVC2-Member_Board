package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;


// �� ���ݿ� ���Ǵ� static �޼ҵ� ��ƿ ��ɵ� ����;

public class HttpUtil {
	
	// 1. ȭ�� ��½� ������
	// 2. �߰�����
	
	// 1. ������
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path){
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("forward ����" + e);
		}
	}
	
}