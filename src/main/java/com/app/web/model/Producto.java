package com.app.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="producto")
public class Producto {


		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private int codigo;
		private String nombre;
		private double precio_unitario;
		private double cantidad;
		private double precio_total;
		private double monto_total;
			
		public Producto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public double calcularPrecioTotal() {
		    return cantidad * precio_unitario;
		}

		public Producto(int id, int codigo, String nombre, double precio_unitario, double cantidad, double precio_total,
				double monto_total) {
			super();
			this.id = id;
			this.codigo = codigo;
			this.nombre = nombre;
			this.precio_unitario = precio_unitario;
			this.cantidad = cantidad;
			this.precio_total = precio_total;
			this.monto_total = monto_total;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public double getPrecio_unitario() {
			return precio_unitario;
		}

		public void setPrecio_unitario(double precio_unitario) {
			this.precio_unitario = precio_unitario;
		}

		public double getCantidad() {
			return cantidad;
		}

		public void setCantidad(double cantidad) {
			this.cantidad = cantidad;
		}

		public double getPrecio_total() {
			return precio_total;
		}

		public void setPrecio_total(double precio_total) {
			this.precio_total = precio_total;
		}

		public double getMonto_total() {
			return monto_total;
		}

		public void setMonto_total(double monto_total) {
			this.monto_total = monto_total;
		}
		
			
	


	
		
}
