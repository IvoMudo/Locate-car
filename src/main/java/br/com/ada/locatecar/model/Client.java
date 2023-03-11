package br.com.ada.locatecar.model;

import jakarta.persistence.*;
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
    private String name;
    private String phone;
    @Column(unique = true)
    private String document;
    private String type;

}
