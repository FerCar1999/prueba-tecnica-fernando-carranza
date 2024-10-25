/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.apiproductos.resources;

import com.mycompany.apiproductos.modelos.Producto;
import com.mycompany.apiproductos.servicios.ProductoServicio;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;
import java.util.UUID;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @Inject
    private ProductoServicio productoServicio;

    @GET
    public List<Producto> listarProductos() {
        return productoServicio.obtenerTodos();
    }

    @GET
    @Path("/categoria/{categoriaId}")
    public List<Producto> listarPorCategoria(@PathParam("categoriaId") UUID categoriaId) {
        return productoServicio.obtenerPorCategoria(categoriaId);
    }

    @POST
    public Response crearProducto(Producto producto, @QueryParam("categoriaId") UUID categoriaId) {
        productoServicio.registrar(producto, categoriaId);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarProducto(@PathParam("id") UUID id, Producto producto) {
        productoServicio.actualizar(id, producto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarProducto(@PathParam("id") UUID id) {
        productoServicio.eliminar(id);
        return Response.noContent().build();
    }
}
