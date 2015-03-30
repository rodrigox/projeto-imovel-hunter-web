package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Imagem;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface ImagemService {
	
	Imagem inserir(Imagem imagem);
	
	Imagem atualizar(Imagem imagem);
	
	Boolean remover(Imagem imagem);
	
	Boolean removerPorId(Imagem imagem);
	
	Imagem buscarPorId(long id);
	
	List<Imagem> listarTodos();

	List<Imagem> listarTodos(int index,int quantidade);
	
}
