package gov.nih.nlm.lode.servlet;

import org.springframework.web.bind.annotation.ResponseStatus;

import uk.ac.ebi.fgpt.lode.exception.LodeException;

import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LodeNotFoundException extends LodeException {
    private static final long serialVersionUID = 1L;

    public LodeNotFoundException() {
        super();
    }

    public LodeNotFoundException(String message) {
        super(message);
    }

    public LodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LodeNotFoundException(Throwable cause) {
        super(cause);
    }
}
