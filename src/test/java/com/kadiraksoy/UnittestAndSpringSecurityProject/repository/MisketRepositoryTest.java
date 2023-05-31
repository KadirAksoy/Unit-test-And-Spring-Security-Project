package com.kadiraksoy.UnittestAndSpringSecurityProject.repository;

import com.kadiraksoy.UnittestAndSpringSecurityProject.model.Misket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

// The @DataJpaTest annotation is used in unit tests that involve testing components related to JPA (Java Persistence API).
// The @AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) annotation is used to configure the database used in unit tests
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MisketRepositoryTest {

    @Autowired
    private MisketRepository misketRepository;

    @Test
    public void whenMisketRepositorySaveAll_thenReturnMisket(){
        Misket misket = Misket.builder().name("blue").type("hit").build();   // Data

        Misket savedMisket = misketRepository.save(misket);                   //function

        Assertions.assertEquals(misket,savedMisket);                         // Assert
    }

    @Test
    public void whenMisketRepositoryGetAllMisket_thenReturnMisket(){
        Misket misket1 = Misket.builder().name("blue").type("hit").build();        // Data
        Misket misket2 = Misket.builder().name("yellow").type("defense").build();

        misketRepository.save(misket1);                                         // function
        misketRepository.save(misket2);

        List<Misket> misketList = misketRepository.findAll();                       // Data

        org.assertj.core.api.Assertions.assertThat(misketList).isNotNull();              // Assert
        org.assertj.core.api.Assertions.assertThat(misketList.size()).isEqualTo(2);
    }

    @Test
    public void whenMisketRepositoryFindByMisketId_thenReturnMisket(){
        Misket misket = Misket.builder().name("blue").type("hit").build();

        misketRepository.save(misket);

        Misket savedMisket = misketRepository.findById(misket.getId()).get();

        Assertions.assertEquals(misket,savedMisket);
    }

    @Test
    public void whenMisketRepositoryFindByMisketType_thenReturnMisket(){
        Misket misket = Misket.builder().name("blue").type("hit").build();

        misketRepository.save(misket);

        Misket savedMisket = misketRepository.findByType(misket.getType()).get();

        Assertions.assertEquals(misket,savedMisket);
    }

    @Test
    public void whenMisketRepositoryUpdateMisketWithMisketId_thenReturnMisket(){
        Misket misket = Misket.builder().name("blue").type("hit").build();


        misketRepository.save(misket);

        Misket updatedMisket = misketRepository.findById(misket.getId()).get();
        updatedMisket.setName("purple");
        updatedMisket.setType("goolkeep");

        misketRepository.save(updatedMisket);

        org.assertj.core.api.Assertions.assertThat(updatedMisket.getName()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(updatedMisket.getType()).isNotNull();
    }

    @Test
    public void whenMisketRepositoryDeleteMisketWithMisketId_thenReturnIsEmpty(){
        Misket misket = Misket.builder().name("blue").type("hit").build();

        misketRepository.save(misket);
        misketRepository.deleteById(misket.getId());

        Optional<Misket> optionalMisket = misketRepository.findById(misket.getId());

        org.assertj.core.api.Assertions.assertThat(optionalMisket).isEmpty();
    }


}