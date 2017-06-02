/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Empresa;
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
@Path("com.model.empresa")
public class EmpresaFacadeREST extends AbstractFacade<Empresa> {

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public EmpresaFacadeREST() {
        super(Empresa.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Empresa entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Empresa entity) {
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
    public Empresa find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empresa> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empresa> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    //METODOS
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("nombre") String nombre) {
        String mensaje="{\"exitoso\":false}";
        try{
            if (obtener_usuario(nombre)== null){
                create(new Empresa(nombre,false));
                mensaje="{\"exitoso\":true}"; 
            }      
        }catch(Exception ex){           
        }
            
        return mensaje;
    }
    //-----------------------------------------------------------------------------------------------
    @POST
    @Path("consulta")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Empresa> consulta(@FormParam(   "id")int id) {
         List<Empresa> retorno=obtenerid(id);
         return retorno;
    }
    //--------------------------------------------------------------------------------------------------
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("id") int id,@FormParam("nombre") String nombre) {
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Empresa u= buscarid(id);
        if(u!=null){
            u.setNombre(nombre);
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
    /// ------EDITAR ELIMINADO------------------------
    @POST
    @Path("editareliminado")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editareliminado(@FormParam("id") int id){
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Empresa u= buscarid(id);
        if(u!=null){
            u.setEliminado(true);
            try {
                edit( u);
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        public Empresa obtener_usuario(String nombre){
        Empresa em=null;
        TypedQuery<Empresa> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Empresa e WHERE e.nombre = :nombre and e.eliminado = :eliminado", Empresa.class);
        qry.setParameter("nombre",nombre);
        qry.setParameter("eliminado",false);
         try {
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    //---------------------------------------------------------------------
        public Empresa buscarid(int idempresa){
        Empresa u=null;
        TypedQuery<Empresa> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Empresa e WHERE e.idempresa = :idempresa and e.eliminado = :eliminado", Empresa.class);
        qry.setParameter("idempresa",idempresa);
        qry.setParameter("eliminado",false);
        
         try {
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    //------------------------------------------------------------------------------------------------------------
    List<Empresa> obtenerid(int valor) {
        TypedQuery<Empresa> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Empresa e WHERE e.idempresa = :idempresa ", Empresa.class);
        qry.setParameter("idempresa", valor);
        try {
            return qry.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
