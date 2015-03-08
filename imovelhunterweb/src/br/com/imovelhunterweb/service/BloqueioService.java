package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Bloqueio;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface BloqueioService {
	
	Bloqueio inserir(Bloqueio bloqueio);
	
	Bloqueio atualizar(Bloqueio bloqueio);
	
	Boolean remover(Bloqueio bloqueio);
	
	Boolean removerPorId(Bloqueio bloqueio);
	
	Bloqueio buscarPorId(ID id);
	
	List<Bloqueio> listarTodos();

	List<Bloqueio> listarTodos(int index,int quantidade);
	
}
