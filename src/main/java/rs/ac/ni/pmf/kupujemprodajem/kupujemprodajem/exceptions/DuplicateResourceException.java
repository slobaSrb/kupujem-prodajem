package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class DuplicateResourceException extends ResourceException {
    private static final long serialVersionUID = 1L;

    public DuplicateResourceException(final ApiError.ResourceType resourceType, final String message) {
        super(resourceType, message);
    }
}
