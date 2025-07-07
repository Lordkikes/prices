package com.prices.inditex;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/* Verifies the behavior of the application when interacting with the REST API.
        */
@SpringBootTest
@AutoConfigureMockMvc
class InditexApplicationTests {

    /**
     * Constant representing the product ID used in the tests.
     */
    private static final int PRODUCT_ID = 35455;

    /**
     * Constant representing the brand ID used in the tests.
     */
    private static final int BRAND_ID = 1;

    /**
     * MockMvc instance for performing HTTP requests in tests.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Provides test cases for parameterized tests.
     * Each test case includes an expected price and an application date.
     *
     * @return A stream of arguments containing expected price and application date.
     */
    static Stream<Arguments> priceTestCases() {
        return Stream.of(
                arguments(new BigDecimal("35.50"), "2025-06-14T10:00:00.000"),
                arguments(new BigDecimal("25.45"), "2025-06-14T16:00:00.000"),
                arguments(new BigDecimal("35.50"), "2025-06-14T21:00:00.000"),
                arguments(new BigDecimal("30.50"), "2025-06-15T10:00:00.000"),
                arguments(new BigDecimal("38.95"), "2025-06-16T21:00:00.000")
        );
    }

    /**
     * Parameterized test to verify that the correct price is returned for a given application date.
     *
     * @param expectedPrice The expected price for the given application date.
     * @param applicationDate The application date for which the price is queried.
     * @throws Exception If an error occurs during the test execution.
     */
    @ParameterizedTest
    @MethodSource("priceTestCases")
    @DisplayName("Should return correct price for given application date")
    void shouldReturnCorrectPriceForGivenApplicationDate(BigDecimal expectedPrice, String applicationDate) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", applicationDate)
                        .param("productId", String.valueOf(PRODUCT_ID))
                        .param("brandId", String.valueOf(BRAND_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price")
                        .value(expectedPrice.setScale(2, RoundingMode.HALF_UP).doubleValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(BRAND_ID));
    }

    /**
     * Test to verify that a 400 Bad Request status is returned when the brandId parameter is missing.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    @DisplayName("Should return 400 Bad Request when brandId parameter is missing")
    void shouldReturnBadRequestWhenBrandIdIsMissing() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2025-06-14T10:00:00.000")
                        .param("productId", String.valueOf(PRODUCT_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    /**
     * Test to verify that a 404 Not Found status is returned when no price is available
     * for the given date and product.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    @DisplayName("Should return 404 Not Found when no price is available for the given date and product")
    void shouldReturnNotFoundWhenNoPriceIsAvailable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/prices")
                        .param("applicationDate", "2024-06-14T10:00:00.000")
                        .param("productId", String.valueOf(PRODUCT_ID))
                        .param("brandId", String.valueOf(BRAND_ID))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No price available."));
    }
}