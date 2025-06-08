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

  @Test
  void testUpdateMarathon() {
    Marathon marathon = new Marathon();
    marathon.setName("Original Name");
    Marathon saved = repository.save(marathon);

    saved.setName("Updated Name");
    Marathon updated = repository.save(saved);

    Optional<Marathon> found = repository.findById(updated.getId());
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Updated Name");
  }

  @Test
  void testDeleteMarathon() {
    Marathon marathon = new Marathon();
    marathon.setName("To Delete");
    Marathon saved = repository.save(marathon);

    repository.delete(saved);

    Optional<Marathon> found = repository.findById(saved.getId());
    assertThat(found).isNotPresent();
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Marathon> found = repository.findById(-1L);
    assertThat(found).isNotPresent();
  }

  @Test
  void testSaveMultipleMarathons() {
    Marathon marathon1 = new Marathon();
    marathon1.setName("Marathon 1");
    Marathon marathon2 = new Marathon();
    marathon2.setName("Marathon 2");

    repository.save(marathon1);
    repository.save(marathon2);

    assertThat(repository.findAll()).hasSizeGreaterThanOrEqualTo(2);
  }

}
