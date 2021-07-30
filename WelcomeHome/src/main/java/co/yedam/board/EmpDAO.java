package co.yedam.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.DAO;

public class EmpDAO extends DAO {

	// 전체사원리스트
	public List<Employee> getEmpList() {
		connect(); // connection 객체
		List<Employee> empList = new ArrayList<Employee>();// empList에 값을 담으려고 배열을 만든다.
		try {
			psmt = conn.prepareStatement("select * from emp_temp");
			rs = psmt.executeQuery();// result set 배열에 담는다.
			while (rs.next()) { // 배열의 갯수만큼 반복
				Employee emp = new Employee();// 초기화
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));

				empList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return empList;
	}
}
