package com.API.gestionempleados.Services;

import com.API.gestionempleados.Entities.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpleadoServiceInt {
    List<Empleado> findAll();
    Page<?> findAll(Pageable p);
    void save(Empleado e);
    Empleado findOne(long id);
    void delete(long id);
}
