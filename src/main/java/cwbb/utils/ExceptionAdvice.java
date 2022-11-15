package cwbb.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public Result deException(Exception e){
        e.printStackTrace();
        return Result.fail("服务器故障，请稍后再试");
    }
}
