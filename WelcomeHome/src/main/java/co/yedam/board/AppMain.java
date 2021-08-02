package co.yedam.board;

public class AppMain {
	public static void main(String[] args) {

		// DB 연결 확인
//		DAO dao = new DAO();
//		dao.connect();

		CommentDAO dao = CommentDAO.getInstance();
		dao.selectAll();
	}
}
