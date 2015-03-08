package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.ImovelDAO;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.service.ImovelService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("imovelService")
public class ImovelServiceImp implements ImovelService,Serializable {

	/**
	 * 
	 */
	
	
	@Resource(name = "imovelDAO")
	private ImovelDAO imovelDAO;

	@Override
	@Transactional
	@Rollback
	public Imovel inserir(Imovel imovel) {
		return this.imovelDAO.insert(imovel);
	}

	@Override
	@Transactional
	@Rollback
	public Imovel atualizar(Imovel imovel) {
		return this.imovelDAO.update(imovel);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Imovel imovel) {
		try{
			this.imovelDAO.remove(imovel);
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
	public Boolean removerPorId(Imovel imovel) {
		try{
			this.imovelDAO.removeById(imovel.getIdImovel());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Imovel buscarPorId(ID id) {
		return this.imovelDAO.findById(id);
	}

	@Override
	public List<Imovel> listarTodos() {
		return this.imovelDAO.findAll();
	}

	@Override
	public List<Imovel> listarTodos(int index, int quantidade) {
		return this.imovelDAO.findAll(index,quantidade);
	}
	
	
}
