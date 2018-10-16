package kr.co.happy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {

	public static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	private BoardDAO() {}
	private final int LIST_CNT = 20;
	
	public ArrayList<BoardDTO> getBoardList(int intBtype, int page){
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			int start = (page - 1) * LIST_CNT + 1;
			int end = page * LIST_CNT;
			
			String sql = " select * from "
					+ " (select h.*, row_number() over (order by seq desc) as rnum"
					+ " from h_board h where h.btype = ?) "
					+ " where rnum between ? and ? ";
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, intBtype);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBregdate(rs.getString("bregdate"));
				list.add(dto);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		return list;
	}
	
	public int maxPage(int intBtype) {
		
		int maxPage = 0;
		int maxNo = 0;
		int divNo = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnector.getConn();
			String sql = " select count(btype) from h_board"
					+ " where btype = ? ";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, intBtype);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				maxNo = rs.getInt(1);
			}
			if(maxNo%LIST_CNT>0) {
				divNo = 1;
			}
			
			maxPage = maxNo/LIST_CNT + divNo;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
		return maxPage;
	}
	
	public void insert(int intBtype, String btitle, String bcontent, String pw) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnector.getConn();
			String sql = " insert into h_board "
					+ " (bid, btype, seq, btitle, bcontent, pw) "
					+ " values "
					+ " ((select nvl(max(bid),0)+1 from h_board), ?, "
					+ " (select nvl(max(seq),0)+1 from h_board where btype = ?), "
					+ "  ?, ?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, intBtype);
			ps.setInt(2, intBtype);
			ps.setString(3, btitle);
			ps.setString(4, bcontent);
			ps.setString(5, pw);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
	}
	
	public BoardDTO boardDetail(int intBid){
		
		BoardDTO list = new BoardDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnector.getConn();
			String sql = " select * from h_board "
					+ " where bid = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, intBid);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setBtype(rs.getInt("btype"));
				dto.setSeq(rs.getInt("Seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
				dto.setBregdate(rs.getString("bregdate"));
				dto.setPw(rs.getString("pw"));
				list = dto;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		return list;
	}
	
	public void update(int intBid, String btitle, String bcontent, String pw) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnector.getConn();
			String sql = " update h_board "
					+ " set btitle = ?, bcontent = ?, pw = ? "
					+ " where bid = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, btitle);
			ps.setString(2, bcontent);
			ps.setString(3, pw);
			ps.setInt(4, intBid);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
	}
	
	public void delete(int intBid) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnector.getConn();
			String sql = " delete from h_board "
					+ " where bid = ? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, intBid);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
	}
	
}
