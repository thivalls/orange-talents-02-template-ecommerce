package br.com.zup.mercadolivre.order;

import br.com.zup.mercadolivre.category.Category;
import br.com.zup.mercadolivre.payment.IGatewayRequest;
import br.com.zup.mercadolivre.payment.SystemTransactionStatus;
import br.com.zup.mercadolivre.payment.Transaction;
import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.ProductFeatureRequest;
import br.com.zup.mercadolivre.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest
class OrderTest {
    @Test
    @DisplayName("It should create a new transaction")
    void itShouldCreateNewTransaction() {
        Order order = newOrder();
        // f(x) -> y
        IGatewayRequest returnPagseguroRequest = (compra) -> {
            return new Transaction(SystemTransactionStatus.success, "1", compra);
        };
        order.addTransaction(returnPagseguroRequest);
    }

    @Test
    @DisplayName("It should not create a new transaction if the order has been concluded")
    void itShouldNotCreateNewTransactionIfOrderHasBeenConcluded() {
        Order order = newOrder();
        IGatewayRequest returnPagseguroRequest = (compra) -> {
            return new Transaction(SystemTransactionStatus.success, "1", compra);
        };
        order.addTransaction(returnPagseguroRequest);
        IGatewayRequest returnPagseguroRequest1 = (compra) -> {
            return new Transaction(SystemTransactionStatus.success, "2", compra);
        };

        Assertions.assertThrows(IllegalStateException.class, () -> {
            order.addTransaction(returnPagseguroRequest1);
        });
    }

    @Test
    @DisplayName("It should create a new transaction if the order has any failed transactions before")
    void itShouldCreateNewTransactionWithSomeFailedTransactionsBefore() {
        Order order = newOrder();
        IGatewayRequest returnPagseguroRequest = (compra) -> {
            return new Transaction(SystemTransactionStatus.fail, "1", compra);
        };
        order.addTransaction(returnPagseguroRequest);
        IGatewayRequest returnPagseguroRequest1 = (compra) -> {
            return new Transaction(SystemTransactionStatus.fail, "2", compra);
        };
        order.addTransaction(returnPagseguroRequest1);
        IGatewayRequest returnPagseguroRequest2 = (compra) -> {
            return new Transaction(SystemTransactionStatus.success, "3", compra);
        };
        order.addTransaction(returnPagseguroRequest2);
    }

    @Test
    @DisplayName("It should return true if the order has success transaction registered")
    void itShouldReturnTrueIfTheOrderHasSuccessTransactionRegistered() {
        Order order = newOrder();
        IGatewayRequest returnPagseguroRequest = (compra) -> {
            return new Transaction(SystemTransactionStatus.success, "1", compra);
        };
        order.addTransaction(returnPagseguroRequest);

        Assertions.assertTrue(() -> order.processedWithSuccess());
    }

    @Test
    @DisplayName("It should return false if the order has no success transaction registered")
    void itShouldReturnFalseIfTheOrderHasNoSuccessTransactionRegistered() {
        Order order = newOrder();
        IGatewayRequest returnPagseguroRequest = (compra) -> {
            return new Transaction(SystemTransactionStatus.fail, "1", compra);
        };
        order.addTransaction(returnPagseguroRequest);

        Assertions.assertFalse(() -> order.processedWithSuccess());
    }

    private Order newOrder() {
        Category categoria = new Category("teste");
        User dono = new User("email@email.com", "123456");
        Collection<ProductFeatureRequest> caracteristicas = new ArrayList<>();
        caracteristicas.add(new ProductFeatureRequest("nome", "descricao"));
        caracteristicas
                .add(new ProductFeatureRequest("nome1", "descricao"));
        caracteristicas
                .add(new ProductFeatureRequest("nome2", "descricao"));

        Product produtoASerComprado = new Product("teste", BigDecimal.TEN, 100,
                "Product description", categoria, dono, caracteristicas);

        User comprador = new User("comprador@email.com", "123456");

        Gateway gatewayPagamento = Gateway.PAGSEGURO;

        return new Order(gatewayPagamento, produtoASerComprado, 10, comprador);
    }
}