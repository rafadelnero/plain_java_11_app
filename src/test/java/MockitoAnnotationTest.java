import jdk.internal.joptsimple.internal.Strings;
import model.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.OrderRepository;
import service.OrderService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MockitoAnnotationTest {

    @Mock
    private OrderRepository orderRepository;
    private AutoCloseable closeable;
    private OrderService orderService;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void createOrderSetsTheCreationDate() {
        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());
//        Mockito.when(repository.getStuff())
//          .thenReturn(Arrays.asList("A", "B", "CDEFGHIJK", "12345", "1234"));
        Order order = new Order();

        Order savedOrder = orderService.create(order);

        assertNotNull(savedOrder.getCreationDate());
        verify(orderRepository).save(savedOrder);

//        Assertions.assertThrows(IllegalArgumentException.class, () -> MathTools.convertToDecimal(3, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
        assertTrue(StringUtils.isBlank(input));
    }
}
