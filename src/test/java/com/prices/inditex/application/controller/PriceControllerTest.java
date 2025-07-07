package com.prices.inditex.application.controller;

import com.prices.inditex.application.port.IGetPriceUseCase;
import com.prices.inditex.domain.model.PriceResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the PriceController class.
 * Verifies the behavior of the controller when interacting with mocked dependencies.
 */
@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    /**
     * Mocked instance of IGetPriceUseCase for simulating business logic interactions.
     */
    @Mock
    private IGetPriceUseCase getPriceUseCase;

    /**
     */
    @InjectMocks
    private PriceController priceController;

    /**
     * Test to verify that getPrices returns a ResponseEntity with the correct data
     * when a matching price exists.
     */
    @Test
    void getPrices_ShouldReturnResponseEntity_WhenPriceExists() {
        // Arrange: Set up test data and mock behavior
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 1;
        Integer brandId = 1;
        PriceResponseDTO mockDto = new PriceResponseDTO();

        // Simulate the behavior of the use case
        when(getPriceUseCase.getPreferredPrice(applicationDate, productId, brandId)).thenReturn(mockDto);

        // Act: Call the method under test
        ResponseEntity<PriceResponseDTO> response = priceController.getPrices(applicationDate, productId, brandId);

        // Assert: Verify the response
        assertNotNull(response); // Ensure the response is not null
        assertEquals(200, response.getStatusCode().value()); // Verify the HTTP status code
        assertEquals(mockDto, response.getBody()); // Verify the response body matches the expected DTO
    }
}