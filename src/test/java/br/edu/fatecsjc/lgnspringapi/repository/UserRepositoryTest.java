package br.edu.fatecsjc.lgnspringapi.repository;

import br.edu.fatecsjc.lgnspringapi.entity.User;
import br.edu.fatecsjc.lgnspringapi.enums.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository repository;

  @Test
  void testSaveAndFindByEmail() {
    User user = new User();
    user.setEmail("test@example.com");
    user.setFirstName("Test");
    user.setLastName("User");
    user.setPassword("somepassword"); 
    user.setRole(Role.USER);

    repository.save(user);

    Optional<User> found = repository.findByEmail("test@example.com");

    assertThat(found).isPresent();
    assertThat(found.get().getEmail()).isEqualTo("test@example.com");
    assertThat(found.get().getFirstName()).isEqualTo("Test");
    assertThat(found.get().getLastName()).isEqualTo("User");
  }

}
