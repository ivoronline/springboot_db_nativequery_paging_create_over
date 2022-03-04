package com.ivoronline.springboot_db_nativequery_paging_create_over.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
public class Person {

  //PROPERTIES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer    id;
  public String     name;
  public Integer    age;

  @JsonIgnore
  public BigInteger count;

}
