package Pago;
import Usuario.Cliente;
import Usuario.Cuenta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Transacciones {
    public Scanner teclado = new Scanner(System.in);

    public void abonarPrincipal(Cliente clienteActual) {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("No tienes cuentas para abonar dinero.");
            return;
        }

        System.out.println("Seleccione la cuenta en la que desea abonar dinero:");
        clienteActual.listarCuentas();
        Cuenta cuentaSeleccionada = null;
        while (cuentaSeleccionada == null) {
            try {
                System.out.print("Ingrese el número de cuenta: ");
                int numCuenta = teclado.nextInt();
                teclado.nextLine();
                cuentaSeleccionada = clienteActual.buscarCuentaPorNumero(numCuenta);
                if (cuentaSeleccionada == null) {
                    System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                teclado.nextLine();
            }
        }

        double monto = 0;
        boolean montoValido = false;
        while (!montoValido) {
            try {
                System.out.print("Ingrese el monto a abonar: ");
                monto = teclado.nextDouble();
                teclado.nextLine();
                if (monto <= 0) {
                    System.out.println("Error: El monto debe ser mayor a 0.");
                } else {
                    montoValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un monto válido.");
                teclado.nextLine();
            }
        }

        abonar(cuentaSeleccionada, monto);
    }
    public void abonar(Cuenta cuenta, double monto) {
        if (monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);

            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            String transaccion = "Abono de " + monto + " " + cuenta.getMoneda() + " el " + fecha;
            cuenta.agregarTransaccion(transaccion);

            System.out.println("Se han abonado " + monto + " " + cuenta.getMoneda() + " a la cuenta " + cuenta.getNumCuenta());
        } else {
            System.out.println("Error: El monto a abonar debe ser mayor a 0.");
        }

    }

    public  void debitarPrincipal(Cliente clienteActual) {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("No tienes cuentas para debitar dinero.");
            return;
        }

        System.out.println("Seleccione la cuenta de la cual desea debitar dinero:");
        clienteActual.listarCuentas();
        Cuenta cuentaSeleccionada = null;
        while (cuentaSeleccionada == null) {
            try {
                System.out.print("Ingrese el número de cuenta: ");
                int numCuenta = teclado.nextInt();
                teclado.nextLine();
                cuentaSeleccionada = clienteActual.buscarCuentaPorNumero(numCuenta);
                if (cuentaSeleccionada == null) {
                    System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                teclado.nextLine();
            }
        }

        double monto = 0;
        boolean montoValido = false;
        while (!montoValido) {
            try {
                System.out.print("Bot: Ingrese el monto a debitar: ");
                monto = teclado.nextDouble();
                teclado.nextLine();
                if (monto <= 0) {
                    System.out.println("Error: El monto debe ser mayor a 0.");
                } else if (cuentaSeleccionada.getSaldo() < monto) {
                    System.out.println("Error: Saldo insuficiente en la cuenta.");
                } else {
                    montoValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un monto válido.");
                teclado.nextLine();
            }
        }

        debitar(cuentaSeleccionada, monto);
    }

    public void debitar(Cuenta cuenta, double monto) {
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

    public void verExtractoTransacciones(Cliente clienteActual) {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("No tienes cuentas para ver el extracto.");
            return;
        }

        System.out.println("Seleccione la cuenta para ver el extracto:");
        clienteActual.listarCuentas();
        Cuenta cuentaSeleccionada = null;
        while (cuentaSeleccionada == null) {
            try {
                System.out.print("Ingrese el número de cuenta: ");
                int numCuenta = teclado.nextInt();
                teclado.nextLine();
                cuentaSeleccionada = clienteActual.buscarCuentaPorNumero(numCuenta);
                if (cuentaSeleccionada == null) {
                    System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                teclado.nextLine();
            }
        }

        cuentaSeleccionada.mostrarTransacciones();
    }


}

