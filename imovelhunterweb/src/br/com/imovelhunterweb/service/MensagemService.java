package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Mensagem;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface MensagemService {
	
	Mensagem inserir(Mensagem mensagem);
	
	Mensagem atualizar(Mensagem mensagem);
	
	Boolean remover(Mensagem mensagem);
	
	Boolean removerPorId(Mensagem mensagem);
	
	Mensagem buscarPorId(ID id);
	
	List<Mensagem> listarTodos();

	List<Mensagem> listarTodos(int index,int quantidade);
	
}
