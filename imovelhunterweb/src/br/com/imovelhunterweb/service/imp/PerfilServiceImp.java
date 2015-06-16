package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.PerfilDAO;
import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.entitys.Perfil;
import br.com.imovelhunterweb.enums.SituacaoImovel;
import br.com.imovelhunterweb.enums.TipoImovel;
import br.com.imovelhunterweb.service.PerfilService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("perfilService")
public class PerfilServiceImp implements PerfilService,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1745434852488883038L;
	
	@Resource(name = "perfilDAO")
	private PerfilDAO perfilDao;
	

	@Override
	@Transactional
	@Rollback
	public Perfil inserir(Perfil perfil) {
		
		Perfil perfil2 = new Perfil();
	
		perfil2.setIdPerfil(perfil.getIdPerfil());
		perfil2.setBairro(perfil.getBairro());
		perfil2.setCidade(perfil.getCidade());
		perfil2.setQtQuartos(perfil.getQtQuartos());
		perfil2.setSituacaoImovel(perfil.getSituacaoImovel());
		perfil2.setTipo(perfil.getTipo());
		perfil2.setUf(perfil.getUf());
		perfil2.setValor(perfil.getValor());
		perfil2.setUsuario(perfil.getUsuario());
		
		perfil2 = this.perfilDao.insert(perfil2);
			
		return perfil2;
	}

	@Override
	@Transactional
	@Rollback
	public Perfil atualizar(Perfil perfil) {

		return this.perfilDao.update(perfil);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Perfil perfil) {
			
		try{
				this.perfilDao.remove(perfil);
			}
		catch(Exception ex){
		
				ex.printStackTrace();
				return false;
		}
		
		return true;
	}

	@Override
	@Transactional
	@Rollback
	public Boolean removerPorId(Perfil perfil) {

		try{
			this.perfilDao.removeById(perfil.getIdPerfil());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Perfil buscarPorId(ID id) {

		Perfil p =  this.perfilDao.findById(id);
		return p;
		
	}

	@Override
	public List<Perfil> listarTodos() {
		
		return this.perfilDao.findAll();
	}

	@Override
	public List<Perfil> listarTodos(int index, int quantidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public PerfilDAO getPerfilDao() {
		return perfilDao;
	}

	public void setPerfilDao(PerfilDAO perfilDao) {
		this.perfilDao = perfilDao;
	}

	@Override
	public List<Perfil> listarPerfilPorImovel(Imovel imovel) {		
		
		String query = "FROM Perfil p WHERE ";
		
		Map<String,Object> parametros = new HashMap<String,Object>();
		
		
		if(imovel.getSituacaoImovel().equals(SituacaoImovel.LOCACAO)){
			/*
		<item>Ate R$ 1000</item>
        <item>De R$ 1001 a R$ 1500</item>
        <item>De R$ 1501 a R$ 2000</item>
        <item>De R$ 2001 a R$ 2500</item>
        <item>De R$ 2501 a R$ 3000</item>
        <item>De R$ 3001 a R$ 5000</item>*/
			parametros.put("situacaoImovel",SituacaoImovel.LOCACAO);
			query += "p.situacaoImovel=:situacaoImovel ";
			
			double precoImovel = imovel.getPreco();
			
			if(precoImovel <= 1000d){
				query += "AND (p.valor = 1 OR  p.valor = 0) ";
			}else if(1001d < precoImovel && precoImovel <= 1500d){
				query += "AND (p.valor = 2 OR  p.valor = 0) ";
			}else if(1501d < precoImovel && precoImovel <= 2000d){
				query += "AND (p.valor = 3 OR  p.valor = 0) ";
			}else if(2001d < precoImovel && precoImovel <= 2500d){
				query += "AND (p.valor = 4 OR  p.valor = 0) ";
			}else if(2501d < precoImovel && precoImovel <= 3000d){
				query += "AND (p.valor = 5 OR  p.valor = 0) ";
			}else if(3001d < precoImovel && precoImovel <= 5000d){
				query += "AND (p.valor = 6 OR  p.valor = 0) ";
			}else if(precoImovel > 5000d){
				query += "AND (p.valor = 7 OR  p.valor = 0) ";
			}
			
		}else if(imovel.getSituacaoImovel().equals(SituacaoImovel.VENDA)){
			/*
		<item>Ate R$ 135 mil</item>
        <item>De R$ 136 mil a R$ 250 mil</item>
        <item>De R$ 251 mil a R$ 350</item>
        <item>De R$ 350 mil a R$ 500 mil</item>
        <item>De R$ 501 mil a R$ 700 mil</item>
        <item>De R$ 701 mil a R$ 1 milhão</item>
        <item>Acima de R$ 1 milhão</item>*/
			
			parametros.put("situacaoImovel",SituacaoImovel.VENDA);
			query += "p.situacaoImovel=:situacaoImovel ";
			double precoImovel = imovel.getPreco();
			
			if(precoImovel <= 135000.0){
				query += "AND (p.valor = 1 OR  p.valor = 0) ";
			}else if(precoImovel > 135000d && precoImovel <= 250000d){
				query += "AND (p.valor = 2 OR  p.valor = 0) ";
			}else if(precoImovel > 250000d && precoImovel <=  350000d){
				query += "AND (p.valor = 3 OR  p.valor = 0) ";
			}else if(precoImovel > 350000d && precoImovel <=  500000d){
				query += "AND (p.valor = 4 OR  p.valor = 0) ";
			}else if(precoImovel > 500000d && precoImovel <=  700000d){
				query += "AND (p.valor = 5 OR  p.valor = 0) ";
			}else if(precoImovel > 700000d && precoImovel <=  1000000d){
				query += "AND (p.valor = 6 OR  p.valor = 0) ";
			}else if(precoImovel > 1000000d){
				query += "AND (p.valor = 7 OR  p.valor = 0) ";
			}
			
		}
		
		
		parametros.put("uf","%"+imovel.getEstado().toUpperCase()+"%");
		query += "AND UPPER(p.uf) LIKE :uf ";
		
		parametros.put("cidade","%"+imovel.getCidade().toUpperCase()+"%");
		query += "AND UPPER(p.cidade) LIKE :cidade ";
		
		parametros.put("bairro","%"+imovel.getBairro().toUpperCase()+"%");
		query += "AND UPPER(p.bairro) LIKE :bairro ";
		
		Integer qtdQUartos = imovel.getNumeroDeQuartos();
		
		//parametros.put("nQuartos", qtdQUartos);
		
		/*
		<item>1 quarto</item>
        <item>2 quartos</item>
        <item>3 quartos</item>
        <item>4 quartos</item>
        <item>5 ou mais quartos</item>*/
		
		if(qtdQUartos != null){
			if(qtdQUartos == 1){
				query += "AND (p.qtQuartos = 1 OR p.qtQuartos IS NULL OR p.qtQuartos = 0) ";
			}else if(qtdQUartos == 2){
				query += "AND (p.qtQuartos = 2 OR p.qtQuartos IS NULL OR p.qtQuartos = 0) ";
			}else if(qtdQUartos == 3){
				query += "AND (p.qtQuartos = 3 OR p.qtQuartos IS NULL OR p.qtQuartos = 0) ";
			}else if(qtdQUartos == 4){
				query += "AND (p.qtQuartos = 4 OR p.qtQuartos IS NULL OR p.qtQuartos = 0) ";
			}else if(qtdQUartos >= 5){
				query += "AND (p.qtQuartos >= 5 OR p.qtQuartos IS NULL OR p.qtQuartos = 0) ";
			}
		}
		
		try{
			TipoImovel tipoImovel = imovel.getTipoImovel();
			
			parametros.put("tipoImovel",tipoImovel);
			query += "AND (p.tipo=:tipoImovel OR p.tipo IS NULL)";
		}
		catch(Exception ex){
			ex.printStackTrace();
			
			
		}
		
		
	
		
		System.out.println(query);
		
		return this.perfilDao.useQuery(query,parametros);
	}
	
	

}
