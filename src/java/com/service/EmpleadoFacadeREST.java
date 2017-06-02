/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Empleado;
import com.model.Empresa;
import com.model.Profesor;
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
@Path("com.model.empleado")
public class EmpleadoFacadeREST extends AbstractFacade<Empleado> {
    @EJB
    EmpresaFacadeREST empresaFacadeREST;

    @PersistenceContext(unitName = "BDD_docentesPU")
    private EntityManager em;

    public EmpleadoFacadeREST() {
        super(Empleado.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Empleado entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Empleado entity) {
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
    public Empleado find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Empleado> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    //----------------------------------------------------------------------------------------------
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("nombre") String nombre, @FormParam("empresa") int id) {
        String mensaje="{\"exitoso\":false}";
        Empresa e=empresaFacadeREST.find(id);
        try{
            if (obtener_usuario(nombre)== null){
                create(new Empleado(nombre,false,e));
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
    public List<Empleado> consulta(@FormParam("id")int id) {
         List<Empleado> retorno=obtenerid(id);
         return retorno;
    }
    //--------------------------------------------------------------------------------------------------
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("id") int id,@FormParam("nombre") String nombre, @FormParam("empresa") int empresa) {
        String mensaje="{\"exitoso\":false,\"motivo\":";
        Empleado u= buscarid(id);
        Empresa e=empresaFacadeREST.find(empresa);
        if(u!=null){
            u.setNombre(nombre);
            u.setIdempresa(e);
            try {
                edit(id, u);
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
        Empleado u= buscarid(id);
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        public Empleado obtener_usuario(String nombre){
        Empleado em=null;
        TypedQuery<Empleado> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Empleado e WHERE e.nombre = :nombre and e.eliminado = :eliminado", Empleado.class);
        qry.setParameter("nombre",nombre);
        qry.setParameter("eliminado",false);
         try {
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    //---------------------------------------------------------------------
        public Empleado buscarid(int pk_usuario){
        Empleado u=null;
        TypedQuery<Empleado> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Empleado e WHERE e.idempleado = :idempleado and e.eliminado = :eliminado", Empleado.class);
        qry.setParameter("idempleado",pk_usuario);
        qry.setParameter("eliminado",false);
        
         try {
            return qry.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    //------------------------------------------------------------------------------------------------------------
    List<Empleado> obtenerid(int valor) {
        TypedQuery<Empleado> qry;
        qry = getEntityManager().createQuery("SELECT e FROM Empleado e WHERE e.idempleado = :idempleado ", Empleado.class);
        qry.setParameter("idempleado", valor);
        try {
            return qry.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
