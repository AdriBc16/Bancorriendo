package Ejecucion;
import Pago.Servicios;
import Usuario.Beneficiario;
import Usuario.Cliente;
import Usuario.Cuenta;
import Pago.Transacciones;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private static Scanner teclado = new Scanner(System.in);
    private static Cliente clienteActual;
    private static Servicios servicio = new Servicios(0,0,"") ;
    private static Cuenta cuenta = new Cuenta(0,0,"");

    public static void main(String[] args) {
        Cliente.clientesPredefinidos();
        servicio.deudasAgua();
        servicio.deudasLuz();
        menuInicio();
    }

    public static void menuInicio(){

        boolean condicion = true;
        do {

            System.out.println("-----BIENVENIDO A BANCORRIENDO-----");
            System.out.println("|Bot: ¿Qué te gustaría hacer?     |");
            System.out.println("|1. Iniciar Sesión                |");
            System.out.println("|2. Registrar Usuario             |");
            System.out.println("|3. Listar Clientes               |");
            System.out.println("|4. Cerrar Programa               |");
            System.out.println("|_________________________________|");

            try {
                System.out.print("Bot: Seleccione una opción: ");
                int sesion = teclado.nextInt();


                switch (sesion) {
                    case 1:
                        iniciarSesion();
                        break;
                    case 2:
                        registrarCliente();
                        break;
                    case 3:
                        listarClientes();
                        break;
                    case 4:
                        condicion = false;
                        System.out.println("Bot: Hasta luego!");
                        break;
                    default:
                        System.out.println("Error: Por favor seleccione una opción correcta.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
                teclado.nextLine();
            }
        } while (condicion);

    }

    public static void iniciarSesion() {
        clienteActual = null;
        try {
            System.out.print("Bot: Por favor, ingrese su PIN: ");
            int pin = teclado.nextInt();

            clienteActual = Cliente.buscarClientePorPin(pin);
            if (clienteActual != null) {
                System.out.println("Bot: ¡BIENVENIDO " + clienteActual.getNombre() +  "!");
                menuCliente();
            } else {
                System.out.println("Error: Cliente no encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada de Dato no válida, por favor intente nuevamente.");
            teclado.nextLine();
        }
    }

    public static void registrarCliente() {
        Cliente nuevoCliente = Cliente.registrarCliente();
        if (nuevoCliente != null) {
            System.out.println("Bot: Cliente registrado exitosamente.");
        }
    }

    public static void listarClientes() {
        Cliente.listarClientes();
    }

    public static void menuCliente(){
        boolean condicion = true;

        do {
            System.out.println("\n--------Bot: ¿Qué te gustaría hacer?--------");
            System.out.println("|1. Crear Cuenta                           |");
            System.out.println("|2. Listar Cuentas                         |");
            System.out.println("|3. Transferir Dinero entre Cuentas Propias|");
            System.out.println("|4. Transferencia a Terceros               |");
            System.out.println("|5. Crear Beneficiario                     |");
            System.out.println("|6. Listar Beneficiarios                   |");
            System.out.println("|7. Transferir Dinero a Beneficiario       |");
            System.out.println("|8. Pagar Servicios                        |");
            System.out.println("|9. Abonar dinero                          |");
            System.out.println("|10. Debitar Dinero                        |");
            System.out.println("|11. Extracto                              |");
            System.out.println("|0. Cerrar Sesión                          |");
            System.out.println("|__________________________________________|");

            try {
                System.out.print("Bot: Seleccione una opción: ");
                int opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        clienteActual.crearCuenta();
                        break;
                    case 2:
                        clienteActual.listarCuentas();
                        break;
                    case 3:
                        //transferirEntreCuentas();
                        break;
                    case 4:
                        //transferenciaATerceros();
                        break;
                    case 5:
                       Beneficiario.crearBeneficiario(clienteActual);
                        break;
                    case 6:
                        Beneficiario.listarBeneficiarios(clienteActual);
                        break;
                    case 7:
                        //transferirDinero();
                        break;
                    case 8:
                        System.out.println("Bot: Seleccione que servicio desea pagar: ");
                        System.out.println("1. Agua");
                        System.out.println("2. Luz");
                        System.out.println("3. Telecomunicaciones");
                        System.out.println("4. Colegiatura");
                        int seleccion = teclado.nextInt();

                        switch (seleccion){
                            case 1:
                                servicio.pagarAgua(clienteActual);
                                break;
                            case 2:
                                servicio.pagarLuz(clienteActual);
                                break;
                            case 3:
                                break;
                                case 4:
                            servicio.pagarColegiatura(clienteActual);
                             break;
                }

                    case 9:
                        abonar();
                        break;
                    case 10:
                        debitar();
                        break;
                    case 11:
                        verExtracto();

                        break;
                    case 0:
                        condicion = false;
                        clienteActual = null;
                        System.out.println("Bot: Sesión cerrada.");
                        break;
                    default:
                        System.out.println("Error: Por favor seleccione una opción válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
                teclado.nextLine();
            }
        } while (condicion);
    }

    public static void verExtracto() {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("Bot: No tienes cuentas para ver el extracto.");
            return;
        }

        System.out.println("\nBot: Seleccione la cuenta para ver el extracto:");
        clienteActual.listarCuentas();
        Cuenta cuentaSeleccionada = null;
        while (cuentaSeleccionada == null) {
            try {
                System.out.print("Bot: Ingrese el número de cuenta: ");
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

    public static void abonar() {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("Bot: No tienes cuentas para abonar dinero.");
            return;
        }

        System.out.println("\nBot: Seleccione la cuenta en la que desea abonar dinero:");
        clienteActual.listarCuentas();
        Cuenta cuentaSeleccionada = null;
        while (cuentaSeleccionada == null) {
            try {
                System.out.print("Bot: Ingrese el número de cuenta: ");
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
                System.out.print("Bot: Ingrese el monto a abonar: ");
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

        Transacciones.abonar(cuentaSeleccionada, monto);
    }

    public static void debitar() {
        if (clienteActual.getCuentas().isEmpty()) {
            System.out.println("Bot: No tienes cuentas para debitar dinero.");
            return;
        }

        System.out.println("\nBot: Seleccione la cuenta de la cual desea debitar dinero:");
        clienteActual.listarCuentas();
        Cuenta cuentaSeleccionada = null;
        while (cuentaSeleccionada == null) {
            try {
                System.out.print("Bot: Ingrese el número de cuenta: ");
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

        Transacciones.debitar(cuentaSeleccionada, monto);
    }


}
