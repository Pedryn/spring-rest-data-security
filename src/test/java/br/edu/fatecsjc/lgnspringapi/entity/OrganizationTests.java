package br.edu.fatecsjc.lgnspringapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrganizationTest {

  private Organization organization;
  private Marathon marathon1;
  private Marathon marathon2;

  @BeforeEach
  void setUp() {
    marathon1 = Marathon.builder()
        .id(1L)
        .name("Meia Maratona SJC")
        .date("2025-06-15")
        .build();

    marathon2 = Marathon.builder()
        .id(2L)
        .name("Circuito Oscar Fila")
        .date("2025-03-25")
        .build();

    organization = Organization.builder()
        .id(1L)
        .name("Red Cross")
        .number("123")
        .street("Rua A")
        .neighborhood("Centro")
        .cep("01000-000")
        .municipality("São Paulo")
        .state("SP")
        .institutionName("Red Cross Institution")
        .hostCountry("Brazil")
        .marathons(new ArrayList<>(List.of(marathon1, marathon2)))
        .build();
  }

  @Test
  void shouldInitializeEntityWithBuilder() {
    assertThat(organization).isNotNull();
    assertThat(organization.getId()).isEqualTo(1L);
    assertThat(organization.getName()).isEqualTo("Red Cross");
    assertThat(organization.getNumber()).isEqualTo("123");
    assertThat(organization.getStreet()).isEqualTo("Rua A");
    assertThat(organization.getNeighborhood()).isEqualTo("Centro");
    assertThat(organization.getCep()).isEqualTo("01000-000");
    assertThat(organization.getMunicipality()).isEqualTo("São Paulo");
    assertThat(organization.getState()).isEqualTo("SP");
    assertThat(organization.getInstitutionName()).isEqualTo("Red Cross Institution");
    assertThat(organization.getHostCountry()).isEqualTo("Brazil");
    assertThat(organization.getMarathons()).hasSize(2);
    assertThat(organization.getMarathons().get(0).getName()).isEqualTo("Meia Maratona SJC");
    assertThat(organization.getMarathons().get(1).getName()).isEqualTo("Circuito Oscar Fila");
  }

  @Test
  void shouldSetAndGetId() {
    organization.setId(99L);
    assertThat(organization.getId()).isEqualTo(99L);
  }

  @Test
  void shouldSetAndGetName() {
    organization.setName("Green Peace");
    assertThat(organization.getName()).isEqualTo("Green Peace");
  }

  @Test
  void shouldSetAndGetAddressFields() {
    organization.setNumber("456");
    organization.setStreet("Rua B");
    organization.setNeighborhood("Jardim");
    organization.setCep("02000-000");
    organization.setMunicipality("Rio de Janeiro");
    organization.setState("RJ");

    assertThat(organization.getNumber()).isEqualTo("456");
    assertThat(organization.getStreet()).isEqualTo("Rua B");
    assertThat(organization.getNeighborhood()).isEqualTo("Jardim");
    assertThat(organization.getCep()).isEqualTo("02000-000");
    assertThat(organization.getMunicipality()).isEqualTo("Rio de Janeiro");
    assertThat(organization.getState()).isEqualTo("RJ");
  }

  @Test
  void shouldSetAndGetInstitutionAndHostCountry() {
    organization.setInstitutionName("Nova Instituição");
    organization.setHostCountry("USA");

    assertThat(organization.getInstitutionName()).isEqualTo("Nova Instituição");
    assertThat(organization.getHostCountry()).isEqualTo("USA");
  }

  @Test
  void shouldAddMarathonToList() {
    Marathon newMarathon = Marathon.builder()
        .id(3L)
        .name("Brasília Marathon")
        .date("2025-08-10")
        .build();

    organization.getMarathons().add(newMarathon);

    assertThat(organization.getMarathons()).hasSize(3);
    assertThat(organization.getMarathons().get(2).getName()).isEqualTo("Brasília Marathon");
  }

  @Test
  void shouldRemoveMarathonFromList() {
    organization.getMarathons().remove(marathon1);

    assertThat(organization.getMarathons()).hasSize(1);
    assertThat(organization.getMarathons()).doesNotContain(marathon1);
  }

  @Test
  void shouldReturnToString() {
    String result = organization.toString();
    assertThat(result).contains("Red Cross");
    assertThat(result).contains("Meia Maratona SJC"); // aceita que aparece
  }

  @Test
  void shouldInitializeMarathonListManuallyIfNotSet() {
    Organization org = Organization.builder()
        .id(10L)
        .name("Instituto Alpha")
        .build();

    org.setMarathons(new ArrayList<>());
    assertThat(org.getMarathons()).isEmpty();
  }

  @Test
  void shouldAllowSettingMarathonsToNull() {
    organization.setMarathons(null);
    assertThat(organization.getMarathons()).isNull();
  }

  @Test
  void shouldAllowReplacingMarathonList() {
    List<Marathon> newList = List.of(
        Marathon.builder().id(99L).name("Nova Corrida").date("2025-12-12").build()
    );
    organization.setMarathons(newList);

    assertThat(organization.getMarathons()).hasSize(1);
    assertThat(organization.getMarathons().get(0).getName()).isEqualTo("Nova Corrida");
  }

@Test
void shouldTestEqualsAndHashCode() {
  Organization org1 = Organization.builder().id(1L).name("Org").build();
  Organization org2 = Organization.builder().id(1L).name("Org").build();
  Organization org3 = Organization.builder().id(2L).name("Other").build();

  assertThat(org1).isEqualTo(org2);
  assertThat(org1).hasSameHashCodeAs(org2);
  assertThat(org1).isNotEqualTo(org3);
}
}
