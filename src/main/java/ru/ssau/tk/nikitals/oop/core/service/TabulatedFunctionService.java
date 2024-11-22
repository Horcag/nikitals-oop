//package ru.ssau.tk.nikitals.oop.core.service;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Service;
//import ru.ssau.tk.nikitals.oop.core.domain.functions.api.TabulatedFunction;
//import ru.ssau.tk.nikitals.oop.core.domain.functions.impl.ArrayTabulatedFunction;
//import ru.ssau.tk.nikitals.oop.core.models.MathFunctionEntity;
//import ru.ssau.tk.nikitals.oop.core.models.TabulatedFunctionEntity;
//import ru.ssau.tk.nikitals.oop.core.ports.TabulatedFunctionRepository;
//import ru.ssau.tk.nikitals.oop.core.ports.io.FunctionsIO;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class TabulatedFunctionService {
//    private final TabulatedFunctionRepository repository;
//    private static final Logger logger = LoggerFactory.getLogger(TabulatedFunctionService.class);
//
//
//    public TabulatedFunctionEntity saveFunction(String name, TabulatedFunction function) {
//        logger.debug("Creating function: {}", name);
//        try (StringWriter writer = new StringWriter();
//             BufferedWriter bufferedWriter = new BufferedWriter(writer);) {
//            FunctionsIO.serializeJson(bufferedWriter, (ArrayTabulatedFunction) function);
//            MathFunctionEntity entity = new MathFunctionEntity();
//            entity.setName(name);
//            entity.setSerializedData(writer.toString());
//            logger.debug("xValues: {}, yValues: {}", xValues, yValues);
//            TabulatedFunctionEntity savedFunction = repository.save(entity);
//            logger.info("Function created with ID: {}", savedFunction.getId());
//            return savedFunction;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//
//    }
//
//    public Optional<TabulatedFunctionEntity> getFunctionById(Long id) {
//        logger.debug("Fetching function by id: {}", id);
//        return repository.findById(id);
//    }
//
//    public List<TabulatedFunctionEntity> getAllFunctions() {
//        logger.debug("Fetching all functions");
//        return repository.findAll();
//    }
//
//    public void deleteFunction(Long id) {
//        logger.debug("Deleting function with id: {}", id);
//        repository.deleteById(id);
//        logger.info("Function deleted with id: {}", id);
//    }
//
//    public String getXValuesAsString(TabulatedFunctionEntity function) {
//        return function.getPoints().stream()
//                .map(p -> p.getXValue().toString())
//                .collect(Collectors.joining(","));
//    }
//
//    public String getYValuesAsString(TabulatedFunctionEntity function) {
//        return function.getPoints().stream()
//                .map(p -> p.getYValue().toString())
//                .collect(Collectors.joining(","));
//    }
//}
