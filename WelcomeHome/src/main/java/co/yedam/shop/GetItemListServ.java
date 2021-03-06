package co.yedam.shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/GetItemListServ")
public class GetItemListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetItemListServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json;charset=utf-8"); //웹페이지 한글 표현
		response.setCharacterEncoding("utf-8");//데이터 한글 표현
		
		ShopDAO dao = new ShopDAO();
		List<Item> list = dao.getItemList();
		Gson gson = new GsonBuilder().create();
		
		response.getWriter().println(gson.toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
