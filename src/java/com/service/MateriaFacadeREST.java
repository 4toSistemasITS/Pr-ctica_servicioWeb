/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Area;
import com.model.Materia;
import java.util.List;
import javax.ejb.EJB;
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
@Path("com.model.materia")
public class MateriaFacadeREST extends AbstractFacade<Materia> {
    //-----------SE INSTACIA LA CLASE---------------------------
    @EJB
    AreaFacadeREST areaFacadeREST;

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public MateriaFacadeREST() {
        super(Materia.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Materia entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Materia entity) {
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
    public Materia find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Materia> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Materia> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    //--------------------------------CREAR-----------------------------------------------
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("nombre") String nombre, @FormParam("area") int id) {
        String mensaje="{\"exitoso\":false}";
        Area e= areaFacadeREST.find(id);
        try{
            if (obtener_usuario(nombre)== null){
                create(new Materia(nombre,false,e));
                mensaje="{\"exitoso\":true}"; 
            }      
        }catch(Exception ex){           
        }
            
        return mensaje;
    }
    //---------------------------LEER--------------------------------------------
    @POST
    @Path("consulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Materia> consulta(@FormParam("id")int id) {
         List<Materia> retorno=obtenerid(id);
         return retorno;
    }
    //-------------------------------------------------------------------

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //---------------------METODO PARA BUSCAR  SI EL USUARIO EXISTE Y PODER CREARLO----------------
        public Materia obtener_usuario(String nombre){
        Materia em=null;
        TypedQuery<Materia> qry;
        qry = getEntityManager().createQuery("SELECT m FROM Materia m WHERE m.nombre = :nombre and m.eliminado = :eliminado", Materia.class);
        qry.setParameter("nombre",nombre);
        qry.setParameter("eliminado",false);
         try {
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    //-----------------METODO PARA LEER------------------------------------------------------
        List<Materia> obtenerid(int valor) {
        TypedQuery<Materia> qry;
        qry = getEntityManager().createQuery("SELECT m FROM Materia m WHERE m.idmateria = :idmateria ", Materia.class);
        qry.setParameter("idmateria", valor);
        try {
            return qry.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }        
}
