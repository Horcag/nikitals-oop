package ru.ssau.tk.nikitals.oop.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.Insertable;
import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.Point;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
import ru.ssau.tk.nikitals.oop.core.ports.repository.PointRepository;
import ru.ssau.tk.nikitals.oop.core.ports.repository.TabulatedFunctionRepository;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final TabulatedFunctionService functionService;
    private final TabulatedFunctionRepository functionRepository;
    private final PointRepository pointRepository;

    public PointEntity addPoint(Long functionId, double x, double y) {
        TabulatedFunctionEntity functionEntity = functionService.getTabulatedFunction(functionId);
        TabulatedFunction tabulatedFunction = functionService.loadFunction(functionEntity);

        if (tabulatedFunction instanceof Insertable insertable) {
            insertable.insert(x, y);
        } else {
            throw new IllegalArgumentException("Function is not insertable");
        }
        Long pointId = updateFunctionAfterAddPoint(functionEntity, tabulatedFunction, x);
        return PointEntity.builder()
                .id(pointId)
                .x(x)
                .y(y)
                .function(functionEntity)
                .build();
    }

    public void deletePoint(Long pointId) {
        pointRepository.deleteById(pointId);
    }

    public PointEntity updatePoint(Long pointId, double newY) {
        PointEntity point = pointRepository.findById(pointId)
                .orElseThrow(() -> new IllegalArgumentException("Point not found"));
        point.setY(newY);
        return pointRepository.save(point);
    }

    public PointEntity getPoint(Long pointId) {
        return pointRepository.findById(pointId)
                .orElseThrow(() -> new IllegalArgumentException("Point not found"));
    }


    public Long updateFunctionAfterAddPoint(TabulatedFunctionEntity functionEntity, TabulatedFunction tabulatedFunction, double x) {
        if (functionEntity.getPoints().size() == tabulatedFunction.getCount()) {
            for(int i = 0; i < tabulatedFunction.getCount(); i++) {
                if (tabulatedFunction.getX(i) == x) {
                    PointEntity point = functionEntity.getPoints().get(i);
                    point.setY(tabulatedFunction.getY(i));
                    functionRepository.save(functionEntity);
                    return point.getId();
                }
            }
        }
        Long pointId = 0L;
        List<PointEntity> points = functionEntity.getPoints();
        Iterator<Point> iteratorPoint = tabulatedFunction.iterator();
        Point tabulatedPoint;
        for (PointEntity point : points) {
            tabulatedPoint = iteratorPoint.next();
            point.setX(tabulatedPoint.x);
            point.setY(tabulatedPoint.y);
            if (tabulatedPoint.x == x) {
                pointId = point.getId();
            }
        }
        tabulatedPoint = iteratorPoint.next();
        PointEntity point = PointEntity.builder()
                .x(tabulatedPoint.x)
                .y(tabulatedPoint.y)
                .build();
        functionEntity.addPoint(point);
        functionEntity = functionRepository.save(functionEntity);
        if (tabulatedPoint.x == x) {
            pointId = functionEntity.getPoints().get(functionEntity.getPoints().size() - 1).getId();
        }
        return pointId;
    }
}
