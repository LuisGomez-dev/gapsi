package com.apsi.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.apsi.prueba.model.Proveedor;
import com.apsi.prueba.service.ProveedorService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public Page<Proveedor> obtenerProveedores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return proveedorService.obtenerProveedoresPaginados(PageRequest.of(page, size));
    }

    @GetMapping("/{nombre}")
    public Proveedor obtenerProveedorPorNombre(@PathVariable String nombre) {
        return proveedorService.obtenerPorNombre(nombre).orElse(null);
    }

    @PostMapping
    public void agregarProveedor(@RequestBody Proveedor nuevoProveedor) throws IOException {
        proveedorService.agregarProveedor(nuevoProveedor);
    }

    @DeleteMapping("/{nombre}")
    public void eliminarProveedor(@PathVariable String nombre) throws IOException {
        proveedorService.eliminarProveedor(nombre);
    }
}
