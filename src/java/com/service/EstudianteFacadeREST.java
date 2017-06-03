/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Estudiante;
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
@Path("com.model.estudiante")
public class EstudianteFacadeREST extends AbstractFacade<Estudiante> {
    @EJB
    MateriaFacadeREST materiaFacadeREST;

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public EstudianteFacadeREST() {
        super(Estudiante.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Estudiante entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Estudiante entity) {
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
    public Estudiante find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Estudiante> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estudiante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    //----------------------CREAR--------------------------------------------
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("nombre") String nombre, @FormParam("idmateria") int id) {
        String mensaje="{\"exitoso\":false}";
        Materia e=materiaFacadeREST.find(id);
        try{
            if (obtener_usuario(nombre)== null){
                create(new Estudiante(nombre,false,e));
                mensaje="{\"exitoso\":true}"; 
            }      
        }catch(Exception ex){           
        }
            
        return mensaje;
    }
    //--------------------LEER------------------------------------------------
    @POST
    @Path("leer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Estudiante> leer(@FormParam("id")int id) {
         List<Estudiante> retorno=obtenerid(id);
         return retorno;
    }
    //--------------------ACTUALIZAR-------------------------------------------
        @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("id") int id,@FormParam("nombre") String nombre, @FormParam("idmateria") int materia) {
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Estudiante u= buscarid(id);
        Materia e= materiaFacadeREST.find(materia);
        if(u!=null){
            u.setNombre(nombre);
            u.setIdmateria(e);
            try {
                edit(u);
                mensaje = "{\"exitoso\":true";
            } catch (Exception ex) {
                mensaje += "\"Execpcion en base\"";
            }
        }else{
            mensaje+="\"Datos no correctos\"";
        }
        mensaje+="}";   
        return mensaje;
    }
    //--------------------ELIMINAR---------------------------------------------
    @POST
    @Path("editareliminado")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editareliminado(@FormParam("id") int id){
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Estudiante u= buscarid(id);
        if(u!=null){
            u.setEliminado(true);
            try {
                edit(id, u);
                mensaje = "{\"exitoso\":true";
            } catch (Exception e) {
                mensaje += "\"Execpcion en base\"";
            }
        }else{
            mensaje+="\"Datos no correctos\"";
        }
        mensaje+="}";   
        return mensaje;
    }
    //-------------------------------------------------------------------------

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    //---------------------METODO PARA BUSCAR  SI EL USUARIO EXISTE Y PODER CREARLO----------------
    public Estudiante obtener_usuario(String nombre){
    Estudiante e=null;
    TypedQuery<Estudiante> qry;
    qry = getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.nombre = :nombre and e.eliminado = :eliminado", Estudiante.class);
    qry.setParameter("nombre",nombre);
    qry.setParameter("eliminado",false);
     try {
        return qry.getSingleResult();
    } catch (NoResultException ex) {
        return null;
    }
  }
    //-----------------METODO PARA LEER------------------------------------------------------
        List<Estudiante> obtenerid(int valor) {
        TypedQuery<Estudiante> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.idestudiante = :idestudiante ", Estudiante.class);
        qry.setParameter("idestudiante", valor);
        try {
            return qry.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    } 
    //---------------METODO PARA BUSCAR EL ID DEL ESTUDIANTE Y ASÍ PODER ACTUALIZAR-------------------------------
        public Estudiante buscarid(int pk_usuario){
        Estudiante u=null;
        TypedQuery<Estudiante> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.idestudiante = :idestudiante  and e.eliminado = :eliminado", Estudiante.class);
        qry.setParameter("idestudiante",pk_usuario);
        qry.setParameter("eliminado",false);
        
         try {
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }    
}
