package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.CaracteristicaDAO;

import br.com.imovelhunterweb.entitys.Caracteristica;

@Repository("caracteristicaDAO")
public class CaracteristicaDAOImp extends GenericDAOImp<Caracteristica,Serializable> implements CaracteristicaDAO,Serializable{
	

}
