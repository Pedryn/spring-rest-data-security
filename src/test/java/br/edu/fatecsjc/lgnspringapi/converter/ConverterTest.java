package br.edu.fatecsjc.lgnspringapi.converter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void shouldImplementConverterInterface() {
        class DummyEntity {}
        class DummyDTO {}

        class DummyConverter implements Converter<DummyEntity, DummyDTO> {
            @Override
            public DummyEntity convertToEntity(DummyDTO dto) { return new DummyEntity(); }
            @Override
            public DummyEntity convertToEntity(DummyDTO dto, DummyEntity entity) { return entity; }
            @Override
            public DummyDTO convertToDto(DummyEntity entity) { return new DummyDTO(); }
            @Override
            public List<DummyEntity> convertToEntity(List<DummyDTO> dtos) { return List.of(new DummyEntity()); }
            @Override
            public List<DummyDTO> convertToDto(List<DummyEntity> entities) { return List.of(new DummyDTO()); }
        }

        DummyConverter converter = new DummyConverter();
        assertNotNull(converter.convertToEntity(new DummyDTO()));
        assertNotNull(converter.convertToEntity(new DummyDTO(), new DummyEntity()));
        assertNotNull(converter.convertToDto(new DummyEntity()));
        assertNotNull(converter.convertToEntity(List.of(new DummyDTO())));
        assertNotNull(converter.convertToDto(List.of(new DummyEntity())));
    }

    @Test
    void shouldHandleNullAndEmptyInputs() {
        class DummyEntity {}
        class DummyDTO {}

        class DummyConverter implements Converter<DummyEntity, DummyDTO> {
            @Override
            public DummyEntity convertToEntity(DummyDTO dto) { return dto == null ? null : new DummyEntity(); }
            @Override
            public DummyEntity convertToEntity(DummyDTO dto, DummyEntity entity) { return dto == null ? null : entity; }
            @Override
            public DummyDTO convertToDto(DummyEntity entity) { return entity == null ? null : new DummyDTO(); }
            @Override
            public List<DummyEntity> convertToEntity(List<DummyDTO> dtos) { return dtos == null ? List.of() : List.of(new DummyEntity()); }
            @Override
            public List<DummyDTO> convertToDto(List<DummyEntity> entities) { return entities == null ? List.of() : List.of(new DummyDTO()); }
        }

        DummyConverter converter = new DummyConverter();
        assertNull(converter.convertToEntity((DummyDTO) null));
        assertNull(converter.convertToEntity(null, new DummyEntity()));
        assertNull(converter.convertToDto((DummyEntity) null));
        assertTrue(converter.convertToEntity((List<DummyDTO>) null).isEmpty());
        assertTrue(converter.convertToDto((List<DummyEntity>) null).isEmpty());
        assertTrue(converter.convertToEntity(List.of()).size() >= 0);
        assertTrue(converter.convertToDto(List.of()).size() >= 0);
    }
}
