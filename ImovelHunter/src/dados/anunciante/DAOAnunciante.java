package dados.anunciante;

import javax.persistence.EntityManager;

import modelo.Anunciante;

import org.hibernate.Session;

import dados.DAOGenerico;

public class DAOAnunciante extends DAOGenerico<Anunciante, Integer> implements IDAOAnunciante {
	
	public DAOAnunciante(EntityManager em, Session session) {
		super(em, session);
	}

}