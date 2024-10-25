/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apiproductos.servicios;

import com.mycompany.apiproductos.modelos.Categoria;
import com.mycompany.apiproductos.repositorios.CategoriaRepositorio;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fer
 */
@Stateless
public class CategoriaServicio {
    @Inject
    private CategoriaRepositorio categoriaRepositorio;
    
      public List<Categoria> obtenerTodos() {
        return categoriaRepositorio.listar();
    }
      
    public void registrar(Categoria categoria) {
        categoriaRepositorio.guardar(categoria);
    }

    public void actualizar(UUID id, Categoria categoriaActualizada) {
        Categoria categoria = categoriaRepositorio.buscarPorId(id);
        if (categoria != null) {
            categoria.setNombre(categoriaActualizada.getNombre());
            categoriaRepositorio.guardar(categoria);
        }
    }

    public void eliminar(UUID id) {
        categoriaRepositorio.eliminar(id);
    }
}
