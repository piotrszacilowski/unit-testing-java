public class IncorectVatException extends Throwable {
    private String message;

    public IncorectVatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
