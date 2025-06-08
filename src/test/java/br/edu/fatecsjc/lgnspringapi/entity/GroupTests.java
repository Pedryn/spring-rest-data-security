package br.edu.fatecsjc.lgnspringapi.entity;

import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GroupTest {

  private Group group;
  private Member member1;
  private Member member2;

  @BeforeEach
  void setUp() {
    member1 = Member.builder()
        .id(1L)
        .name("Pedro")
        .age(20)
        .build();

    member2 = Member.builder()
        .id(2L)
        .name("Ana Clara")
        .age(18)
        .build();

    group = Group.builder()
        .id(1L)
        .name("Grupo da amizade é tudo")
        .members(new java.util.ArrayList<>(List.of(member1, member2)))
        .build();
  }

  @Test
  void shouldInitializeEntityWithBuilder() {
    assertThat(group).isNotNull();
    assertThat(group.getId()).isEqualTo(1L);
    assertThat(group.getName()).isEqualTo("Grupo da amizade é tudo");
    assertThat(group.getMembers()).hasSize(2);
    assertThat(group.getMembers().get(0).getName()).isEqualTo("Pedro");
    assertThat(group.getMembers().get(1).getName()).isEqualTo("Ana Clara");
  }

  @Test
  void shouldSetAndGetId() {
    group.setId(99L);
    assertThat(group.getId()).isEqualTo(99L);
  }

  @Test
  void shouldSetAndGetName() {

    group.setName("Novo Nome");

    assertThat(group.getName()).isEqualTo("Novo Nome");
  }

  @Test
  void shouldAddMemberToList() {
    Member newMember = Member.builder()
        .id(3L)
        .name("André")
        .age(22)
        .build();
    group.getMembers().add(newMember);
    assertThat(group.getMembers()).hasSize(3);
    assertThat(group.getMembers().get(2).getName()).isEqualTo("André");
  }

  @Test
  void shouldRemoveMemberFromList() {
    group.getMembers().remove(member1);
    assertThat(group.getMembers()).hasSize(1);
    assertThat(group.getMembers().contains(member1)).isFalse();
  }

  @Test
  void shouldToStringExcludeMembersDueToAnnotation() {
    String toString = group.toString();
    assertThat(toString).contains("name=Grupo da amizade é tudo");
    assertThat(toString).doesNotContain("members");
  }

  @Test
  void shouldDefaultMembersListBeInitialized() {
    Group emptyGroup = new Group();
    assertThat(emptyGroup.getMembers()).isNotNull();
    assertThat(emptyGroup.getMembers()).isEmpty();
  }
}