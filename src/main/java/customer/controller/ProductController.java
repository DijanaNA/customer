package main.java.customer.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import main.java.customer.entity.Product;
import main.java.customer.services.ProductService;

@Path("/product")
public class ProductController {
  
  @POST
  @Path("/create")
  @Consumes(MediaType.APPLICATION_JSON)
  public String createProduct(Product product) {
    ProductService.createProduct(product);
    return "Product " + product.getName() + " is created successfully";
  }


}
