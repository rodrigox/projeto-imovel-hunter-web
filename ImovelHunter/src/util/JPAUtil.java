package util;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class JPAUtil {	
	private static EntityManagerFactory entityManagerFactory;
	private static Map<String,EntityManager> mapaEntitys;	
	private static EntityManager currentEntityManager;
	
	
	public static void comecarJPA(){
		try {			
			Class.forName("com.mysql.jdbc.Driver");	
			entityManagerFactory = Persistence.createEntityManagerFactory("configuracaoJPA");	
			mapaEntitys = new HashMap<String,EntityManager>();			
		} catch (ClassNotFoundException e) {			
			System.out.println("Não achou o driver do mysql");	
			System.exit(0);
		} 		
	}


	public static EntityManager createEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	public static Map<String,EntityManager> getMap(){
		synchronized (mapaEntitys){			
			return mapaEntitys;
		}		
	}
	
	public static void destruirEntityManager(String sessao){
		
		mapaEntitys.remove(sessao);
		
	}
	
	public static EntityManager getCurrentEntityManager(){
		return createEntityManager();
	}
	
	
	public static EntityManager getEntityManeger(String sessao) throws InterruptedException{
		
		if(!mapaEntitys.containsKey(sessao)){
			mapaEntitys.put(sessao,entityManagerFactory.createEntityManager());
		}		
		
		EntityManager em = mapaEntitys.get(sessao);
		
		if(em == null || !em.isOpen()){
			em = entityManagerFactory.createEntityManager();
			mapaEntitys.put(sessao,em);			
		}
		
		currentEntityManager = em;
		
		return em;
	}
	
}
