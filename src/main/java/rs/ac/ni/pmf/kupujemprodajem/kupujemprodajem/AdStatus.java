package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem;

public enum AdStatus {
    Available,
    OutOfStock,
    Removed;

    public static boolean exists(AdStatus status) {
        for(AdStatus ads1 : AdStatus.values()){
            if(ads1.toString().equals(status.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}

enum PurchaseStatus{
    Preparing,
    DeniedByOwner,
    DeniedByBuyer,
    SentInTransit,
    DeniedByTransit,
    ReturnedByOwner,
    WaitingForPickUp,
    Delivered;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}


//v1
//korisnik postavlja projekat i on je automatski Available
//drugi korisnik salje zahtev za kupovinom, oglas postaje zauzet Busy
//dolazi do otkaza kupovine, oglas postaje Available, dolazi do kupovine, oglas postaje Saled i
//tog trenutka se moze oceniti od strane kupca koji je kupio proizvod sa oglasa
//kada se oglas oceni ili kada istekne period vazenja oglasa posle kupovine od 30 dana,
// oglas se moze obrisati od strane admina ili korisnika, ali moze se i obrisati i ranije,
// samo nije dozvoljeno brisanje kada je status Saled ili Busy, sve dok se ne oceni
// ocene ostaju kao komentari na profilu korisnika
// ima tri kategorije koje moze poslati ocenjivac, to cu videti da li cu uvesti

