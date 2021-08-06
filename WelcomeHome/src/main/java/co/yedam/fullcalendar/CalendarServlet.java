package co.yedam.fullcalendar;

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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalendarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		// xml 데이터 만들기
		PrintWriter out = response.getWriter();
		String cmd = request.getParameter("cmd");

		if (cmd == null) {
			out.println(errorXML("cmd null"));
			// json 데이터 처리하기
		} else if (cmd.equals("selectJson")) { // 전체 조회
			response.setContentType("text/json;charset=utf-8"); // 한글인식
			List<HashMap<String, Object>> list = CalendarDAO.getInstance().selectCalendar();
			Gson gson = new GsonBuilder().create();
			
			JsonArray outAry = new JsonArray(); // 바깥 배열 모양
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, Object> map = list.get(i); // map 타입
				JsonObject jObj = new JsonObject(); // 안쪽 object 모양
				jObj.addProperty("title", (String) map.get("title")); // object타입을 string타입으로 바꿔줌
				jObj.addProperty("start", (String) map.get("start"));
				jObj.addProperty("end", (String) map.get("end"));
				outAry.add(jObj);
			}

			response.getWriter().print(gson.toJson(outAry));

		} else if (cmd.equals("insertJson")) { // json데이터 입력
			response.setContentType("text/json;charset=utf-8");// 한글인식
			String title = request.getParameter("title");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			// comment 안에 name, content 답기
			Schedule schedule = new Schedule();
			schedule.setTitle(title);
			schedule.setStart(start);
			schedule.setEnd(end);
			// commentdao class의 instance를 만들고 comment 전송
			HashMap<String, Object> map = CalendarDAO.getInstance().insertCalendar(schedule);
			Gson gson = new GsonBuilder().create();
			out.println(gson.toJson(map));

		} else if (cmd.equals("deleteJson")) { // json데이터 수정
			response.setContentType("text/json;charset-utf-8");
			String title = request.getParameter("title");
			// commentdao class의 instance를 만들고 comment 전송
			HashMap<String, Object> map = CalendarDAO.getInstance().deleteCalendar(title);
			Gson gson = new GsonBuilder().create();
			out.println(gson.toJson(map));
		}
	}

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
