package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiError
{
    private ErrorCode code;
    private ResourceType resourceType;
    private String message;

    public enum ErrorCode
    {
        NOT_AUTHORIZED,
        NOT_FOUND,
        DUPLICATE,
        NOT_CHANGEABLE,
        AUTHENTICATION_FAILED,
        NOT_REMOVABLE
    }

    public enum ResourceType {
        ACCESS,
        AD,
        COMMENT,
        PURCHASE,
        USER,
        ROLE
    }
}

