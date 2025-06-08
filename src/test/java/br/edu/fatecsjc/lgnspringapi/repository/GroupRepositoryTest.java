package br.edu.fatecsjc.lgnspringapi.repository;

import br.edu.fatecsjc.lgnspringapi.entity.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GroupRepositoryTest {

  @Autowired
  private GroupRepository repository;

  @Test
  void testSaveAndFindById() {
    Group group = new Group();
    group.setName("Test Group");
    Group saved = repository.save(group);

    Optional<Group> found = repository.findById(saved.getId());

    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Test Group");
  }

  @Test
  void testUpdateGroup() {
    Group group = new Group();
    group.setName("Original");
    Group saved = repository.save(group);

    saved.setName("Updated");
    Group updated = repository.save(saved);

    Optional<Group> found = repository.findById(updated.getId());
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Updated");
  }

  @Test
  void testDeleteGroup() {
    Group group = new Group();
    group.setName("To Delete");
    Group saved = repository.save(group);

    repository.delete(saved);

    Optional<Group> found = repository.findById(saved.getId());
    assertThat(found).isNotPresent();
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Group> found = repository.findById(-1L);
    assertThat(found).isNotPresent();
  }

  @Test
  void testSaveMultipleGroups() {
    Group group1 = new Group();
    group1.setName("Group 1");
    Group group2 = new Group();
    group2.setName("Group 2");

    repository.save(group1);
    repository.save(group2);

    assertThat(repository.findAll()).hasSizeGreaterThanOrEqualTo(2);
  }

}
