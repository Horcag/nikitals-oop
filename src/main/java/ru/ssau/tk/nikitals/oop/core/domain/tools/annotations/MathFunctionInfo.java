package ru.ssau.tk.nikitals.oop.core.domain.tools.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MathFunctionInfo {
    String name();         // Название функции (локализованное)
    int priority() default 0; // Приоритет для отображения
}
