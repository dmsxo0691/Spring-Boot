package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    // message 와 cause 근원적인 exception 을 넣어주기 위해 override
    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
