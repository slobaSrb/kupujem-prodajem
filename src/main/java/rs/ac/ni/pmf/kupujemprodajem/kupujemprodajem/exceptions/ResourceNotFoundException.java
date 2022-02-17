package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class ResourceNotFoundException extends ResourceException {
    public ResourceNotFoundException(final ApiError.ResourceType resourceType, final String message) {
        super(resourceType, message);
    }
}
