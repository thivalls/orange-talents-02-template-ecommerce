package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReturnPagseguroRequest implements IGatewayRequest{
    @NotBlank
    private String transactionId;

    @NotNull
    private PagseguroReturnStatus pagseguroReturnStatus; // success or fail Paypal(0,1) Pagseguro(SUCCESS, FAIL)

    public ReturnPagseguroRequest(@NotBlank String transactionId, @NotNull PagseguroReturnStatus pagseguroReturnStatus) {
        this.transactionId = transactionId;
        this.pagseguroReturnStatus = pagseguroReturnStatus;
    }

    @Override
    public String toString() {
        return "ReturnPagseguroRequest{" +
                "orderId='" + transactionId + '\'' +
                ", pagseguroReturnStatus=" + pagseguroReturnStatus +
                '}';
    }

    public Transaction toTransaction(Order order) {
        return new Transaction(pagseguroReturnStatus.normalize(), transactionId, order);
    }
}
