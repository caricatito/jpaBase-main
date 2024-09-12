package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="articulo")
@Audited

public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="denominacion")
    private String denominacion;

    @Column(name="precio")
    private Float precio;

    @Column(name="cantidad")
    private int cantidad;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    private Set<DetalleFactura> detallefactura = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "articulo_categoria", joinColumns = @JoinColumn(name = "articulo_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categoria = new HashSet<Categoria>();

    public Articulo(Float precio,String denominacion, int cantidad){
        this.precio=precio;
        this.denominacion=denominacion;
        this.cantidad=cantidad;
    }

}