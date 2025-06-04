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
}
