package br.com.zup.mercadolivre.shared.errors;

public class ErrorDefaultDTO {
    private String field;
    private String message;

    public ErrorDefaultDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}

