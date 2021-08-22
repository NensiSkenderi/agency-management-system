package dao;

import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import model.users;
import utils.Utils;

public class userDao extends DAO {

	public userDao() {
		super();
	}

	public boolean check_user_and_pass(String user,String pass) {
		String sql = "SELECT full_name, password, user_id, group_access FROM liberty.users WHERE full_name=? and password = ? ";

		try {

			stm = connector.prepareStatement(sql);
			stm.setString(1, user);
			stm.setString(2, pass);
			rs = stm.executeQuery();

			while(rs.next()) {
				Utils.username = rs.getString(1); 
				Utils.passwordDecrypted = Utils.decrypt(Utils.key, Utils.initVector, rs.getString(2));
				Utils.user_group = rs.getString(4);
				Utils.idP = rs.getInt(3);
				Utils.full_name.setValue(rs.getString(1));
				return rs.getString(1).equals(user) && rs.getString(2).equals(pass);
			}
			stm.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public void addEmp(users emp) throws SQLException {

		if(this.checkEmp(emp.getFull_name())) {
			Utils.alerti("Warning!", "Employee exists!", AlertType.WARNING);
			return;
		}
		String insert = "insert into liberty.users(full_name, password,group_access,user_status) values(?,?,?,?)";
		stm = connector.prepareStatement(insert);
		stm.setString(1, emp.getFull_name());
		stm.setString(2, Utils.encrypt(Utils.key, Utils.initVector, emp.getPassword()));
		stm.setString(3, "Employee");
		stm.setString(4, "Active");

		stm.executeUpdate();
		stm.close();
	}

	public boolean checkEmp(String empFullName) throws SQLException {
		String query = "select * from liberty.users where full_name = '"+empFullName+"'";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		if(rs.next() == true)
			return true;
		else 
			return false;
	}

	public void updateGeneralUserInfo(users user) {
		if (Utils.idP == user.getUser_id()) {
			Utils.full_name.setValue(user.getFull_name());
			Utils.user_group = user.getGroup_access();
		}
	}

	public void updateUser(users user) {
		try {
			String update = "update liberty.users set full_name = ?, password = ?, group_access = ?, user_status = ?  where user_id = ?";

			stm = connector.prepareStatement(update);

			stm.setString(1, user.getFull_name());
			stm.setString(2, Utils.encrypt(Utils.key, Utils.initVector, user.getPassword()));
			stm.setString(3, "Employee");
			stm.setString(4, "Active");
			stm.setInt(5, user.getUser_id());

			stm.executeUpdate();
			stm.close();

			updateGeneralUserInfo(user);

			updateGeneralUserInfo(user);
		} catch (SQLException e) {
			Utils.alerti("Warning!", "Employee exists!", AlertType.WARNING);
			return;
		}
	}

}
