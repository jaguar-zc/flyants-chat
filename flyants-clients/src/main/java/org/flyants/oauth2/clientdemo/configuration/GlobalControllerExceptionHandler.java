package org.flyants.oauth2.clientdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.flyants.common.ResponseData;
import org.flyants.common.exception.BusinessException;
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

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData constraintBusinessException(BusinessException ex) {
        log.error("Business exception, errorCode: {}, errorDesc: {}", ex.getErrorCode(), ex.getErrorMsg());
        return new ResponseData(Integer.valueOf(ex.getErrorCode()),ex.getErrorMsg());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData constraintException(Exception ex) {
        log.error("Unknow exception", ex);
        return new ResponseData(500,"系统异常");
    }

    /**
     * Handle violation exception
     * 验证异常处理message提示
     *
     * @param ex
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData error(ConstraintViolationException ex) {
        log.error("Params violation excetion", ex);

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errorMsg = new LinkedList<>();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                errorMsg.add(violation.getMessage());
            }
        }
        return new ResponseData(403,errorMsg.toString());
    }

}
