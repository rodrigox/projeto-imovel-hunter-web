package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.GrupoCaracteristicaDAO;
import br.com.imovelhunterweb.entitys.GrupoCaracteristica;
//import br.com.imovelhunterweb.service.GrupoCaracteristicaService;
import br.com.imovelhunterweb.service.GrupoCarecteristicaService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("grupoCaracteristicaService")
public class GrupoCaracteristicaServiceImp implements GrupoCarecteristicaService,Serializable {

	/**
	 * 
	 */
	
	
	@Resource(name = "grupoCaracteristicaDAO")
	private GrupoCaracteristicaDAO grupoCaracteristicaDAO;

	@Override
	@Transactional
	@Rollback
	public GrupoCaracteristica inserir(GrupoCaracteristica grupoCaracteristica) {
		return this.grupoCaracteristicaDAO.insert(grupoCaracteristica);
	}

	@Override
	@Transactional
	@Rollback
	public GrupoCaracteristica atualizar(GrupoCaracteristica grupoCaracteristica) {
		return this.grupoCaracteristicaDAO.update(grupoCaracteristica);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(GrupoCaracteristica grupoCaracteristica) {
		try{
			this.grupoCaracteristicaDAO.remove(grupoCaracteristica);
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
	public Boolean removerPorId(GrupoCaracteristica grupoCaracteristica) {
		try{
			this.grupoCaracteristicaDAO.removeById(grupoCaracteristica.getIdGrupoCaracteristica());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public GrupoCaracteristica buscarPorId(ID id) {
		return this.grupoCaracteristicaDAO.findById(id);
	}

	@Override
	public List<GrupoCaracteristica> listarTodos() {
		return this.grupoCaracteristicaDAO.findAll();
	}

	@Override
	public List<GrupoCaracteristica> listarTodos(int index, int quantidade) {
		return this.grupoCaracteristicaDAO.findAll(index,quantidade);
	}
	
	
}
