package br.com.zup.mercadolivre.payment;

import br.com.zup.mercadolivre.order.Order;
import br.com.zup.mercadolivre.shared.validations.ExistField;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReturnPagseguroRequest {
    @NotBlank
    private String transactionId;

    @NotNull
    private PagseguroReturnStatus gatewayStatus; // success or fail Paypal(0,1) Pagseguro(SUCCESS, FAIL)

    public ReturnPagseguroRequest(@NotBlank String transactionId, @NotBlank PagseguroReturnStatus gatewayStatus) {
        this.transactionId = transactionId;
        this.gatewayStatus = gatewayStatus;
    }

    @Override
    public String toString() {
        return "{\"ReturnPagseguroRequest\":{"
                + "\"transactionId\":\"" + transactionId + "\""
                + ", \"gatewayStatus\":\"" + gatewayStatus + "\""
                + "}}";
    }
}
