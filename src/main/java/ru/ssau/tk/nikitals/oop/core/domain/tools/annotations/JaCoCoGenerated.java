package ru.ssau.tk.nikitals.oop.core.domain.tools.annotations;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация для игнорирования классов, методов или конструкторов при создании отчета о тестировании JaCoCo.
 */
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD, CONSTRUCTOR})
public @interface JaCoCoGenerated {
}
