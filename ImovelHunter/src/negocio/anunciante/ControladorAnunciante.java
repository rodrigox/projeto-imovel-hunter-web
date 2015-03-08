package negocio.anunciante;


import java.util.List;
import modelo.Anunciante;
import dados.DAOFactory;
import dados.anunciante.IDAOAnunciante;
import exceptions.DaoException;
import exceptions.RegistroInexistenteException;


public class ControladorAnunciante implements IControladorAnunciante {
	private static IControladorAnunciante instancia;

	
	static {
		if (instancia == null)
			instancia = new ControladorAnunciante();
	}

	private IDAOAnunciante anuncianteDAO;


	private ControladorAnunciante() {
		this.anuncianteDAO = DAOFactory.getDAOAnunciante();

	}

	public static IControladorAnunciante obterInstancia() {
		return instancia;
	}

	@Override
	public void inserirReserva(Anunciante anunciante) throws DaoException {		
		
		anuncianteDAO.inserir(anunciante);
	
	}

	@Override
	public void removerAnunciante(Anunciante anunciante) throws DaoException, RegistroInexistenteException  {
		
		this.anuncianteDAO.validarRegistro(anunciante.getIdAnunciante());
		this.anuncianteDAO.remover(anunciante);
	
	}

	@Override
	public void atualizarAnunciante(Anunciante anunciante) throws DaoException  {
		
		this.anuncianteDAO.atualizar(anunciante);
	}

	@Override
	public Anunciante pesquisarAnunciantePorId(Integer id) {
		return this.anuncianteDAO.pesquisarPorId(id);
	}

	@Override
	public List<Anunciante> buscarAnuncinates() {
		return this.anuncianteDAO.buscarTodos();
	}
	
}
