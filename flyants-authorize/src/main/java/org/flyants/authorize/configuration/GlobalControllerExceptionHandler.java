package org.flyants.authorize.configuration;

import lombok.extern.slf4j.Slf4j;
import org.flyants.authorize.utils.ResponseDataUtils;
import org.flyants.common.ResponseData;
import org.flyants.common.exception.BusinessException;
import org.flyants.common.exception.TokenExpireException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Author zhangchao
 * @Date 2019/4/25 16:32
 * @Version v1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {


    @ExceptionHandler(value = {TokenExpireException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseData constraintTokenExpireException(TokenExpireException ex) {
        log.error("TokenExpireException, errorCode: {}, errorDesc: {}", ex.getErrorCode(), ex.getErrorMsg());
        return ResponseDataUtils.buildError(ex.getErrorCode(),ex.getErrorMsg());
    }

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData constraintBusinessException(BusinessException ex) {
        log.error("BusinessException, errorCode: {}, errorDesc: {}", ex.getErrorCode(), ex.getErrorMsg());
        return ResponseDataUtils.buildError(ex.getErrorCode(),ex.getErrorMsg());
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData error(ConstraintViolationException ex) {
        log.error("ConstraintViolationException:{}", ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errorMsg = new LinkedList<>();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.add(violation.getMessage());
            }
        }
        return ResponseDataUtils.buildError(errorMsg.toString());
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData constraintException(Exception ex) {
        log.error("Exception:{}", ex);
        return ResponseDataUtils.buildError("500","系统异常");
    }



}
