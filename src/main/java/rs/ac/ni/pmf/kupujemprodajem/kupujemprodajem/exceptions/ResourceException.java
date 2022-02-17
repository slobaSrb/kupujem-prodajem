package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

import lombok.Getter;

@Getter
public class ResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final ApiError.ResourceType resourceType;

    public ResourceException(final ApiError.ResourceType resourceType, final String message) {
        super(message);
        this.resourceType = resourceType;
    }

}
