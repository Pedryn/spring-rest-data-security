package br.edu.fatecsjc.lgnspringapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberMarathonTest {

  private MemberMarathon memberMarathon;
  private Member member;
  private Marathon marathon;

  @BeforeEach
  void setUp() {
    member = Member.builder()
        .id(1L)
        .name("Neymar Jr.")
        .age(25)
        .build();

    marathon = Marathon.builder()
        .id(1L)
        .name("Meia Maratona SJC")
        .date("2025-06-15")
        .build();

    memberMarathon = MemberMarathon.builder()
        .id(1L)
        .name("Neymar Jr.")
        .time(7200L) // 2 horas em segundos
        .member(member)
        .marathon(marathon)
        .build();
  }

  @Test
  void shouldInitializeEntityWithBuilder() {
    // Then
    assertThat(memberMarathon).isNotNull();
    assertThat(memberMarathon.getId()).isEqualTo(1L);
    assertThat(memberMarathon.getName()).isEqualTo("Neymar Jr.");
    assertThat(memberMarathon.getTime()).isEqualTo(7200L);
    assertThat(memberMarathon.getMember().getName()).isEqualTo("Neymar Jr.");
    assertThat(memberMarathon.getMarathon().getName()).isEqualTo("Meia Maratona SJC");
  }

  @Test
  void shouldSetAndGetId() {
    // When
    memberMarathon.setId(99L);

    // Then
    assertThat(memberMarathon.getId()).isEqualTo(99L);
  }

  @Test
  void shouldSetAndGetName() {
    // When
    memberMarathon.setName("Cristiano Ronaldo");

    // Then
    assertThat(memberMarathon.getName()).isEqualTo("Cristiano Ronaldo");
  }

  @Test
  void shouldSetAndGetTime() {
    // When
    memberMarathon.setTime(8000L);

    // Then
    assertThat(memberMarathon.getTime()).isEqualTo(8000L);
  }

  @Test
  void shouldSetAndGetMember() {
    // Given
    Member newMember = Member.builder()
        .id(2L)
        .name("Carlos Souza")
        .age(30)
        .build();

    // When
    memberMarathon.setMember(newMember);

    // Then
    assertThat(memberMarathon.getMember()).isEqualTo(newMember);
    assertThat(memberMarathon.getMember().getName()).isEqualTo("Carlos Souza");
  }

  @Test
  void shouldSetAndGetMarathon() {
    // Given
    Marathon newMarathon = Marathon.builder()
        .id(2L)
        .name("Rio Marathon")
        .date("2025-07-20")
        .build();

    // When
    memberMarathon.setMarathon(newMarathon);

    // Then
    assertThat(memberMarathon.getMarathon()).isEqualTo(newMarathon);
    assertThat(memberMarathon.getMarathon().getName()).isEqualTo("Rio Marathon");
  }
}