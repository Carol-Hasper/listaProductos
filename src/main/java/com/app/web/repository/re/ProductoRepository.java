package com.app.web.repository.re;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, Integer>{



	

}
