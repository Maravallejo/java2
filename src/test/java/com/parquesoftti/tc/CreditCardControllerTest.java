package com.parquesoftti.tc;



import com.parquesoftti.tc.controller.CreditCardController;
import com.parquesoftti.tc.model.CreditCard;
import com.parquesoftti.tc.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreditCardControllerTest {

    @InjectMocks
    private CreditCardController creditCardController;

    @Mock
    private CreditCardService creditCardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCards() {
        // Arrange
        List<CreditCard> mockCards = Arrays.asList(
                new CreditCard(1L, "1234-5678-9012-3456", "12/25",  "123"),
                new CreditCard(2L, "9876-5432-1098-7654", "01/26", "123")
        );
        when(creditCardService.getCreditCards()).thenReturn(mockCards);

        // Act
        ResponseEntity<List<CreditCard>> response = creditCardController.getCards();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(creditCardService, times(1)).getCreditCards();
    }
/*
    @Test
    void testGetCardById() {
        // Arrange
        Long cardId = 1L;
        CreditCard mockCard = new CreditCard(cardId, "1234-5678-9012-3456", "12/25", "123");
        when(creditCardService.getCardById(cardId)).thenReturn(Optional.of(mockCard));

        // Act
        ResponseEntity<CreditCard> response = creditCardController.getCardById(cardId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("1234-5678-9012-3456", response.getBody().getNumber());
        verify(creditCardService, times(1)).getCardById(cardId);
    }

    @Test
    void testSaveCreditCard() {
        // Arrange
        CreditCard newCard = new CreditCard(null, "1111-2222-3333-4444", "Alice Doe", "11/27", 789);
        CreditCard savedCard = new CreditCard(1L, "1111-2222-3333-4444", "Alice Doe", "11/27", 789);
        when(creditCardService.saveCreditCard(newCard)).thenReturn(savedCard);

        // Act
        ResponseEntity<CreditCard> response = creditCardController.saveCreditCard(newCard);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(creditCardService, times(1)).saveCreditCard(newCard);
    }

    @Test
    void testUpdateCreditCard() {
        // Arrange
        Long cardId = 1L;
        CreditCard updatedCard = new CreditCard(cardId, "1234-5678-9012-3456", "John Smith", "10/28", 321);
        when(creditCardService.updateCreditCard(updatedCard, cardId)).thenReturn(updatedCard);

        // Act
        ResponseEntity<CreditCard> response = creditCardController.updateCreditCard(updatedCard, cardId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Smith", response.getBody().getHolder());
        verify(creditCardService, times(1)).updateCreditCard(updatedCard, cardId);
    }

    @Test
    void testDeleteCard() {
        // Arrange
        Long cardId = 1L;
        doNothing().when(creditCardService).deleteCard(cardId);

        // Act
        ResponseEntity<?> response = creditCardController.deleteCard(cardId);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(creditCardService, times(1)).deleteCard(cardId);
    }

    @Test
    void testGetCreditCardsByCardNumber() {
        // Arrange
        String cardNumber = "1234-5678-9012-3456";
        CreditCard mockCard = new CreditCard(1L, cardNumber, "John Doe", "12/25", 123);
        when(creditCardService.getCreditCardsByCardNumber(cardNumber)).thenReturn(mockCard);

        // Act
        ResponseEntity<CreditCard> response = creditCardController.getCreditCardsByCardNumber(cardNumber);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getHolder());
        verify(creditCardService, times(1)).getCreditCardsByCardNumber(cardNumber);
    }*/
}
