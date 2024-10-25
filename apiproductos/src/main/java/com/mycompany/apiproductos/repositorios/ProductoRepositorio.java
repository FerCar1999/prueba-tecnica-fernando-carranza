/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apiproductos.repositorios;

import com.mycompany.apiproductos.modelos.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author fer
 */
@Stateless
public class ProductoRepositorio {
    @PersistenceContext
    private EntityManager em;
    
    public List<Producto> listar() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class)
                 .getResultList();
    }
    
    public List<Producto> buscarPorCategoria(Integer categoriaId){
        return em.createQuery("SELECT p FROM Producto p WHERE p.categoria.id=:categoriaId", Producto.class)
                .setParameter("categoriaId",categoriaId).getResultList();
    }

    public Producto buscarPorId(Integer id) {
        return em.find(Producto.class, id);
    }

    public void guardar(Producto producto) {
        em.merge(producto);
    }

    public void eliminar(Integer id) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            em.remove(producto);
        }
    }
}
