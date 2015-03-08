package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.CaracteristicaDAO;
import br.com.imovelhunterweb.entitys.Caracteristica;
import br.com.imovelhunterweb.service.CaracteristicaService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("caracteristicaService")
public class CaracteristicaServiceImp implements CaracteristicaService,Serializable {

	@Resource(name = "caracteristicaDAO")
	private CaracteristicaDAO caracteristicaDAO;

	@Override
	@Transactional
	@Rollback
	public Caracteristica inserir(Caracteristica caracteristica) {
		return this.caracteristicaDAO.insert(caracteristica);
	}

	@Override
	@Transactional
	@Rollback
	public Caracteristica atualizar(Caracteristica caracteristica) {
		return this.caracteristicaDAO.update(caracteristica);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Caracteristica caracteristica) {
		try{
			this.caracteristicaDAO.remove(caracteristica);
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
	public Boolean removerPorId(Caracteristica caracteristica) {
		try{
			this.caracteristicaDAO.removeById(caracteristica.getIdCaracteristica());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Caracteristica buscarPorId(ID id) {
		return this.caracteristicaDAO.findById(id);
	}

	@Override
	public List<Caracteristica> listarTodos() {
		return this.caracteristicaDAO.findAll();
	}

	@Override
	public List<Caracteristica> listarTodos(int index, int quantidade) {
		return this.caracteristicaDAO.findAll(index,quantidade);
	}
	
	
}
