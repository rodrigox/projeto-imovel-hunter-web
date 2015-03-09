package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.AnuncianteDAO;
import br.com.imovelhunterweb.entitys.Anunciante;

@Repository("anuncianteDAO")
public class AnuncianteDAOImp extends GenericDAOImp<Anunciante,Serializable> implements AnuncianteDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3444869977638262537L;

}
