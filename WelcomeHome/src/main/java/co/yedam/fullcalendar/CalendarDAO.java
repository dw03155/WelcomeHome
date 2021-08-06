package co.yedam.fullcalendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalendarDAO extends DAO {

	private static CalendarDAO instance; // singleton방식

	private CalendarDAO() {
	} // 외부에서 호출하지 못하도록

	public static CalendarDAO getInstance() {
		instance = new CalendarDAO();
		return instance;
	}

	// 일정조회
	public List<HashMap<String, Object>> selectCalendar() {
		connect();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			psmt = conn.prepareStatement("select * from schedule");
			rs = psmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("title", rs.getString("title"));
				map.put("start", rs.getString("start"));
				map.put("end", rs.getString("end"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//end of SC
	// 일정입력
	public HashMap<String, Object> insertCalendar (Schedule schedule) {
		connect();
		try {
			psmt = conn.prepareStatement("insert into schedule values (?,?,?)");
			psmt.setString(1, schedule.getTitle());
			psmt.setString(2, schedule.getStart());
			psmt.setString(3, schedule.getEnd());
			int r = psmt.executeUpdate();
			System.out.println("입력건수 : " + r);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("title", schedule.getTitle());
			map.put("start", schedule.getStart());
			map.put("end", schedule.getEnd());
			
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("msg", e.getMessage());
				
				return map;

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			disconnect();
		}
		return null;
	}
	// 일정삭제
	public HashMap<String, Object> deleteCalendar(String title) {
		connect();
		String sql = "delete from schedule where title = ?";
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			int r = psmt.executeUpdate();
			System.out.println("삭제건수 : " + r);

			map.put("title", title);

			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
		}
}
