package service;

import dao.MemberDAO;
import beans.MemberDTO;

// ȸ�������� ���� ������ ����ϴ� ��
// 1. ȸ������ ����

public class MemberService {

	// �̱��� ���� ����//
	// ���ǹ��� ��ü �������� �޸� �����ϰ� ���°� ���ϱ� ����, ��ü�� �ϳ� �����ϰ� �̸� ��Ȱ���ϴ� ������
	
	private static MemberService Memberserv = new MemberService();  // static���ΰ�ü �ϳ��� �޸𸮿� �÷���
		private MemberService() {}									// ���� static ���� �ʿ��� ������
	public static MemberService getInstance() {  		// �ܺο��� �� ���� �ּҰ��� �ʿ��Ҷ� �����ϴ� �޼ҵ�. �̸� ���� ��ü�������� static���� �����
		return Memberserv;   							// �̱��水ü �ϳ��� ��� ��Ȱ���� �� ����. ���� ��������� ��ȭ�� ������ �ɾ����°� �����Ƿ� 
														// �߰����� ��ü������ �ǹ̾���
	}
	
	// �̱��� ���� ����//
	
	public MemberDAO dao = MemberDAO.getInstance();					 // ȸ������ ������ �ʿ��� DAO ȣ��, ���ó� ���� �̱��� ����	
	
	
	
	//1. ȸ������ �޼ҵ�
	public void MemberJoin(MemberDTO member) {
		dao.memberJoin(member);						// ȸ�������� DB�� �����ϴ� DAO�� �޼ҵ� ȣ��
	}
	
}
