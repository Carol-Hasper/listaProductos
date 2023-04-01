package com.app.web.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.web.model.Producto;
import com.app.web.repository.ProductosRepository;
import com.app.web.repository.re.ProductoRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class ProductoService {
	@Autowired
	private ProductoRepository PRepository;
	@Autowired
	private ProductosRepository repohiber;
	
	public List<Producto> findAll() {
		return PRepository.findAll();
	}
	
	public ResponseEntity<InputStreamResource> pdfProductos() throws DocumentException {
		
		List<Producto> productos =PRepository.findAll();
		
		Document document = new Document();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);

		document.open();
		
		 Paragraph titulo = new Paragraph("Lista de productos");
	        titulo.setAlignment(Paragraph.ALIGN_CENTER);
	        document.add(titulo);
		
	        PdfPTable table = new PdfPTable(6);
	        table.setWidthPercentage(100);
	        table.setSpacingBefore(20f);
	        table.setSpacingAfter(20f);
	        
	       
		
		PdfPCell celda1 = new PdfPCell(new Paragraph("Codigo"));
        PdfPCell celda2 = new PdfPCell(new Paragraph("Producto"));
        PdfPCell celda3 = new PdfPCell(new Paragraph("precio unitario"));
        PdfPCell celda4 = new PdfPCell(new Paragraph("cantidad"));
        PdfPCell celda5 = new PdfPCell(new Paragraph("precio total"));
        PdfPCell celda6 = new PdfPCell(new Paragraph("monto total"));
        
		table.addCell(celda1);
		table.addCell(celda2);
		table.addCell(celda3);
		table.addCell(celda4);
		table.addCell(celda5);
		table.addCell(celda6);

		for (Producto p : productos) {
			table.addCell(String.valueOf(p.getCodigo()));
			table.addCell(p.getNombre());
			table.addCell(String.valueOf(p.getPrecio_unitario()));
			table.addCell(String.valueOf(p.getCantidad()));
			table.addCell(String.valueOf(p.getPrecio_total()));
			table.addCell(String.valueOf(p.getMonto_total()));
		}

		document.add(table);

		document.close();

		ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=lista_productos.pdf");
		
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(inputStream));
	}
	
	
	public double calcularMontoTotal() {
		List<Producto> productos = PRepository.findAll();
		double montoTotal = 0;
		for (Producto p : productos) {
			montoTotal += p.getPrecio_total();
			PRepository.save(p);
		}
		return montoTotal;
	}

	public Optional<Producto> findById(Integer id) {
		return PRepository.findById(id);
	}

	public void save(Producto p) {
		PRepository.save(p);
	}

	public void deleteById(Integer id) {
		PRepository.deleteById(id);
	}
	
	public List<Producto> getAllProductos(){
		return repohiber.getAllProductos() ;
	}
	
	public List<Producto> getPreoductoByName(String nombre){
		return repohiber.getPreoductoByName(nombre) ;
	}
	
}
		