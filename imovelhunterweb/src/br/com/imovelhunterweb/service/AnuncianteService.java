package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Anunciante;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface AnuncianteService {
	
	Anunciante inserir(Anunciante anunciante);
	
	Anunciante atualizar(Anunciante anunciante);
	
	Boolean remover(Anunciante anunciante);
	
	Boolean removerPorId(Anunciante anunciante);
	
	Anunciante buscarPorId(ID id);
	
	List<Anunciante> listarTodos();

	List<Anunciante> listarTodos(int index,int quantidade);
	
}
