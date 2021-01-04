package com.order.sample.StepDefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class OrderStepDefinition extends AbstractSpringConfigurationTest {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderStepDefinition.class);
    private ResponseEntity<String> response = null;
    private List<Map<String, String>> obj;
    private static String id;

    @Given("^create \"([^\"]*)\" with following detail$")
    public void create_with_following_detail(String arg1, DataTable table) throws Throwable {
      obj = table.asMaps(String.class,String.class);

        if (logger.isInfoEnabled()) {
            logger.info("{} to be saved with {}",arg1,obj);
        }
    }
    @When("^the client calls POST \"([^\"]*)\" with the given detail$")
    public void the_client_calls_POST_with_the_given_detail(String path) throws Throwable {
        if (logger.isInfoEnabled()) {
            logger.info("path {}", path);
        }
        String url = buildUrl(HOST, PORT, path);
        Map<String, Object> requestMap = new HashMap<>();

        for(int i=0; i<obj.size(); i++) {
            requestMap.put(this.obj.get(i).get("attribute"), this.obj.get(i).get("value"));
        }
        HttpEntity<?> requestEntity = new HttpEntity<>(requestMap, getDefaultHttpHeaders());
        response = invokeRESTCall(url, HttpMethod.POST, requestEntity);
        String responseBody = response.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> responseMap = mapper.readValue(responseBody, Map.class);
        id =  String.valueOf(responseMap.get("id"));
    }
    @Then("^the client receive status code of (\\d+)$")
    public void the_client_receive_status_code_of(int statusCode) throws Throwable {

        if (response== null)
            throw new RuntimeException();
        assertEquals(statusCode, response.getStatusCode().value()) ;
    }
}
