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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valeria
 */
@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")
    , @NamedQuery(name = "Profesor.findByPkProfesor", query = "SELECT p FROM Profesor p WHERE p.pkProfesor = :pkProfesor")
    , @NamedQuery(name = "Profesor.findByNomProfesor", query = "SELECT p FROM Profesor p WHERE p.nomProfesor = :nomProfesor")
    , @NamedQuery(name = "Profesor.findByApellidoProfesor", query = "SELECT p FROM Profesor p WHERE p.apellidoProfesor = :apellidoProfesor")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_profesor")
    private Integer pkProfesor;
    @Size(max = 100)
    @Column(name = "nom_profesor")
    private String nomProfesor;
    @Size(max = 45)
    @Column(name = "apellido_profesor")
    private String apellidoProfesor;

    public Profesor() {
    }

    public Profesor(Integer pkProfesor) {
        this.pkProfesor = pkProfesor;
    }

    public Integer getPkProfesor() {
        return pkProfesor;
    }

    public void setPkProfesor(Integer pkProfesor) {
        this.pkProfesor = pkProfesor;
    }

    public String getNomProfesor() {
        return nomProfesor;
    }

    public void setNomProfesor(String nomProfesor) {
        this.nomProfesor = nomProfesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProfesor != null ? pkProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.pkProfesor == null && other.pkProfesor != null) || (this.pkProfesor != null && !this.pkProfesor.equals(other.pkProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Profesor[ pkProfesor=" + pkProfesor + " ]";
    }
    
}
