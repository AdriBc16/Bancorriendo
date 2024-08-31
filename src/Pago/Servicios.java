package Pago;

import Usuario.Cliente;
import Usuario.Cuenta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Servicios {
    // Atributos
    public double colegiatura = 7000;

    // Arreglos
    Scanner teclado = new Scanner(System.in);
    Cliente client = new Cliente(null, 0);

    // Controlar excepciones.
    public void calculoServicio(Cuenta cuentaCreada, int montoPendiente) {
        System.out.println("Selecciona el numero de cuenta: ");
        cuentaCreada.listarCuenta(client);

        Cuenta cuentaSelec = null;
        System.out.print("Ingrese el numero de la cuenta ");
        int num = teclado.nextInt();
        for (Cuenta cuenta : cuentaCreada.cuentas) {
            if (cuenta.numCuenta == num) {
                System.out.println(num);
                cuentaSelec = cuenta;
                break;
            }
        }

        if (cuentaSelec != null) {
            if (cuentaSelec.saldo >= montoPendiente) {
                cuentaSelec.saldo -= montoPendiente;

                String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                String transaccion = "Debito de " + montoPendiente + " " + cuentaCreada.moneda + " el " + fecha;
                cuentaCreada.agregarTransaccion(transaccion);

                System.out.println("Se han debitado " + montoPendiente + " " + cuentaCreada.moneda + " a la cuenta " + num);
            } else {
                System.out.println("Cuenta no encontrada.");
            }
        }

    }
}

