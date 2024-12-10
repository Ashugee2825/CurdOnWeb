package master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class UserDBService {
	
	public static  String INSERT_USERS_SQL = "INSERT INTO USERS (name, email, city) VALUES (?, ?, ?);";
	public static  String SELECT_USER_BY_ID = "select id, name, city, email from users where id = ?;"; 
	public static  String SELECT_ALL_USERS = "select * from users;";
	public static  String DELETE_USERS_SQL = "delete from users where id = ?;";
	public static  String UPDATE_USERS_SQL = "update users set name=?, email=?, city=? where id = ?;";
	
	Connection con;
	public UserDBService()
	{
		DBConnectionDTO conDTO = new DBConnectionDTO();
		con=conDTO.getConnection();
	}
	
	public void closeConnection()
	{
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//insert user
	public void insertUser(User user) {
		System.out.println(INSERT_USERS_SQL);
		try {
				PreparedStatement pstm = con.prepareStatement(INSERT_USERS_SQL);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getCity());
			System.out.println(pstm);
			pstm.executeUpdate();	}	
		catch (Exception e) {
			  
		  	System.out.println(e);
				}
	}
	//select user by id
	public User selectUser(int id) {
		User user = null;
		try {
			PreparedStatement pstm = con.prepareStatement(SELECT_USER_BY_ID);{
				pstm.setInt(1, id);
				System.out.println(pstm);
				ResultSet rs = pstm.executeQuery();
				
				while(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String city = rs.getString("city");
					user = new User(id, name, email, city);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	//select all users
	public List<User> selectAllUsers(){
		List<User> users = new ArrayList<>();
		try(
		PreparedStatement pstm = con.prepareStatement(SELECT_ALL_USERS);){
			System.out.println(pstm);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String city = rs.getString("city");
				users.add(new User(id, name, email, city));
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return users;
		} 
	
	//update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (
				PreparedStatement pstm = con.prepareStatement(UPDATE_USERS_SQL);){
			System.out.println("updated user"+pstm);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getCity());
			pstm.setInt(4, user.getId());
			
			rowUpdated = pstm.executeUpdate() > 0;
		}
		return rowUpdated;
	}
		
	//delete user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (
				PreparedStatement pstm = con.prepareStatement(DELETE_USERS_SQL);){
			pstm.setInt(1, id);
			rowDeleted = pstm.executeUpdate()>0;
		}
		return rowDeleted;
	}
	
	public void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: "+ ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
			Throwable t = ex.getCause();
			while(t !=null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
				}
			}
	}
}
