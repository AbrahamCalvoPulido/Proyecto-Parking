import java.util.Calendar;
import java.util.Scanner;

public class cambios {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Calendar d = Calendar.getInstance();
        int codigoTicket,dineroMulta2,horaDelPago,minDelPago,horaFinEstablecimiento,diaFinEstablecimiento,mesFinEstablecimiento,minFinEstablecimiento,numAux,numAleatorio,longitudTicket,opcionAdmin = 4,opcionMultas,segundosAux = 0,cantidadTickets=0,segundosPagados = 0,minutosPagados = 0,horasPagados = 0;
        double dineroMulta1, cantidadIn;
        String usuarioAdmin = "Admin";
        String passAdmin = "1234",numeroTicket="";
        String numeroMulta = "";

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
                    if (respuestaResidente.equals("no")) {
                        System.out.println("Vale pobre");
                    }else if(respuestaResidente.equals("si")) {
                        do {
                            System.out.print("Introduce el dinero: ");
                            cantidadIn = Double.parseDouble(s.nextLine());
                        } while (cantidadIn < 0.0);

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
                        //Hora a la que hizo el pago y minuto al que hizo el pago
                        horaDelPago=d.get(Calendar.HOUR_OF_DAY);
                        minDelPago=d.get(Calendar.MINUTE);
                        //Numero aleatorio para el ticket
                        numAleatorio= (int) (Math.random()*1000000);
                        numAux=numAleatorio;
                        longitudTicket=0;
                        numeroTicket="";
                        do {
                            numAux/=10;
                            longitudTicket++;
                        }while(numAux>0);
                        if (longitudTicket<6){
                            for (int i = 0; i < 6-longitudTicket; i++) {
                                numeroTicket+=("0");
                            }
                        }
                        numeroTicket+=(numAleatorio);
                        numeroTicket+=("-");
                        numeroTicket+=(cantidadTickets);
                        cantidadTickets++;

                        if (Calendar.DAY_OF_WEEK!=7 || Calendar.DAY_OF_WEEK!=1){
                            int horaActual=d.get(Calendar.HOUR_OF_DAY);
                            switch(horaActual){
                                case 0,1,2,3,4,5,6,7,8:
                                    horaFinEstablecimiento=9+horasPagados;
                                    minFinEstablecimiento=minutosPagados;
                                    diaFinEstablecimiento=d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento=(d.get(Calendar.MONTH)+1);
                                    break;
                                case 14,15,16:
                                    horaFinEstablecimiento=17+horasPagados;
                                    minFinEstablecimiento=minutosPagados;
                                    diaFinEstablecimiento=d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento=(d.get(Calendar.MONTH)+1);
                                    break;
                                case 12:


                                    break;
                                case 21,22,23:
                                    horaFinEstablecimiento=9+horasPagados;
                                    minFinEstablecimiento=minutosPagados;
                                    diaFinEstablecimiento=(d.get(Calendar.DAY_OF_MONTH)+1);
                                    mesFinEstablecimiento=d.get(Calendar.MONTH)+1;
                                    System.out.println(diaFinEstablecimiento);
                                    System.out.println(d.get(Calendar.MONTH)+1);
                                    switch(d.get(Calendar.MONTH)+1){
                                        case 1,3,5,7,8,10,12:
                                            if (diaFinEstablecimiento==32){
                                                diaFinEstablecimiento=1;
                                                mesFinEstablecimiento+=1;
                                            }
                                            break;
                                        case 2,4,6,9,11:
                                            if (diaFinEstablecimiento==31){
                                                diaFinEstablecimiento=1;
                                                mesFinEstablecimiento+=1;
                                            }
                                            break;

                                    }
                                    break;

                                default:
                                    horaFinEstablecimiento=d.get(Calendar.HOUR_OF_DAY)+horasPagados;
                                    minFinEstablecimiento=d.get(Calendar.MINUTE)+minutosPagados;
                                    if (minFinEstablecimiento>=60){
                                        minFinEstablecimiento-=60;
                                        horaFinEstablecimiento+=1;
                                    }


                                    diaFinEstablecimiento=d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento=(d.get(Calendar.MONTH)+1);


                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("1. ANULAR UNA MULTA POR EXCEDER EL TIEMPO PERMITIDO.");
                    System.out.println("2. ANULAR UNA MULTA POR CARECER DE TICKET.");
                    System.out.print("INTRODUCE TU OPCIÓN: ");
                    opcionMultas = Integer.parseInt(s.nextLine());
                    switch (opcionMultas){
                        case 1:
                            System.out.println("INTRODUCE EL NÚMERO DE TICKET: ");
                            numeroTicket= s.nextLine();

                        System.out.println("NÚMERO INTRODUCIDO VÁLIDO");
                        do {
                            System.out.println("Introduce el dinero (3.00 €):");
                            dineroMulta1 = Double.parseDouble(s.nextLine());

                        }while (dineroMulta1 < 3);
                        case 2:

                            int codigoMulta = ((int)(Math.random()*(999-561+1)+556));
                            if ((codigoMulta <= 999)&& (codigoMulta >= 889)) numeroMulta = codigoMulta +"3";
                            if ((codigoMulta <= 888)&& (codigoMulta >= 778)) numeroMulta = codigoMulta +"5";
                            if ((codigoMulta <= 777)&& (codigoMulta >= 667)) numeroMulta = codigoMulta +"7";
                            if ((codigoMulta <= 666)&& (codigoMulta >= 556)) numeroMulta = codigoMulta +"9";



                                System.out.println("INTRODUZCA EL DINERO DE LA MULTA (6,00 €): ");
                                dineroMulta2 = Integer.parseInt(s.nextLine());

                            System.out.println("=======================================================");
                            System.out.println("| NªTICKET: "+numeroTicket+"                     "    +d.get(Calendar.DAY_OF_MONTH)+":"+(d.get(Calendar.MONTH)+1)+":"+d.get(Calendar.YEAR)+ "\t          |");
                            System.out.println("|                                                     |");
                            System.out.println("|                                   "+d.get(Calendar.HOUR_OF_DAY)+":"+d.get(Calendar.MINUTE)+"              |");
                            System.out.println("| NªMULTA: "+codigoMulta+numeroMulta+"                                    |");
                            System.out.println("|                                                     |");
                            System.out.println("| IMPORTE PAGADO: "+dineroMulta2+" €"+"                                 |");
                            System.out.println("|                                                     |");
                            System.out.println("|       ----- MULTA PAGADA EXITOSAMENTE -----         |");
                            System.out.println("|                                                     |");
                            System.out.println("|              AYUNTAMIENTO DE MARTOS                 |");
                            System.out.println("=======================================================");


                    }

                    break;
                case 3:
                    System.out.println("1. FECHA/HORA ACTUAL ");
                    System.out.println(d.get(Calendar.DAY_OF_MONTH)+":"+(d.get(Calendar.MONTH)+1)+":"+d.get(Calendar.YEAR));
                    System.out.println(d.get(Calendar.HOUR_OF_DAY)+":"+d.get(Calendar.MINUTE));
                    System.out.println();
                    System.out.println("2.Tarifas");
                    System.out.println("""
                            
                             DURACION             NO RESIDENTES            RESIDENTES
                            
                            00:15:00 H              0,25 EUR                0,10 EUR
                            00:29:50 H              0,45 EUR                0,25 EUR
                            00:59:31 H              0,85 EUR                0,40 EUR
                            01:29:12 H              1,25 EUR                0,60 EUR
                            02:00:00 H              1,70 EUR                0,80 EUR
                            """);
                    System.out.println("3.Horario");
                    System.out.println("""
                            Lunes a Viernes
                            09.00h - 14.00h
                            
                            Sabado
                            09.00h - 14.00h
                            """);
                    System.out.print("Pulse enter para continuar...");
                    s.nextLine();
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