package ru.ssau.tk.nikitals.oop.core.ports.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TabulatedFunctionRepositoryTest {

    @Autowired
    private TabulatedFunctionRepository tabulatedFunctionRepository;

    @Test
    public void testFindByNameContainingIgnoreCase() {
        TabulatedFunctionEntity function = TabulatedFunctionEntity.builder().name("TestFunction").factoryType("TypeA").build();
        tabulatedFunctionRepository.save(function);

        List<TabulatedFunctionEntity> foundFunctions = tabulatedFunctionRepository.findByNameContainingIgnoreCase("test");
        assertThat(foundFunctions).isNotEmpty();
        assertThat(foundFunctions.get(0).getName()).isEqualTo("TestFunction");
    }

    @Test
    public void testFindByFactoryType() {
        TabulatedFunctionEntity function = TabulatedFunctionEntity.builder().name("AnotherFunction").factoryType("TypeB").build();
        tabulatedFunctionRepository.save(function);

        List<TabulatedFunctionEntity> foundFunctions = tabulatedFunctionRepository.findByFactoryType("TypeB");
        assertThat(foundFunctions).isNotEmpty();
        assertThat(foundFunctions.get(0).getFactoryType()).isEqualTo("TypeB");
    }
}