public class codigoMulta {
    public static void main(String[] args) {
       String numeroMulta = "";
        int franjaMulta = ((int)(Math.random()*(999-561+1)+556));
        if ((franjaMulta <= 999)&& (franjaMulta >= 889)) numeroMulta = franjaMulta +"3";
        if ((franjaMulta <= 888)&& (franjaMulta >= 778)) numeroMulta = franjaMulta +"5";
        if ((franjaMulta <= 777)&& (franjaMulta >= 667)) numeroMulta = franjaMulta +"7";
        if ((franjaMulta <= 666)&& (franjaMulta >= 556)) numeroMulta = franjaMulta +"9";
        System.out.println(franjaMulta+numeroMulta);
    }
}
