package Pago;

import Usuario.Cliente;
import Usuario.Cuenta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Transacciones {
    static Scanner teclado = new Scanner(System.in);
    Cliente cl = new Cliente(null, 0);

    public static void abonar(Cuenta cuenta, double monto) {
        if (monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);

            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            String transaccion = "Abono de " + monto + " " + cuenta.getMoneda() + " el " + fecha;
            cuenta.agregarTransaccion(transaccion);

            System.out.println("Bot: Se han abonado " + monto + " " + cuenta.getMoneda() + " a la cuenta " + cuenta.getNumCuenta());
        } else {
            System.out.println("Error: El monto a abonar debe ser mayor a 0.");
        }

    }

    public static void debitar(Cuenta cuenta, double monto) {
        if (monto > 0 && cuenta.getSaldo() >= monto) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);

            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            String transaccion = "Debito de " + monto + " " + cuenta.getMoneda() + " el " + fecha;
            cuenta.agregarTransaccion(transaccion);

            System.out.println("Bot: Se han debitado " + monto + " " + cuenta.getMoneda() + " de la cuenta " + cuenta.getNumCuenta());
        } else if (monto <= 0) {
            System.out.println("Error: El monto a debitar debe ser mayor a 0.");
        } else {
            System.out.println("Error: Saldo insuficiente.");
        }
    }

}

