package ru.ssau.tk.nikitals.oop.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TabulatedFunctionDTO {
    /// Идентификатор функции
    private Long id;
    /// Имя функции
    private String name;
    /// Тип фабрики (список доступных фабрик: "Массив", "Связный список")
    private String factoryType;
    /// Список точек функции
    private List<PointDTO> points;
    /// Имя математической функции (если используется второй тип конструктора)
    private String mathFunctionName;
    /// Начало интервала для математической функции
    private Double xFrom;
    /// Конец интервала
    private Double xTo;
    /// Количество точек для разбиения
    private Integer count;


    public void setxFrom(Double xFrom) {
        this.xFrom = xFrom;
    }

    public Double getxFrom() {
        return xFrom;
    }

    public void setxTo(Double xTo) {
        this.xTo = xTo;
    }

    public Double getxTo() {
        return xTo;
    }

    public void addPoint(PointDTO point) {
        if (points == null) {
            points = new ArrayList<>();
        }
        points.add(point);
        point.setFunctionId(this.getId());
    }

}
