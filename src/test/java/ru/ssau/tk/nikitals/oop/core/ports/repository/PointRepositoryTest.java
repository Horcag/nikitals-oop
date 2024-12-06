package ru.ssau.tk.nikitals.oop.core.ports.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.ssau.tk.nikitals.oop.core.models.PointEntity;
import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private TabulatedFunctionRepository tabulatedFunctionRepository;


    @Test
    public void testSaveAndFindPoint() {
        TabulatedFunctionEntity tabulatedFunction = TabulatedFunctionEntity.builder()
                .name("someFunction")
                .factoryType("Массив")
                .build();
        tabulatedFunctionRepository.save(tabulatedFunction);

        PointEntity point = PointEntity.builder()
                .x(1.0)
                .y(2.0)
                .function(tabulatedFunction)
                .build();
        pointRepository.save(point);

        PointEntity foundPoint = pointRepository.findById(point.getId()).orElse(null);
        assertThat(foundPoint).isNotNull();
        assertEquals(1.0, foundPoint.getX());
        assertEquals(2.0, foundPoint.getY());
    }


    @Test
    public void testFindAllPointsByFunction() {
        TabulatedFunctionEntity tabulatedFunction = TabulatedFunctionEntity.builder()
                .name("someFunction")
                .factoryType("Массив")
                .build();
        PointEntity point1 = PointEntity.builder()
                .x(1.0)
                .y(2.0)
                .function(tabulatedFunction)
                .build();
        PointEntity point2 = PointEntity.builder()
                .x(2.0)
                .y(3.0)
                .function(tabulatedFunction)
                .build();
        tabulatedFunction.addPoint(point1);
        tabulatedFunction.addPoint(point2);
        tabulatedFunctionRepository.save(tabulatedFunction);
        List<PointEntity> points = tabulatedFunctionRepository.findById(tabulatedFunction.getId()).get().getPoints();
        assertThat(points).hasSize(2);
        assertThat(points).contains(point1, point2);
    }

    @Test
    public void testUpdatePoint() {
        TabulatedFunctionEntity tabulatedFunction = TabulatedFunctionEntity.builder()
                .name("someFunction")
                .factoryType("Массив")
                .build();
        tabulatedFunctionRepository.save(tabulatedFunction);

        PointEntity point = PointEntity.builder()
                .x(1.0)
                .y(2.0)
                .function(tabulatedFunction)
                .build();
        pointRepository.save(point);

        point.setX(3.0);
        point.setY(4.0);
        pointRepository.save(point);

        PointEntity updatedPoint = pointRepository.findById(point.getId()).orElse(null);
        assertThat(updatedPoint).isNotNull();
        assertEquals(3.0, updatedPoint.getX());
        assertEquals(4.0, updatedPoint.getY());
    }

    @Test
    public void testDeletePoint() {
        TabulatedFunctionEntity tabulatedFunction = TabulatedFunctionEntity.builder()
                .name("someFunction")
                .factoryType("Массив")
                .build();
        tabulatedFunctionRepository.save(tabulatedFunction);

        PointEntity point = PointEntity.builder()
                .x(1.0)
                .y(2.0)
                .function(tabulatedFunction)
                .build();
        pointRepository.save(point);

        pointRepository.deleteById(point.getId());
        PointEntity deletedPoint = pointRepository.findById(point.getId()).orElse(null);
        assertThat(deletedPoint).isNull();
    }

}