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

  @Test
  void testFindByEmailNotFound() {
    Optional<User> found = repository.findByEmail("notfound@example.com");
    assertThat(found).isNotPresent();
  }

  @Test
  void testSaveAndUpdateUser() {
    User user = new User();
    user.setEmail("update@example.com");
    user.setFirstName("First");
    user.setLastName("Last");
    user.setPassword("pass");
    user.setRole(Role.USER);

    User saved = repository.save(user);

    saved.setFirstName("Updated");
    repository.save(saved);

    Optional<User> found = repository.findByEmail("update@example.com");
    assertThat(found).isPresent();
    assertThat(found.get().getFirstName()).isEqualTo("Updated");
  }

  @Test
  void testDeleteUser() {
    User user = new User();
    user.setEmail("delete@example.com");
    user.setFirstName("ToDelete");
    user.setLastName("User");
    user.setPassword("pass");
    user.setRole(Role.USER);

    User saved = repository.save(user);
    repository.delete(saved);

    Optional<User> found = repository.findByEmail("delete@example.com");
    assertThat(found).isNotPresent();
  }

}
