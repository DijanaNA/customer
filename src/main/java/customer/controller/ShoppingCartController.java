
package main.java.customer.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import main.java.customer.entity.ShoppingCart;
import main.java.customer.services.ShoppingCartService;

@Path("/buy")
public class ShoppingCartController {

  @GET
  @Path("/init")
  @Produces(MediaType.APPLICATION_JSON)
  public String init() {

    ShoppingCartService.initService();

    return "Init";
  }

  @POST
  @Path("/cart/{customerId}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void createShoppingCart(@PathParam("customerId") Integer customerId, ShoppingCart cart) {

    ShoppingCartService.createCart(customerId, cart);

  }

}
