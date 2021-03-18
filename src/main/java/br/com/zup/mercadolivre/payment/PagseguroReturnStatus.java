package br.com.zup.mercadolivre.payment;

public enum PagseguroReturnStatus {
    SUCCESS, ERROR;

    public SystemTransactionStatus normalize() {
        if(this.equals(SUCCESS)) {
            return SystemTransactionStatus.success;
        }

        return SystemTransactionStatus.fail;
    }
}
