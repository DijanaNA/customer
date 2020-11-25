package main.java.customer.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import main.java.customer.entity.Customer;
import main.java.customer.services.CustomerService;

@Path("/customer")
public class CustomerController {
  
  @POST
  @Path("/create")
  @Consumes(MediaType.APPLICATION_JSON)
  public String createUser(Customer customer) {
    CustomerService.createCustomer(customer);
    return "User " + customer.getName() + " is created successfully";
  }


}
