package com.apsi.prueba.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.apsi.prueba.model.Proveedor;
import com.apsi.prueba.utils.DatabaseUtils;

@Service
public class ProveedorService {
    private static List<Proveedor> proveedores;

    static {
        try {
            proveedores = DatabaseUtils.obtenerProveedores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Proveedor> obtenerTodos() {
        return proveedores;
    }

    public Optional<Proveedor> obtenerPorNombre(String nombre) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public void agregarProveedor(Proveedor nuevoProveedor) throws IOException {
        if (!obtenerPorNombre(nuevoProveedor.getNombre()).isPresent()) {
            proveedores.add(nuevoProveedor);
            DatabaseUtils.guardarProveedores(proveedores);
        } else {
            // Lanza una excepción o maneja la duplicación según tus necesidades
            throw new IllegalArgumentException("El proveedor con el nombre especificado ya existe.");
        }
    }

    public void eliminarProveedor(String nombre) throws IOException {
        proveedores.removeIf(proveedor -> proveedor.getNombre().equalsIgnoreCase(nombre));
        DatabaseUtils.guardarProveedores(proveedores);
    }
    
    public Page<Proveedor> obtenerProveedoresPaginados(PageRequest pageRequest) {
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), proveedores.size());

        if (start > end || start >= proveedores.size()) {
            return new PageImpl<>(List.of(), pageRequest, 0);
        }

        List<Proveedor> paginados = proveedores.subList(start, end);
        return new PageImpl<>(paginados, pageRequest, proveedores.size());
    }
}
