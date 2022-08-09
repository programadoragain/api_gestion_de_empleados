package com.API.gestionempleados.Repositories;

import com.API.gestionempleados.Entities.Empleado;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long> {
    List<Empleado> findByCategoria (String categoria);
}
