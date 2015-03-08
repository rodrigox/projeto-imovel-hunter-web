package br.com.imovelhunterweb.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.imovelhunterweb.dao.UsuarioDAO;
import br.com.imovelhunterweb.entitys.Usuario;
import br.com.imovelhunterweb.service.UsuarioService;

import com.sun.xml.internal.bind.v2.model.core.ID;

@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService,Serializable {

	
	@Resource(name = "usuarioDAO")
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	@Rollback
	public Usuario inserir(Usuario usuario) {
		return this.usuarioDAO.insert(usuario);
	}

	@Override
	@Transactional
	@Rollback
	public Usuario atualizar(Usuario usuario) {
		return this.usuarioDAO.update(usuario);
	}

	@Override
	@Transactional
	@Rollback
	public Boolean remover(Usuario usuario) {
		try{
			this.usuarioDAO.remove(usuario);
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
	public Boolean removerPorId(Usuario usuario) {
		try{
			this.usuarioDAO.removeById(usuario.getIdUsuario());
		}
		catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Usuario buscarPorId(ID id) {
		return this.usuarioDAO.findById(id);
	}

	@Override
	public List<Usuario> listarTodos() {
		return this.usuarioDAO.findAll();
	}

	@Override
	public List<Usuario> listarTodos(int index, int quantidade) {
		return this.usuarioDAO.findAll(index,quantidade);
	}
	
	
}
