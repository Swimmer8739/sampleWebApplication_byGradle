package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySQLConnector {

	// jdbc:mysql://localhost/caloriestock?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Asia/Tokyo
	final String URL = "jdbc:mysql://localhost:3306/reversi";

	Connection connection = null;
	public MySQLConnector() throws SQLException, NamingException{

        Context context = new InitialContext();
        DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/myds");

		connection = ds.getConnection();
		System.out.println("MySQLに接続できました。");
	}
	public void destructor() throws SQLException {
		if (connection != null) {
			connection.close();
		}
		System.out.println("MySQLへのアクセスを終了しました。");
	}

	public void viewUser() throws SQLException{

        Statement statement = connection.createStatement();
        String sql = "select * from t01UserConfig";
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("取得結果 -> " + id + ":" + name);
        }
	}


	public void getUserConfig() throws SQLException{

	}

	public boolean permitUser(String name,String password) {
		String sq_name_sq="'"+name+"'"  ;//sq = シングルクォーテーション

        Statement statement;
		try {
			statement = connection.createStatement();

			//[1]get UserID from name
	        String sql = "select id from t01UserConfig where name = " + sq_name_sq;
	        ResultSet rs = statement.executeQuery(sql);

	        int id =0;
	        while(rs.next()){
	            id = rs.getInt("id");
	            System.out.println("取得結果 -> " + id );
	        }

			//[2]get password from userID
	        sql = "select password from t02Login where id = " + id;
	        rs = statement.executeQuery(sql);

	        String pass="";
	        while(rs.next()){
	        	pass = rs.getString("password");
	            System.out.println("取得結果 -> " + pass );
	        }

			//[3]set return value
	        if (password.equals(pass)){
	        	return true;
	        }else{
	        	return false;
	        }

		} catch (SQLException e) {
			e.printStackTrace();
        	return false;
		}
	}

	public void updateUserConfig() throws SQLException{

	}

	public void createNewGame() throws SQLException{

	}
	public void AppendGameRecord() throws SQLException{

	}

}
