package com.API.gestionempleados.Services;

import com.API.gestionempleados.Entities.Empleado;
import com.API.gestionempleados.Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoServiceInt {

    @Autowired
    private EmpleadoRepository empleadoRepo;

    @Transactional(readOnly= true)
    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoRepo.findAll();
    }

    @Transactional(readOnly= true)
    @Override
    public Page<Empleado> findAll(Pageable p) {
        return empleadoRepo.findAll(p);
    }

    @Override
    @Transactional(readOnly= true)
    public Empleado findOne(long id) {
        return empleadoRepo.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Empleado e) {
        empleadoRepo.save(e);
    }

    @Override
    public void delete(long id) {
        empleadoRepo.deleteById(id);
    }
}
