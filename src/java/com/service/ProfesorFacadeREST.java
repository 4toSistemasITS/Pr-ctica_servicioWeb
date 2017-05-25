/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Funciones;
import com.model.Profesor;
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
@Path("com.model.profesor")
public class ProfesorFacadeREST extends AbstractFacade<Profesor> {

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public ProfesorFacadeREST() {
        super(Profesor.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Profesor entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Profesor entity) {
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
    public Profesor find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Profesor> findAll() {
        return super.findAll();
    }
    @POST
    @Path("consulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Profesor> consulta(@FormParam("usuario")String valor,@FormParam("contraseña")int password) {
         List<Profesor> retorno=null;
         if (valor.equals("Lady")&& password==123){
             retorno=super.findAll();
         }
        return retorno;
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Profesor> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @POST
    @Path("contar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String contar(@FormParam("usuario")int valor) {
        String retorno="";
        if(valor ==1){
            return String.valueOf(super.count());
        }
        return retorno;
    }
    @POST
    @Path("consultarid")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Profesor> consultaid(@FormParam("usuario")String usuario,@FormParam("contraseña")int password,@FormParam("pkProfesor")  int valor ) {
         List<Profesor> retorno=obtenerid(valor);
         if (usuario.equals("Lady")&& password==123){
             retorno=obtenerid(valor);
         }
        return retorno;
    }
//     public List<Profesor> consultarid(@FormParam("pkProfesor")  int valor) {
//         List<Profesor>retorno=obtenerid(valor);
//         return retorno;
//     }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    List<Profesor> obtenerid(int valor) {
        TypedQuery<Profesor> qry;
        qry = getEntityManager().createQuery("SELECT p FROM Profesor p WHERE p.pkProfesor = :pkProfesor", Profesor.class);
        qry.setParameter("pkProfesor", valor);
        try {
            return qry.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
