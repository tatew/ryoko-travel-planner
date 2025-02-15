package tatew.ryoko;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tatew.ryoko.model.dto.ErrorDto;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    public ErrorDto defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception
    {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
        {
            throw e;
        }
        log.error("An unhandled error occurred: ", e);
        return new ErrorDto("An unexpected server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
