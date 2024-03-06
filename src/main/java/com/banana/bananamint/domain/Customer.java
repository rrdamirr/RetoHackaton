package com.banana.bananamint.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.banana.bananamint.exception.CustomerException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
@Schema(name = "customer", description = "Model customer")
@XmlRootElement
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Identificador customer", example = "1", required = false)
    private Long id;

    @Size(min = 2, max = 60)
    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Schema(name = "name customer", example = "Manolo Perez Perez", required = true)
    private String name;

    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Size(min = 7, max = 60)
    @Schema(name = "email customer", example = "manoloperez@gmail.com", required = true)
    private String email;

    @DateTimeFormat
    @NotNull
    @Schema(name = "birth date customer", example = "2024-02-14", required = true)
    private LocalDate birthDate;

    @NotNull
    @NotBlank(message = "Debe tener valor")
    @Size(min = 9, max = 9)
    @Schema(name = "dni customer", example = "12345678L", required = true)
    private String dni;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "owner")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Customer accounts list", example = "model account", required = false)
    private List<Account> accounts;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Customer budget list", example = "model budget", required = false)
    private List<Budget> budgets;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Customer expense list", example = "model expense", required = false)
    private List<Expense> expenses;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Customer income list", example = "model income", required = false)
    private List<Income> incomes;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @Schema(name = "Customer goal list", example = "model goal", required = false)
    private List<Goal> goals;

    public boolean isValid() throws CustomerException {
        // Para que el usuario sea valido:
        // email válido
        // mayor de 18 años
        // dni: 8_Numeros + 1_Letra
        // Si no es válido, debe lanzar exception
        //  return false;
        boolean valido = validarEmail() &&
                validarEdad() &&
                validarDNI(this.dni);

        if (!valido) {
            new CustomerException("Cliente no valido!");
        }

        return true;

    }

    private boolean validarEmail() {
        return this.email != null && this.email.indexOf("@") > 0 && this.email.indexOf(".") > 0;
    }

    private boolean validarEdad() {
        return this.birthDate != null && this.birthDate.compareTo(LocalDate.now()) <= 6570;
    }

    private boolean validarDNI(String dni) throws CustomerException {
        if (dni != null && dni.length() == 9) {
            String intPartDNI = dni.trim().replaceAll(" ", "").substring(0, 7);
            char ltrDNI = dni.charAt(8);
            int valNumDni = Integer.parseInt(intPartDNI);
            return valNumDni > 0 || !Character.isLetter(ltrDNI);
        } else return false;
    }

}
