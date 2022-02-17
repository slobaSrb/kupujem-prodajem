package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(long id){ super("Purchase with id "+ id + " not found."); }
}
