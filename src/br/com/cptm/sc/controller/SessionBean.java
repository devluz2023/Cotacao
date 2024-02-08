/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabio julio
 */
@ManagedBean
@SessionScoped
public class SessionBean implements Serializable{ 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String sair() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.invalidate();
        return "/restrito/home.xhtml?faces-redirect=true";
    }
}
