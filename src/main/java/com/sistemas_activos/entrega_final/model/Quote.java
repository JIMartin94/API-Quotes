package com.sistemas_activos.entrega_final.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quotes")
public class Quote extends Base {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dateIn = setDateIn();

    @Future
    @NotNull(message = "No puede ingresar una fecha vacia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime assignedDate;

    @NotBlank(message = "No puede ingresar una origen vacia")
    @Column(length = 20)
    private String origin;

    @Size(max = 80 ,message = "El comentario no puede ser mas de 80")
    @Column(length = 80)
    private String comments;

    @Size(max = 20 ,message = "El nombre de la conyugue no puede ser mas de 20")
    @Column(length = 20)
    private String spouse;

    @Size(max = 20 ,message = "El nombre del hijo no puede ser mas de 20")
    @Column(length = 20)
    private String sons;

    @NotNull(message = "No puede ingresar una edad vacia")
    @Max(value = 110 ,message = "El maximo de la edad es 110")
    @Column(length = 3)
    @Positive(message = "La edad debe ser positiva")
    private Integer age;

    @NotNull(message = "No puede ingresar una edad vacia")
    @Max(value = 110 ,message = "El maximo de la edad del contugue es 110")
    @Column(length = 3)
    @Positive(message = "La edad del conyugue debe ser positiva")
    private Integer spouseAge;

    @Positive(message = "El valor cotizado debe ser positivo")
    private Double quotedValue;
    @Positive(message = "El sueldo neto debe ser positivo")
    private Double netSalary;
    @Positive(message = "El costo debe ser positivo")
    private Double cost;

    public LocalDateTime setDateIn() {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fechaActual.format(formatter);
        return LocalDateTime.parse(fechaFormateada, formatter);
    }
}
