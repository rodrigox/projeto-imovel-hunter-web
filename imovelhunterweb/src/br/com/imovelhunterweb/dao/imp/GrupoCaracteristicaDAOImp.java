package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.GrupoCaracteristicaDAO;
import br.com.imovelhunterweb.entitys.GrupoCaracteristica;

@Repository("grupoCaracteristicaDAO")
public class GrupoCaracteristicaDAOImp extends GenericDAOImp<GrupoCaracteristica,Serializable> implements GrupoCaracteristicaDAO,Serializable{
	

}