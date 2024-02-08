/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cptm.sc.modelos.material;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.cptm.sc.modelos.material.andamento.AndamentoRN;

/**
 *
 * @author fabiolu
 */
public class MaterialRN {

    private final MaterialJDBC mjdbc;

    public MaterialRN() {
        this.mjdbc = new MaterialJDBC();
    }

    public void salvar(Material material) {

        try {
            if (material.getId() == 0) {
                mjdbc.salvar(material);
            } else {
                mjdbc.editar(material);
                
            }
        } catch (SQLException ex) {
        	Logger.getLogger(MaterialRN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public Material buscar(long id) {
        try {
            Material material = this.mjdbc.buscarMaterial(id);
            material.setListaAndamento(new AndamentoRN().lista(id));
            return material;
        } catch (SQLException ex) {
            Logger.getLogger(MaterialRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public List<Material> listar() {
        try {
            return this.mjdbc.listar();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    public void excluir(long id) {
        try {
            this.mjdbc.excluir(id);
        } catch (SQLException ex) {
            Logger.getLogger(MaterialRN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
