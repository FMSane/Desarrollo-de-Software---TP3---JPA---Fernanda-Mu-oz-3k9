package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("en marcha Alberto");

        try {
            //Iniciar una transacción
            entityManager.getTransaction().begin();

            System.out.println("---------------------------------------------");

            Factura factura1 = new Factura();
            factura1.setNumero(15);
            factura1.setFecha("02/09/24");

            Cliente cliente = new Cliente("Fernanda", "Muñoz", 44986209);
            Domicilio domicilio = new Domicilio("San Martín", 1231);

            //se persiste el cliente y con él se persiste el domicilio.
            //No se ve reflejada en las tablas (con fk) pero sí en la POO.
            cliente.setDomicilio(domicilio);
            domicilio.setCliente(cliente);

            //Persistir la entidad
            entityManager.persist(cliente);

            //Traer un domicilio a partir de un cliente, y un cliente a partir de domicilio (registros guardados en la bd)
            /*Esto se hace con un metodo de entity manager llamado find.*/
            //Le avisamos con la clase a la que queremos llamar (class) y la clave primaria (en este caso id va en Long)
            //Bidireccionalidad.
            Domicilio dom = entityManager.find(Domicilio.class, 1L);
            Cliente cli = entityManager.find(Cliente.class, 1L);

            System.out.println("DNI o PK de Cliente a partir de Domicilio: " + dom.getCliente().getDni());
            System.out.println("Domicilio (nombre calle) a partir de Cliente: " + cli.getDomicilio().getNombreCalle());

            //Continuando con la factura:
            factura1.setCliente(cli);

            Categoria perecederos = new Categoria("Perecederos");
            Categoria lacteos = new Categoria("Lacteos");
            Categoria limpieza = new Categoria("Limpieza");

            Articulo art1 = new Articulo(200, "Dulce de Leche Ilolay", 1520);
            Articulo art2 = new Articulo(300, "Detergente Magistral", 2600);

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = new DetalleFactura();
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(3040);

            art1.getDetalle().add(det1);
            factura1.getDetalles().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = new DetalleFactura();
            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(2600);

            art2.getDetalle().add(det2);
            factura1.getDetalles().add(det2);
            det2.setFactura(factura1);

            factura1.setTotal(5640);

            entityManager.persist(factura1);

            //Limpiar la conexión y terminar de hacer la persistencia en la bd.
            entityManager.flush();
            entityManager.getTransaction().commit();


        }catch (Exception e){

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo grabar la clase Cliente");}

        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
