package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.ContatoDAO;
import br.com.imovelhunterweb.entitys.Contato;
import br.com.imovelhunterweb.service.ContatoService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("contatoService")
public class ContatoServiceImp implements ContatoService,Serializable {

	/**
	 * 
	 */
	
	
	@Resource(name = "contatoDAO")
	private ContatoDAO contatoDAO;

	@Override
	@Transactional
	@Rollback
	public Contato inserir(Contato contato) {
		return this.contatoDAO.insert(contato);
	}

	@Override
	@Transactional
	@Rollback
	public Contato atualizar(Contato contato) {
		return this.contatoDAO.update(contato);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Contato contato) {
		try{
			this.contatoDAO.remove(contato);
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
	public Boolean removerPorId(Contato contato) {
		try{
			this.contatoDAO.removeById(contato.getIdContato());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Contato buscarPorId(ID id) {
		return this.contatoDAO.findById(id);
	}

	@Override
	public List<Contato> listarTodos() {
		return this.contatoDAO.findAll();
	}

	@Override
	public List<Contato> listarTodos(int index, int quantidade) {
		return this.contatoDAO.findAll(index,quantidade);
	}
	
	
}
