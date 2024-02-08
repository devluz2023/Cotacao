/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.controller;

import br.com.cptm.sc.modelos.usuario.Usuario;
import br.com.cptm.sc.modelos.usuario.UsuarioRN;
import br.com.cptm.sc.modelos.usuario.util.UsuarioUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabiolu
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private List<Usuario> usuarios;
	private boolean editar;

	@PostConstruct
	public void init() {
		this.usuario = new UsuarioUtil().getUsuario();
	}

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		if (this.usuario.getSenha().equals(this.usuario.getC_senha())) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Usuário Alterado com sucesso"));
			this.usuario = new Usuario();
			return "home.xhtml";
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Senhas Não conferem"));
			return "perfil.xhtml";
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new UsuarioRN().listar();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

}
