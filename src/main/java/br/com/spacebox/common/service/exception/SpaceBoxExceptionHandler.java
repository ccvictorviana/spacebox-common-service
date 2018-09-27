package br.com.spacebox.common.service.exception;

import br.com.spacebox.common.exceptions.BusinessException;
import br.com.spacebox.common.messages.EMessage;
import br.com.spacebox.common.messages.MessageSourceCustom;
import br.com.spacebox.common.model.response.ErrorMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SpaceBoxExceptionHandler {

    @Autowired
    protected MessageSourceCustom messageSource;

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<?> handleAuthorizationException(AuthorizationException ex) {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> handleException(Exception ex) {
        ErrorMessageResponse response = new ErrorMessageResponse(messageSource.getMessage(EMessage.GENERIC_ERROR));
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessageResponse> handleException(BusinessException ex) {
        ErrorMessageResponse response = new ErrorMessageResponse(ex.getErros());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}