package br.edu.fatecsjc.lgnspringapi.repository;

import br.edu.fatecsjc.lgnspringapi.entity.Marathon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MarathonRepositoryTest {

  @Autowired
  private MarathonRepository repository;

  @Test
  void testSaveAndFindById() {
    Marathon marathon = new Marathon();
    marathon.setName("Test Marathon");
    Marathon saved = repository.save(marathon);

    Optional<Marathon> found = repository.findById(saved.getId());

    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Test Marathon");
  }
}
