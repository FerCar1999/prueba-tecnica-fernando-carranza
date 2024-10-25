/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apiproductos.repositorios;

import com.mycompany.apiproductos.modelos.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fer
 */
@Stateless
public class CategoriaRepositorio {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Categoria> listar() {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class)
                 .getResultList();
    }

    public Categoria buscarPorId(Integer id) {
        return em.find(Categoria.class, id);
    }

    public void guardar(Categoria categoria) {
        if (categoria.getId() != null) {
            em.merge(categoria);
        } else {
            em.persist(categoria);
        }
    }

    public void modificar(Categoria categoria) {
        em.merge(categoria);
    }

    public void eliminar(Integer id) {
        Categoria categoria = buscarPorId(id);
        if (categoria != null) {
            em.remove(categoria);
        }
    }
}
