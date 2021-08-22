package dao;


public class ControlDAO {

	private static ControlDAO dao = new ControlDAO();
	private userDao user_dao = new userDao();
	private clientDao client_dao = new clientDao();
	private insurances_Dao addNewPayerDao = new insurances_Dao();
	private agency_usersDao agencyUsersDao = new agency_usersDao();
	private contactPersonDao contactPersonDao = new contactPersonDao();
	private accountingInstructionsDao accountingInstructionsDao = new accountingInstructionsDao();
	private login_dao loginDao = new login_dao();
	private reports_dao reportsDao = new reports_dao();
	private client_comm_notesDao clientsCommNotesDao = new client_comm_notesDao();
	private add_to_listDao addToListDao = new add_to_listDao();
	private inactiveClientDao inactive_client_dao = new inactiveClientDao();
	private agency_notesDao agencyNotesDao = new agency_notesDao();
	private quickInfoDao quick_info_dao = new quickInfoDao();
	
	public static ControlDAO getControlDao() {
		return dao;
	}
	
	public userDao getUserDao() {
		return user_dao;
	}
	
	public clientDao getClientDao() {
		return client_dao;
	}
	
	public insurances_Dao getNewPayerDao() {
		return addNewPayerDao;
	}
	
	public agency_usersDao getAgencyUsersDao() {
		return agencyUsersDao;
	}
	
	public contactPersonDao getContactPersonDao() {
		return contactPersonDao;
	}
	
	public accountingInstructionsDao getAccountingDao() {
		return accountingInstructionsDao;
	}
	
	public login_dao getLoginDao() {
		return loginDao;
	}
	
	public reports_dao getReportsDao() {
		return reportsDao;
	}
	
	public client_comm_notesDao getClientCommNotesDao() {
		return clientsCommNotesDao;
	}
	
	public add_to_listDao getAddToListDao() {
		return addToListDao;
	}
	
	public inactiveClientDao getInactiveClientDao() {
		return inactive_client_dao;
	}
	
	public agency_notesDao getAgencyNotesDao() {
		return agencyNotesDao;
	}
	
	public quickInfoDao getQuickInfoDao() {
		return quick_info_dao;
	}
}
