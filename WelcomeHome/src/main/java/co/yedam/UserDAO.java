package co.yedam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {

	public List<UserVO> getUsers() { //selectList
		connect();
		List<UserVO> userList = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select * from users");
			rs = psmt.executeQuery();
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setUserPw(rs.getString("user_pw"));
				vo.setUserName(rs.getString("user_name"));
				vo.setUserGen(rs.getString("user_gen"));
				vo.setUserHobby(rs.getString("user_hobby"));
				vo.setUserBirth(rs.getString("user_birth"));
				
				userList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public void insertUser(UserVO vo) {
		connect();
		String sql = "insert into users(user_id, user_pw, user_name, user_gen, user_hobby_user_birth) values(?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserPw());
			psmt.setString(3, vo.getUserName());
			psmt.setString(4, vo.getUserGen());
			psmt.setString(5, vo.getUserHobby());
			psmt.setString(6, vo.getUserBirth());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}