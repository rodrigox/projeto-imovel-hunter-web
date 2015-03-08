package dados;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import dados.anunciante.DAOAnunciante;
import dados.anunciante.IDAOAnunciante;


public abstract class DAOFactory {
	private static EntityManager manager;
	private static final EntityManagerFactory factory;
	private static final Session session;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		factory = Persistence.createEntityManagerFactory("persistenceimovelhunter");
		
		if (manager == null || !manager.isOpen()) {
			manager = factory.createEntityManager();
		}
		
		session = manager.unwrap(org.hibernate.Session.class);
	}
	
	public static IDAOAnunciante getDAOAnunciante(){
		IDAOAnunciante anuncianteDAO = new DAOAnunciante(manager, session);
		return anuncianteDAO;
	}
	
}
