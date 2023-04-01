package com.app.web.conex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.app.web.model.Producto;


public class ConexionHibernate {

    /**
     * Añade todas las clases mapeadas a la sesion.
     *
     * @return
     */
    public static Session tomarConexion() {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Producto.class)
                .buildSessionFactory();
        return sf.getCurrentSession();
    }

}
