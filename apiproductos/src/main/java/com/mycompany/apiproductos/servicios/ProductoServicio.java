/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apiproductos.servicios;

import com.mycompany.apiproductos.modelos.Producto;
import com.mycompany.apiproductos.repositorios.CategoriaRepositorio;
import com.mycompany.apiproductos.repositorios.ProductoRepositorio;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fer
 */
@Stateless
public class ProductoServicio {
    @Inject
    private ProductoRepositorio productoRepositorio;
    
      public List<Producto> obtenerTodos() {
        return productoRepositorio.listar();
    }
      
      public Producto buscarPorId(Integer id){
          return productoRepositorio.buscarPorId(id);
      }

    public List<Producto> obtenerPorCategoria(Integer categoriaId) {
        return productoRepositorio.buscarPorCategoria(categoriaId);
    }

    public void registrar(Producto producto, Integer categoriaId) {
        productoRepositorio.guardar(producto);
    }

    public void actualizar(Integer id, Producto productoActualizado) {
        Producto producto = productoRepositorio.buscarPorId(id);
        if (producto != null) {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCategoria(productoActualizado.getCategoria());
            productoRepositorio.guardar(producto);
        }
    }

    public void eliminar(Integer id) {
        productoRepositorio.eliminar(id);
    }
}
