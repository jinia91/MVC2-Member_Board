package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// ��� ��Ʈ�ѷ��� ��ӹ޴� ��Ʈ�ѷ� �������̽���, ����Ʈ ��Ʈ�ѷ����� �ش� ��Ʈ�ѷ� ��ü�� �ҷ����� ���� �ο��ϴ� ����Ӽ�

public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
