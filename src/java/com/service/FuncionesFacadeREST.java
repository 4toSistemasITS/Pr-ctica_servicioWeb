/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Funciones;
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
@Path("com.model.funciones")
public class FuncionesFacadeREST extends AbstractFacade<Funciones> {

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public FuncionesFacadeREST() {
        super(Funciones.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Funciones entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Funciones entity) {
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
    public Funciones find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Funciones> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Funciones> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @POST
    @Path("consultarValidos")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
     public List<Funciones> consultaValidos(@PathParam("eliminado")  int eliminado) {
         List<Funciones>retorno=obtenerEliminado(eliminado);
         return retorno;
     }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    List<Funciones>obtenerEliminado(int valor){
        TypedQuery<Funciones> qry;
        qry=getEntityManager().createQuery("SELECT f FROM Funciones f WHERE f.eliminado = :eliminado", Funciones.class);
        qry.setParameter("eliminado", valor);
        try{
            return qry.getResultList();
        }catch(NoResultException e){
            return null;
        }
   }
}
