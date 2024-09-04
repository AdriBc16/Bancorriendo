package Pago;
import Usuario.Cliente;
import Usuario.Cuenta;
import java.util.*;

public class Servicios {
    public static Scanner teclado = new Scanner(System.in);
    public int nroM;
    public int monto;
    public String mes;
    public Servicios[] medidoresAgua = new Servicios[2];
    public Servicios[] medidoresLuz= new Servicios[2];
    public Servicios[] telecomunicaciones= new Servicios[2];
    public double colegiaturaPendiente = 7000;

    public Servicios(int nroM, int monto, String mes) {
        this.nroM = nroM;
        this.monto = monto;
        this.mes = mes;
    }


    public void deudasAgua(){
      medidoresAgua[0] = new Servicios(1212,150,"Agosto");
      medidoresAgua[1]= new Servicios(9876,200,"Julio");
    }
    public void deudasLuz(){
        medidoresLuz[0]= new Servicios(1212,400,"Agosto");
        medidoresLuz[1] = new Servicios(9876,300,"Julio");
    }

    public void deudasTelecomunicaciones(){
        telecomunicaciones[0] = new Servicios(77651338,400,"Prepago");
        telecomunicaciones[1]= new Servicios(76073152,300,"PostPago");
    }

    public void listarAgua(){
        for (int i = 0; i < medidoresAgua.length; i++) {
            if (medidoresAgua[i] != null) {
                System.out.println((i + 1) + ". " + medidoresAgua[i]);
            }
        }
    }

    public void listarLuz(){
        for (int i = 0; i < medidoresLuz.length; i++) {
            if (medidoresLuz[i] != null) {
                System.out.println((i + 1 ) + "." + medidoresLuz[i]);
            }
        }
    }

    public void listarTelecomunicaciones(){
        for (int i = 0; i < telecomunicaciones.length ; i++) {
            if(telecomunicaciones[i] != null){
                for (Servicios servicios : telecomunicaciones) {
                    System.out.println((i + 1 ) + "." + "NroTelefono : " + servicios.getNroM() + ", Monto: " + servicios.getMonto() + ", Tipo: " + servicios.getMes());
                }
            }
        }
    }


        public void pagarAgua(Cliente clientesC) {
            clientesC.listarCuentas();

            System.out.print("Ingrese el número de cuenta para pagar: ");
            int numeroCuenta = teclado.nextInt();
            teclado.nextLine();

            Cuenta cuentaSeleccionada = clientesC.getCuenta(numeroCuenta);
            if (cuentaSeleccionada != null) {
                System.out.println("Elija el medidor que desea pagar: ");
                listarAgua();

                int opcion = teclado.nextInt();
                teclado.nextLine();

                if (opcion >= 1 && opcion <= medidoresAgua.length && medidoresAgua[opcion - 1] != null) {
                    Servicios medidorSeleccionado = medidoresAgua[opcion - 1];
                    double montoAPagar = medidorSeleccionado.getMonto();

                    if (montoAPagar > 0) {
                        double saldoActual = cuentaSeleccionada.getSaldo();

                        if (saldoActual >= montoAPagar) {
                            cuentaSeleccionada.setSaldo(saldoActual - montoAPagar);
                            medidorSeleccionado.setMonto(0);
                            cuentaSeleccionada.agregarTransaccion("Pago de Agua - Medidor: " + medidorSeleccionado.getNroM() + " - Monto: " + montoAPagar + " " + cuentaSeleccionada.getMoneda());
                            listarAgua();
                            System.out.println("Pago realizado con éxito. Nuevo saldo: " + cuentaSeleccionada.getSaldo());
                            System.out.println("Monto del medidor después del pago: " + medidorSeleccionado.getMonto());
                        } else {
                            System.out.println("Saldo insuficiente para realizar el pago.");
                        }
                    } else {
                        System.out.println("La deuda ya ha sido pagada.");
                    }
                } else {
                    System.out.println("Opción de medidor inválida.");
                }
            } else {
                System.out.println("Número de cuenta no encontrado.");
            }
        }

