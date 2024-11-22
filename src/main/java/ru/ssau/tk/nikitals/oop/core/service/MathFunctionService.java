package ru.ssau.tk.nikitals.oop.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.tk.nikitals.oop.core.models.MathFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.MathFunctionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MathFunctionService {

    private final MathFunctionRepository repository;

    public MathFunctionEntity createFunction(String name, String type, String parameters) {
        MathFunctionEntity function = MathFunctionEntity.builder()
                .name(name)
                .type(type)
                .parameters(parameters)
                .build();
        return repository.save(function);
    }

    public Optional<MathFunctionEntity> getFunctionById(Long id) {
        return repository.findById(id);
    }

    public List<MathFunctionEntity> getAllFunctions() {
        return repository.findAll();
    }

    public void deleteFunction(Long id) {
        repository.deleteById(id);
    }
}


