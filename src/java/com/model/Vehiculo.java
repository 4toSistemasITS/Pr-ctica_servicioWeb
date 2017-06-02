/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Valeria
 */
@Entity
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
    , @NamedQuery(name = "Vehiculo.findByPkVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.pkVehiculo = :pkVehiculo")
    , @NamedQuery(name = "Vehiculo.findByTipo", query = "SELECT v FROM Vehiculo v WHERE v.tipo = :tipo")
    , @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca")
    , @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa")
    , @NamedQuery(name = "Vehiculo.findByEliminado", query = "SELECT v FROM Vehiculo v WHERE v.eliminado = :eliminado")
    , @NamedQuery(name = "Vehiculo.findByFechaCreacion", query = "SELECT v FROM Vehiculo v WHERE v.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo")
    , @NamedQuery(name = "Vehiculo.findByColor", query = "SELECT v FROM Vehiculo v WHERE v.color = :color")})
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_vehiculo")
    private Integer pkVehiculo;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "marca")
    private String marca;
    @Size(max = 45)
    @Column(name = "placa")
    private String placa;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 45)
    @Column(name = "color")
    private String color;

    public Vehiculo() {
    }

    public Vehiculo(String tipo, String marca, String placa, Boolean eliminado, Date fechaCreacion, String modelo, String color) {
        this.tipo = tipo;
        this.marca = marca;
        this.placa = placa;
        this.eliminado = eliminado;
        this.fechaCreacion = fechaCreacion;
        this.modelo = modelo;
        this.color = color;
    }
    


    

    public Vehiculo(Integer pkVehiculo) {
        this.pkVehiculo = pkVehiculo;
    }

    public Integer getPkVehiculo() {
        return pkVehiculo;
    }

    public void setPkVehiculo(Integer pkVehiculo) {
        this.pkVehiculo = pkVehiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVehiculo != null ? pkVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.pkVehiculo == null && other.pkVehiculo != null) || (this.pkVehiculo != null && !this.pkVehiculo.equals(other.pkVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Vehiculo[ pkVehiculo=" + pkVehiculo + " ]";
    }
    
}
