package com.API.gestionempleados.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name="empleados")
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Complete este campo por favor")
    @Size(min = 2, max = 32, message = "El nombre debe tener entre 2 y 32 caracteres")
    private String nombre;

    @NotNull(message= "Complete este campo por favor")
    private String apellido;

    @NotNull(message= "Complete este campo por favor")
    private String telefono;

    @NotNull(message= "Complete este campo por favor")
    @Email (message= "Debe tener formato de email, ej: correo@empresa.com")
    private String email;

    @NotNull(message= "Complete este campo por favor")
    private String categoria;

    @NotEmpty(message= "Complete este campo por favor")
    private double sueldo;

    @NotNull(message= "Complete este campo por favor")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    private String sexo;
}
