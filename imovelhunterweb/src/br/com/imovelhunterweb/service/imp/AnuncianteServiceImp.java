package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.AnuncianteDAO;
import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.service.AnuncianteService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("anuncianteService")
public class AnuncianteServiceImp implements AnuncianteService,Serializable {

	
	@Resource(name = "anuncianteDAO")
	private AnuncianteDAO anuncianteDAO;

	@Override
	@Transactional
	@Rollback
	public Anunciante inserir(Anunciante anunciante) {
		return this.anuncianteDAO.insert(anunciante);
	}

	@Override
	@Transactional
	@Rollback
	public Anunciante atualizar(Anunciante anunciante) {
		return this.anuncianteDAO.update(anunciante);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Anunciante anunciante) {
		try{
			this.anuncianteDAO.remove(anunciante);
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
	public Boolean removerPorId(Anunciante anunciante) {
		try{
			this.anuncianteDAO.removeById(anunciante.getIdAnunciante());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Anunciante buscarPorId(ID id) {
		return this.anuncianteDAO.findById(id);
	}

	@Override
	public List<Anunciante> listarTodos() {
		return this.anuncianteDAO.findAll();
	}

	@Override
	public List<Anunciante> listarTodos(int index, int quantidade) {
		return this.anuncianteDAO.findAll(index,quantidade);
	}
	
	
}
