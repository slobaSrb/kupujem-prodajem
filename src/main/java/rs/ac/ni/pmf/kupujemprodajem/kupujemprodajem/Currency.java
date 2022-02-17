package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem;

public enum Currency {
    Dollar,
    Euro,
    RSD;

    public static boolean exists(Currency currency) {

        for(Currency cur1 : Currency.values()){
            if(cur1.toString().equals(currency.toString())) {
                return true;
            }
        }
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
