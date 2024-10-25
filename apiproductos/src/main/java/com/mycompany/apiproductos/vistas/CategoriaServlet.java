package com.mycompany.apiproductos.vistas;

import com.mycompany.apiproductos.modelos.Categoria;
import com.mycompany.apiproductos.servicios.CategoriaServicio;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper; // Librería para manejo de JSON

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    @Inject
    private CategoriaServicio categoriaServicio;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null) {
            Categoria categoria = categoriaServicio.buscarPorId(Integer.valueOf(id));
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(categoria));
        } else {
            List<Categoria> categorias = categoriaServicio.obtenerTodos();
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(categorias));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        Map<String, Object> respuesta = new HashMap<>();
        if (id != null && !id.isEmpty()) {
            categoria.setId(Integer.valueOf(id));
            categoriaServicio.actualizar(Integer.valueOf(id), categoria);
            respuesta.put("success", true);
            respuesta.put("message", "Categoria actualizada con éxito");
        } else {
            categoriaServicio.registrar(categoria);
            respuesta.put("success", true);
            respuesta.put("message", "Categoria registrada con éxito");
        }

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(respuesta));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Map<String, Object> respuesta = new HashMap<>();

        if (id != null && !id.isEmpty()) {
            categoriaServicio.eliminar(Integer.valueOf(id));
            respuesta.put("success", true);
            respuesta.put("message", "Categoria eliminada con éxito");
        } else {
            respuesta.put("success", false);
            respuesta.put("message", "ID de la categoria no proporcionado");
        }

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(respuesta));
    }
}
