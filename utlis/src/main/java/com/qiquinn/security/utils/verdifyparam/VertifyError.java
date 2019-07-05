package com.qiquinn.security.utils.verdifyparam;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by QiQuinn on 2019/7/4.
 * 验证错误消息处类
 */
@ControllerAdvice
public class VertifyError
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String,Object> vertifyErrorDeal(MethodArgumentNotValidException ex)
    {
        System.out.println("method not vaild: "+ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = "field error: ";
        if(bindingResult.hasErrors())
        {
            errorMessage = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(" | "));
        }
        Map<String,Object> errorMap = new HashMap<String,Object>();
        errorMap.put("code",1111);
        errorMap.put("message",errorMessage);
        return errorMap;
    }
}
