package org.example;
import lombok.Builder;
import lombok.Data;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
@Data
@Builder

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("A crear una base de Datos");
        try {
            entityManager.getTransaction().begin();

            Factura factura1 = Factura.builder()
                    .numero(12)
                    .fecha("20/08/2024")
                    .total(220)
                    .build();

            Domicilio dom1 = new Domicilio("San Martin", 1222);
            Cliente cliente1 = new Cliente("Facundo", "Caricato", 45023207);

            cliente1.setDomicilio(dom1);
            dom1.setCliente(cliente1);
            factura1.setCliente(cliente1);

            Categoria percederos = new Categoria("Percederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo articulo1 = new Articulo("Yogurt", 200F, 100);
            Articulo articulo2 = new Articulo("Arroz", 900F, 150);
            Articulo articulo3 = new Articulo("Detergente", 1000F, 300);

            articulo1.getCategoria().add(lacteos);
            articulo2.getCategoria().add(percederos);
            articulo3.getCategoria().add(limpieza);
            lacteos.getArticulos().add(articulo1);
            percederos.getArticulos().add(articulo2);
            limpieza.getArticulos().add(articulo3);

            DetalleFactura det1 = DetalleFactura.builder()
                    .cantidad(2)
                    .subtotal(40)
                    .build();
            det1.setArticulo(articulo1);

            articulo1.getDetalle().add(det1);
            factura1.getDetalle().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder()
                    .cantidad(5)
                    .subtotal(80)
                    .build();

            det2.setArticulo(articulo2);


            articulo2.getDetalle().add(det2);
            factura1.getDetalle().add(det2);
            det2.setFactura(factura1);

            DetalleFactura det3 = DetalleFactura.builder()
                    .cantidad(3)
                    .subtotal(100)
                    .build();
            det3.setArticulo(articulo3);

            articulo3.getDetalle().add(det3);
            factura1.getDetalle().add(det3);
            det3.setFactura(factura1);


            entityManager.persist(factura1);

            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
