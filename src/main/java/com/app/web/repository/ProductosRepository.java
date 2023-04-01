package com.app.web.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.app.web.conex.ConexionHibernate;
import com.app.web.model.Producto;
@Repository
public class ProductosRepository {
	@SuppressWarnings("unchecked")
	public List<Producto> getPreoductoByName(String nombre )  {
        try {
            Session miSesion = ConexionHibernate.tomarConexion();
            miSesion.beginTransaction();
            Query<Producto> q = miSesion.createSQLQuery("select * from producto  where nombre ='"+nombre+"';");
            List<Producto> producto= q.list();
            miSesion.close(); 
            return producto;    
            
        } catch (HibernateException ex) {
        	ex.printStackTrace();
        	return null;
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Producto> getAllProductos() {
	    try {
	        Session miSesion = ConexionHibernate.tomarConexion();
	        miSesion.beginTransaction();
	        Query<Producto> q = miSesion.createQuery("from Producto");
	        List<Producto> producto = q.list();
	        miSesion.close();
	        return producto;
	    } catch (HibernateException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	

	
	
}