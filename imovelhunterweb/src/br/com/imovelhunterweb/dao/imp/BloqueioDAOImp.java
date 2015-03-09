package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.BloqueioDAO;
import br.com.imovelhunterweb.entitys.Bloqueio;

@Repository("bloqueioDAO")
public class BloqueioDAOImp extends GenericDAOImp<Bloqueio,Serializable> implements BloqueioDAO,Serializable{
	

}