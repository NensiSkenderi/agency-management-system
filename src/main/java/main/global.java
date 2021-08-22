package main;

import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class global {
	
	//my agencies
	public static final String b1 = "List Info";
	
	//agency list
	public static final String b2 = "Clients Info";
	public static final String b3 = "Add Agency";
	public static final String b10 = "Refresh Agencies";
	
	//instructions
	public static final String b4 = "Billing Instructions";
	public static final String b11 = "Accounting Instructions";
		
	
	//updates
	public static final String b7 = "Communication Notes";
	
	//excel
	public static final String b8 = "Export Reports";
	
	//configurations
	public static final String b9 = "Edit Profile";
	public static final String b12 = "Inactive Agencies";
	public static final String b13 = "CHOOSE QUICK INFO";
	
	public static String INSTALL_DIR = getTargetPath();
	
	public static String getTargetPath() {
		CodeSource codeSource = App.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String jarDir = jarFile.getParentFile().getPath();
		return jarDir;
	}
	
}
