package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.BloqueioDAO;
import br.com.imovelhunterweb.entitys.Bloqueio;
import br.com.imovelhunterweb.service.BloqueioService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("bloqueioService")
public class BloqueioServiceImp implements BloqueioService,Serializable {


	
	@Resource(name = "bloqueioDAO")
	private BloqueioDAO bloqueioDAO;

	@Override
	@Transactional
	@Rollback
	public Bloqueio inserir(Bloqueio bloqueio) {
		return this.bloqueioDAO.insert(bloqueio);
	}

	@Override
	@Transactional
	@Rollback
	public Bloqueio atualizar(Bloqueio bloqueio) {
		return this.bloqueioDAO.update(bloqueio);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Bloqueio bloqueio) {
		try{
			this.bloqueioDAO.remove(bloqueio);
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
	public Boolean removerPorId(Bloqueio bloqueio) {
		try{
			this.bloqueioDAO.removeById(bloqueio.getIdBloqueio());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Bloqueio buscarPorId(ID id) {
		return this.bloqueioDAO.findById(id);
	}

	@Override
	public List<Bloqueio> listarTodos() {
		return this.bloqueioDAO.findAll();
	}

	@Override
	public List<Bloqueio> listarTodos(int index, int quantidade) {
		return this.bloqueioDAO.findAll(index,quantidade);
	}
	
	
}
