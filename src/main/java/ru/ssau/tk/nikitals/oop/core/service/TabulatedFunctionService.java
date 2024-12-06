package ru.ssau.tk.nikitals.oop.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionFilter;
import ru.ssau.tk.nikitals.oop.core.ports.TabulatedFunctionSearch;
import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;
import ru.ssau.tk.nikitals.oop.core.ports.repository.TabulatedFunctionRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TabulatedFunctionService {
    private final TabulatedFunctionRepository repository;
    private final TabulatedFunctionFactoryManager factoryManager;
    private final TabulatedFunctionSearch TabulatedFunctionSearch;

    public TabulatedFunctionEntity createEntity(TabulatedFunction function, String name, String factoryType) {
        TabulatedFunctionEntity tabulatedFunctionEntity = TabulatedFunctionEntity.builder()
                .name(name)
                .factoryType(factoryType)
                .build();

        for (int i = 0; i < function.getCount(); i++) {
            PointEntity pointEntity = PointEntity.builder()
                    .x(function.getX(i))
                    .y(function.getY(i))
                    .build();
            tabulatedFunctionEntity.addPoint(pointEntity);
        }
        return tabulatedFunctionEntity;
    }


    public TabulatedFunctionEntity save(TabulatedFunctionEntity entity) {
        return repository.save(entity);
    }

    public TabulatedFunctionEntity getTabulatedFunction(Long id) {
        TabulatedFunctionEntity functionEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Функция не найдена"));
        List<PointEntity> points = functionEntity.getPoints();
        points.sort(Comparator.comparing(PointEntity::getId));
        functionEntity.setPoints(points);
        return functionEntity;
    }

    public List<TabulatedFunctionEntity> getAllFunctions() {
        return repository.findAll();
    }

    public void deleteFunction(Long id) {
        repository.deleteById(id);
    }


    public TabulatedFunction loadFunction(TabulatedFunctionEntity entity) {
            Optional<TabulatedFunctionFactory> factory = factoryManager.getFactory(entity.getFactoryType());
            if (factory.isEmpty()) {
                throw new IllegalArgumentException("Invalid factory type: " + entity.getFactoryType());
            };
        List<PointEntity> pointEntities = entity.getPoints();
            double[] xValues = new double[pointEntities.size()];
            double[] yValues = new double[pointEntities.size()];
            for (int i = 0; i < pointEntities.size(); i++) {
                xValues[i] = pointEntities.get(i).getX();
                yValues[i] = pointEntities.get(i).getY();
            }
            return factory.get().create(xValues, yValues);
        }

    // Поиск по имени
    public List<TabulatedFunctionEntity> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // Поиск по типу
    public List<TabulatedFunctionEntity> findByType(String type) {
        return repository.findByFactoryType(type);
    }

    /**
     * Поиск функций по фильтру.
     *
     * @param filter Фильтр.
     * @return Список функций.
     */
    public List<TabulatedFunctionEntity> search(TabulatedFunctionFilter filter) {
        return TabulatedFunctionSearch.search(filter);
    }

    public void updateFunctionName(Long id, String newName) {
        TabulatedFunctionEntity function = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Функция не найдена"));
        function.setName(newName);
        repository.save(function);
    }

    public void saveToFile(TabulatedFunctionEntity entity, String fileName) {
        TabulatedFunction function = loadFunction(entity);
//        try {
////            FunctionsIO.serialize(function, fileName);
//        } catch (IOException e) {
//            throw new RuntimeException("Ошибка при сохранении файла: " + fileName, e);
//        }
    }
}
