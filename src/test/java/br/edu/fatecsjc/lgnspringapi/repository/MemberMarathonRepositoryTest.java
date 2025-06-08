package br.edu.fatecsjc.lgnspringapi.repository;

import br.edu.fatecsjc.lgnspringapi.entity.MemberMarathon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberMarathonRepositoryTest {

  @Autowired
  private MemberMarathonRepository repository;

  @Test
  void testSaveAndFindById() {
    MemberMarathon mm = new MemberMarathon();
    // Defina aqui atributos mínimos necessários para salvar
    MemberMarathon saved = repository.save(mm);

    Optional<MemberMarathon> found = repository.findById(saved.getId());

    assertThat(found).isPresent();
  }

  @Test
  void testDeleteMemberMarathon() {
    MemberMarathon mm = new MemberMarathon();
    MemberMarathon saved = repository.save(mm);

    repository.delete(saved);

    Optional<MemberMarathon> found = repository.findById(saved.getId());
    assertThat(found).isNotPresent();
  }

  @Test
  void testFindByIdNotFound() {
    Optional<MemberMarathon> found = repository.findById(-1L);
    assertThat(found).isNotPresent();
  }
}
