package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.MemberDTO;
import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

// ȸ�� ������ �������� �帧 ���� ��Ʈ�ѷ�

// ������ 1. ���� ���̵� ����� ��ȿ�� üũ
// 2. üũ�� ���� �����Ͻ� �������� ���� �� ȸ������ ���� ��ȯ
// 3. ��������� ������� ������

public class MemberSearchAllController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ���� ����
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sessionID");	
		String pwd = request.getParameter("pwd");
		
		// 1. ��ȿ�� üũ
		if(id.isEmpty()||pwd.isEmpty()) {
			
			request.setAttribute("error", "���� ����! �α����� �ٽ����ּ���!");
			HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
			return;
		}
		
		// ��ȿ��üũ(2) ��й�ȣ ��ġ ����
		MemberService service = MemberService.getInstance();	
		int pwdResult = service.MemberLogin(id, pwd);
		
		if(pwdResult==0) { 	// ��й�ȣ Ʋ���� ���Է� �䱸		
			request.setAttribute("error", "��й�ȣ�� Ʋ�Ƚ��ϴ�! �ٽ� �Է����ּ���!");	
			HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
			return;
		}
	
		
		// 2. ��й�ȣ ��ġ�� ȸ������ ��� �ҷ����� ���� ȣ��

		MemberDTO member = service.memberSearchAll(id, pwd);
		
		// 3. ��ȯ�� ��û������ �Ǿ ������� ������
		
		request.setAttribute("member", member);
		HttpUtil.forward(request, response, "/member/MemberUpdate.jsp");
		
		
	}

}
