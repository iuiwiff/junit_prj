package kr.co.sist.day0320;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDAO {

	private static LoginDAO LDAO;
	
	private LoginDAO() {
		
	}
	
	public static LoginDAO getInstance() {
		if(LDAO == null) {
			LDAO = new LoginDAO();
		}//end if
		return LDAO;
	}
	
	
	//로그인 코드를 Statement로 구현
	public LoginResultVO selectLogin(LoginVO lVO) throws SQLException {
		LoginResultVO lrVO = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//1.
		try {
		
		//2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
	
		//3.
			stmt = con.createStatement();
		
		//4.
			StringBuilder selectData = new StringBuilder();
			selectData
			.append("    select     name    ")
            .append("    from     test_login    ")
            .append("    where     id = '"+lVO.getId() +"' and pass ='"+lVO.getPass() +"'    ");
			
			rs = stmt.executeQuery(selectData.toString());
			System.out.println(selectData.toString());
			
			if(rs.next()) {
				lrVO = new LoginResultVO(rs.getString("name"));
			}//end if
		} finally {
		//5.
			dbCon.dbClose(rs, stmt, con);
			
		}//end finally
		return lrVO;
	}//selectLogin
	
	//PreparedStatement를 사용하면 SQLInjection이 방어된다.
	public LoginResultVO selectPreparedLogin(LoginVO lVO) throws SQLException {
		LoginResultVO lrVO = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//1.
		try {
		
		//2.
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
	
		//3.
			StringBuilder selectData = new StringBuilder();
			selectData
			.append("    select     name,input_date    ")
			.append("    from     test_login    ")
			.append("    where     id =? and pass =?    ");
			pstmt = con.prepareStatement(selectData.toString());
		
		//4.
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
			
			//5.
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lrVO = new LoginResultVO(rs.getString("name"), rs.getDate("input_date"));
			}//end if
		} finally {
		//6.
			dbCon.dbClose(rs, pstmt, con);
			
		}//end finally
		return lrVO;
	}//selectLogin
	
	
	public static void main(String[] args) throws SQLException{
		System.out.println(LoginDAO.getInstance().selectLogin(new LoginVO("jin", "1234")));
	}
	
}
