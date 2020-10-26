package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import domains.Mapping;
import domains.SessionStorage;
import domains.User;

public class DB {
	private static Set<User> users = new HashSet<>();
	private static Set<SessionStorage> sessionStorages = new HashSet<>();
	private static Set<Mapping> mappings = new HashSet<>();
	static {
		User u1=addUser("01", "0");
		User u2=addUser("02", "0");
		addMapping(1L,u1,"app1u1","localhost","/app1");
		addMapping(2L,u1,"app2u1","localhost","/app2");
		addMapping(3L,u2,"app1u2","localhost","/app1");
		addMapping(4L,u2,"app2u2","localhost","/app2");

	}

	public static User addUser(String id, String pwd) {
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			return user;
		}
		
	}


	

	//閸愬懘鍎存担璺ㄦ暏
	public static User getUser(String id) {
		Session session = (Session) HibernateUtil.getSession();
		try {
			return session.get(User.class, id);
		} finally {
			session.close();
		}
	}
	//妤犲矁鐦夋担璺ㄦ暏
	public static User findUser(String id, String pwd) {
		Session session = (Session) HibernateUtil.getSession();
		try {
			return session.get(User.class, id);
		} finally {
			session.close();
		}
	}


	//tgc閻庣數鎳撶花鍙夌▔椤撶偟濡囧ù鍏间亢閻︾祤d,sessionid濞戞挾顕﹑p濞村吋淇洪惁绲燿
	public static void addSessionStorage(String LOCAL_SERVICE, String id, String sessionId) {
		SessionStorage sessionStorage = new SessionStorage();
		System.out.println("session id ="+sessionId);
		sessionStorage.setLocalService(LOCAL_SERVICE);
		sessionStorage.setUid(id);
		sessionStorage.setSessionId(sessionId);
		sessionStorage.setId(sessionId);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(sessionStorage);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public static void addMapping(Long id,User casUser,String localUser, String host,String app)
	{
		Mapping m=new Mapping();
		m.setId(id);
		m.setCasUser(casUser);
		m.setLocalUser(localUser);
		m.setHost(host);
		m.setApp(app);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(m);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static Mapping findMappingByHostAndAppAndCasUser(String host, String app, User user) {
		//鏉′欢鏌ヨ锛屽弬鏁扮储寮曞�间粠0寮�濮嬶紝绱㈠紩浣嶇疆銆傞�氳繃setString,setParameter璁剧疆鍙傛暟      
		String hql = "from Mapping where host=?1 and app=?2 and casUser.id=?3";
		Session session = HibernateUtil.getSession();
		Query<Mapping> query = session.createQuery(hql);      
		query.setParameter(1, host);
		query.setParameter(2, app);
		query.setParameter(3, user.getId());         
		List<Mapping> list =query.list();      
		if(list.size()!=0)
		return list.get(0);
		else return null;
	}

	public static List<SessionStorage> findSessionStorage(String id) {
		String hql = "from SessionStorage where sid=?1";
		Session session = HibernateUtil.getSession();
		Query<SessionStorage> query = session.createQuery(hql);           
		query.setParameter(1, id);
		List<SessionStorage> list =query.list();      
		if(list.size()!=0)
		return list;
		else return null;
	}

	public static void deleteSessionStorage(String CAS_ST) {
		Session session = HibernateUtil.getSession();
		String hql="delete from SessionStorage where sid=?1";
		Transaction t=null;
		try {
			t=session.beginTransaction();
			Query<SessionStorage> q=session.createQuery(hql);
			q.setParameter(1, CAS_ST);
			q.executeUpdate();
			t.commit();
		} catch(Exception ex) {
		if(t!=null) {
			t.rollback();
		}
		} finally {
		session.close();
		}
		
	}




	public static Object findSessionStorageBySessionId(String sessionId) {
		String hql = "from SessionStorage where sessionid=?1";
		Session session = HibernateUtil.getSession();
		Query<SessionStorage> query = session.createQuery(hql);           
		query.setParameter(1, sessionId);
		List<SessionStorage> list =query.list();      
		if(list.size()!=0)
		return list.get(0);
		else return null;
	}

}