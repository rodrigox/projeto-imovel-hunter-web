package br.com.imovelhunterweb.dao.imp;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import br.com.imovelhunterweb.dao.GenericDAO;





public class GenericDAOImp<Type, ID extends Serializable> implements GenericDAO<Type, ID> {
	
	protected EntityManager objetoManager = null;
	private final Class<Type> persistentClass;
	
	public Class<Type> getObjectClass(){
		return this.persistentClass;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager objetoManager) {
		this.objetoManager = objetoManager;
	}
	
	@SuppressWarnings("unchecked")
	public GenericDAOImp() {
		this.persistentClass = (Class<Type>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	
	@Override
	public Type insert(Type objeto) {
		try {
			//	objetoManager.clear();
			objetoManager.persist(objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objeto;
	}

	@Override
	public void insertAll(List<Type> list) {
		try {
			for (Type objeto : list) {
				objetoManager.clear();
				objetoManager.persist(objeto);
			}
			System.out.println(this.persistentClass.getSimpleName()
					+ " salvos com sucesso: " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Override
	public Type update(Type objeto) {
		objetoManager.merge(objeto);
		return objeto;
	}

	@Override
	public void remove(Type objeto) {
		objeto = objetoManager.merge(objeto);
		objetoManager.remove(objeto);
	}

	@Override
	public void removeById(ID id) {
		Type objeto = this.findById(id);
		if (objeto != null) {
			objeto = objetoManager.merge(objeto);
			objetoManager.remove(objeto);
		}
	}

	@Override
	public Type findById(ID id){
		Type objeto = null;
		objeto = objetoManager.find(this.persistentClass, id);
		
		return objeto;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> findAll() {
		Query usu = objetoManager.createQuery(
			"from " + persistentClass.getName());
		return usu.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> findAll(Integer indiceInicial, Integer quantidade) {
		if (objetoManager == null) {
			System.out.println("EntityManager e nulo");
			return null;
		}
		
		List<Type> results = null;

		Query query = objetoManager.createQuery(
					"from " + persistentClass.getName());
		query.setFirstResult(indiceInicial);
		query.setMaxResults(quantidade);

		results = (List<Type>) query.getResultList();
		return results;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findNative(String query) {
		if (objetoManager == null) {
			System.out.println("EntityManager e nulo");
			return null;
		}
		
		//
		Query  q = objetoManager.createNativeQuery(query);	
		 
		List<Object[]> lista = q.getResultList();
		 
		return lista;
		//
		
	};

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> findAll(Integer indiceInicial, Integer quantidade,
			Order... ordenacoes) {
		if (objetoManager == null) {
			System.out.println("EntityManager e nulo");
			return null;
		}
		
		List<Type> results = null;
		Query query = objetoManager.createQuery(
					"from " + this.persistentClass.getName()
							+ addOrderByHql(ordenacoes));
		
		query.setFirstResult(indiceInicial);
		query.setMaxResults(quantidade);
		
		results = (List<Type>) query.getResultList();
		
		return results;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Type> search(Type objeto) {
		Session session = (Session) objetoManager.getDelegate();
		Example example = criaExemplo(objeto);
		Criteria criteria = session.createCriteria(objeto.getClass()).add(example);
		return (List<Type>) criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> search(Type objeto, Integer indiceInicial,
			Integer quantidade, Order... ordenacoes) {
		Example example = criaExemplo(objeto);
		Criteria criteria = createCriteria().add(example);
		criteria.setFirstResult(indiceInicial);
		criteria.setMaxResults(quantidade);
		for (Order ord : ordenacoes) {
			criteria.addOrder(ord);
		}
		return (List<Type>) criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Type searchParameter(String query, Map<String, Object> params) {
		Query q = objetoManager.createQuery(query);
		
		for (String chave : params.keySet()) {
			q.setParameter(chave, params.get(chave));
		}
			return (Type) q.getSingleResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> findParameter(String query) {
		if (objetoManager == null) {
			System.out.println("EntityManager e nulo");	//FIXME: o que fazer quando objetoManger for nulo?
			return null;
		}
		
		Query  q = objetoManager.createQuery(query);
				
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findParameterNative(String query, Integer parametro) {
		if (objetoManager == null) {
			System.out.println("EntityManager e nulo");	//FIXME: o que fazer quando objetoManger for nulo?
			return null;
		}
		
		Query  q = objetoManager.createNativeQuery(query);
		
		q.setParameter(1, parametro);
		 
		List<Object[]> lista = q.getResultList();
		 
		return lista;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Type> findAllParameter(String query, Map<String, Object> params) {
		Query  q = objetoManager.createQuery(query);
		
		for (String  chave : params.keySet()) {
			q.setParameter(chave, params.get(chave));
		}
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Type> findAllParameter(String query,
			Map<String, Object> params, int maximo, int atual) {
		Query  q = objetoManager.createQuery(query).setMaxResults(maximo).setFirstResult(atual);
		
		for (String chave : params.keySet()) {
			System.out.println("chave: :"+ chave);
			System.out.println("valor da chave: " + params.get(chave));
			q.setParameter(chave,params.get(chave));
		}
		
		return q.getResultList();
	}
	
	@Override
	public Integer getRows() {
		Criteria criteria = createCriteria();
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).intValue();
	}

	@Override
	public Criteria createCriteria() {
		Session session = (Session) objetoManager.getDelegate();
		return session.createCriteria(this.persistentClass);
	}
	
	@Override
	public List<Type> findParameter(String query, List<String> parametros){
		return null;
	}
	
	/**
	 * M�todo utilizado para criar o objeto Example. Este objeto � utilizado
	 * para realizar a busca por exemplo.
	 * 
	 * @param objeto
	 *            sobre o qual o Example ser� criado
	 * @return em objeto do tipo Example
	 */
	protected final Example criaExemplo(Type objeto) {
		
		Example example = Example.create(objeto);
		example.enableLike(MatchMode.ANYWHERE);
		example.excludeZeroes();
		example.ignoreCase();

		return example;
	}

	/**
	 * Adiciona o orderBy no final da query a ser utilizada.
	 * 
	 * @param ordenacoes
	 *            a serem utilizadas para a busca
	 * @return string com o orderBy
	 */
	protected final static String addOrderByHql(Order... ordenacoes) {
		String result = "";
		if (ordenacoes.length > 0) {
			StringBuilder builder = new StringBuilder(" order by ");
			for (int i = 0; i < ordenacoes.length - 1; i++) {
				builder.append(ordenacoes[i].toString());
				builder.append(", ");
			}
			builder.append(ordenacoes[ordenacoes.length - 1]);
			result = builder.toString();
		}

		return result;
	}

	@Override	
	@SuppressWarnings("unchecked")
	public List<Type> findParameter(String query, int indiceInicial,
			int indiceFinal) {
		if (objetoManager == null) {
			System.out.println("EntityManager e nulo");	//FIXME: o que fazer quando objetoManger for nulo?
			return null;
		}
		
		Query  q = objetoManager.createQuery(query);
		
		q.setFirstResult(indiceInicial);
		q.setMaxResults(indiceFinal);
				
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> useQuery(String query, Map<String, Object> parametros) {
		Query q = this.objetoManager.createQuery(query);
		
		if(parametros != null){
			Iterator<String> iterador = parametros.keySet().iterator();
			while(iterador.hasNext()){
				String chave = iterador.next();
				q.setParameter(chave,parametros.get(chave));
			}
		}
		return q.getResultList();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> useQuery(String query, Map<String, Object> parametros,
			Integer indiceInicial, Integer indiceFinal) {
		Query q = this.objetoManager.createQuery(query);
		
		if(parametros != null){
			Iterator<String> iterador = parametros.keySet().iterator();
			while (iterador.hasNext()) {
				String chave = iterador.next();
				q.setParameter(chave, parametros.get(chave));
			}
		}
		q.setFirstResult(indiceInicial);
		q.setMaxResults(indiceFinal);
		return q.getResultList();		
	}
}