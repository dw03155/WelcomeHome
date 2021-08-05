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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		response.setContentType("text/xml;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// xml 데이터 만들기
		PrintWriter out = response.getWriter();
		String cmd = request.getParameter("cmd");

		if (cmd == null) {
			out.println(errorXML("cmd null"));
			// json 데이터 처리하기
		} else if (cmd.equals("selectJson")) {
			response.setContentType("text/json;charset=utf-8");//한글인식
			List<HashMap<String, Object>> list = CommentDAO.getInstance().selectAll();

			Gson gson = new GsonBuilder().create();
			gson.toJson(list);
			String json = gson.toJson(list);
			out.println(json);

		} else if (cmd.equals("selectAll")) {// 전체 조회
			try {
//				Integer.parseInt("a"); // error 확인을 위해 발생시켜봄
				List<HashMap<String, Object>> list = CommentDAO.getInstance().selectAll();
				StringBuffer sb = new StringBuffer();
				sb.append("<result>");// xml 데이터 타입으로 넘기려고
				sb.append("<code>success</code>");
				sb.append("<data>");
				for (HashMap<String, Object> map : list) {
					sb.append("<row>");
					sb.append("<id>" + map.get("id") + "</id>");
					sb.append("<name>" + map.get("name") + "</name>");
					sb.append("<content>" + map.get("content") + "</content>");
					sb.append("</row>");
				}
				sb.append("</data>");
				sb.append("</result>");
				out.print(sb.toString());
			} catch (Exception e) {
				out.println(errorXML(e.getMessage()));
			}
		} else if (cmd.equals("insert")) {// 한건입력
			try {
				String name = request.getParameter("name");
				String content = request.getParameter("content");
				// comment 안에 name, content 답기
				Comment comment = new Comment();
				comment.setName(name);
				comment.setContent(content);
				// commentdao class의 instance를 만들고 comment 전송
				HashMap<String, Object> map = CommentDAO.getInstance().insert(comment);
				out.println(dataXML(map));
			} catch (Exception e) {
				out.println(errorXML(e.getMessage()));
			}
		} else if (cmd.equals("insertJson")) { // json데이터 입력
			response.setContentType("text/json;charset=utf-8");//한글인식
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			// comment 안에 name, content 답기
			Comment comment = new Comment();
			comment.setName(name);
			comment.setContent(content);
			// commentdao class의 instance를 만들고 comment 전송
			HashMap<String, Object> map = CommentDAO.getInstance().insert(comment);
			Gson gson = new GsonBuilder().create();
			out.println(gson.toJson(map));

		} else if (cmd.equals("update")) {// 수정
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String content = request.getParameter("content");

			Comment comment = new Comment();
			comment.setId(id);
			comment.setName(name);
			comment.setContent(content);

			HashMap<String, Object> map = CommentDAO.getInstance().update(comment);
			out.println(dataXML(map));

		} else if (cmd.equals("updateJson")) { // json데이터 수정
			response.setContentType("text/json;charset-utf-8");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String content = request.getParameter("content");
			// comment 안에 name, content 답기
			Comment comment = new Comment();
			comment.setId(id);
			comment.setName(name);
			comment.setContent(content);
			// commentdao class의 instance를 만들고 comment 전송
			HashMap<String, Object> map = CommentDAO.getInstance().update(comment);
			Gson gson = new GsonBuilder().create();
			out.println(gson.toJson(map));

		} else if (cmd.equals("delete")) {// 삭제
			String id = request.getParameter("id");
			HashMap<String, Object> map = CommentDAO.getInstance().delete(id);
			out.println(dataXML(map));
			
		} else if (cmd.equals("deleteJson")) { // json데이터 수정
			response.setContentType("text/json;charset-utf-8");
			String id = request.getParameter("id");
			// commentdao class의 instance를 만들고 comment 전송
			HashMap<String, Object> map = CommentDAO.getInstance().delete(id);
			Gson gson = new GsonBuilder().create();
			out.println(gson.toJson(map));
		}
	}// end of DG

	private String dataXML(HashMap<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>success</code>");
		sb.append("<data>");
		sb.append("<id>" + map.get("id") + "</id>");
		sb.append("<name>" + map.get("name") + "</name>");
		sb.append("<content>" + map.get("content") + "</content>");
		sb.append("</data>");
		sb.append("</result>");

		return sb.toString();
	}// end of DXML

	private String errorXML(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<result>");
		sb.append("<code>error</code>");
		sb.append("<data>" + msg + "</data>");
		sb.append("</result>");

		return sb.toString();
	}// end of EXML

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
