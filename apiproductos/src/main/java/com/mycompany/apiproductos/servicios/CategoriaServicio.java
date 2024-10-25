package com.mycompany.apiproductos.servicios;

import com.mycompany.apiproductos.modelos.Categoria;
import com.mycompany.apiproductos.repositorios.CategoriaRepositorio;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class CategoriaServicio {
    @Inject
    private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> obtenerTodos() {
        return categoriaRepositorio.listar();
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepositorio.buscarPorId(id);
    }

    public boolean registrar(Categoria categoria) {
        try {
            categoriaRepositorio.guardar(categoria);
            return true; // Registro exitoso
        } catch (Exception e) { // Manejar excepciones si es necesario
            // Manejar excepciones si es necesario
            return false; // Fallo en el registro
        }
    }

    public boolean actualizar(Integer id, Categoria categoriaActualizada) {
        Categoria categoria = categoriaRepositorio.buscarPorId(id);
        if (categoria != null) {
            categoria.setNombre(categoriaActualizada.getNombre());
            try {
                categoriaRepositorio.modificar(categoria);
                return true; // Actualización exitosa
            } catch (Exception e) { // Manejar excepciones si es necesario
                // Manejar excepciones si es necesario
                return false; // Fallo en la actualización
            }
        }
        return false; // Categoría no encontrada
    }

    public boolean eliminar(Integer id) {
        try {
            categoriaRepositorio.eliminar(id);
            return true; // Eliminación exitosa
        } catch (Exception e) { // Manejar excepciones si es necesario
            // Manejar excepciones si es necesario
            return false; // Fallo en la eliminación
        }
    }
}
