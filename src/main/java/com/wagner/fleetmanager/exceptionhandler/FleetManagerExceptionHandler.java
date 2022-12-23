package com.wagner.fleetmanager.exceptionhandler;

import lombok.NonNull;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class FleetManagerExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public FleetManagerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {

        String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

        List<Error> errors = List.of(new Error(userMessage, developerMessage));
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        List<Error> errorList = createErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> errorList = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            errorList.add(new Error(userMessage, developerMessage));
        }
        return errorList;
    }

    @ExceptionHandler({LicensePlateDuplicated.class})
    public ResponseEntity<Object> handleLicensePlateDuplicatedException(LicensePlateDuplicated ex) {
        String userMessage = messageSource.getMessage("car.license_plate_duplicated", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.getMessage();

        List<Error> errorList = List.of(new Error(userMessage, developerMessage));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorList);
    }
}
