package com.apsi.prueba.utils;

import com.apsi.prueba.model.Proveedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String DB_FILE_PATH = "src/main/resources/db.json";

    public static List<Proveedor> obtenerProveedores() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(DB_FILE_PATH);

        if (!file.exists()) {
            file.createNewFile();
            objectMapper.writeValue(file, new ArrayList<Proveedor>());
        }

        return objectMapper.readValue(file, new TypeReference<List<Proveedor>>() {});
    }
    
    public static List<Proveedor> obtenerProveedoresPaginados(int page, int size) throws IOException {
        List<Proveedor> proveedoresTotales = obtenerProveedores();
        int totalProveedores = proveedoresTotales.size();

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, totalProveedores);

        if (startIndex > endIndex || startIndex >= totalProveedores) {
            return new ArrayList<>();
        }

        return proveedoresTotales.subList(startIndex, endIndex);
    }

    public static void guardarProveedores(List<Proveedor> proveedores) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(DB_FILE_PATH);
        objectMapper.writeValue(file, proveedores);
    }
}
