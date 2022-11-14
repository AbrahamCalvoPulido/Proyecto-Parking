


import java.util.Calendar;
import java.util.Scanner;

public class proyectoParking {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Calendar d = Calendar.getInstance();

        //Todo INICIALIZACIÓN DE VARIABLES.
        int codigoTicket, opcionFecha = 0, multasAnuladas = 0, totalMultas = 0, anioDelPago, horaDelPago, minDelPago, horaFinEstablecimiento = 0, diaFinEstablecimiento = 0, mesFinEstablecimiento = 0, minFinEstablecimiento = 0, numAux, numAleatorio, longitudTicket, codigoMulta, codigoMulta2, opcionAdmin = 4, opcionMultas, segundosAux = 0, cantidadTickets = 0, segundosPagados = 0, minutosPagados = 0, horasPagados = 0;
        double dineroMulta1 = 0, cantidadIn, dineroTotalTickets = 0, dineroMulta2 = 0;
        String passAdmin = "1234", numeroTicket = "", numeroTicket2 = "", numeroMulta = "", usuarioAdmin = "Admin", parte1 = "", parte2 = "";
        char caracter = 0;
        boolean codigoValido = false;

        do {
            System.out.println();
            System.out.println("BIENVENIDO HA ZONA AZUL DE MARTOS");
            System.out.println("1. OBTENER UN TICKET ");
            System.out.println();
            System.out.println("2. ANULAR UNA MULTA ");
            System.out.println();
            System.out.println("3. CONSULTAR HORA/FECHA ACTUAL, TARIFA Y HORARIOS ");
            System.out.println();
            System.out.println("4. MENU ADMINISTRADOR ");
            System.out.print("INTRODUCE TU OPCION: ");
            int opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1:
                    do {
                        System.out.println("QUE TIPO DE FECHA DESEA USAR: ");
                        System.out.println("1. FECHA SIMULADA. ");
                        System.out.println("2. FECHA ACTUAL. ");
                        System.out.println();
                        System.out.print("INTRODUCE TU OPCION: ");
                        opcionFecha = Integer.parseInt(s.nextLine());
                        switch (opcionFecha) {

                            //Todo FECHA SIMULADA.
                            case 1:
                                System.out.print("INTRODUCE EL DIA:(1-31)");
                                d.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s.nextLine()));
                                System.out.print("INTRODUCE EL MES:(0=ENERO-11=DICIEMBRE)");
                                d.set(Calendar.MONTH, Integer.parseInt(s.nextLine()));
                                System.out.print("INTRODUCE EL ANIO:");
                                d.set(Calendar.YEAR, Integer.parseInt(s.nextLine()));
                                System.out.print("INTRODUCE LA HORA:(0-23H)");
                                d.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s.nextLine()));
                                System.out.print("INTRODUCE LOS MINUTOS:(0-59M)");
                                d.set(Calendar.MINUTE, Integer.parseInt(s.nextLine()));
                                break;
                            case 2:
                                System.out.println("ESTAS USANDO LA FECHA ACTUAL");
                                break;
                            default:
                                System.out.println("OPCION INCORRECTA INTENTELO DE NUEVO");
                                System.out.println("PULSE PARA CONTINUAR...");
                                s.nextLine();
                                break;
                        }

                    } while (opcionFecha != 1 && opcionFecha != 2);
                    System.out.println("ES USTED RESIDENTE: (SI/NO)? ");
                    String respuestaResidente = s.nextLine().toLowerCase();
                    if (respuestaResidente.equals("no")) {
                        do {
                            System.out.print("INTRODUCE EL DINERO: ");
                            cantidadIn = Double.parseDouble(s.nextLine());
                        } while (cantidadIn < 0.0);

                        //Todo CANTIDAD DE DINERO INTRODUCIDA PASADA A SEGUNDOS.
                        if (cantidadIn >= 1.7) {
                            segundosAux = 7200;
                        } else if (cantidadIn >= 1.25) {
                            segundosAux = 5352;
                        } else if (cantidadIn >= 0.85) {
                            segundosAux = 3571;
                        } else if (cantidadIn >= 0.45) {
                            segundosAux = 1790;
                        } else if (cantidadIn >= 0.25) {
                            segundosAux = 900;
                        }
                        //Todo  LOS SEGUNDOS LOS PASAMOS A HORAS Y MINUTOS Y EL RESTANTE SON LOS SEGUNDOS.
                        horasPagados = segundosAux / 3600;
                        segundosAux %= 3600;
                        minutosPagados = segundosAux / 60;
                        segundosAux %= 60;

                        dineroTotalTickets += cantidadIn;

                        //Todo HORA Y MINUTO EN EL QUE REALIZÓ EL PAGO.
                        horaDelPago = d.get(Calendar.HOUR_OF_DAY);
                        minDelPago = d.get(Calendar.MINUTE);
                        anioDelPago = d.get(Calendar.YEAR);

                        //Todo NUMERO ALEATORIO PARA TICKET.
                        numAleatorio = (int) (Math.random() * 1000000);
                        numAux = numAleatorio;
                        longitudTicket = 0;
                        numeroTicket = "";
                        do {
                            numAux /= 10;
                            longitudTicket++;
                        } while (numAux > 0);

                        //Todo CALCULA LA LONGITUD DEL DIGITO Y AÑADE CEROS DEPENDIENDO DE LA POSICION DEL DIGITO Y SE SUMA A LA CANTIDAD DE TICKETS.
                        if (longitudTicket < 6) {
                            for (int i = 0; i < 6 - longitudTicket; i++) {
                                numeroTicket += ("0");
                            }
                        }
                        numeroTicket += (numAleatorio);
                        numeroTicket += ("-");
                        numeroTicket += (cantidadTickets);
                        cantidadTickets++;

                        //Todo INICIALIZA LA HORA ACTUAL EN UNA VARIABLE.
                        int horaActual = d.get(Calendar.HOUR_OF_DAY);
                        if (d.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && d.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

                            //Todo COMPROBACIONES DE FRANJAS HORARIAS DEL PARKING.
                            switch (horaActual) {
                                case 0, 1, 2, 3, 4, 5, 6, 7, 8:
                                    horaFinEstablecimiento = 9 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    break;
                                case 14, 15, 16:
                                    horaFinEstablecimiento = 17 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    break;

                                case 12:
                                    if (minDelPago < 30 && cantidadIn < 1.7) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                    } else if (minDelPago >= 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.85) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 1.25) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        }
                                        //Todo CASO DE: 1h y 29min.
                                        if (cantidadIn >= 1.25 && cantidadIn < 1.70) {
                                            int horaPagoaux = horaDelPago + 1;
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 17;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));

                                        }
                                        //Todo CASO DE: 2h.
                                    }
                                    if (minDelPago == 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = horaDelPago + horasPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 17;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    }
                                    break;
                                case 19:
                                    if (minDelPago < 30 && cantidadIn < 1.7) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago >= 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                        //Todo CASO DE MINUTO DEL PAGO SEA APARTIR DE LAS Y MEDIA.
                                    } else if (minDelPago >= 30 && cantidadIn >= 0.85) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 1.25) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        }
                                        //Todo CASO DE: 1h y 29min.
                                        if (cantidadIn >= 1.25 && cantidadIn < 1.7) {
                                            int horaPagoaux = horaDelPago + 1;
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 31) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;

                                            }
                                        }
                                        //Todo CASO DE: 2h.
                                    }
                                    if (minDelPago == 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = horaDelPago + horasPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;

                                //Todo CASE DE LAS 13:00 HORAS.
                                case 13:
                                    if (minDelPago < 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago < 30 && cantidadIn >= 0.85 && cantidadIn < 1.25) {
                                        horaFinEstablecimiento = 17;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = 17;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.85) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 1.25) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 17;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        }
                                    }
                                    if (minDelPago == 0 && cantidadIn <= 1.7 && cantidadIn >= 1.25) {
                                        horaFinEstablecimiento = 17;
                                        minFinEstablecimiento = minutosPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago != 0 && cantidadIn <= 1.7 && cantidadIn >= 1.25) {
                                        horaFinEstablecimiento = 17;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento++;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    }

                                    //Todo CASO DE: 2h.
                                    if (minDelPago == 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 18;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 18;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    }
                                    break;

                                //Todo CASE DE LAS 20:00 HORAS.
                                case 20:
                                    if (minDelPago < 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago < 30 && cantidadIn >= 0.85 && cantidadIn < 1.25) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                        //Todo CASO APARTIR DE LAS Y MEDIA.
                                    } else if (minDelPago >= 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        }
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.85) {

                                        //Todo CASODE: 59 min.
                                        if (cantidadIn < 1.25) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 31) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    if (minDelPago == 0 && cantidadIn <= 1.7 && cantidadIn >= 1.25) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    } else if (minDelPago != 0 && cantidadIn <= 1.7 && cantidadIn >= 1.25) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento++;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    //Todo CASO DE: 2h.
                                    if (minDelPago == 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 10;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago != 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 10;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case 21, 22, 23:
                                    horaFinEstablecimiento = 9 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                    mesFinEstablecimiento = d.get(Calendar.MONTH) + 1;
                                    System.out.println(diaFinEstablecimiento);
                                    System.out.println(d.get(Calendar.MONTH) + 1);
                                    switch (d.get(Calendar.MONTH) + 1) {
                                        case 1, 3, 5, 7, 8, 10:
                                            if (diaFinEstablecimiento == 32) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 2, 4, 6, 9, 11:
                                            if (diaFinEstablecimiento == 31) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 12:
                                            if (diaFinEstablecimiento == 32) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento = 1;
                                                anioDelPago += 1;
                                            }
                                            break;

                                    }
                                    break;

                                default:
                                    horaFinEstablecimiento = d.get(Calendar.HOUR_OF_DAY) + horasPagados;
                                    minFinEstablecimiento = d.get(Calendar.MINUTE) + minutosPagados;
                                    if (minFinEstablecimiento >= 60) {
                                        minFinEstablecimiento -= 60;
                                        horaFinEstablecimiento += 1;
                                    }


                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    break;
                            }

                        } else if (d.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            switch (horaActual) {
                                case 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8:
                                    horaFinEstablecimiento = 9 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    switch (d.get(Calendar.MONTH) + 1) {
                                        case 1, 3, 5, 7, 8, 10:
                                            if (diaFinEstablecimiento == 33) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 2, 4, 6, 9, 11:
                                            if (diaFinEstablecimiento == 32) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 12:
                                            if (diaFinEstablecimiento == 33) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento = 1;
                                                anioDelPago += 1;
                                            }
                                            break;

                                    }
                                    break;
                                case 9, 10, 11:
                                    horaFinEstablecimiento = d.get(Calendar.HOUR_OF_DAY) + horasPagados;
                                    minFinEstablecimiento = d.get(Calendar.MINUTE) + minutosPagados;
                                    if (minFinEstablecimiento >= 60) {
                                        minFinEstablecimiento -= 60;
                                        horaFinEstablecimiento += 1;
                                    }


                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    break;
                                case 12:
                                    if (minDelPago < 30 && cantidadIn < 1.7) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago >= 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.85) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 1.25) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        }
                                        //Todo CASO DE: 1h y 29min.
                                        if (cantidadIn >= 1.25 && cantidadIn < 1.7) {
                                            int horaPagoaux = horaDelPago + 1;
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;

                                            }
                                        }
                                        //Todo CASO DE: 2h.
                                    }
                                    if (minDelPago == 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = horaDelPago + horasPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;

                                //Todo CASE DE LAS 13:00 HORAS.
                                case 13:
                                    if (minDelPago < 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago < 30 && cantidadIn >= 0.85 && cantidadIn < 1.25) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago >= 30 && cantidadIn < 0.85) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        }
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                        //Todo CASO MINUTOS PAGADOS APARTIR DE LAS Y MEDIA.
                                    } else if (minDelPago >= 30 && cantidadIn >= 0.85) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 1.25) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    if (minDelPago == 0 && cantidadIn <= 1.7 && cantidadIn >= 1.25) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    } else if (minDelPago != 0 && cantidadIn <= 1.7 && cantidadIn >= 1.25) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento++;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    //Todo CASO DE: 2h.
                                    if (minDelPago == 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 10;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago != 0 && cantidadIn >= 1.7) {
                                        horaFinEstablecimiento = 10;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;
                            }
                        } else {
                            horaFinEstablecimiento = 9 + horasPagados;
                            minFinEstablecimiento = minutosPagados;
                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                            switch (d.get(Calendar.MONTH) + 1) {
                                case 1, 3, 5, 7, 8, 10, 12:
                                    if (diaFinEstablecimiento == 32) {
                                        diaFinEstablecimiento = 1;
                                        mesFinEstablecimiento += 1;
                                    }
                                    break;
                                case 2, 4, 6, 9, 11:
                                    if (diaFinEstablecimiento == 31) {
                                        diaFinEstablecimiento = 1;
                                        mesFinEstablecimiento += 1;
                                    }
                                    break;
                            }
                        }
                        //Todo IMPRESION DEL TICKET.
                        delay("CARGANDO TICKET");
                        System.out.println();
                        System.out.println(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" +
                                "|COD.TICKET                                                             |");
                        System.out.printf("| %s                     FIN DE ESTACIONAMIENTO: %02d : %02d          |", numeroTicket, horaFinEstablecimiento, minFinEstablecimiento);
                        System.out.println();
                        System.out.println("|                                                                       |");
                        System.out.printf("|YEAR %4d                     DIA-MES] %02d / %02d                         |", anioDelPago, diaFinEstablecimiento, mesFinEstablecimiento);
                        System.out.println();
                        System.out.println("|                             IMPORTE PAGADO]" + cantidadIn + "EUR                     |\n" +
                                "|                                                                       |");
                        System.out.printf("!       %02d/%02d/%02d      %02d:%02d                                           !", d.get(Calendar.DAY_OF_MONTH), (d.get(Calendar.MONTH) + 1), d.get(Calendar.YEAR), horaDelPago, minDelPago);
                        System.out.println();
                        System.out.println(":                                   EXCELENTISIMO AYUNTAMIENTO DE MARTOS:\n" +
                                "|                                                                       |\n" +
                                "`-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-'");
                        System.out.println("PULSE UNA LETRA PARA CONTINUAR.");
                        s.nextLine();


                    } else if (respuestaResidente.equals("si")) {
                        do {
                            System.out.print("INTRODUCE EL DINERO: ");
                            cantidadIn = Double.parseDouble(s.nextLine());
                        } while (cantidadIn < 0.0);

                        //Todo CANTIDAD DE DINERO INTRODUCIDA PASADA A SEGUNDOS.
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
                        dineroTotalTickets += cantidadIn;

                        //Todo LOS SEGUNDOS LOS PASAMOS A HORAS Y MINUTOS Y EL RESTANTE SON LOS SEGUNDOS.
                        horasPagados = segundosAux / 3600;
                        segundosAux %= 3600;
                        minutosPagados = segundosAux / 60;
                        segundosAux %= 60;

                        //Todo HORA Y MINUTO AL QUE HIZO EL PAGO.
                        horaDelPago = d.get(Calendar.HOUR_OF_DAY);
                        minDelPago = d.get(Calendar.MINUTE);
                        anioDelPago = d.get(Calendar.YEAR);

                        //Todo NUMERO ALEATORIO PARA EL TICKET.
                        numAleatorio = (int) (Math.random() * 1000000);
                        numAux = numAleatorio;
                        longitudTicket = 0;
                        numeroTicket = "";
                        do {
                            numAux /= 10;
                            longitudTicket++;
                        } while (numAux > 0);
                        if (longitudTicket < 6) {
                            for (int i = 0; i < 6 - longitudTicket; i++) {
                                numeroTicket += ("0");
                            }
                        }
                        numeroTicket += (numAleatorio);
                        numeroTicket += ("-");
                        numeroTicket += (cantidadTickets);
                        cantidadTickets++;
                        int horaActual = d.get(Calendar.HOUR_OF_DAY);
                        if (d.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && d.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                            switch (horaActual) {
                                case 0, 1, 2, 3, 4, 5, 6, 7, 8:
                                    horaFinEstablecimiento = 9 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    break;
                                case 14, 15, 16:
                                    horaFinEstablecimiento = 17 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    break;

                                //Todo CASE DE LAS 12:00 HORAS.
                                case 12:
                                    if (minDelPago < 30 && cantidadIn < 0.8) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                    } else if (minDelPago >= 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.4) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 0.6) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        }

                                        //Todo CASO DE: 1h y 29min.
                                        if (cantidadIn >= 0.6 && cantidadIn < 0.8) {
                                            int horaPagoaux = horaDelPago + 1;
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 17;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));

                                        }

                                        //Todo CASO DE : 2h.
                                    }
                                    if (minDelPago == 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = horaDelPago + horasPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 17;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    }
                                    break;

                                //Todo CASE DE LAS 19:00 HORAS.
                                case 19:
                                    if (minDelPago < 30 && cantidadIn < 0.8) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago >= 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.4) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 0.6) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        }
                                        //Todo CASO DE: 1h y 29min.
                                        if (cantidadIn >= 0.6 && cantidadIn < 0.8) {
                                            int horaPagoaux = horaDelPago + 1;
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 31) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;

                                            }
                                        }
                                        //Todo CASO DE: 2h.
                                    }
                                    if (minDelPago == 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = horaDelPago + horasPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;

                                //Todo CASE DE LAS 13:00 HORAS.
                                case 13:
                                    if (minDelPago < 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago < 30 && cantidadIn >= 0.4 && cantidadIn < 0.6) {
                                        horaFinEstablecimiento = 17;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = 17;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.4) {

                                        //Todo CASO DE 59 min.
                                        if (cantidadIn < 0.6) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 17;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        }
                                    }
                                    if (minDelPago == 0 && cantidadIn <= 0.8 && cantidadIn >= 0.6) {
                                        horaFinEstablecimiento = 17;
                                        minFinEstablecimiento = minutosPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago != 0 && cantidadIn <= 0.8 && cantidadIn >= 0.6) {
                                        horaFinEstablecimiento = 17;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento++;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    }
                                    //Todo CASO DE: 2h
                                    if (minDelPago == 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 18;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 18;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    }
                                    break;

                                //Todo CASO DE 29:50 min o menor DE LAS 20:00 HORAS.
                                case 20:
                                    if (minDelPago < 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago < 30 && cantidadIn >= 0.4 && cantidadIn < 0.6) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago >= 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        }
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.4) {
                                        //Todo CASO DE: 59 min
                                        if (cantidadIn < 0.6) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 31) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    if (minDelPago == 0 && cantidadIn <= 0.8 && cantidadIn >= 0.6) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    } else if (minDelPago != 0 && cantidadIn <= 0.8 && cantidadIn >= 0.6) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento++;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    //Todo CASO DE: 2h DE LAS 20:00
                                    if (minDelPago == 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 10;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago != 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 10;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 31) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                //Todo CASE DE LAS HORAS 9,10 y 11 DE LA TARDE.
                                case 21, 22, 23:
                                    horaFinEstablecimiento = 9 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                                    mesFinEstablecimiento = d.get(Calendar.MONTH) + 1;
                                    System.out.println(diaFinEstablecimiento);
                                    System.out.println(d.get(Calendar.MONTH) + 1);
                                    switch (d.get(Calendar.MONTH) + 1) {
                                        case 1, 3, 5, 7, 8, 10:
                                            if (diaFinEstablecimiento == 32) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 2, 4, 6, 9, 11:
                                            if (diaFinEstablecimiento == 31) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 12:
                                            if (diaFinEstablecimiento == 32) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento = 1;
                                                anioDelPago += 1;
                                            }
                                            break;

                                    }
                                    break;

                                default:
                                    horaFinEstablecimiento = d.get(Calendar.HOUR_OF_DAY) + horasPagados;
                                    minFinEstablecimiento = d.get(Calendar.MINUTE) + minutosPagados;
                                    if (minFinEstablecimiento >= 60) {
                                        minFinEstablecimiento -= 60;
                                        horaFinEstablecimiento += 1;
                                    }


                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    break;
                            }

                        } else if (d.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            switch (horaActual) {
                                case 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2, 3, 4, 5, 6, 7, 8:
                                    horaFinEstablecimiento = 9 + horasPagados;
                                    minFinEstablecimiento = minutosPagados;
                                    diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    switch (d.get(Calendar.MONTH) + 1) {
                                        case 1, 3, 5, 7, 8, 10:
                                            if (diaFinEstablecimiento == 33) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 2, 4, 6, 9, 11:
                                            if (diaFinEstablecimiento == 32) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento += 1;
                                            }
                                            break;
                                        case 12:
                                            if (diaFinEstablecimiento == 33) {
                                                diaFinEstablecimiento = 1;
                                                mesFinEstablecimiento = 1;
                                                anioDelPago += 1;
                                            }
                                            break;

                                    }
                                    break;
                                case 9, 10, 11:
                                    horaFinEstablecimiento = d.get(Calendar.HOUR_OF_DAY) + horasPagados;
                                    minFinEstablecimiento = d.get(Calendar.MINUTE) + minutosPagados;
                                    if (minFinEstablecimiento >= 60) {
                                        minFinEstablecimiento -= 60;
                                        horaFinEstablecimiento += 1;
                                    }


                                    diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                    mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    break;
                                case 12:
                                    if (minDelPago < 30 && cantidadIn < 0.8) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago >= 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }

                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.4) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 0.6) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        }
                                        //Todo CASO DE: 1h y 29min.
                                        if (cantidadIn >= 0.6 && cantidadIn < 0.8) {
                                            int horaPagoaux = horaDelPago + 1;
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;

                                            }
                                        }
                                        //Todo CASO DE: 2h.
                                    }
                                    if (minDelPago == 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = horaDelPago + horasPagados;
                                        diaFinEstablecimiento = d.get(Calendar.DAY_OF_MONTH);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);


                                    } else if (minDelPago != 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                //Todo CASE DE LAS 13:00 HORAS.
                                case 13:
                                    if (minDelPago < 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = horaDelPago + 1;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                    } else if (minDelPago < 30 && cantidadIn >= 0.4 && cantidadIn < 0.6) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago >= 30 && cantidadIn < 0.4) {
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        } else {
                                            horaFinEstablecimiento = horaDelPago + horasPagados;
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH));
                                        }
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago >= 30 && cantidadIn >= 0.4) {

                                        //Todo CASO DE: 59 min.
                                        if (cantidadIn < 0.6) {
                                            int minPagoaux = minDelPago;
                                            minPagoaux += minutosPagados;
                                            if (minPagoaux >= 60) horaFinEstablecimiento = 9;
                                            minFinEstablecimiento = minPagoaux - 60;
                                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                            switch (d.get(Calendar.MONTH) + 1) {
                                                case 1, 3, 5, 7, 8, 10:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 2, 4, 6, 9, 11:
                                                    if (diaFinEstablecimiento == 32) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento += 1;
                                                    }
                                                    break;
                                                case 12:
                                                    if (diaFinEstablecimiento == 33) {
                                                        diaFinEstablecimiento = 1;
                                                        mesFinEstablecimiento = 1;
                                                        anioDelPago += 1;
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    if (minDelPago == 0 && cantidadIn <= 0.8 && cantidadIn >= 0.6) {
                                        horaFinEstablecimiento = 9;
                                        minFinEstablecimiento = minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    } else if (minDelPago != 0 && cantidadIn <= 0.8 && cantidadIn >= 0.6) {
                                        horaFinEstablecimiento = 9;
                                        int minPagoaux = minDelPago;
                                        minPagoaux += minutosPagados;
                                        if (minPagoaux >= 60) {
                                            horaFinEstablecimiento++;
                                            minFinEstablecimiento = minPagoaux - 60;
                                        } else {
                                            minFinEstablecimiento = minDelPago + minutosPagados;
                                        }
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    //Todo CASO DE: 2h.
                                    if (minDelPago == 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 10;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }

                                    } else if (minDelPago != 0 && cantidadIn >= 0.8) {
                                        horaFinEstablecimiento = 10;
                                        minFinEstablecimiento = minDelPago + minutosPagados;
                                        diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 2);
                                        mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                                        switch (d.get(Calendar.MONTH) + 1) {
                                            case 1, 3, 5, 7, 8, 10:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 2, 4, 6, 9, 11:
                                                if (diaFinEstablecimiento == 32) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento += 1;
                                                }
                                                break;
                                            case 12:
                                                if (diaFinEstablecimiento == 33) {
                                                    diaFinEstablecimiento = 1;
                                                    mesFinEstablecimiento = 1;
                                                    anioDelPago += 1;
                                                }
                                                break;
                                        }
                                    }
                                    break;
                            }
                        } else {
                            horaFinEstablecimiento = 9 + horasPagados;
                            minFinEstablecimiento = minutosPagados;
                            diaFinEstablecimiento = (d.get(Calendar.DAY_OF_MONTH) + 1);
                            mesFinEstablecimiento = (d.get(Calendar.MONTH) + 1);
                            switch (d.get(Calendar.MONTH) + 1) {
                                case 1, 3, 5, 7, 8, 10, 12:
                                    if (diaFinEstablecimiento == 32) {
                                        diaFinEstablecimiento = 1;
                                        mesFinEstablecimiento += 1;
                                    }
                                    break;
                                case 2, 4, 6, 9, 11:
                                    if (diaFinEstablecimiento == 31) {
                                        diaFinEstablecimiento = 1;
                                        mesFinEstablecimiento += 1;
                                    }
                                    break;
                            }
                        }
                        //Todo IMPRESION DEL TICKET.
                        delay("CARGANDO TICKET");
                        System.out.println();
                        System.out.println(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" +
                                "|COD.TICKET                                                             |");
                        System.out.printf("| %s                     FIN DE ESTACIONAMIENTO: %02d : %02d          |", numeroTicket, horaFinEstablecimiento, minFinEstablecimiento);
                        System.out.println();
                        System.out.println("|                                                                       |");
                        System.out.printf("|YEAR %4d                     DIA-MES] %02d / %02d                         |", anioDelPago, diaFinEstablecimiento, mesFinEstablecimiento);
                        System.out.println();
                        System.out.println("|                             IMPORTE PAGADO]" + cantidadIn + "EUR                     |\n" +
                                "|                                                                       |");
                        System.out.printf("!       %02d/%02d/%02d      %02d:%02d                                           !", d.get(Calendar.DAY_OF_MONTH), (d.get(Calendar.MONTH) + 1), d.get(Calendar.YEAR), horaDelPago, minDelPago);
                        System.out.println();
                        System.out.println(":                                   EXCELENTISIMO AYUNTAMIENTO DE MARTOS:\n" +
                                "|                                                                       |\n" +
                                "`-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-'");
                        System.out.println("PULSE UNA LETRA PARA CONTINUAR.");
                        s.nextLine();

                    }


                    break;
                case 2:
                    System.out.println("""
                            1.ANULAR MULTA POR EXCEDER TIEMPO PERMITIDO.
                            2.ANULAR MULTA POR CARECER DE TICKET.
                            """);
                    System.out.print("INTRODUCE TU OPCION: ");
                    opcionMultas = Integer.parseInt(s.nextLine());

                    switch (opcionMultas) {
                        case 1:
                            //Todo (GENERADOR DEL CODIGO MULTA CON NUMERO ADICIONAL EN EL ULTIMO DIGITO SI ESTA DENTRO DE CADA INTERVALO)

                            codigoMulta = ((int) (Math.random() * (999 - 561 + 1) + 556));
                            if ((codigoMulta <= 999) && (codigoMulta >= 889)) numeroMulta = codigoMulta + "-1";
                            if ((codigoMulta <= 888) && (codigoMulta >= 778)) numeroMulta = codigoMulta + "-2";
                            if ((codigoMulta <= 777) && (codigoMulta >= 667)) numeroMulta = codigoMulta + "-3";
                            if ((codigoMulta <= 666) && (codigoMulta >= 556)) numeroMulta = codigoMulta + "-4";

                            do {
                                System.out.print("INTRODUCE EL NÚMERO DE TICKET: ");
                                numeroTicket2 = s.nextLine();

                                //Todo VERIFICACION DE QUE EL CODIGO DEL TICKET SEA VÁLIDO.
                                int posicionCaracter = 0;
                                for (int i = 0; i < numeroTicket2.length(); i++) {
                                    if (numeroTicket.charAt(i) == '-') {
                                        posicionCaracter = i;
                                        break;
                                    }
                                }
                                parte1 = numeroTicket2.substring(0, posicionCaracter);
                                caracter = numeroTicket2.charAt(posicionCaracter);
                                parte2 = numeroTicket2.substring(posicionCaracter + 1);
                            } while ((Integer.parseInt(parte1) < 1 || Integer.parseInt(parte1) > 1000000) || Integer.parseInt(parte2) >= cantidadTickets || caracter != '-');

                            //Todo UNA VEZ VERIFICADO QUE EL CODIGO DE LA MULTA ES CORRECTO SE PROCEDE A PEDIR EL PAGO DE LA MULTA.
                            do {
                                System.out.print("INTRODUCE EL DINERO (3.00 EUR): ");
                                dineroMulta1 = Double.parseDouble(s.nextLine());
                                totalMultas += dineroMulta1;

                            } while (dineroMulta1 < 3);
                            multasAnuladas++;

                            //Todo IMPRESION DEL TICKET DE MULTA OPCION 1.
                            delay("PROCESANDO PAGO");
                            System.out.println();
                            System.out.println("=======================================================");
                            System.out.println("| N-TICKET: " + numeroTicket2 + "             " + d.get(Calendar.DAY_OF_MONTH) + "/" + (d.get(Calendar.MONTH) + 1) + "/" + d.get(Calendar.YEAR) + "           |");
                            System.out.println("|                                                     |");
                            System.out.printf("|                                      %02d:%02d          |", d.get(Calendar.HOUR_OF_DAY), d.get(Calendar.MINUTE));
                            System.out.println();
                            System.out.println("| N-MULTA: " + codigoMulta + numeroMulta + "                                   |");
                            System.out.println("|                                                     |");
                            System.out.println("| IMPORTE PAGADO: " + dineroMulta1 + " EUR" + "                             |");
                            System.out.println("|                                                     |");
                            System.out.println("|       ----- MULTA PAGADA CORRECTAMENTE -----        |");
                            System.out.println("|                                                     |");
                            System.out.println("|              AYUNTAMIENTO DE MARTOS                 |");
                            System.out.println("=======================================================");
                            System.out.println("PULSE UNA TECLA PARA CONTINUAR...");
                            s.nextLine();
                            break;
                        case 2:

                            //Todo (GENERADOR DEL CODIGO MULTA CON NUMERO ADICIONAL EN EL ULTIMO DIGITO SI ESTA DENTRO DE CADA INTERVALO)
                            codigoMulta2 = ((int) (Math.random() * (999 - 561 + 1) + 556));

                            if ((codigoMulta2 <= 999) && (codigoMulta2 >= 889)) numeroMulta = codigoMulta2 + "-1";
                            if ((codigoMulta2 <= 888) && (codigoMulta2 >= 778)) numeroMulta = codigoMulta2 + "-2";
                            if ((codigoMulta2 <= 777) && (codigoMulta2 >= 667)) numeroMulta = codigoMulta2 + "-3";
                            if ((codigoMulta2 <= 666) && (codigoMulta2 >= 556)) numeroMulta = codigoMulta2 + "-4";


                            do {

                                System.out.print("INTRODUZCA EL DINERO DE LA MULTA (6,00 EUR): ");
                                dineroMulta2 = Double.parseDouble(s.nextLine());
                                totalMultas += dineroMulta2;

                            } while (dineroMulta2 < 6);
                            multasAnuladas++;
                            delay("PROCESANDO PAGO");
                            System.out.println();
                            System.out.println("=======================================================");
                            System.out.println("| N-TICKET: " + numeroTicket + "                      " + d.get(Calendar.DAY_OF_MONTH) + "/" + (d.get(Calendar.MONTH) + 1) + "/" + d.get(Calendar.YEAR) + "          |");
                            System.out.println("|                                                     |");
                            System.out.printf("|                                      %02d:%02d          |", d.get(Calendar.HOUR_OF_DAY), d.get(Calendar.MINUTE));
                            System.out.println();
                            System.out.println("| N-MULTA: " + codigoMulta2 + numeroMulta + "                                   |");
                            System.out.println("|                                                     |");
                            System.out.println("| IMPORTE PAGADO: " + dineroMulta2 + " EUR" + "                             |");
                            System.out.println("|                                                     |");
                            System.out.println("|       ----- MULTA PAGADA EXITOSAMENTE -----         |");
                            System.out.println("|                                                     |");
                            System.out.println("|           EXCMO. AYUNTAMIENTO DE MARTOS             |");
                            System.out.println("=======================================================");
                            System.out.println("PULSE UNA TECLA PARA CONTINUAR...");
                            s.nextLine();
                        default:
                            System.out.println("OPCION INCORRECTA.");
                    }

                    break;

                case 3:
                    delay("CARGANDO MENU");
                    System.out.println();
                    System.out.println("1. FECHA/HORA ACTUAL ");
                    System.out.println(d.get(Calendar.DAY_OF_MONTH) + "/" + (d.get(Calendar.MONTH) + 1) + "/" + d.get(Calendar.YEAR));
                    System.out.printf("%02d:%02d", d.get(Calendar.HOUR_OF_DAY), d.get(Calendar.MINUTE));
                    System.out.println();
                    System.out.println("2.TARIFAS");
                    System.out.println("""
                                                        
                             DURACION             NO RESIDENTES            RESIDENTES
                                                        
                            00:15:00 H              0,25 EUR                0,10 EUR
                            00:29:50 H              0,45 EUR                0,20 EUR
                            00:59:31 H              0,85 EUR                0,40 EUR
                            01:29:12 H              1,25 EUR                0,60 EUR
                            02:00:00 H              1,70 EUR                0,80 EUR
                            """);
                    System.out.println("3.HORARIO");
                    System.out.println("""
                            LUNES A VIERNES
                            09.00 H - 14.00 H
                                                        
                            SABADO
                            09.00 H - 14.00 H
                            """);
                    System.out.print("PULSE ENTER PARA CONTINUAR...");
                    s.nextLine();
                    break;
                case 4:
                    int intentosLog = 3;
                    String usuarioIn;
                    String passIn;
                    do {
                        System.out.print("INTRODUCE TUS USUARIO ADMINISTRADOR: ");
                        usuarioIn = s.nextLine();
                        System.out.print("INTRODUCE LA CONTRASENIA: ");
                        passIn = s.nextLine();
                        if (!usuarioIn.equals(usuarioAdmin) || !passIn.equals(passAdmin)) {
                            System.out.println("USUARIO O CONTRASENIA INCORRECTA," + " LE QUEDAN " + intentosLog + " INTENTOS");
                            intentosLog--;
                        }
                    } while ((!usuarioIn.equals(usuarioAdmin) || !passIn.equals(passAdmin)) && intentosLog > 0);

                    if (intentosLog == 0) {
                        delayBloqueo("LA CUENTA HA SIDO VOLVER A INTENTARLO EN...");
                    } else {
                        System.out.println("BIENVENIDO AL MENU ADMINISTRADOR " + usuarioAdmin);
                        System.out.println();
                        System.out.println("1. CONSULTAR EL DINERO INGRESADO EN TICKET Y TICKET GENERADOS. ");
                        System.out.println();
                        System.out.println("2. CONSULTAR EL DINERO INGRESADO POR MULTAS Y LAS MULTAS ANULADAS. ");
                        System.out.println();
                        System.out.println("3. APAGAR PROGRAMA. ");
                        System.out.println("INTRODUZCA SU OPCION: ");
                        opcionAdmin = Integer.parseInt(s.nextLine());

                        switch (opcionAdmin) {
                            case 1:
                                System.out.printf("EL DINERO GENERADO EN TICKETS HA SIDO: %.2f EUR", dineroTotalTickets);
                                System.out.println();
                                System.out.println("CANTIDAD DE TICKETS GENERADOS SON: " + cantidadTickets + " TICKETS");
                                System.out.println();
                                System.out.println("PULSE UNA TECLA PARA CONTINUAR...");
                                s.nextLine();
                                break;
                            case 2:
                                System.out.println("EL DINERO GENERADO POR MULTAS HA SIDO: " + totalMultas);
                                System.out.println();
                                System.out.println("EL TOTAL DE MULTAS ANULADAS HAN SIDO" + multasAnuladas);
                                System.out.println();
                                System.out.println("PULSE UNA TECLA PARA CONTINUAR...");
                                s.nextLine();
                                break;
                            case 3:
                                System.out.println("GRACIAS POR USAR NUESTRO PROGRAMA ");
                                delay("APAGANDO PROGRAMA");
                                continue;
                            default:
                                System.out.println("DEBE INTRODUCIR UNA DE LAS 3 OPCIONES.");
                        }
                    }
                    break;
                default:
                    System.out.println("OPCIÓN INCORRECTA.");
            }
        } while (opcionAdmin != 3);

    }

    //Todo FUNCIONES DE DELAY.
    public static void delay(String mensaje) {
        System.out.print(mensaje);
        for (int i = 0; i < 4; i++) {
            System.out.print(".");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }

    public static void delayBloqueo(String mensaje) {
        System.out.print(mensaje);
        for (int i = 5; i > 0; i--) {
            System.out.print(i + "  ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }

}