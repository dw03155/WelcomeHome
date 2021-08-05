package co.yedam.board;

import java.io.IOException;
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

@WebServlet("/GetBoardServlet")
public class GetBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBoardServlet() {
        super();
    }
    //{"data":[[1],[2],[3],[4]...[n]]}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8"); //한글인식
		List<HashMap<String, Object>> list = CommentDAO.getInstance().selectAll();
		Gson gson = new GsonBuilder().create();
		
		JsonArray outAry = new JsonArray(); // 바깥 배열 모양
		for(int i=0; i<list.size();i++) {
			HashMap<String, Object> map = list.get(i); //map 타입
			JsonArray inAry = new JsonArray(); // 안쪽 배열 모양
			inAry.add((String) map.get("id")); //object타입을 string타입으로 바꿔줌
			inAry.add((String) map.get("name"));
			inAry.add((String) map.get("content"));
			
			outAry.add(inAry);
		}
		JsonObject obj = new JsonObject(); //object타입으로 만들기
		obj.add("data", outAry);
		response.getWriter().print(gson.toJson(obj));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
