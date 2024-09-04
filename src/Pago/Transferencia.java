package Pago;
import Usuario.Beneficiario;
import Usuario.Cliente;
import Usuario.Cuenta;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Transferencia {
    public Scanner teclado = new Scanner(System.in);
    public void transferir(Cuenta origen, Cuenta destino, double monto) {
        if (!origen.getMoneda().equals(destino.getMoneda())) {
            System.out.println("Error: La transferencia solo se permite entre cuentas con la misma moneda.");
            return;
        }

        if (monto <= 0) {
            System.out.println("Error: El monto a transferir debe ser mayor a 0.");
            return;
        }

        if (origen.getSaldo() < monto) {
            System.out.println("Error: Saldo insuficiente en la cuenta de origen.");
            return;
        }


        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        String transaccion = "Transferencia de " + monto + " " + origen.getMoneda() +
                " de la cuenta " + origen.getNumCuenta() +
                " a la cuenta " + destino.getNumCuenta();
        origen.agregarTransaccion(transaccion);
        destino.agregarTransaccion(transaccion);

        System.out.println("Bot: Transferencia realizada con éxito de " + monto + " " +
                origen.getMoneda() + " desde la cuenta " + origen.getNumCuenta() +
                " a la cuenta " + destino.getNumCuenta());
    }

    public void transferirBeneficiario(Cuenta origen, Beneficiario beneficiario, double monto) {
        try {

            if (monto > 0 && origen.getSaldo() >= monto) {
                origen.setSaldo(origen.getSaldo() - monto);

                String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                String transaccion = "Transferencia de " + monto + " " + origen.getMoneda() + " a " + beneficiario.getNombre() + " (Cuenta: " + beneficiario.getNumCuenta() + ") el " + fecha;
                origen.agregarTransaccion(transaccion);


                System.out.println("Se han transferido " + monto + " " + origen.getMoneda() + " a " + beneficiario.getNombre() + " (Cuenta: " + beneficiario.getNumCuenta() + ")");
            } else if (monto <= 0) {
                System.out.println("Error: El monto a transferir debe ser mayor a 0.");
            } else {
                System.out.println("Error: Saldo insuficiente.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ocurrió un problema al realizar la transferencia. Por favor, intente nuevamente.");
        }
    }
    public void transferirBeneficiarioPrincipal(Cliente clienteActual) {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("No tienes cuentas para realizar una transferencia.");
            return;
        }

        System.out.println("Seleccione la cuenta de origen:");
        clienteActual.listarCuentas();
        Cuenta cuentaOrigen = null;
        while (cuentaOrigen == null) {
            try {
                System.out.print("Bot: Ingrese el número de cuenta de origen: ");
                int numCuentaOrigen = teclado.nextInt();
                teclado.nextLine();
                cuentaOrigen = clienteActual.buscarCuentaPorNumero(numCuentaOrigen);
                if (cuentaOrigen == null) {
                    System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                teclado.nextLine();
            }
        }

        if (clienteActual.getBeneficiarios().isEmpty()) {
            System.out.println("No tienes beneficiarios registrados.");
            return;
        }

        System.out.println("Seleccione un beneficiario:");
        Beneficiario.listarBeneficiarios(clienteActual);
        Beneficiario beneficiarioSeleccionado = null;
        while (beneficiarioSeleccionado == null) {
            try {
                System.out.print("Ingrese el número de cuenta del beneficiario: ");
                int numCuentaBeneficiario = teclado.nextInt();
                teclado.nextLine();
                beneficiarioSeleccionado = Beneficiario.buscarBeneficiarioPorNumero(clienteActual, numCuentaBeneficiario);
                if (beneficiarioSeleccionado == null) {
                    System.out.println("Error: Beneficiario no encontrado. Intente nuevamente.");
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
                System.out.print("Ingrese el monto a transferir: ");
                monto = teclado.nextDouble();
                teclado.nextLine();
                if (monto <= 0) {
                    System.out.println("Error: El monto debe ser mayor a 0.");
                } else if (cuentaOrigen.getSaldo() < monto) {
                    System.out.println("Error: Saldo insuficiente en la cuenta de origen.");
                } else {
                    montoValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un monto válido.");
                teclado.nextLine();
            }
        }


        transferirBeneficiario(cuentaOrigen, beneficiarioSeleccionado, monto);
    }

    public void transferirEntreCuentas(Cliente clienteActual) {
        if (clienteActual.getCuentas().size() < 2) {
            System.out.println("Necesitas al menos dos cuentas para realizar una transferencia.");
            return;
        }

        Cuenta cuentaOrigen = null;
        while (cuentaOrigen == null) {
            try {
                System.out.println("Seleccione la cuenta de origen:");
                clienteActual.listarCuentas();

                System.out.print("Ingrese el número de cuenta de origen: ");
                int numCuentaOrigen = teclado.nextInt();
                cuentaOrigen = clienteActual.buscarCuentaPorNumero(numCuentaOrigen);
                if (cuentaOrigen == null) {
                    System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
            }
            System.out.println("Seleccione la cuenta de destino:");
            Cuenta cuentaDestino = null;
            while (cuentaDestino == null) {
                try {
                    System.out.print("Ingrese el número de cuenta de destino: ");
                    int numCuentaDestino = teclado.nextInt();
                    teclado.nextLine();
                    cuentaDestino = clienteActual.buscarCuentaPorNumero(numCuentaDestino);
                    if (cuentaDestino == null) {
                        System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                    } else if (cuentaDestino.equals(cuentaOrigen)) {
                        System.out.println("Error: La cuenta de destino no puede ser la misma que la cuenta de origen.");
                        cuentaDestino = null;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                    teclado.nextLine();
                }
            }

            double montoEntreC = 0;
            boolean montoValido = false;
            while (!montoValido) {
                try {
                    System.out.print("Ingrese el monto a transferir: ");
                    montoEntreC = teclado.nextDouble();
                    teclado.nextLine();
                    if (montoEntreC <= 0) {
                        System.out.println("Error: El monto debe ser mayor a 0.");
                    } else if (cuentaOrigen.getSaldo() < montoEntreC) {
                        System.out.println("Error: Saldo insuficiente en la cuenta de origen.");
                    } else {
                        montoValido = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no válida, por favor ingrese un monto válido.");
                    teclado.nextLine();
                }
            }

            transferir(cuentaOrigen, cuentaDestino, montoEntreC);
        }
    }

    public void transferenciaATerceros(Cliente clienteActual) {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("No tienes cuentas para realizar una transferencia.");
            return;
        }

        System.out.println("Seleccione la cuenta de origen:");
        clienteActual.listarCuentas();

        Cuenta cuentaOrigen = null;
        while (cuentaOrigen == null) {
            try {
                System.out.print("Ingrese el número de cuenta de origen: ");
                int numCuentaOrigen = teclado.nextInt();
                teclado.nextLine();
                cuentaOrigen = clienteActual.buscarCuentaPorNumero(numCuentaOrigen);
                if (cuentaOrigen == null) {
                    System.out.println("Error: Cuenta no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                teclado.nextLine();
            }
        }

        System.out.println("Seleccione el cliente destino:");
        Cliente.listarClientes();

        Cliente clienteDestino = null;
        while(clienteDestino == null) {
            try {
                System.out.print("Ingrese el PIN del cliente destino: ");
                int pinClienteDestino = teclado.nextInt();
                teclado.nextLine();
                clienteDestino = Cliente.buscarClientePorPin(pinClienteDestino);

                if (clienteDestino == null) {
                    System.out.println("Error: Cliente destino no encontrado. Intente nuevamente.");
                } else if (clienteDestino.equals(clienteActual)) {
                    System.out.println("Error: No puedes seleccionar una cuenta propia como destino.");
                    clienteDestino = null;
                }
            } catch (NullPointerException e) {
                System.out.println("Error: Intentelo denuevo.");
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un PIN válido.");
                teclado.nextLine();
            }
        }


        if (clienteDestino.getCuentas().isEmpty()) {
            System.out.println("No tienes cuentas para realizar una transferencia.");
            return;
        }

        System.out.println("Seleccione la cuenta de destino:");
        clienteDestino.listarCuentas();

        Cuenta cuentaDestino = null;
        while (cuentaDestino == null) {
            try {
                System.out.print("Ingrese el número de cuenta de destino: ");
                int numCuentaDestino = teclado.nextInt();
                teclado.nextLine();
                cuentaDestino = clienteDestino.buscarCuentaPorNumero(numCuentaDestino);
                if (cuentaDestino == null) {
                    System.out.println("Error: Cuenta destino no encontrada. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un número de cuenta válido.");
                teclado.nextLine();
            }
        }

        double montoBene = 0;
        boolean montoValido = false;
        while (!montoValido) {
            try {
                System.out.print("Ingrese el monto a transferir: ");
                montoBene= teclado.nextDouble();
                teclado.nextLine();
                if (montoBene <= 0) {
                    System.out.println("Error: El monto debe ser mayor a 0.");
                } else if (cuentaOrigen.getSaldo() < montoBene) {
                    System.out.println("Error: Saldo insuficiente en la cuenta de origen.");
                } else {
                    montoValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor ingrese un monto válido.");
                teclado.nextLine();
            }
        }


        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - montoBene);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + montoBene);

        System.out.println("Transferencia realizada con éxito.");
        System.out.println("Nuevo saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
    }

}


