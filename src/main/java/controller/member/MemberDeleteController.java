package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.HttpUtil;
import service.MemberService;
	

//ȸ������ ������ ������ �帧�� �ٷ�� ��Ʈ�ѷ�

//1. ���� ����
//2. ��й�ȣ ��ȿ�� üũ
//3. delete �������� �ش� ���� ������ ��� ��ȯ
//4. ������� ��� ����

	

public class MemberDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. �������� ���̵� ��й�ȣ ����
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");
		String pwd = request.getParameter("pwd");
		
		// 2. ��ȿ�� üũ
		if(id.isEmpty()||pwd.isEmpty()) {
					
		request.setAttribute("error", "���� ����! �α����� �ٽ����ּ���!");
		HttpUtil.forward(request, response, "/member/MemberDelete.jsp");
		return;
		}
		
		// ��ȿ��üũ(2) ��й�ȣ ��ġ ����
		MemberService service = MemberService.getInstance();	
		int pwdResult = service.MemberLogin(id, pwd);
				
		if(pwdResult==0) { 	// ��й�ȣ Ʋ���� ���Է� �䱸		
			request.setAttribute("error", "��й�ȣ�� Ʋ�Ƚ��ϴ�! �ٽ� �Է����ּ���!");	
			HttpUtil.forward(request, response, "/member/MemberDelete.jsp");
			return;
		}
			
		
		// ���� ��ü �޼ҵ� ȣ��
		service.MemberDelete(id,pwd);
		
		// 4. ������� ������
		request.setAttribute("id", id);
		session.invalidate();
		HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberDeleteOutput.jsp");
				
	}

}
