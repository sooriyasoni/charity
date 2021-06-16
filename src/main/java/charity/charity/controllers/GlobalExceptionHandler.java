package charity.charity.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // 1
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    // DataAccessException is the super class of many Spring database exceptions
    // including BadSqlGrammarException.
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleException(DataAccessException ex) {

        // Log the exception?
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse("We can't show you the details, but something went wrong in our database. Sorry :("),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {

        // Log the exception?

        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // This is our absolute last resort. Yuck.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {

        // Log the exception?

        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse("Something went wrong on our end. Your request failed. :("),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(charity.charity.controllers.RecordNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(charity.charity.controllers.RecordNotFoundException exception, WebRequest webRequest) {
        charity.charity.controllers.ExceptionResponse response = new charity.charity.controllers.ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Sorry record not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(charity.charity.controllers.MissingParameters.class)
    public ResponseEntity<Object> handleExceptions(charity.charity.controllers.MissingParameters exception, WebRequest webRequest) {
        charity.charity.controllers.ExceptionResponse response = new charity.charity.controllers.ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Please check the data, request invalid");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }

    @ExceptionHandler(RequestConflictException.class)
    public ResponseEntity<Object> handleExceptions( RequestConflictException exception, WebRequest webRequest) {
        charity.charity.controllers.ExceptionResponse response = new charity.charity.controllers.ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Sorry, There is a conflict in request");
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }
}