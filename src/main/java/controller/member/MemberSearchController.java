package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.HttpUtil;
import service.MemberService;

// ȸ�� ���� ã�⿡ ���õ� ���� �帧 ���� ��Ʈ�ѷ�

// ������ 1. ��ȿ�� üũ
// 2. üũ�� ���� �����Ͻ� �������� ���� �� ȸ������ ���� ��ȯ
// 3. ��������� ������� ������

public class MemberSearchController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ���� ����
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		// 1. ��ȿ�� üũ
		if(id.isEmpty()||email.isEmpty()) {
			
			request.setAttribute("error", "��� �׸��� �������� �����ֽñ� �ٶ��ϴ�!");
			HttpUtil.forward(request, response, "/member/MemberSearch.jsp");
			return;
		}
		
		// 2. �����Ͻ� ���� ȣ��, ȸ�� ��й�ȣ ��ȯ
		MemberService service = MemberService.getInstance();		
		String pwd = service.memberSearch(id,email);
		
		// 3. ������� ���� ��ܿ� ������
		
		if(pwd==null) {
			request.setAttribute("error", "�˻��� ������ �����ϴ�.");
			HttpUtil.forward(request, response, "/member/MemberSearch.jsp");
			
		}
		else {
			request.setAttribute("id", id);
			request.setAttribute("pwd", pwd);
			HttpUtil.forward(request, response, "/WEB-INF/result/member/MemberSearchResult.jsp");
		}
		
		
	}

}
