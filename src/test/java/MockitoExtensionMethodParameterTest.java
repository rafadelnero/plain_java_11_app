import model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.OrderRepository;
import service.OrderService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockitoExtensionMethodParameterTest {

    @Test
    void createOrderSetsTheCreationDate(@Mock OrderRepository orderRepository) {
        OrderService orderService = new OrderService(orderRepository);
        when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

        Order order = new Order();

        Order savedOrder = orderService.create(order);

        assertNotNull(savedOrder.getCreationDate());
    }
}
