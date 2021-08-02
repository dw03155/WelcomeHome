package co.yedam.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CommentServlet") // java를 실행시켜주기 위해 servlet 필요
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글로 나오는 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset-utf-8");
		response.setCharacterEncoding("utf-8");

		// xml 데이터 만들기
		PrintWriter out = response.getWriter();
		String cmd = request.getParameter("cmd");

		if (cmd == null) {
			StringBuffer sb = new StringBuffer();
			sb.append("<result>");// xml 데이터 타입으로 넘기려고
			sb.append("<code>error</code>");
			sb.append("<data>");
			sb.append("cmd null");
			sb.append("</data>");
			sb.append("</result>");
			out.print(sb.toString());

		} else if (cmd.equals("selectAll")) {// select 조회
			List<HashMap<String, Object>> list = CommentDAO.getInstance().selectAll();
			StringBuffer sb = new StringBuffer();
			sb.append("<result>");// xml 데이터 타입으로 넘기려고
			sb.append("<code>success</code>");
			for (HashMap<String, Object> map : list) {
				sb.append("<data>");
				sb.append("<id>" + map.get("id") + "</id>");
				sb.append("<name>" + map.get("name") + "</name>");
				sb.append("<content>" + map.get("content") + "</content>");
				sb.append("</data>");
			}
			sb.append("</result>");
			out.print(sb.toString());

		} else if (cmd.equals("insert")) {// insert 입력
			CommentDAO.getInstance();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
