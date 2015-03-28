package br.com.imovelhunterweb.service;

import java.util.List;

import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Imovel;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface ImovelService {
	
	Imovel inserir(Imovel imovel);
	
	Imovel atualizar(Imovel imovel);
	
	Boolean remover(Imovel imovel);
	
	Boolean removerPorId(Imovel imovel);
	
	Imovel buscarPorId(long l);
	
	List<Imovel> listarTodos();

	List<Imovel> listarTodos(int index,int quantidade);
	
	List<Imovel> listarImoveisDoAnunciante(Anunciante anunciante);

	
}
