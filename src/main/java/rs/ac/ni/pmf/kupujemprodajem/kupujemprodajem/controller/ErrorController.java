package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.*;

@ControllerAdvice
@ResponseBody
public class ErrorController {

    private final Class<UserNotFoundException> userNotFoundExceptionClass = UserNotFoundException.class;

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handle(org.springframework.web.HttpRequestMethodNotSupportedException e) {
        return ApiError.builder()
                .code(ApiError.ErrorCode.NOT_AUTHORIZED)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler({AdNotFoundException.class, UserNotFoundException.class, CommentNotFoundException.class, RatingNotFoundException.class, PurchaseNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final RuntimeException e) {
        return ApiError.builder()
                .code(ApiError.ErrorCode.NOT_FOUND)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final ResourceNotFoundException e) {
        return ApiError.builder()
                .code(ApiError.ErrorCode.NOT_FOUND)
                .resourceType(e.getResourceType())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleDuplicateResourceException(final DuplicateResourceException e) {
        return ApiError.builder()
                .code(ApiError.ErrorCode.DUPLICATE)
                .resourceType(e.getResourceType())
                .message(e.getMessage())
                .build();
    }

}
