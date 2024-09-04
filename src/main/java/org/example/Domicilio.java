package org.example;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name="Domicilio")
public class Domicilio {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre_calle")
    private String nombreCalle;

    @Column(name = "numero")
    private int numero;

    @OneToOne(mappedBy= "domicilio")
    private Cliente cliente;

    public Domicilio(String nombreCalle, int numero){
        this.nombreCalle=nombreCalle;
        this.numero=numero;
    }

}