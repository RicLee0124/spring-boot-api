package com.lrm.handler;

import com.lrm.exception.InvalidRequestException;
import com.lrm.exception.NotFoundException;
import com.lrm.resource.ErrorResource;
import com.lrm.resource.FieldResource;
import com.lrm.resource.InvalidErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理找不到异常
     * @param e
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handlerNotFound(RuntimeException e){
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        ResponseEntity result = new ResponseEntity<>(errorResource,HttpStatus.NOT_FOUND);
        logger.debug("result ----- : {}",result);
        return result;
    }


    /**
     * 处理参数请求异常
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handlerInvalidRequests(InvalidRequestException e){
        Errors errors = e.getErrors();
        List<FieldResource> fieldResources = new ArrayList<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            FieldResource fieldResource = new FieldResource(fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage());
            fieldResources.add(fieldResource);
        }
        InvalidErrorResource ier = new InvalidErrorResource(e.getMessage(),fieldResources);
        ResponseEntity result = new ResponseEntity<Object>(ier,HttpStatus.BAD_REQUEST);
        logger.debug("result ----- : {}",result);
        return result;
    }

    /**
     * 处理全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception e){
        logger.error("error : {}",e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
