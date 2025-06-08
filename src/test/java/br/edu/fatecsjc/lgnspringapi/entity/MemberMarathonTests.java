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
        .time(7200L) 
        .member(member)
        .marathon(marathon)
        .build();
  }

  @Test
  void shouldInitializeEntityWithBuilder() {
    assertThat(memberMarathon).isNotNull();
    assertThat(memberMarathon.getId()).isEqualTo(1L);
    assertThat(memberMarathon.getName()).isEqualTo("Neymar Jr.");
    assertThat(memberMarathon.getTime()).isEqualTo(7200L);
    assertThat(memberMarathon.getMember().getName()).isEqualTo("Neymar Jr.");
    assertThat(memberMarathon.getMarathon().getName()).isEqualTo("Meia Maratona SJC");
  }

  @Test
  void shouldSetAndGetId() {
    memberMarathon.setId(99L);
    assertThat(memberMarathon.getId()).isEqualTo(99L);
  }

  @Test
  void shouldSetAndGetName() {
    memberMarathon.setName("Cristiano Ronaldo");
    assertThat(memberMarathon.getName()).isEqualTo("Cristiano Ronaldo");
  }

  @Test
  void shouldSetAndGetTime() {
    memberMarathon.setTime(8000L);
    assertThat(memberMarathon.getTime()).isEqualTo(8000L);
  }

  @Test
  void shouldSetAndGetMember() {
    Member newMember = Member.builder()
        .id(2L)
        .name("Pedro Antonio")
        .age(30)
        .build();


    memberMarathon.setMember(newMember);

    assertThat(memberMarathon.getMember()).isEqualTo(newMember);
    assertThat(memberMarathon.getMember().getName()).isEqualTo("Pedro Antonio");
  }

  @Test
  void shouldSetAndGetMarathon() {

    Marathon newMarathon = Marathon.builder()
        .id(2L)
        .name("Meia Maratona SJC")
        .date("2025-05-18")
        .build();

    memberMarathon.setMarathon(newMarathon);

    assertThat(memberMarathon.getMarathon()).isEqualTo(newMarathon);
    assertThat(memberMarathon.getMarathon().getName()).isEqualTo("Meia Maratona SJC");
  }
}