package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException(long id)
    {
        super("Ad with id " + id + " does not exist.");
    }
}

