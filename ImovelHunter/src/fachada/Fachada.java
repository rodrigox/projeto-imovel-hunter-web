package fachada;

import java.util.List;

import modelo.Anunciante;
import negocio.anunciante.ControladorAnunciante;
import negocio.anunciante.IControladorAnunciante;

import exceptions.DaoException;
import exceptions.RegistroInexistenteException;


public class Fachada implements IFachada {
	private static IFachada instancia;

	static {
		if (instancia == null)
			instancia = new Fachada();
	}

	private IControladorAnunciante controladorAnunciante;


	private Fachada() {
		this.controladorAnunciante = ControladorAnunciante.obterInstancia();

	}

	public static IFachada obterInstancia() {
		return instancia;
	}

	@Override
	public void inserirReserva(Anunciante anunciante) throws DaoException {
		controladorAnunciante.inserirReserva(anunciante);
		
	}

	@Override
	public void removerAnunciante(Anunciante anunciante) throws DaoException,
			RegistroInexistenteException {
		controladorAnunciante.removerAnunciante(anunciante);
		
	}

	@Override
	public void atualizarAnunciante(Anunciante anunciante) throws DaoException {
		controladorAnunciante.atualizarAnunciante(anunciante);
		
	}

	@Override
	public Anunciante pesquisarAnunciantePorId(Integer id) {
		return controladorAnunciante.pesquisarAnunciantePorId(id);
	}

	@Override
	public List<Anunciante> buscarAnuncinates() {
		return controladorAnunciante.buscarAnuncinates();
	}


}