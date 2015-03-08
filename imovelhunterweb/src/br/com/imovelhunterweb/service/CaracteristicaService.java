package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Caracteristica;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface CaracteristicaService {
	
	Caracteristica inserir(Caracteristica caracteristica);
	
	Caracteristica atualizar(Caracteristica caracteristica);
	
	Boolean remover(Caracteristica caracteristica);
	
	Boolean removerPorId(Caracteristica caracteristica);
	
	Caracteristica buscarPorId(ID id);
	
	List<Caracteristica> listarTodos();

	List<Caracteristica> listarTodos(int index,int quantidade);
	
}
