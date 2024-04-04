package dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Department;

public class DepartmentDao extends UnicastRemoteObject implements IDepartmentDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7552075657190884943L;
	private SessionFactory sessionFactory;
	public DepartmentDao() throws RemoteException {
		sessionFactory = MySessionFactory.getInstance().getSessionFactory();
	}
//	select departmentId, num = count(*)
//			from Doctors
//			group by departmentId
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Map<Department, Integer> getNumOfDoctorsByDepartments () {
		Map<Department, Integer> map = new HashMap<>();
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String sql = "select departmentId, num = count(*) "
					+ "from Doctors "
					+ "group by departmentId";
			List<Object[]> list =session
					.createNativeQuery(sql)
					.getResultList();
			
			for(Object[] obj : list) {
				String deptId =(String)obj[0];
				Department dept = session.find(Department.class, deptId);
				int num =(int)obj[1];
				
				map.put(dept, num);
			}
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		
		return map;
	}
}
