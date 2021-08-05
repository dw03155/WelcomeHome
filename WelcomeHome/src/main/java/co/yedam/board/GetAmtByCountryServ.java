package co.yedam.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

@WebServlet("/GetAmtByCountryServ")
public class GetAmtByCountryServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetAmtByCountryServ() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HashMap<String, Integer> map = CommentDAO.getInstance().getAmtByCountry();
		Gson gson = new GsonBuilder().create();

		JsonArray aryOut = new JsonArray(); // 바깥 배열 모양
		Set<String> set = map.keySet(); // key에 해당하는 것만 뽑아내기
		Iterator<String> iter = set.iterator(); // 반복자
		while (iter.hasNext()) {
			JsonArray aryIn = new JsonArray(); // 안쪽 배열 모양

			String key = iter.next();
			Integer val = map.get(key);

			aryIn.add(key); // [k],[k],[k]
			aryIn.add(val);// [v],[v],[v]

			aryOut.add(aryIn); // 배열 안에 배열
		}
		response.getWriter().print(gson.toJson(aryOut));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
