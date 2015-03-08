package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.ImagemDAO;
import br.com.imovelhunterweb.entitys.Imagem;

@Repository("ImagemDAO")
public class ImagemDAOImp extends GenericDAOImp<Imagem,Serializable> implements ImagemDAO,Serializable{
	

}