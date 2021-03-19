package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReturnPaypalRequest implements IGatewayRequest {
    @NotBlank
    private String transactionId;

    @Max(1)
    @Min(0)
    private int paypalReturnStatus; // success or fail Pagseguro(SUCCESS, FAIL)

    public ReturnPaypalRequest(@NotBlank String transactionId, @NotNull int paypalReturnStatus) {
        this.transactionId = transactionId;
        this.paypalReturnStatus = paypalReturnStatus;
    }

    @Override
    public String toString() {
        return "ReturnPagseguroRequest{" +
                "orderId='" + transactionId + '\'' +
                ", pagseguroReturnStatus=" + paypalReturnStatus +
                '}';
    }

    public Transaction toTransaction(Order order) {
        SystemTransactionStatus systemTransactionStatus = paypalReturnStatus == 1 ? SystemTransactionStatus.success : SystemTransactionStatus.fail;
        return new Transaction(systemTransactionStatus, transactionId, order);
    }
}
