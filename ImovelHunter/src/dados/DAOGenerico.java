package dados;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.hibernate.Session;

import exceptions.DaoException;
import exceptions.RegistroInexistenteException;

public abstract class DAOGenerico<TEntidade, Id extends Serializable> implements IDAOGenerico<TEntidade, Id> {
	private EntityManager entityManager;
	private Session session;
	private Class<TEntidade> classePersistente;

	@SuppressWarnings("unchecked")	
	public DAOGenerico(EntityManager em, Session session){
		this.setEntityManager(em);
		this.setSession(session);
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
	    classePersistente = (Class<TEntidade>) parameterizedType.getActualTypeArguments()[0];  
	}
	
	@Override
	public void inserir(TEntidade obj) throws DaoException {
		EntityTransaction tx = this.getEntityManager().getTransaction();
		try {
			tx.begin();
			getEntityManager().persist(obj);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null){
				tx.rollback();
			}
			throw new DaoException("Erro ao tentar atualizar o registro no banco de dados.");
		}
	}

	@Override
	public void atualizar(TEntidade obj) throws DaoException{
		EntityTransaction tx = this.getEntityManager().getTransaction();
		try {
			tx.begin();
			getEntityManager().merge(obj);
			tx.commit();			
		} catch (PersistenceException e) {
			if (tx != null){
				tx.rollback();
			}	
			throw new DaoException("Erro ao tentar atualizar o registro no banco de dados.");
		}
	}

	@Override
	public void remover(TEntidade obj) throws DaoException {
		EntityTransaction tx = this.getEntityManager().getTransaction();
		try {
			tx.begin();
			getEntityManager().remove(obj);

			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null){
				tx.rollback();
			}
			throw new DaoException("Erro ao tentar remover o registro no banco de dados.");
		}
	}

	@Override
	public TEntidade pesquisarPorId(Serializable id) {
		try {
					return (TEntidade) getEntityManager().find(getClassePersistente(), id);	
		} catch (PersistenceException e) {
		}
		return null;
	}
	
	@Override
	public void validarRegistro(Serializable id) throws RegistroInexistenteException{
		if (this.pesquisarPorId(id) == null) {
			throw new RegistroInexistenteException();
		}
	}
	
    @SuppressWarnings("unchecked") 
    @Override
    public List<TEntidade> buscarTodos() {  
    	return entityManager.createQuery(("FROM " + classePersistente.getSimpleName())).getResultList();	
    } 
	
	protected final Class<TEntidade> getClassePersistente(){
		return classePersistente;
	} 

	public final void refresh(TEntidade object) {
		getEntityManager().refresh(object);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
