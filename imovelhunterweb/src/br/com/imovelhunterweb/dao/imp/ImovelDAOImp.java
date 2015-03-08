package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.ImovelDAO;
import br.com.imovelhunterweb.entitys.Imovel;

@Repository("ImovelDAO")
public class ImovelDAOImp extends GenericDAOImp<Imovel,Serializable> implements ImovelDAO,Serializable{
	

}