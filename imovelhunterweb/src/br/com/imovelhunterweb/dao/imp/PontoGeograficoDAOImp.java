package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.PontoGeograficoDAO;
import br.com.imovelhunterweb.entitys.PontoGeografico;

@Repository("pontoGeograficoDAO")
public class PontoGeograficoDAOImp extends GenericDAOImp<PontoGeografico,Serializable> implements PontoGeograficoDAO,Serializable{
	private static final long serialVersionUID = -7202061051650350481L;
}
