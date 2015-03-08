package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.ImagemDAO;
import br.com.imovelhunterweb.entitys.Imagem;
import br.com.imovelhunterweb.service.ImagemService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("imagemService")
public class ImagemServiceImp implements ImagemService,Serializable {

	/**
	 * 
	 */
	
	
	@Resource(name = "imagemDAO")
	private ImagemDAO imagemDAO;

	@Override
	@Transactional
	@Rollback
	public Imagem inserir(Imagem imagem) {
		return this.imagemDAO.insert(imagem);
	}

	@Override
	@Transactional
	@Rollback
	public Imagem atualizar(Imagem imagem) {
		return this.imagemDAO.update(imagem);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Imagem imagem) {
		try{
			this.imagemDAO.remove(imagem);
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
	public Boolean removerPorId(Imagem imagem) {
		try{
			this.imagemDAO.removeById(imagem.getIdImagem());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Imagem buscarPorId(ID id) {
		return this.imagemDAO.findById(id);
	}

	@Override
	public List<Imagem> listarTodos() {
		return this.imagemDAO.findAll();
	}

	@Override
	public List<Imagem> listarTodos(int index, int quantidade) {
		return this.imagemDAO.findAll(index,quantidade);
	}
	
	
}
