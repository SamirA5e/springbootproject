package com.onlyjavatech.samir.service.TestingService;

import com.onlyjavatech.samir.model.TestingModel.Testing;
import com.onlyjavatech.samir.model.TestingModel.TestingRequestModel;
import com.onlyjavatech.samir.model.TestingModel.TestingResponseModel;
import com.onlyjavatech.samir.repository.TestingRepository.TestingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestingService.class);

    @Autowired
    private TestingRepository testingRepository;


    public TestingResponseModel registerTester(TestingRequestModel test) {
        Testing testing = new Testing();
        testing.setFirstName(test.getFirstName());
        testing.setMiddleName(test.getMiddleName());
        testing.setLastName(test.getLastName());
        testing.setEmailId(test.getEmailId());

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        testing.setId(uuidAsString);

        Testing newTester = testingRepository.save(testing);

        return setTesterResponseModel(newTester);
    }

    public List<TestingResponseModel> getTesters() {
        Iterable<Testing> testers = testingRepository.findAll();
        List<TestingResponseModel> testersList = new ArrayList<>();

        for (Testing tester : testers) {
            TestingResponseModel tester_row = setTesterResponseModel(tester);
            testersList.add(tester_row);
        }

        return testersList;

    }

    public List<TestingResponseModel> getTestersLog() {
        Iterable<Testing> testers = testingRepository.findAll();
        List<TestingResponseModel> testersList = new ArrayList<>();

        for (Testing tester : testers) {
            TestingResponseModel tester_row = setTesterResponseModel(tester);
            testersList.add(tester_row);
        }


        //    -------------------------------------- stream ----------------------------------------
        List<Integer> integersList = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = integersList.stream().reduce(0, Integer::sum);
        Integer maxValue = integersList.stream().reduce(0, Integer::max);
        List<Integer> square = integersList.stream().map(x -> x * x).collect(Collectors.toList());
        int even = integersList.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
        int sumAll = integersList.stream().reduce(5, Integer::sum);
        int multiplication = integersList.stream().reduce(2, (ans, i) -> ans * i);
        float division = integersList.stream().reduce(1, (ans, i) -> ans / i);


        List<String> names = Arrays.asList("samir1", "", "arbaz", "tejas", "nikhil");

        LOGGER.info("----------------- Info message by samir --------------");
        LOGGER.info("-- sum --");
        LOGGER.info("" + sum);
        LOGGER.info("-- sumAll --");
        LOGGER.info("" + sumAll);
        LOGGER.info("-- multiplication --");
        LOGGER.info("" + multiplication);

        LOGGER.info("-- devision --");
        LOGGER.info("" + division);
        LOGGER.info("-- maxValue --");
        LOGGER.info("" + maxValue);
        LOGGER.info("-- square --");
        LOGGER.info("" + square);
        LOGGER.info("-- even --");
        LOGGER.info("" + even);
        LOGGER.info("-- forEach --");
        integersList.stream().map(x -> x * x).forEach(System.out::println);
        LOGGER.info("-- forEach Samir --");
        integersList.forEach(System.out::println);
        LOGGER.info("--- limit in stream ------");
        LOGGER.info("" + integersList.stream().limit(2).collect(Collectors.toList()));
        LOGGER.info("----- sort integer list ------");
        LOGGER.info("" + integersList.stream().sorted().collect(Collectors.toList()));

        LOGGER.info("------- Names -----------");
        names.stream().filter(x -> !x.isEmpty()).forEach(System.out::println);

        LOGGER.info("" + names.stream().sorted().collect(Collectors.toList()));
        LOGGER.info("------- Names starts with -----------");
        LOGGER.info("" + names.stream().filter(i -> i.startsWith("a")).collect(Collectors.toList()));
        LOGGER.info("---- String Names Sorting -----");
        LOGGER.info("" + names.stream().sorted().collect(Collectors.toList()));

        LOGGER.info("-- list.sort---");
        Collections.sort(integersList);
        LOGGER.info("" + integersList);


        LOGGER.debug("----------------- debug message by samir --------------");
        LOGGER.warn("----------------- warn message by samir --------------");
        LOGGER.error("----------------- Error message by samir --------------");
        LOGGER.trace("----------------- Trace message by samir --------------");
        //    ---------------------------------------- end stream --------------------------------------

        return testersList;
    }

    private TestingResponseModel setTesterResponseModel(Testing request) {
        TestingResponseModel response = new TestingResponseModel();
        response.setId(request.getId());
        response.setFirstName(request.getFirstName());
        response.setMiddleName(request.getMiddleName());
        response.setLastName(request.getLastName());
        response.setEmailId(request.getEmailId());

        return response;
    }

}
