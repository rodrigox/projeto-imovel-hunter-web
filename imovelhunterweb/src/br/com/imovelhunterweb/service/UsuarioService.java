package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Usuario;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface UsuarioService {
	
	Usuario inserir(Usuario usuario);
	
	Usuario atualizar(Usuario usuario);
	
	Boolean remover(Usuario usuario);
	
	Boolean removerPorId(Usuario usuario);
	
	Usuario buscarPorId(ID id);
	
	List<Usuario> listarTodos();

	List<Usuario> listarTodos(int index,int quantidade);
	
}
