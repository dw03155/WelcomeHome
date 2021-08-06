package co.yedam.fullcalendar.P;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.yedam.board.DAO;

public class CalDAO extends DAO {

	private static CalDAO instance;

	private CalDAO() {

	}

	public static CalDAO getInstance() {
		if (instance == null) {
			instance = new CalDAO();
		}
		return instance;
	}

	// 삭제.
	public HashMap<String, Object> delete(String title) throws Exception {
		connect();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "delete from schedule where title=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 변경.");
			map.put("title", title);

		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception(e.getMessage());
		} finally {
			disconnect();

		}

		return map;
	}

	// 입력.
	public HashMap<String, Object> insert(Calendar cal) throws Exception {
		connect();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String sql = "insert into schedule values(?,?,?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cal.getTitle());
			psmt.setString(2, cal.getStart());
			psmt.setString(3, cal.getEnd());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력.");

			map.put("title", cal.getTitle());
			map.put("start", cal.getStart());
			map.put("end", cal.getEnd());

		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception(e.getMessage());

		} finally {
			disconnect();
		}

		return map;
	}

	// 전체조회.
	public List<HashMap<String, Object>> getSchedules() throws Exception {
		connect();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "select * from schedule";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("title", rs.getString("title"));
				map.put("start", rs.getString("start"));
				map.put("end", rs.getString("end"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			throw new Exception(e.getMessage());

		} finally {
			disconnect();
		}
		return list;
	}
}
