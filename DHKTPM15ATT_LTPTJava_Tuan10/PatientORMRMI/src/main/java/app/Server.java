package app;

import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import dao.DepartmentDao;
import dao.IDepartmentDao;

public class Server {
	public static void main(String[] args) throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("java.security.policy", "policy/policy.policy");
		Context context = new InitialContext(env);
		
		LocateRegistry.createRegistry(9898);
		
		IDepartmentDao demaprtmentDao = new DepartmentDao();
		
		context.bind("rmi://DESKTOP-G8E3BC9:9898/demaprtmentDao", demaprtmentDao);
		
		System.out.println("Ready...");
	}
}
