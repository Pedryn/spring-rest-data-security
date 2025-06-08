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

  @Test
  void testUpdateOrganization() {
    Organization org = new Organization();
    org.setName("Original Name");
    Organization saved = repository.save(org);

    saved.setName("Updated Name");
    Organization updated = repository.save(saved);

    Optional<Organization> found = repository.findById(updated.getId());
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Updated Name");
  }

  @Test
  void testDeleteOrganization() {
    Organization org = new Organization();
    org.setName("To Delete");
    Organization saved = repository.save(org);

    repository.delete(saved);

    Optional<Organization> found = repository.findById(saved.getId());
    assertThat(found).isNotPresent();
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Organization> found = repository.findById(-1L);
    assertThat(found).isNotPresent();
  }

  @Test
  void testSaveMultipleOrganizations() {
    Organization org1 = new Organization();
    org1.setName("Org 1");
    Organization org2 = new Organization();
    org2.setName("Org 2");

    repository.save(org1);
    repository.save(org2);

    assertThat(repository.findAll()).hasSizeGreaterThanOrEqualTo(2);
  }

}
