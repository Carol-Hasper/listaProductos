package com.app.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.web.model.Producto;
import com.app.web.service.ProductoService;
import com.itextpdf.text.DocumentException;

@RequestMapping("mercado")
@RestController
public class ProductoController {
	@Autowired
	private ProductoService pservice;
	
	
	
	@GetMapping("findAll")
	public List<Producto> findAll(){
		return pservice.findAll();
	}
	
	
	@PostMapping("save")
	public String save(@RequestBody Producto p ){
		 p.setPrecio_total(p.calcularPrecioTotal());
		 p.setMonto_total(pservice.calcularMontoTotal());
		 pservice.save(p);
		 
		    return "exito al guardar";
		}	
	
	@GetMapping("findById/{id}")
	public Optional<Producto> findById(@PathVariable("id")Integer id){
		return pservice.findById(id);
	}
	
	
	@GetMapping("deleteById/{id}")
	public String deleteById(@PathVariable("id")Integer id){
		pservice.deleteById(id);
		return "exito al eliminar";
	}

	@GetMapping("montoTotal")
	public double montoTotal(){
		return pservice.calcularMontoTotal();
		}	
	
	@GetMapping("generarPdf")
	 public ResponseEntity<InputStreamResource> generarPDF() throws IOException, DocumentException {
		ResponseEntity<InputStreamResource> productos = pservice.pdfProductos();
		return productos;
		

	}
		@GetMapping("getAllProductos")
		public List<Producto> getAllProductos ()  {
			return pservice.getAllProductos();
	
		
	}
		
		@GetMapping("getPreoductoByName/{nombre}")
		public List<Producto> getPreoductoByName (@PathVariable("nombre")String nombre)  {
			return pservice.getPreoductoByName(nombre);
	
		
	}
	


}