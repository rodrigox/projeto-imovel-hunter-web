package br.com.imovelhunterweb.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public interface GenericDAO<Type, ID extends Serializable> {
	
	/**
	 * inseri um objeto no banco de dados
	 * 
	 * @param objeto
	 * @throws CreateException
	 */
	public Type insert(Type objeto) ;

	/**
	 * inseri uma lista de objetos (do mesmo tipo) no banco de dados
	 * 
	 * @param list
	 * @return retorna o objeto inserido.
	 */
	public void insertAll(List<Type> list);

	/**
	 * atualiza as informa��es de um objeto passado por parametro
	 * 
	 * @param objeto
	 * @throws DataException
	 * @return retorna o objeto atualizado. 
	 */
	public Type update(Type objeto) ;
	
	
	public List<Type> findParameter(String query, List<String> parametros);

	/**
	 * apaga do banco de dados o objeto passado por parametro
	 * 
	 * @param objeto
	 * @throws DataException
	 */
	public void remove(Type objeto);

	/**
	 * apaga do banco de dados o cadastro que tenha o id igual ao passado por  parametro
	 * 
	 * @param id
	 * @throws DataException
	 */
	public void removeById(ID id) ;

	/**
	 * Busca um objeto que tenha o id igual ao passado por parametro
	 * 
	 * @param id
	 * @return objeto o qual foi passado o id
	 * 
	 * @throws FindException
	 */
	public Type findById(ID id) ;

	/**
	 * lista todos  os objetos do tipo passado por parametro
	 * 
	 * @return Lista de objetos
	 * @throws FindException
	 */
	public List<Type> findAll() ;

	/**
	 * 
	 * lista todos os objetos de uma entidade espec�fica de um �ndice inicial
	 * at� um �ndice final.
	 * 
	 * @param indiceInicial
	 *            indice inicial da busca
	 * @param indiceFinal
	 *            indice final da pesquisa.
	 * @param ordena��es
	 *            lista de ordena��o a ser criado
	 * @return uma lista de objetos do tipo passado por parametro.
	 */
	public List<Type> findAll(Integer indiceInicial, Integer quantidade);
	
	/**
	 * faz a busca através de SQl sem parâmetro
	 * @param query
	 * @return
	 */
	public List<Object[]> findNative(String query);
	
	/**
	 * 
	 * lista todos os objetos de uma entidade espec�fica de um �ndice inicial
	 * at� um  �ndice final.
	 * 
	 * @param indiceInicial
	 *            indice inicial da busca.
	 * @param indiceFinal
	 *            indice final da pesquisa.
	 * @param ordena��es
	 *            lista de ordena��o a ser criado.
	 * @return uma lista de objetos do tipo passado por parametro.
	 */
	public List<Type> findAll(Integer indiceInicial, Integer quantidade, Order... ordenacoes);

	/**
	 * Busca o objeto de acordo com o objeto preenchido com os valores passado
	 * como exemplo.
	 * 
	 * @param objeto utilizado para realizar a busca.
	 * @param ordenacoes lista de crit�rios de ordena��o.
	 * @return Lista de objetos do tipo passado por parametro.
	 */
	public List<Type> search(Type objeto);

	/**
	 * pesquisa e retorna uma lista de objetos do tipo passado por parametro.
	 * 
	 * @param objeto a ser pesquisado
	 * @param indiceInicial indice inicial da busca.
	 * @param quantidade lista de ordena��o a ser criado.
	 * @param ordenacoes lista de ordena��o a ser criado
	 * @return uma lista de objetos do tipo passado por parametro.
	 */
	public List<Type> search(Type objeto, Integer indiceInicial, Integer quantidade, Order... ordenacoes);
	
	/**
	 * pesquisa e retorna um objeto do tipo passado por parametro, com os filtros definido na query. 
	 * 
	 * @return lista de objetos
	 */
	public Type searchParameter(String query, Map<String, Object> params);
	
	/**
	 * lista todos objetos do tipo passado por parametro, definido na query. 
	 * 
	 * @return lista de objetos
	 */
	public List<Type> findParameter(String query);
	
	/**
	 * busca através de uma query, só que com uma range de objetos
	 * @param query
	 * @param indiceInicial
	 * @param indiceFinal
	 * @return
	 */
	public List<Type> findParameter(String query,int indiceInicial,int indiceFinal);
	
	/**
	 * lista todos objetos do tipo passado por parametro, definido em uma query nativa SQL. 
	 * 
	 * @return lista de objetos
	 */
	public List<Object[]> findParameterNative(String query, Integer parametro);
	
	/**
	 * lista todos objetos do tipo passado por parametro, com os filtros definido na query. 
	 * 
	 * @return lista de objetos
	 */
	public List<Type> findAllParameter(String query, Map<String, Object> params);
	
	/**
	 * lista todos objetos do tipo passado por parametro, com os filtros definido na query. 
	 * 
	 * @return lista de objetos
	 */
	public List<Type> findAllParameter(String query, Map<String, Object> params, int maximo, int atual);

	/**
	 * Retorna a quantidade total de objetos da entidade passada por paramnetro.
	 * 
	 * @return quantidade de objetos
	 */
	public Integer getRows();
	
	/**
	 * Retorna o objeto da classes Criteria.
	 * 
	 * @return um objeto do tipo Criteria do Hibernate
	 */
	public Criteria createCriteria();
	
	public List<Type> useQuery(String query,Map<String,Object> parametros);
	
	public List<Type> useQuery(String query,Map<String,Object> parametros,Integer indiceInicial,Integer indiceFinal);
	
	}


	


