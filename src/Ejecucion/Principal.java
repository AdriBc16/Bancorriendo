package Ejecucion;
import Pago.Servicios;
import Pago.Transferencia;
import Usuario.Beneficiario;
import Usuario.Cliente;
import Pago.Transacciones;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private static final Scanner teclado = new Scanner(System.in);
    private static Cliente clienteActual;
    private static final Servicios servicio = new Servicios(0, 0, "");
    private static final Transacciones transacciones = new Transacciones();
    private static final Transferencia transferencia = new Transferencia();

    public static void main(String[] args) {
        Cliente.clientesPredefinidos();
        servicio.deudasAgua();
        servicio.deudasLuz();
        servicio.deudasTelecomunicaciones();
        menuInicio();
    }

    public static void menuInicio() {

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
                System.out.print("Seleccione una opción: ");
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
                        System.out.println("Hasta luego!");
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
            System.out.print("Por favor, Ingrese su PIN: ");
            int pin = teclado.nextInt();

            clienteActual = Cliente.buscarClientePorPin(pin);
            if (clienteActual != null) {
                System.out.println("Bot: ¡BIENVENIDO " + clienteActual.getNombre() + "!");
                menuCliente();
            } else {
                System.out.println("Error: Cliente no encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada de Dato no válida, por favor intente nuevamente.");
        }
    }

    public static void registrarCliente() {
        Cliente nuevoCliente = Cliente.registrarCliente();
        if (nuevoCliente != null) {
            System.out.println("Cliente registrado exitosamente.");
        }
    }

    public static void listarClientes() {
        Cliente.listarClientes();
    }

    public static void menuCliente() {
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
                System.out.print("Seleccione una opción: ");
                int opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        clienteActual.crearCuenta();
                        break;
                    case 2:
                        clienteActual.listarCuentas();
                        break;
                    case 3:
                        transferencia.transferirEntreCuentas(clienteActual);
                        break;
                    case 4:
                        transferencia.transferenciaATerceros(clienteActual);
                        break;
                    case 5:
                        Beneficiario.crearBeneficiario(clienteActual);
                        break;
                    case 6:
                        Beneficiario.listarBeneficiarios(clienteActual);
                        break;
                    case 7:
                        transferencia.transferirBeneficiarioPrincipal(clienteActual);
                        break;
                    case 8:
                        System.out.println("Seleccione que servicio desea pagar: ");
                        System.out.println("1. Agua");
                        System.out.println("2. Luz");
                        System.out.println("3. Telecomunicaciones");
                        System.out.println("4. Colegiatura");
                        int seleccion = teclado.nextInt();

                        switch (seleccion) {
                            case 1:
                                servicio.pagarAgua(clienteActual);
                                break;
                            case 2:
                                servicio.pagarLuz(clienteActual);
                                break;
                            case 3:
                                servicio.pagarTelecomunicaciones(clienteActual);
                                break;
                            case 4:
                                servicio.pagarColegiatura(clienteActual);
                                break;
                        }
                        break;
                    case 9:
                        transacciones.abonarPrincipal(clienteActual);
                        break;
                    case 10:
                        transacciones.debitarPrincipal(clienteActual);
                        break;
                    case 11:
                        transacciones.verExtractoTransacciones(clienteActual);
                        break;
                    case 0:
                        condicion = false;
                        clienteActual = null;
                        System.out.println("Sesión cerrada.");
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
}

