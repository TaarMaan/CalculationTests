package com.artamonov.tests.bek.definitions;

import com.artamonov.tests.bek.model.Numbers;
import com.artamonov.tests.bek.model.entity.Calculations;
import com.artamonov.tests.bek.repository.CalculationsRepository;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import io.cucumber.java.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.Delimiter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class StepDefsIntegrationTest extends SpringIntegrationTest {
    @Autowired
    CalculationsRepository calculationsRepository;

    private final ObjectMapper objectMapper =
            new ObjectMapper().registerModule(new JSR310Module());

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        JavaType javaType = objectMapper.constructType(toValueType);
        return objectMapper.convertValue(fromValue, javaType);
    }

    @ParameterType("(?:.+;)+.+")
    public List<String> stringList(final String raw) {
        final String[] values = raw.split(";");
        return Arrays.asList(values);
    }

    @When("i execute endpoint is {string} with num1 is {string} in {int} and num2 is {string} in {int}")
    public void theClientCallsEndpointIs(final String path, final String num1,final int sys1, final String num2, final int sys2) throws IOException {
        System.out.println(num1);
        System.out.println(num2);
        executeGet("http://localhost:8080/calculations/" + path, num1, num2, sys1, sys2);
    }

    @When("i execute endpoint is subtraction with \"{stringList}\"")
    public void theClientCallsEndpointIs(@Delimiter("; ") final List<String> arg) throws IOException {
        System.out.println("theClientCallsEndpointIs");
        executeGet("http://localhost:8080/calculations/subtraction", arg.get(0), arg.get(1), 16, 2);
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(final int statusCode) {
        final var currentResult = status;
        assertThat("status is incorrect : " + status, currentResult, is(statusCode));
    }

    @And("the client receives result is {string}")
    public void theClientReceivesResultIs(final String result) {
        final var currentResult = latestResponse.getBody();
        assertThat("result is incorrect : " + latestResponse.getBody(), currentResult, is(result));
    }

    @And("^the date create is (.+)$")
    public boolean theClientReceivesResultIs(final Date result) {
        final var currentResult = calculationsRepository.findDate("338");
        List<Calculations> calculations1 = calculationsRepository.findAll();
        for (Calculations calculation : calculations1) {
            System.out.println(calculation);
        }
        return currentResult.after(result);
    }

    @Given("test get parameters for class")
    public void testGetParametersForClass(final List<Numbers> arg) throws Throwable {
        for (final Numbers numbers : arg) {
            System.out.print(numbers.getNum1());
            System.out.print(numbers.getNum2());
        }
    }

    @Given("test get parameters for class for subtraction")
    public void testGetParametersForClassSub(final List<Numbers> arg) throws Throwable {
        for (final Numbers numbers : arg) {
            executeGet("http://localhost:8080/calculations/subtraction", String.valueOf(numbers.getNum1()), String.valueOf(numbers.getNum2()), 8, 8);
        }
    }

    @Before
    public void cleanDatabase() {
        calculationsRepository.clear();
    }
}
