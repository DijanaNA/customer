
package main.java.customer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import main.java.customer.entity.Customer;
import main.java.customer.entity.Manufactorer;
import main.java.customer.entity.Product;
import main.java.customer.entity.ShoppingCart;

public class ShoppingCartService {

  static SessionFactory factory;

  public static Session init() {

    Configuration cfg = new Configuration();
    cfg.addAnnotatedClass(main.java.customer.entity.Address.class);
    cfg.addAnnotatedClass(main.java.customer.entity.Customer.class);
    cfg.addAnnotatedClass(main.java.customer.entity.Manufactorer.class);
    cfg.addAnnotatedClass(main.java.customer.entity.Product.class);
    cfg.addAnnotatedClass(main.java.customer.entity.ShoppingCart.class);
    cfg.configure();

    factory = cfg.configure()
        .buildSessionFactory();
    Session session = factory.openSession();
    return session;

  }

  public static void initService() {

    Session session = init();
    Transaction tx = null;

    try {

      tx = session.beginTransaction();

      // Date date = new Date(System.currentTimeMillis());
      // user.setCreatedOn(date);
      //
      // session.save(user);

      tx.commit();
      session.close();

    } catch (Exception e) {
      tx.rollback();
      System.out.println(e);
    }

  }

  public static Response createCart(Integer customerId, ShoppingCart cart) {

    Session session = init();
    Transaction tx = null;

    try {

      tx = session.beginTransaction();

      Customer customer = session.get(Customer.class, customerId);

      if (customer == null) {

        return Response.noContent()
            .build();

      }

      cart.setCreatedOn(new Date(System.currentTimeMillis()));
      cart.setCustomer(customer);
      
      List<Product> products = getProducts(cart.getProducts());
      
      cart.setProducts(products);

      session.save(cart);

      tx.commit();
      session.close();

      return Response.ok(cart, MediaType.APPLICATION_JSON)
          .build();

    } catch (Exception e) {
      tx.rollback();
      System.out.println(e);
      return Response.notModified()
          .build();
    }

  }

  private static List<Product> getProducts(List<Product> products) {
    
    Session session = init();
    Transaction tx = null;

    List<Product> res = new ArrayList<Product>();
    try {

      session = factory.openSession();
      tx = session.beginTransaction();
      
      for (Product product : products) {
        
        Query query = session.createQuery("FROM main.java.customer.entity.Product p WHERE p.name=:name ");
        query.setParameter("name", product.getName());
        
        Product p = (Product) query.getResultList().get(0);
        
        res.add(p);
        
      }
      
      
      tx.commit();
      session.close();
      return res;

    } catch (Exception e) {
      tx.rollback();
      System.out.println(e);
      return null;
    }
    
    
    
    
  }

}
