package br.edu.fatecsjc.lgnspringapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MarathonTest {

  private Marathon marathon;
  private Organization organization;
  private MemberMarathon memberMarathon1;
  private MemberMarathon memberMarathon2;

  @BeforeEach
  void setUp() {
    organization = Organization.builder()
        .id(1L)
        .name("Guerreiros de Esparta")
        .build();

    memberMarathon1 = MemberMarathon.builder()
        .id(1L)
        .name("Neymar Jr.")
        .time(7200L) // time: 2 hours in seconds
        .build();

    memberMarathon2 = MemberMarathon.builder()
        .id(2L)
        .name("Cristiano Ronaldo")
        .time(7800L) // time: 2h10 hours in seconds
        .build();

    marathon = Marathon.builder()
        .id(1L)
        .name("Meia Maratona SJC")
        .date("2025-05-18")
        .organization(organization)
        .memberMarathons(new java.util.ArrayList<>(List.of(memberMarathon1, memberMarathon2)))
        .build();
  }

  @Test
  void shouldInitializeEntityWithBuilder() {
    assertThat(marathon).isNotNull();
    assertThat(marathon.getId()).isEqualTo(1L);
    assertThat(marathon.getName()).isEqualTo("Meia Maratona SJC");
    assertThat(marathon.getDate()).isEqualTo("2025-05-18");
    assertThat(marathon.getOrganization().getName()).isEqualTo("Guerreiros de Esparta");
    assertThat(marathon.getMemberMarathons()).hasSize(2);
    assertThat(marathon.getMemberMarathons().get(0).getName()).isEqualTo("Neymar Jr.");
    assertThat(marathon.getMemberMarathons().get(1).getTime()).isEqualTo(7800L);
  }

  @Test
  void shouldSetAndGetId() {
    marathon.setId(99L);
    assertThat(marathon.getId()).isEqualTo(99L);
  }

  @Test
  void shouldSetAndGetName() {
    marathon.setName("Circuito Oscar Fila");
    assertThat(marathon.getName()).isEqualTo("Circuito Oscar Fila");
  }

  @Test
  void shouldSetAndGetDate() {
    marathon.setDate("2025-03-25");
    assertThat(marathon.getDate()).isEqualTo("2025-03-25");
  }

  @Test
  void shouldSetAndGetOrganization() {
    Organization newOrg = Organization.builder()
        .id(2L)
        .name("Amigos de Jacareí")
        .build();

    marathon.setOrganization(newOrg);

    assertThat(marathon.getOrganization()).isEqualTo(newOrg);
    assertThat(marathon.getOrganization().getName()).isEqualTo("Amigos de Jacareí");
  }

  @Test
  void shouldAddMemberMarathonToList() {
    MemberMarathon newMember = MemberMarathon.builder()
        .id(3L)
        .name("Pedro Antonio")
        .time(7800L)
        .build();

    marathon.getMemberMarathons().add(newMember);

    assertThat(marathon.getMemberMarathons()).hasSize(3);
    assertThat(marathon.getMemberMarathons().get(2).getName()).isEqualTo("Pedro Antonio");
  }

  @Test
  void shouldRemoveMemberMarathonFromList() {
    marathon.getMemberMarathons().remove(memberMarathon1);

    assertThat(marathon.getMemberMarathons()).hasSize(1);
    assertThat(marathon.getMemberMarathons()).doesNotContain(memberMarathon1);
  }
}