package br.com.imovelhunterweb.dao.imp;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.imovelhunterweb.dao.UsuarioDAO;
import br.com.imovelhunterweb.entitys.Usuario;

@Repository("usuarioDAO")
public class UsuarioDAOImp extends GenericDAOImp<Usuario,Serializable> implements UsuarioDAO,Serializable{
	

}