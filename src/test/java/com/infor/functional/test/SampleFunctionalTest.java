package com.infor.functional.test;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class SampleFunctionalTest {

    private static final String APPLICATION_JSON = "application/json";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void test() {

        stubFor(WireMock.get(urlEqualTo("/greetings")).willReturn(
            aResponse().withStatus(200)
                .withHeader("Content-Type", APPLICATION_JSON)
                .withBody("Hello world!")));

        get("/greetings")
            .then()
            .body( equalTo("Hello world!"));

    }
}
