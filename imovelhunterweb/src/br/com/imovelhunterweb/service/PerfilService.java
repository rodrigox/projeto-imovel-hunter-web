package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.entitys.Perfil;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface PerfilService {
	
	Perfil inserir(Perfil perfil);
	
	Perfil atualizar(Perfil perfil);
	
	Boolean remover(Perfil perfil);
	
	Boolean removerPorId(Perfil perfil);
	
	Perfil buscarPorId(ID id);
	
	List<Perfil> listarPerfilPorImovel(Imovel imovel);
	
	List<Perfil> listarTodos();

	List<Perfil> listarTodos(int index,int quantidade);
	
	
	
	

}