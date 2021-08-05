package co.yedam.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentDAO extends DAO {
	private static CommentDAO instance; // singleton방식

	private CommentDAO() {
	} // 외부에서 호출하지 못하도록

	public static CommentDAO getInstance() {
		instance = new CommentDAO();
		return instance;
	}

	// 글목록
	public List<HashMap<String, Object>> selectAll() {
		connect();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			psmt = conn.prepareStatement("select * from comments");
			rs = psmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("id", rs.getString("id"));
				map.put("name", rs.getString("name"));
				map.put("content", rs.getString("content"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();// 접속을 끊어줘야 다음에 안되는 상황을 없애기
		}
		return list;
	}// end of SA

	// 글등록
	public HashMap<String, Object> insert(Comment comment) {
		int nextId = 0;
		connect();
		try {
			// id_repository 테이블에서 현재 시쿼스 번호 가져오기
			conn.setAutoCommit(false);// 처리하더라도 사용자가 commit명령을 하기 전에는 db에 반영X
			stmt = conn.createStatement(); // statement
			rs = stmt.executeQuery("select value from id_repository where name='comment'");
			// comment에 해달하는 value 속성을 불러옴
			if (rs.next()) {// rs의 데이터가 있으면 true, 없으면 false
				nextId = rs.getInt("value");
			}
			// 시퀀스에 1 증가 후 comment 입력
			nextId++;
			psmt = conn.prepareStatement("insert into comments values(?,?,?)");// query 선언하지 않고 바로 ?로 대체
			// comment 테이블에 추가
			psmt.setInt(1, nextId);
			psmt.setString(2, comment.getName());
			psmt.setString(3, comment.getContent());
			int r = psmt.executeUpdate();// 조회:excuteQuery(), 입력수정삭제:excuteUpdate();
			System.out.println("입력건수 : " + r);

			// id_repository에 새로운 시쿼스 번호로 변경
			psmt = conn.prepareStatement("update id_repository set value=? where name='comment'");
			psmt.setInt(1, nextId);
			psmt.executeUpdate();

			conn.commit();
			// map에 다시 값 담아주기
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", nextId);
			map.put("name", comment.getName());
			map.put("content", comment.getContent());

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
	}// end of I

	// 글수정
	public HashMap<String, Object> update(Comment comment) {
		connect();
		try {
			psmt = conn.prepareStatement("update comments set name = ?, content = ? where id = ?");
			psmt.setString(1, comment.getName());
			psmt.setString(2, comment.getContent());
			psmt.setString(3, comment.getId());
			int r = psmt.executeUpdate();
			System.out.println("수정건수 : " + r);

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", comment.getId());
			map.put("name", comment.getName());
			map.put("content", comment.getContent());

			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}// end of U
	
	//pieChart
	public HashMap<String, Integer> getAmtByCountry() {
		connect();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String sql = "select billingCountry, sum(total) as amt from invoices i group by BillingCountry" ;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
//				map.put(rs.getString("billingCountry"), rs.getInt("amt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return map;
	}
	
	// 글삭제
	public HashMap<String, Object> delete(String id) {
		connect();
		String sql = "delete from comments where id = ?";
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int r = psmt.executeUpdate();
			System.out.println("삭제건수 : " + r);

			map.put("id", id);

			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}// end of D
}
