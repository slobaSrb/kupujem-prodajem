package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(Long id){ super("Rating with id " + id + " not found."); }
}
