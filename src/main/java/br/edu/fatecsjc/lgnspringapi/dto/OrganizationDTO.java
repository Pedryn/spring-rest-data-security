package br.edu.fatecsjc.lgnspringapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
  private Long id;
  private String name;
  private String number;
  private String street;
  private String neighborhood;
  private String cep;
  private String municipality;
  private String state;
  private List<MarathonDTO> marathons;
}