    public void pagarLuz(Cliente clientesC) {
        clientesC.listarCuentas();

        System.out.print("Ingrese el número de cuenta para pagar: ");
        int numeroCuenta = teclado.nextInt();
        teclado.nextLine();

        Cuenta cuentaSeleccionada = clientesC.getCuenta(numeroCuenta);
        if (cuentaSeleccionada != null) {
            System.out.println("Elija el medidor que desea pagar: ");
            listarLuz();

            int opcion = teclado.nextInt();
            teclado.nextLine();

            if (opcion >= 1 && opcion <= medidoresLuz.length && medidoresLuz[opcion - 1] != null) {
                Servicios medidorSeleccionado = medidoresLuz[opcion - 1];
                double montoAPagar = medidorSeleccionado.getMonto();

                if (montoAPagar > 0) {
                    double saldoActual = cuentaSeleccionada.getSaldo();

                    if (saldoActual >= montoAPagar) {
                        cuentaSeleccionada.setSaldo(saldoActual - montoAPagar);
                        medidorSeleccionado.setMonto(0);
                        cuentaSeleccionada.agregarTransaccion("Pago de Luz - Medidor: " + medidorSeleccionado.getNroM() + " - Monto: " + montoAPagar + " " + cuentaSeleccionada.getMoneda());
                        System.out.println("Pago realizado con éxito. Nuevo saldo: " + cuentaSeleccionada.getSaldo());
                        System.out.println("Monto del medidor después del pago: " + medidorSeleccionado.getMonto());
                    } else {
                        System.out.println("Saldo insuficiente para realizar el pago.");
                    }
                } else {
                    System.out.println("La deuda ya ha sido pagada.");
                }
            } else {
                System.out.println("Opción de medidor inválida.");
            }
        } else {
            System.out.println("Número de cuenta no encontrado.");
        }
    }


        public void pagarColegiatura (Cliente clientesC) {
            clientesC.listarCuentas();
            System.out.print("Ingrese el número de cuenta para pagar: ");
            int numC = teclado.nextInt();
            teclado.nextLine();

            Cuenta cuentaSeleccionada = clientesC.getCuenta(numC);
            if (cuentaSeleccionada != null) {
                if (cuentaSeleccionada.getSaldo() > 0) {
                    cuentaSeleccionada.setSaldo(cuentaSeleccionada.getSaldo() - 7000);
                    colegiaturaPendiente = 0;
                    System.out.println(colegiaturaPendiente);
                    System.out.println("Pago realizado con éxito. Nuevo saldo: " + cuentaSeleccionada.getSaldo());
                    if (colegiaturaPendiente == 0) {
                        System.out.println("La deuda ya ha sido pagada.");
                    }
                } else {
                    System.out.println("Saldo insuficiente para realizar el pago.");
                }
            } else {
                System.out.println("Número de cuenta no encontrado.");
            }
        }

        public void pagarTelecomunicaciones(Cliente clientesC){
            clientesC.listarCuentas();

            System.out.print("Ingrese el número de cuenta para pagar: ");
            int numeroCuenta = teclado.nextInt();
            teclado.nextLine();

            Cuenta cuentaSeleccionada = clientesC.getCuenta(numeroCuenta);
            if (cuentaSeleccionada != null) {
                System.out.println("Elija el nro de telefono que desea pagar: ");
                listarTelecomunicaciones();

                int opcion = teclado.nextInt();
                teclado.nextLine();

                if (opcion >= 1 && opcion <= telecomunicaciones.length && telecomunicaciones[opcion - 1] != null) {
                    Servicios telefonoSeleccionado = telecomunicaciones[opcion - 1];
                    double montoAPagar = telefonoSeleccionado.getMonto();

                    if (montoAPagar > 0) {
                        double saldoActual = cuentaSeleccionada.getSaldo();

                        if (saldoActual >= montoAPagar) {
                            cuentaSeleccionada.setSaldo(saldoActual - montoAPagar);
                            telefonoSeleccionado.setMonto(0);
                            cuentaSeleccionada.agregarTransaccion("Pago de Telecominicaciones - Telefono: " + telefonoSeleccionado.getNroM() + " - Monto: " + montoAPagar + " " + cuentaSeleccionada.getMoneda());
                            System.out.println("Pago realizado con éxito. Nuevo saldo: " + cuentaSeleccionada.getSaldo());
                            System.out.println("Monto del telefono después del pago: " + telefonoSeleccionado.getMonto());
                        } else {
                            System.out.println("Saldo insuficiente para realizar el pago.");
                        }
                    } else {
                        System.out.println("La deuda ya ha sido pagada.");
                    }
                } else {
                    System.out.println("Opción de nro de telefono inválida.");
                }
            } else {
                System.out.println("Número de cuenta no encontrado.");
            }
        }


    @Override
    public String toString() {
        return "Servicios: " +
                "NroMedidor=" + nroM +
                ", Monto=" + monto +
                ", Mes='" + mes ;
    }

    public int getNroM() {
        return nroM;
    }


    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMes() {
        return mes;
    }

}

