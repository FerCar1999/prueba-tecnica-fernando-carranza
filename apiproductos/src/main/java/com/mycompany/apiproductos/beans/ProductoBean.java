/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.apiproductos.beans;

import com.mycompany.apiproductos.modelos.Categoria;
import com.mycompany.apiproductos.modelos.Producto;
import com.mycompany.apiproductos.servicios.CategoriaServicio;
import com.mycompany.apiproductos.servicios.ProductoServicio;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fer
 */
@Named
@RequestScoped
public class ProductoBean {

    @Inject
    private ProductoServicio productoService;    
    private CategoriaServicio categoriaService;


    private List<Producto> productos;
    private List<Categoria> categorias;
    private Producto producto = new Producto();

    @PostConstruct
    public void init() {
        productos = productoService.obtenerTodos();
        categorias = categoriaService.obtenerTodos(); // Asumiendo que tienes este método
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String guardarProducto() {
        if (producto.getId() == null) {
            productoService.registrar(producto, producto.getCategoria().getId());
        } else {
            productoService.actualizar(producto.getId(), producto);
        }
        return "productos.xhtml?faces-redirect=true";
    }

    public String editarProducto(Producto producto) {
        this.producto = producto;
        return "crearProducto.xhtml?faces-redirect=true";
    }

    public void eliminarProducto(UUID id) {
        productoService.eliminar(id);
    }
    
}
