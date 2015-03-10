package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.AnuncianteDAO;
import br.com.imovelhunterweb.entitys.Anunciante;
import br.com.imovelhunterweb.entitys.Usuario;
import br.com.imovelhunterweb.service.AnuncianteService;
import br.com.imovelhunterweb.util.Criptografar;
import br.com.imovelhunterweb.util.Email;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("anuncianteService")
public class AnuncianteServiceImp implements AnuncianteService,Serializable {

	
	@Resource(name = "anuncianteDAO")
	private AnuncianteDAO anuncianteDAO;

	@Override
	@Transactional
	@Rollback
	public Anunciante inserir(Anunciante anunciante) {
		anunciante.setSenha(br.com.imovelhunterweb.util.Criptografar.gerarMd5(anunciante.getSenha()));
		return this.anuncianteDAO.insert(anunciante);
	}

	@Override
	@Transactional
	@Rollback
	public Anunciante atualizar(Anunciante anunciante) {
		anunciante.setSenha(br.com.imovelhunterweb.util.Criptografar.gerarMd5(anunciante.getSenha()));
		return this.anuncianteDAO.update(anunciante);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Anunciante anunciante) {
		try{
			this.anuncianteDAO.remove(anunciante);
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
	public Boolean removerPorId(Anunciante anunciante) {
		try{
			this.anuncianteDAO.removeById(anunciante.getIdAnunciante());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Anunciante buscarPorId(ID id) {
		return this.anuncianteDAO.findById(id);
	}

	@Override
	public List<Anunciante> listarTodos() {
		return this.anuncianteDAO.findAll();
	}

	@Override
	public List<Anunciante> listarTodos(int index, int quantidade) {
		return this.anuncianteDAO.findAll(index,quantidade);
	}

	@Override
	@Transactional
	@Rollback
	public String recuperarSenha(String email) {
		
		
		Anunciante anuncianteContemplado = this.buscarPorEmail(email);
		
		if(anuncianteContemplado != null){
			//gera a nova senha criptografa e grava no banco
			String novaSenha = "";			
			for(int n=0;n<=8;n++){
				int valor = (int) Math.round(Math.random()*101);
				if(valor < 50){
					//insere nÃºmero
					novaSenha += String.valueOf(Math.round(Math.random()*9));
				}
				else{
					//insere caracter
					int caracter = (int) Math.round(Math.random()*25);
					caracter = 96 + caracter;
					if(caracter < 97){
						caracter =  97;
					}
					else if(caracter > 122){
						caracter = 122;
					}
					int maiusculo = (int) Math.round(Math.random()*101);
					if(maiusculo > 50){
						//letra em maiÃºsculo
						char letra = (char) caracter;	
						String letraMaiuscula  = new String();
						letraMaiuscula += letra;						
						novaSenha +=  letraMaiuscula.toUpperCase();
					}
					else{
						//letra em minusculo
						char letra = (char) caracter;						
						String letraMinuscula = new String();
						letraMinuscula += letra;						
						novaSenha +=  letraMinuscula.toLowerCase();
					}					
				}
			}
			String senhaCriptografada = Criptografar.gerarMd5(novaSenha);

			String senhaAntiga = anuncianteContemplado.getSenha();
			anuncianteContemplado.setSenha(senhaCriptografada);
			anuncianteContemplado = this.anuncianteDAO.update(anuncianteContemplado);
			
			
			try {							
				Email emaill= new Email("Imovel Hunter");
				emaill.adicionarDestinatario(anuncianteContemplado.getNome(), email);
				emaill.enviar("novaSenha","Imovel Hunter, Sua nova senha: "+novaSenha);
				return "ISua nova senha foi enviada para o e-mail "+email;									
			}
			catch (Exception e) {
				anuncianteContemplado.setSenha(senhaAntiga);
				anuncianteContemplado = this.anuncianteDAO.update(anuncianteContemplado);
				e.printStackTrace();						
				return "EErro na recuperação da senha";								
			}
			
		}
		else{
			return "AE-mail inválido";					
		}	
	}

	@Override
	public Anunciante buscarPorEmail(String email) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("email",email);
		List<Anunciante> anuncs = this.anuncianteDAO.useQuery("FROM Anunciante a WHERE a.email = :email",parametros,0,1);
		return anuncs != null && anuncs.size() > 0 ? anuncs.get(0) : null;
	}

	@Override
	public Anunciante buscarPorLoginESenha(String login, String senha) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("login",login);
		parametros.put("senha",Criptografar.gerarMd5(senha));
		List<Anunciante> anuncs = this.anuncianteDAO.useQuery("FROM Anunciante a WHERE (a.login = :login OR a.email = :login) AND a.senha = :senha",parametros,0,1);
		return anuncs != null && anuncs.size() > 0 ? anuncs.get(0) : null;
	}

	@Override
	public boolean existeLogin(String login) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("login",login);		
		List<Anunciante> anuncs = this.anuncianteDAO.useQuery("FROM Anunciante a WHERE a.login = :login",parametros,0,1);
		return anuncs != null && anuncs.size() > 0 ? true : false;
	}

	@Override
	public boolean existeEmail(String email) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("email",email);		
		List<Anunciante> anuncs = this.anuncianteDAO.useQuery("FROM Anunciante a WHERE a.email = :login",parametros,0,1);
		return anuncs != null && anuncs.size() > 0 ? true : false;
	}
	
	
}
