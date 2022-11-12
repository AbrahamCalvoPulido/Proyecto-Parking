
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;

public class proyectoParking {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Calendar d = Calendar.getInstance();
        int codigoTicket,opcionAdmin = 4,opcionMultas,segundosAux = 0,segundosPagados = 0,minutosPagados = 0,horasPagados = 0;
        double dineroMulta1,dineroMulta2;
        String usuarioAdmin = "Admin",passAdmin = "1234",numeroTicket,numeroMulta = "";
        boolean codigoValido = false;
        do {
            System.out.println();
            System.out.println("Bienvenido a Zona Azul de Martos");
            System.out.println("1. OBTENER UN TICKET ");
            System.out.println();
            System.out.println("2. ANULAR UNA MULTA ");
            System.out.println();
            System.out.println("3. CONSULTAR HORA/FECHA ACTUAL, TARIFA Y HORARIOS ");
            System.out.println();
            System.out.println("4. MENU ADMINISTRADOR ");
            System.out.print("Introduce tu opcion: ");
            int opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1:
                    System.out.println("¿ES USTED RESIDENTE?: ");
                    String respuestaResidente = s.nextLine().toLowerCase();
                    if (!respuestaResidente.equals("si")) {
                        break;
                    }

                    double cantidadIn = 0;
                    do {
                        System.out.print("Introduce el dinero: ");
                        cantidadIn = Double.parseDouble(s.nextLine());
                    } while(cantidadIn < 0.0);

                    if (cantidadIn >= 0.8) {
                        segundosAux = 7200;
                    } else if (cantidadIn >= 0.6) {
                        segundosAux = 5352;
                    } else if (cantidadIn >= 0.4) {
                        segundosAux = 3571;
                    } else if (cantidadIn >= 0.2) {
                        segundosAux = 1790;
                    } else if (cantidadIn >= 0.1) {
                        segundosAux = 900;
                    }

                    horasPagados = segundosAux / 3600;
                    segundosAux %= 3600;
                    minutosPagados = segundosAux / 60;
                    segundosAux %= 60;


                case 2:
                    System.out.println("1. ANULAR UNA MULTA POR EXCEDER EL TIEMPO PERMITIDO.");
                    System.out.println("2. ANULAR UNA MULTA POR CARECER DE TICKET.");
                    System.out.print("INTRODUCE TU OPCIÓN: ");
                     opcionMultas = Integer.parseInt(s.nextLine());

                     switch (opcionMultas){

                         case 1:
                         System.out.println("INTRODUCE EL NÚMERO DE TICKET: ");
                         numeroTicket= s.nextLine();



                         case 2:
                             do {
                                 int codigoMulta = ((int)(Math.random()*(999-561+1)+556));
                                 if ((codigoMulta <= 999)&& (codigoMulta >= 889)) numeroMulta = codigoMulta +"3";
                                 if ((codigoMulta <= 888)&& (codigoMulta >= 778)) numeroMulta = codigoMulta +"5";
                                 if ((codigoMulta <= 777)&& (codigoMulta >= 667)) numeroMulta = codigoMulta +"7";
                                 if ((codigoMulta <= 666)&& (codigoMulta >= 556)) numeroMulta = codigoMulta +"9";

                                 System.out.println("INTRODUZCA EL DINERO DE LA MULTA (6,00 €): ");
                                 dineroMulta2 = Integer.parseInt(s.nextLine());
                             }while (dineroMulta2 >=6);



                     }


                    break;
                case 3:


                    PrintStream var10000 = System.out;
                    int var10001 = d.get(5);
                    System.out.println();
                    System.out.println("  FECHA:");
                    var10000.println("" + var10001 + "/" + (d.get(2) + 1) + "/" + d.get(1));
                    var10000 = System.out;
                    System.out.println();
                    var10001 = d.get(11);
                    System.out.println("HORA:");
                    var10000.println("" + var10001 + ":" + d.get(12));
                    System.out.println();
                    System.out.println("                         TARIFAS:");
                    System.out.println("""
                            
                             DURACIÓN             NO RESIDENTES            RESIDENTES
                            
                            00:15:00 H              0,25 EUR                0,10 EUR
                            00:29:50 H              0,45 EUR                0,25 EUR
                            00:59:31 H              0,85 EUR                0,40 EUR
                            01:29:12 H              1,25 EUR                0,60 EUR
                            02:00:00 H              1,70 EUR                0,80 EUR
                            """);

                    break;
                case 4:
                    int intentosLog = 3;

                    String usuarioIn;
                    String passIn;
                    do {
                        System.out.print("INTRODUCE TUS USUARIO ADMINISTRADOR: ");
                        usuarioIn = s.nextLine();
                        System.out.print("INTRODUCE LA CONTRASEÑA: ");
                        passIn = s.nextLine();
                        if (!usuarioIn.equals(usuarioAdmin) || !passIn.equals(passAdmin)) {
                            System.out.println("USUARIO O CONTRASEÑA INCORRECTA.");
                            intentosLog--;
                        }
                    } while((!usuarioIn.equals(usuarioAdmin) || !passIn.equals(passAdmin)) && intentosLog > 0);

                    if (intentosLog == 0) {
                        System.out.println("LA CUENTA A SIDO BLOQUEADA.");
                    } else {
                        System.out.println("BIENVENIDO AL MENU ADMINISTRADOR " + usuarioAdmin);
                        System.out.println();
                        System.out.println("1. CONSULTAR EL DINERO INGRESADO EN TICKET Y TICKET GENERADOS. ");
                        System.out.println();
                        System.out.println("2. CONSULTAR EL DINERO INGRESADO POR MULTAS Y LAS MULTAS ANULADAS. ");
                        System.out.println();
                        System.out.println("3. APAGAR PROGRAMA. ");
                        System.out.println("INTRODUZCA SU OPCIÓN: ");
                        opcionAdmin = Integer.parseInt(s.nextLine());

                        switch (opcionAdmin) {
                            case 1:
                            case 2:
                                continue;
                            case 3:
                                System.out.println("GRACIAS POR USAR NUESTRO PROGRAMA ");
                                continue;
                            default:
                                System.out.println("DEBE INTRODUCIR UNA DE LAS 3 OPCIONES.");
                        }
                    }
                    break;
                default:
                    System.out.println("OPCIÓN INCORRECTA.");
            }
        } while(opcionAdmin != 3);

    }
}