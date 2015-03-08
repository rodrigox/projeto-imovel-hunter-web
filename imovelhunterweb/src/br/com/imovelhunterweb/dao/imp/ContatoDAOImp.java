package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.ContatoDAO;
import br.com.imovelhunterweb.entitys.Contato;

@Repository("ContatoDAO")
public class ContatoDAOImp extends GenericDAOImp<Contato,Serializable> implements ContatoDAO,Serializable{
	

}