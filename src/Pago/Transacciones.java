package Pago;

import Usuario.Cliente;
import Usuario.Cuenta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Transacciones {
    Scanner teclado = new Scanner(System.in);
    Cliente cl = new Cliente(null,0);

    public void abonar(Cuenta cuentaA) {
        System.out.println("Seleccione la cuenta a la que desea abonar:");
        cuentaA.listarCuenta(cl);
        System.out.print("Ingrese el número de cuenta: ");
        int numCuenta = teclado.nextInt();

        Cuenta cuentaSeleccionada = null;
        for (Cuenta cuenta : cuentaA.cuentas) {
            if (cuenta.numCuenta == numCuenta) {
                cuentaSeleccionada = cuenta;
                break;
            }
        }

        if (cuentaSeleccionada != null) {
            System.out.print("Ingrese el monto a abonar: ");
            double monto = teclado.nextDouble();
            cuentaSeleccionada.saldo += monto;

            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            String transaccion = "Abono de " + monto + " " + cuentaSeleccionada.moneda + " el " + fecha;
            cuentaSeleccionada.agregarTransaccion(transaccion);

            System.out.println("Se han abonado " + monto + " " + cuentaSeleccionada.moneda + " a la cuenta " + numCuenta);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void debitar(Cuenta cuentaD) {
        System.out.println("Seleccione la cuenta de la que desea debitar:");
        cuentaD.listarCuenta(cl);
        System.out.print("Ingrese el número de cuenta: ");
        int numCuenta = teclado.nextInt();

        Cuenta cuentaSeleccionada = null;
        for (Cuenta cuenta : cuentaD.cuentas) {
            if (cuenta.numCuenta == numCuenta) {
                cuentaSeleccionada = cuenta;
                break;
            }
        }

        if (cuentaSeleccionada != null) {
            System.out.print("Ingrese el monto a debitar: ");
            double monto = teclado.nextDouble();
            if (cuentaSeleccionada.saldo >= monto) {
                cuentaSeleccionada.saldo -= monto;

                String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                String transaccion = "Debito de " + monto + " " + cuentaSeleccionada.moneda + " el " + fecha;
                cuentaSeleccionada.agregarTransaccion(transaccion);

                System.out.println("Se han debitado " + monto + " " + cuentaSeleccionada.moneda + " de la cuenta " + numCuenta);
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    public void extracto(Cuenta cuentaE) {
        System.out.println("Seleccione la cuenta para ver el extracto:");
        cuentaE.listarCuenta(cl);
        System.out.print("Ingrese el número de cuenta: ");
        int numCuenta = teclado.nextInt();

        Cuenta cuentaSeleccionada = null;
        for (Cuenta cuenta : cuentaE.cuentas) {
            if (cuenta.numCuenta == numCuenta) {
                cuentaSeleccionada = cuenta;
                break;
            }
        }

        if (cuentaSeleccionada != null) {
            cuentaSeleccionada.mostrarTransacciones();

            System.out.println("Saldo actual de la cuenta " + numCuenta + ": " + cuentaSeleccionada.saldo + " " + cuentaSeleccionada.moneda);
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
}