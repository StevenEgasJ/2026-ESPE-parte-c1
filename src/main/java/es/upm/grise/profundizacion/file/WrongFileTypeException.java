package es.upm.grise.profundizacion.file;

public class WrongFileTypeException extends RuntimeException {

    public WrongFileTypeException() {
        super();
    }

    public WrongFileTypeException(String message) {
        super(message);
    }

}
