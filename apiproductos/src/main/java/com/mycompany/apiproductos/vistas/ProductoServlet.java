package com.mycompany.apiproductos.vistas;

import com.mycompany.apiproductos.modelos.Producto;
import com.mycompany.apiproductos.modelos.Categoria;
import com.mycompany.apiproductos.servicios.CategoriaServicio;
import com.mycompany.apiproductos.servicios.ProductoServicio;
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

@WebServlet("/producto")
public class ProductoServlet extends HttpServlet {

    @Inject
    private ProductoServicio productoServicio;

    @Inject
    private CategoriaServicio categoriaServicio;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null) {
            Producto producto = productoServicio.buscarPorId(Integer.valueOf(id));
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(producto));
        } else {
            List<Producto> productos = productoServicio.obtenerTodos();
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(productos));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String categoriaId = request.getParameter("categoria_id");

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);

        Categoria categoria = categoriaServicio.buscarPorId(Integer.valueOf(categoriaId));
        producto.setCategoria(categoria);

        Map<String, Object> respuesta = new HashMap<>();
        if (id != null && !id.isEmpty()) {
            producto.setId(Integer.valueOf(id));
            productoServicio.actualizar(Integer.valueOf(id), producto);
            respuesta.put("success", true);
            respuesta.put("message", "Producto actualizado con éxito");
        } else {
            productoServicio.registrar(producto,Integer.valueOf(categoriaId));
            respuesta.put("success", true);
            respuesta.put("message", "Producto registrado con éxito");
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
            productoServicio.eliminar(Integer.valueOf(id));
            respuesta.put("success", true);
            respuesta.put("message", "Producto eliminado con éxito");
        } else {
            respuesta.put("success", false);
            respuesta.put("message", "ID de producto no proporcionado");
        }

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(respuesta));
    }
}
