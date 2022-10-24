package org.kosta.myproject.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
/*
 * 	Singleton Design Pattern 적용해서 시스템 상에서 MemberDAO 객체를 단 한번 생성해서 사용하도록 한다.
 * 	1. private 생성자
 * 	2. private static 변수로 객체 생성해서 초기화 (instance 변수명)
 * 	3. public static method로 외부에 공유 (getInstance())
 */
public class MemberDAO {
	private static MemberDAO instance=new MemberDAO();
	private DataSource dataSource; 
	private MemberDAO() {
		dataSource=DataSourceManager.getInstance().getDataSource(); 
	}
	public static MemberDAO getInstance() {
		return instance;
	}
	public  Connection getConnection() throws SQLException{
		return dataSource.getConnection(); //컨넥션을 dbcp(DataSource)로부터 빌려온다.
		//return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close(); // 컨넥션을 dbcp(DataSource)로 반납한다.
		}
	}
	
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		closeAll(pstmt, con); // 컨넥션을 dbcp(DataSource)로 반납한다.
	}
	
	public int findMemberCount() throws SQLException {
		int count=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
    		String sql="SELECT COUNT(*) FROM member";
    		pstmt=con.prepareStatement(sql);
    		rs=pstmt.executeQuery();
    		rs.next();
    		count=rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	public MemberVO findMemberById(String id) throws SQLException {
		MemberVO memberVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT name,address FROM MEMBER WHERE id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				memberVO=new MemberVO(id, null, rs.getString("name"),rs.getString("address"));
			} 
			return memberVO;
		} finally {
			closeAll(rs,pstmt,con);
		}
	}

	public ArrayList<MemberVO> findMemberListByAddress(String address) throws SQLException{
		ArrayList<MemberVO> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			String sql="SELECT id, name FROM MEMBER WHERE address=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, address);
			rs=pstmt.executeQuery();
			MemberVO vo;
				while(rs.next()) {
					vo=new MemberVO(rs.getString("id"),null,rs.getString("name"),address);
					list.add(vo);
				}	
		} finally {
			closeAll(rs,pstmt,con);
		}
		return list;
	}

	public void register(MemberVO memberVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="INSERT INTO member(id,password,name,address) VALUES(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPassword());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getAddress());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt,con);
		}		
	}
	public MemberVO login(String id, String password) throws SQLException {
		MemberVO mvo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT id,password,name,address FROM member WHERE id=? AND password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mvo=new MemberVO(id, null,rs.getString("name"),rs.getString("address"));
			}
		} finally {
			closeAll(rs,pstmt,con);
		}
		return mvo;
	}
	
	public void update(String id, String name, String password, String address) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=getConnection();
			String sql="UPDATE MEMBER SET name=?,password=?,address=? WHERE id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, address);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt,con);
		}
	}
	public boolean checkId(String id) throws SQLException {
		boolean result=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="SELECT COUNT(*) FROM member WHERE id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)>0) {
					result=true;					
				}
			}
		} finally {
			closeAll(rs,pstmt,con);
		}
		return result;
	}
}

//원래 썼던 코드는 위와 같은데 setString할 때 id가 아니라 sql(자동생성)로 되어있어서 안먹었던 거 아래가 강사님 팁 rs.next()하고 && 0보다 크면으로 조건을 준다.

//public boolean checkId(String id) throws SQLException {
//	boolean result=false;
//	Connection con=null;
//	PreparedStatement pstmt=null;
//	ResultSet rs=null;
//	try {
//		con=getConnection();
//		String sql="SELECT COUNT(*) FROM member WHERE id=?";
//		pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, id);
//		rs=pstmt.executeQuery();
//		if(rs.next()&&rs.getInt(1)>0) {
//			result=true;
//		}
//	} finally {
//		closeAll(rs,pstmt,con);
//	}
//	return result;
//}

