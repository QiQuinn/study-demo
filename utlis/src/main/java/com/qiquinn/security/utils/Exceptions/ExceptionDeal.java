package com.qiquinn.security.utils.Exceptions;


import com.qiquinn.security.utils.ResultSerlizerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:QiQuinn
 * @Desicription: 异常处理类
 * @Date:Created in 2019/7/10
 * @Modified By:
 */
@ControllerAdvice
public class ExceptionDeal
{
    private final static Logger loggers = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
      * @Author:QiQuinn
      * @Desicription: 自定义错误处理
      * @Date:Created in 2019/7/10 11:40
      *@return java.util.Map<java.lang.String,java.lang.Object>
      * @Modified By:
      */
    @ExceptionHandler(value = CustomerExpection.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public Map<String,Object> defualtErrorHandler(CustomerExpection exception)
    {
        loggers.error(exception.toString(),exception);
        return ResultSerlizerUtils.error(exception.getCode(),exception.getMessage());
    }

    /**
      * @Author:QiQuinn
      * @Desicription: 参数校验错误处理
      * @Date:Created in 2019/7/10 11:39
      *@return java.util.Map<java.lang.String,java.lang.Object>
      * @Modified By:
      */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,Object> vertifyErrorDeal(MethodArgumentNotValidException ex)
    {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = "field error: ";
        if(bindingResult.hasErrors())
        {
            errorMessage = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(" | "));
        }
        loggers.error("参数验证错误: "+errorMessage+": "+ex.getLocalizedMessage());
        return ResultSerlizerUtils.error(1111,errorMessage);
    }
}
