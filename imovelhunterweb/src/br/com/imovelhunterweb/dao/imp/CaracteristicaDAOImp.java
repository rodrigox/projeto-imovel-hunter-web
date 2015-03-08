package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.CarcateristicaDAO;
import br.com.imovelhunterweb.entitys.Caracteristica;

@Repository("CaracteristicaDAO")
public class CaracteristicaDAO extends GenericDAOImp<Caracteristica,Serializable> implements CaracteristicaDAO,Serializable{
	
	
}
