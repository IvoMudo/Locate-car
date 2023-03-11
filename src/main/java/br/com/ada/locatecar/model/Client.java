package br.com.ada.locatecar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Obrigatório o preencher o nome")
    @NotBlank(message = "Informar nome válido")
    private String name;
    private String phone;
    @NotEmpty(message = "Obrigatório o preencher o documento")
    @NotBlank(message = "Informar um documento válido")
    private String document;
    @NotEmpty
    private String type;
    private String cepNumber;

}
