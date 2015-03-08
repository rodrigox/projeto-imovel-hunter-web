package dados;

import java.io.Serializable;
import java.util.List;

import exceptions.DaoException;
import exceptions.RegistroInexistenteException;

public interface IDAOGenerico<TEntidade, Id extends Serializable> {
	void inserir(TEntidade obj) throws DaoException;
	void atualizar(TEntidade obj) throws DaoException;
	void remover(TEntidade obj) throws DaoException;
	TEntidade pesquisarPorId(Serializable id);
	List<TEntidade> buscarTodos();
	void validarRegistro(Serializable id) throws RegistroInexistenteException;
}