package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.GrupoCaracteristica;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface GrupoCarecteristicaService {
	
	GrupoCaracteristica inserir(GrupoCaracteristica grupoCarecteristica);
	
	GrupoCaracteristica atualizar(GrupoCaracteristica grupoCarecteristica);
	
	Boolean remover(GrupoCaracteristica grupoCarecteristica);
	
	Boolean removerPorId(GrupoCaracteristica grupoCarecteristica);
	
	GrupoCaracteristica buscarPorId(ID id);
	
	List<GrupoCaracteristica> listarTodos();

	List<GrupoCaracteristica> listarTodos(int index,int quantidade);
	
}
