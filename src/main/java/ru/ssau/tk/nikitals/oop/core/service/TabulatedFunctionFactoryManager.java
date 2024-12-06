package ru.ssau.tk.nikitals.oop.core.service;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.domain.tools.annotations.MathFunctionInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class TabulatedFunctionFactoryManager {

    private final Map<String, TabulatedFunctionFactory> factories = new HashMap<>();

    public TabulatedFunctionFactoryManager() {
        Reflections reflections = new Reflections("ru.ssau.tk.nikitals.oop.core.domain.functions.factory.impl");
        Set<Class<?>> factoryClasses = reflections.getTypesAnnotatedWith(MathFunctionInfo.class);

        for (Class<?> factoryClass : factoryClasses) {
            try {
                MathFunctionInfo info = factoryClass.getAnnotation(MathFunctionInfo.class);
                TabulatedFunctionFactory factory = (TabulatedFunctionFactory) factoryClass.getDeclaredConstructor().newInstance();
                factories.put(info.name(), factory);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при регистрации фабрики: " + factoryClass.getName(), e);
            }
        }
    }

    public Optional<TabulatedFunctionFactory> getFactory(String name) {
        return Optional.ofNullable(factories.get(name));
    }

    public Class<? extends TabulatedFunction> getTypeClass(String factoryType) {
        Optional<TabulatedFunctionFactory> factory = getFactory(factoryType);
        if (factory.isEmpty()) {
            throw new RuntimeException("Не найдена фабрика для типа: " + factoryType);
        }
        return factory.get().typeClass();
    }

}

