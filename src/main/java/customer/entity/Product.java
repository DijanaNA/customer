package main.java.customer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String category;
  
  @ManyToOne
  @JoinColumn(referencedColumnName = "id")
  private Manufactorer manufactorer;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Manufactorer getManufactorer() {
    return manufactorer;
  }

  public void setManufactorer(Manufactorer manufactorer) {
    this.manufactorer = manufactorer;
  }

  public Product(String name, String category, Manufactorer manufactorer) {
    super();
    this.name = name;
    this.category = category;
    this.manufactorer = manufactorer;
  }
  
  public Product() {
    
  }

}
