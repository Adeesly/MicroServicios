package com.pablo.microServ.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String lastName;
    private int age;
    private LocalDate birthDate;

    @JsonProperty("estimatedDateOfDeath")
    public LocalDate getEstimatedDateOfDeath() {
        return birthDate != null ? birthDate.plusYears(90) : null;
    }
}
