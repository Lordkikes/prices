package com.prices.inditex.application.service;

        import com.prices.inditex.domain.exception.NotFoundException;
        import com.prices.inditex.domain.model.PriceResponseDTO;
        import com.prices.inditex.domain.repository.PriceRepository;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.time.LocalDateTime;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.when;

        /**
         * Unit tests for the GetPriceUseCaseImpl class.
         * Verifies the behavior of the use case implementation when interacting with mocked dependencies.
         */
        @ExtendWith(MockitoExtension.class)
        class GetPriceUseCaseImplTest {

            /**
             * Mocked instance of PriceRepository for simulating repository interactions.
             */
            @Mock
            private PriceRepository priceRepository;

            /**
             * Injected instance of GetPriceUseCaseImpl with mocked dependencies.
             */
            @InjectMocks
            private GetPriceUseCaseImpl getPriceUseCaseImpl;

            /**
             * Test to verify that getPreferredPrice returns a PriceResponseDTO
             * when a matching price exists in the repository.
             */
            @Test
            void getPreferredPrice_ShouldReturnPrice_WhenPriceExists() {
                LocalDateTime applicationDate = LocalDateTime.now();
                Integer productId = 1;
                Integer brandId = 1;
                PriceResponseDTO mockDto = new PriceResponseDTO();

                // Simulate repository behavior with a matching price
                when(priceRepository.getPreferredPrice(applicationDate, productId, brandId)).thenReturn(Optional.of(mockDto));

                // Call the method under test
                PriceResponseDTO result = getPriceUseCaseImpl.getPreferredPrice(applicationDate, productId, brandId);

                // Verify the result
                assertNotNull(result);
                assertEquals(mockDto, result);
            }

            /**
             * Test to verify that getPreferredPrice throws a NotFoundException
             * when no matching price exists in the repository.
             */
            @Test
            void getPreferredPrice_ShouldThrowException_WhenPriceDoesNotExist() {
                LocalDateTime applicationDate = LocalDateTime.now();
                Integer productId = 1;
                Integer brandId = 1;

                // Simulate repository behavior with no matching price
                when(priceRepository.getPreferredPrice(applicationDate, productId, brandId)).thenReturn(Optional.empty());

                // Call the method under test and verify the exception
                assertThrows(NotFoundException.class, () ->
                        getPriceUseCaseImpl.getPreferredPrice(applicationDate, productId, brandId)
                );
            }
        }