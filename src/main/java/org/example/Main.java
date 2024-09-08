package org.example;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            Factura fac1 = new Factura();
            DetalleFactura dfac1 = new DetalleFactura();
            DetalleFactura dfac2 = new DetalleFactura();

            Categoria cat1 = new Categoria("Lacteos");
            Categoria cat2 = new Categoria("Limpieza");
            Categoria cat3 = new Categoria("Perecedero");

            Articulo art1 = new Articulo(5f, "Leche", 200);
            Articulo art2 = new Articulo(10f, "Jabon", 350);
            Articulo art3 = new Articulo(3f, "Escoba", 500);
            Articulo art4 = new Articulo(8f, "Yogurt", 150);

            Domicilio dom1 = new Domicilio("Benavente", 6229);
            Domicilio dom2 = new Domicilio("Rivadavia", 548);

            Cliente cli1 = new Cliente("Facundo", "Caricato", 45023207);
            Cliente cli2 = new Cliente("Alejo", "Carobolante", 45360753);

            dom1.setCliente(cli1);
            dom2.setCliente(cli2);

            cli1.setDomicilio(dom1);
            cli2.setDomicilio(dom2);

            art1.getCategoria().add(cat1);
            art1.getCategoria().add(cat3);
            art2.getCategoria().add(cat2);
            art3.getCategoria().add(cat2);
            art4.getCategoria().add(cat1);
            art4.getCategoria().add(cat3);

            cat1.getArticulo().add(art1);
            cat1.getArticulo().add(art4);
            cat2.getArticulo().add(art2);
            cat2.getArticulo().add(art3);
            cat3.getArticulo().add(art1);
            cat3.getArticulo().add(art4);

            art2.getDetallefactura().add(dfac1);

            fac1.setCliente(cli1);
            fac1.setFecha("28/09/2024");
            fac1.getDetalleFactura().add(dfac1);
            fac1.getDetalleFactura().add(dfac2);

            dfac1.setArticulo(art2);
            dfac1.setCantidad(20);
            dfac1.setSubtotal(100);
            dfac1.setFactura(fac1);

            dfac2.setArticulo(art1);
            dfac2.setCantidad(50);
            dfac2.setSubtotal(200);
            dfac2.setFactura(fac1);

            fac1.setTotal(300);

            entityManager.persist(fac1);

            entityManager.flush();
            entityManager.getTransaction().commit();

        }catch (Exception e){
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar las clases");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}