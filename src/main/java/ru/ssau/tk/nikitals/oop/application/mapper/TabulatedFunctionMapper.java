package ru.ssau.tk.nikitals.oop.application.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.ssau.tk.nikitals.oop.application.dto.PointDTO;
import ru.ssau.tk.nikitals.oop.application.dto.TabulatedFunctionDTO;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.MathFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.factory.api.TabulatedFunctionFactory;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.service.MathFunctionRegistry;
import ru.ssau.tk.nikitals.oop.core.service.TabulatedFunctionFactoryManager;
import ru.ssau.tk.nikitals.oop.core.service.TabulatedFunctionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class TabulatedFunctionMapper {
    private final TabulatedFunctionService service;
    private final TabulatedFunctionFactoryManager factoryManager;
    private final MathFunctionRegistry mathFunctionRegistry;
    private final PointMapper pointMapper;

    public TabulatedFunctionDTO toDto(TabulatedFunctionEntity entity) {
        return TabulatedFunctionDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .factoryType(entity.getFactoryType())
                .points(entity.getPoints().stream().map(pointMapper::toDto).toList())
                .build();
    }

    public TabulatedFunctionDTO toDto(TabulatedFunction function){
        List<PointDTO> points = new ArrayList<>();
        for (int i = 0; i < function.getCount(); i++) {
            PointDTO point = PointDTO.builder()
                    .x(function.getX(i))
                    .y(function.getY(i))
                    .build();
            points.add(point);
        }
        return TabulatedFunctionDTO.builder()
                .points(points)
                .build();
    }

    public TabulatedFunctionEntity toEntity(TabulatedFunctionDTO dto) {
        log.debug("Creating new function with name: {}, factoryType: {}", dto.getName(), dto.getFactoryType());
        Optional<TabulatedFunctionFactory> factory = factoryManager.getFactory(dto.getFactoryType());
        if (factory.isEmpty()) {
            throw new IllegalArgumentException("Invalid factory type: " + dto.getFactoryType());
        }
        if (dto.getMathFunctionName() != null && dto.getPoints() != null) {
            throw new IllegalArgumentException("Either mathFunctionName or points must be provided");
        }
        TabulatedFunction function;
        if (dto.getMathFunctionName() != null) {
            function = createWithMathFunction(dto, factory.get());
        } else {
            function = createWithTable(dto.getPoints(), factory.get());
        }
        TabulatedFunctionEntity entity = service.createEntity(function, dto.getName(), dto.getFactoryType());
        entity.setId(dto.getId());
        return entity;
    }

    private TabulatedFunction createWithTable(List<PointDTO> points, TabulatedFunctionFactory factory) {
        double[] xValues = new double[points.size()];
        double[] yValues = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            xValues[i] = points.get(i).getX();
            yValues[i] = points.get(i).getY();
        }
        log.debug("Creating new function with xValues: {}, yValues: {}", xValues, yValues);
        return factory.create(xValues, yValues);
    }

    private TabulatedFunction createWithMathFunction(TabulatedFunctionDTO dto, TabulatedFunctionFactory factory) {
        log.debug("Creating new function with math function: {}, xFrom: {}, xTo: {}, count: {}", dto.getMathFunctionName(), dto.getxFrom(), dto.getxTo(), dto.getCount());
        Optional<MathFunction> mathFunction = mathFunctionRegistry.createFunction(dto.getMathFunctionName());
        if (mathFunction.isPresent()) {
            return factory.create(mathFunction.get(), dto.getxFrom(), dto.getxTo(), dto.getCount());
        }
        throw new IllegalArgumentException("Invalid math function name: " + dto.getMathFunctionName());
    }


}
