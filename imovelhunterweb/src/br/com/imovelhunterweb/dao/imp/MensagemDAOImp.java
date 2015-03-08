package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.MensagemDAO;
import br.com.imovelhunterweb.entitys.Mensagem;

@Repository("MensagemDAO")
public class MensagemDAOImp extends GenericDAOImp<Mensagem,Serializable> implements MensagemDAO,Serializable{
	

}