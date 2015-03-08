package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.MensagemDAO;
import br.com.imovelhunterweb.entitys.Mensagem;
import br.com.imovelhunterweb.service.MensagemService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("mensagemService")
public class MensagemServiceImp implements MensagemService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6608442898872230013L;
	
	@Resource(name = "mensagemDAO")
	private MensagemDAO mensagemDAO;

	@Override
	@Transactional
	@Rollback
	public Mensagem inserir(Mensagem mensagem) {
		return this.mensagemDAO.insert(mensagem);
	}

	@Override
	@Transactional
	@Rollback
	public Mensagem atualizar(Mensagem mensagem) {
		return this.mensagemDAO.update(mensagem);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Mensagem mensagem) {
		try{
			this.mensagemDAO.remove(mensagem);
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
	public Boolean removerPorId(Mensagem mensagem) {
		try{
			this.mensagemDAO.removeById(mensagem.getIdMensagem());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Mensagem buscarPorId(ID id) {
		return this.mensagemDAO.findById(id);
	}

	@Override
	public List<Mensagem> listarTodos() {
		return this.mensagemDAO.findAll();
	}

	@Override
	public List<Mensagem> listarTodos(int index, int quantidade) {
		return this.mensagemDAO.findAll(index,quantidade);
	}
	
	
}
