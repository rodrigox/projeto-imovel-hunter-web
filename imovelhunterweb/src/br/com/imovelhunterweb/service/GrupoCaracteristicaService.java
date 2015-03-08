package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.GrupoCarecteristica;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface GrupoCarecteristicaService {
	
	GrupoCarecteristica inserir(GrupoCarecteristica grupoCarecteristica);
	
	GrupoCarecteristica atualizar(GrupoCarecteristica grupoCarecteristica);
	
	Boolean remover(GrupoCarecteristica grupoCarecteristica);
	
	Boolean removerPorId(GrupoCarecteristica grupoCarecteristica);
	
	GrupoCarecteristica buscarPorId(ID id);
	
	List<GrupoCarecteristica> listarTodos();

	List<GrupoCarecteristica> listarTodos(int index,int quantidade);
	
}
