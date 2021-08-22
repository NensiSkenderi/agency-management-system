package dao;

import java.sql.SQLException;

import model.users;
import utils.Utils;


public class login_dao extends DAO {

	public login_dao() {
		super();
	}
	
	public void changePassword(users u) throws SQLException {
		String update_password = "update liberty.users set password = ? where user_id = ?";
		
		stm = connector.prepareStatement(update_password);
		stm.setString(1, Utils.encrypt(Utils.key, Utils.initVector,u.getPassword()));
		stm.setInt(2, Utils.idP);
		
		stm.executeUpdate();
		stm.close();
	}
	
	public String get_pass() throws SQLException {
		String pass = "";
		String sql = "SELECT password FROM liberty.users where user_id = '"+Utils.idP+"' ";
		stm = connector.prepareStatement(sql);
		rs = stm.executeQuery(sql);
		
		while(rs.next()) {
			pass =  Utils.decrypt(Utils.key, Utils.initVector, rs.getString(1));
		}
		return pass;
	}
}
