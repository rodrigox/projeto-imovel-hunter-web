package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Contato;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface ContatoService {
	
	Contato inserir(Contato contato);
	
	Contato atualizar(Contato contato);
	
	Boolean remover(Contato contato);
	
	Boolean removerPorId(Contato contato);
	
	Contato buscarPorId(ID id);
	
	List<Contato> listarTodos();

	List<Contato> listarTodos(int index,int quantidade);
	
}
