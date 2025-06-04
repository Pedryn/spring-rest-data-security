package br.edu.fatecsjc.lgnspringapi.repository;

import br.edu.fatecsjc.lgnspringapi.entity.Group;
import br.edu.fatecsjc.lgnspringapi.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private GroupRepository groupRepository;

  @Test
  void testSaveAndFindById() {
    Group group = new Group();
    group.setName("Group for Members");
    group = groupRepository.save(group);

    Member member = new Member();
    member.setName("Member 1");
    member.setGroup(group);
    Member saved = memberRepository.save(member);

    Optional<Member> found = memberRepository.findById(saved.getId());
    assertThat(found).isPresent();
    assertThat(found.get().getName()).isEqualTo("Member 1");
  }

  @Test
  void testDeleteMembersByGroup() {
    Group group = new Group();
    group.setName("Group to delete members");
    group = groupRepository.save(group);

    Member member1 = new Member();
    member1.setName("Member A");
    member1.setGroup(group);
    memberRepository.save(member1);

    Member member2 = new Member();
    member2.setName("Member B");
    member2.setGroup(group);
    memberRepository.save(member2);

    List<Member> beforeDelete = memberRepository.findAll();
    assertThat(beforeDelete).hasSize(2);

    memberRepository.deleteMembersByGroup(group);

    List<Member> afterDelete = memberRepository.findAll();
    assertThat(afterDelete).isEmpty();
  }
}
