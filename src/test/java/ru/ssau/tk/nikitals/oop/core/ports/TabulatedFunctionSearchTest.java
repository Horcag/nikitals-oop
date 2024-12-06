package ru.ssau.tk.nikitals.oop.core.ports;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import org.springframework.test.context.ActiveProfiles;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.repository.TabulatedFunctionRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TabulatedFunctionSearchTest {

    @Autowired
    private TabulatedFunctionRepository tabulatedFunctionRepository;
    @Test
    void testFindByName() {
        TabulatedFunctionEntity function = TabulatedFunctionEntity.builder()
                .name("TestFunction")
                .factoryType("Array")
                .build();
        tabulatedFunctionRepository.save(function);

        List<TabulatedFunctionEntity> foundFunction = tabulatedFunctionRepository.findByNameContainingIgnoreCase("TestFunction");
        assertThat(foundFunction).isNotEmpty();
        assertThat(foundFunction.get(0).getName()).isEqualTo("TestFunction");

    }

    @Test
    void testFindById() {
        TabulatedFunctionEntity function = TabulatedFunctionEntity.builder()
                .name("TestFunction")
                .factoryType("Array")
                .build();
        tabulatedFunctionRepository.save(function);

        Optional<TabulatedFunctionEntity> foundFunction = tabulatedFunctionRepository.findById(function.getId());
        assertThat(foundFunction).isPresent();
        assertThat(foundFunction.get().getId()).isEqualTo(function.getId());
    }

    @Test
    void testFindByFactoryType() {
        TabulatedFunctionEntity function1 = TabulatedFunctionEntity.builder()
                .name("Function1")
                .factoryType("Array")
                .build();
        TabulatedFunctionEntity function2 = TabulatedFunctionEntity.builder()
                .name("Function2")
                .factoryType("Array")
                .build();
        tabulatedFunctionRepository.save(function1);
        tabulatedFunctionRepository.save(function2);

        List<TabulatedFunctionEntity> functions = tabulatedFunctionRepository.findByFactoryType("Array");
        assertThat(functions).hasSize(2);
        assertThat(functions).extracting(TabulatedFunctionEntity::getName).contains("Function1", "Function2");
    }

    @Test
    void testFindAll() {
        TabulatedFunctionEntity function1 = TabulatedFunctionEntity.builder()
                .name("Function1")
                .factoryType("Array")
                .build();
        TabulatedFunctionEntity function2 = TabulatedFunctionEntity.builder()
                .name("Function2")
                .factoryType("Array")
                .build();
        tabulatedFunctionRepository.save(function1);
        tabulatedFunctionRepository.save(function2);

        List<TabulatedFunctionEntity> functions = tabulatedFunctionRepository.findAll();
        assertThat(functions).hasSize(2);
        assertThat(functions).extracting(TabulatedFunctionEntity::getName).contains("Function1", "Function2");
    }
}