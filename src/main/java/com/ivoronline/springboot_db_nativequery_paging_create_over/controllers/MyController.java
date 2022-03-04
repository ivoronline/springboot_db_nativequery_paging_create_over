package com.ivoronline.springboot_db_nativequery_paging_create_over.controllers;

import com.ivoronline.springboot_db_nativequery_paging_create_over.dto.PersonsDTO;
import com.ivoronline.springboot_db_nativequery_paging_create_over.entities.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@RestController
public class MyController {

  //PROPERTIES
  @PersistenceContext EntityManager entityManager;

  //================================================================
  // SELECT PERSON
  //================================================================
  // http://localhost:8080/GetPersons?pageNumber=0&pageSize=2
  @RequestMapping("GetPersons")
  PersonsDTO getPersons(
    @RequestParam Integer pageNumber,
    @RequestParam Integer pageSize
  ) {

    //EXECUTE PAGING QUERY
    String         select = "SELECT id, name, age, COUNT(*) OVER() as count FROM Person LIMIT :pageSize OFFSET :pageNumber * :pageSize";
    Query          query  = entityManager.createNativeQuery(select, Person.class);
                   query.setParameter("pageNumber", pageNumber);
                   query.setParameter("pageSize"  , pageSize  );
    List<Person>   persons = (List<Person>) query.getResultList();

    //CREATE DTO
    PersonsDTO  personsDTO         = new PersonsDTO();
                personsDTO.count   = persons.get(0).count;
                personsDTO.persons = persons;

    //RETURN DTO
    return personsDTO;

  }

}


