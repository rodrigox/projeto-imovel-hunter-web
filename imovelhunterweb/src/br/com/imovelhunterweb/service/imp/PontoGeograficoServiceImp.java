package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.PontoGeograficoDAO;
import br.com.imovelhunterweb.entitys.PontoGeografico;
import br.com.imovelhunterweb.service.PontoGeograficoService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("pontoGeograficoService")
public class PontoGeograficoServiceImp implements PontoGeograficoService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6608442898872230013L;
	
	@Resource(name = "pontoGeograficoDAO")
	private PontoGeograficoDAO pontoGeograficoDAO;

	@Override
	@Transactional
	@Rollback
	public PontoGeografico inserir(PontoGeografico pontoGeografico) {
		return this.pontoGeograficoDAO.insert(pontoGeografico);
	}

	@Override
	@Transactional
	@Rollback
	public PontoGeografico atualizar(PontoGeografico pontoGeografico) {
		return this.pontoGeograficoDAO.update(pontoGeografico);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(PontoGeografico pontoGeografico) {
		try{
			this.pontoGeograficoDAO.remove(pontoGeografico);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	@Rollback
	public Boolean removerPorId(PontoGeografico pontoGeografico) {
		try{
			this.pontoGeograficoDAO.removeById(pontoGeografico.getIdPontoGeografico());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public PontoGeografico buscarPorId(ID id) {
		return this.pontoGeograficoDAO.findById(id);
	}

	@Override
	public List<PontoGeografico> listarTodos() {
		return this.pontoGeograficoDAO.findAll();
	}

	@Override
	public List<PontoGeografico> listarTodos(int index, int quantidade) {
		return this.pontoGeograficoDAO.findAll(index,quantidade);
	}
	
	
}
