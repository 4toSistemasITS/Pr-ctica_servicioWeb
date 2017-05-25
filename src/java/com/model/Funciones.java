/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valeria
 */
@Entity
@Table(name = "funciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funciones.findAll", query = "SELECT f FROM Funciones f")
    , @NamedQuery(name = "Funciones.findByIdfunciones", query = "SELECT f FROM Funciones f WHERE f.idfunciones = :idfunciones")
    , @NamedQuery(name = "Funciones.findByEliminado", query = "SELECT f FROM Funciones f WHERE f.eliminado = :eliminado")})
public class Funciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfunciones")
    private Integer idfunciones;
    @Column(name = "eliminado")
    private Short eliminado;

    public Funciones() {
    }

    public Funciones(Integer idfunciones) {
        this.idfunciones = idfunciones;
    }

    public Integer getIdfunciones() {
        return idfunciones;
    }

    public void setIdfunciones(Integer idfunciones) {
        this.idfunciones = idfunciones;
    }

    public Short getEliminado() {
        return eliminado;
    }

    public void setEliminado(Short eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfunciones != null ? idfunciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funciones)) {
            return false;
        }
        Funciones other = (Funciones) object;
        if ((this.idfunciones == null && other.idfunciones != null) || (this.idfunciones != null && !this.idfunciones.equals(other.idfunciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Funciones[ idfunciones=" + idfunciones + " ]";
    }
    
}
