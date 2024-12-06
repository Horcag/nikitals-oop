package ru.ssau.tk.nikitals.oop.core.service;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MathFunctionRegistry {
    private final Map<String, Class<? extends MathFunction>> functionMap = new HashMap<>();

    public MathFunctionRegistry() {
        Reflections reflections = new Reflections("ru.ssau.tk.nikitals.oop.core.domain.functions.impl");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MathFunctionInfo.class);

        for (Class<?> clazz : annotatedClasses) {
            if (MathFunction.class.isAssignableFrom(clazz)) {
                MathFunctionInfo info = clazz.getAnnotation(MathFunctionInfo.class);
                functionMap.put(info.name(), safeCast(clazz));
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Class<? extends MathFunction> safeCast(Class<T> clazz) {
        return (Class<? extends MathFunction>) clazz;
    }

    public List<String> getAvailableFunctions() {
        return functionMap.keySet().stream().sorted().collect(Collectors.toList());
    }

    public Optional<MathFunction> createFunction(String name) {
        Class<? extends MathFunction> clazz = functionMap.get(name);
        if (clazz != null) {
            try {
                return Optional.of(clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при создании функции: " + name, e);
            }
        }
        return Optional.empty();
    }
}
