package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.PontoGeografico;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface PontoGeograficoService {
	
	PontoGeografico inserir(PontoGeografico pontoGeografico);
	
	PontoGeografico atualizar(PontoGeografico pontoGeografico);
	
	Boolean remover(PontoGeografico pontoGeografico);
	
	Boolean removerPorId(PontoGeografico pontoGeografico);
	
	PontoGeografico buscarPorId(ID id);
	
	List<PontoGeografico> listarTodos();

	List<PontoGeografico> listarTodos(int index,int quantidade);
	
}
