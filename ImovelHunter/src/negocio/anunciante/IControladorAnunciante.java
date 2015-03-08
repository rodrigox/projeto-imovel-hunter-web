package negocio.anunciante;

import java.util.List;


import exceptions.DaoException;
import exceptions.RegistroInexistenteException;
import modelo.Anunciante;


public interface IControladorAnunciante {

	void inserirReserva(Anunciante anunciante) throws DaoException;
	void removerAnunciante(Anunciante anunciante) throws DaoException, RegistroInexistenteException;
	void atualizarAnunciante(Anunciante anunciante) throws DaoException;
	Anunciante pesquisarAnunciantePorId(Integer id);
	List<Anunciante> buscarAnuncinates();
}
