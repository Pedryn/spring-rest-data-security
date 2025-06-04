package br.edu.fatecsjc.lgnspringapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.edu.fatecsjc.lgnspringapi.entity.Organization;

@DataJpaTest
public class OrganizationRepositoryTest {

  @Autowired
  private OrganizationRepository repository;

  @Test
  void testSaveAndFindById() {
    Organization org = new Organization();
    org.setName("Test Organization");
    Organization saved = repository.save(org);

    Optional<Organization> found = repository.findById(saved.getId());

    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Test Organization");
  }
}
