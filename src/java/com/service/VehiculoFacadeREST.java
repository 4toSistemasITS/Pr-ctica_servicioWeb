/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Usuario;
import com.model.Vehiculo;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Valeria
 */
@Stateless
@Path("com.model.vehiculo")
public class VehiculoFacadeREST extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public VehiculoFacadeREST() {
        super(Vehiculo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Vehiculo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Vehiculo entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Vehiculo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Vehiculo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Vehiculo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("tipo") String tipo,@FormParam("marca") String marca,@FormParam("placa") String placa,
            @FormParam("eliminado") boolean eliminado,@FormParam("fecha") Date fecha,@FormParam("modelo") String modelo,@FormParam("color") String color) {
        String mensaje="{\"exitoso\":false}";
        try{
            if (reporte(placa,marca,modelo,color)== null){
                create(new Vehiculo(placa,marca,modelo,color));
                mensaje="{\"exitoso\":true}"; 
            }      
        }catch(Exception e){           
        }
            
        return mensaje;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        public Vehiculo reporte(String placa,String marca, String modelo, String color) {
        Vehiculo v =null;
        TypedQuery<Vehiculo> qry;
            qry = getEntityManager().createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :placa and v.marca = :marca and  v.modelo = :modelo and v.color = :color", Vehiculo.class);
            qry.setParameter("placa", placa);
            qry.setParameter("marca", marca);
            qry.setParameter("modelo", modelo);
            qry.setParameter("color", color);
            
        try {
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    
}
