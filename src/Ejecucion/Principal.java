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
    public static void menuInicio(){

        System.out.println("PRUEBA");
        Scanner teclado = new Scanner(System.in);
        Cliente c = new Cliente(null, 0);
        boolean condicion = true;
        do {
            System.out.println("Bienvenido a Bancorriendo");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Cerrar Programa");
            int sesion = Integer.parseInt(teclado.next());
            switch(sesion) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    c.registrarCliente();
                    break;
                case 3:
                    condicion = false;
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Error, por favor elija una opcion correcta");
                    break;
            }
        } while (condicion);

    }

    public static void menuCliente(){
        boolean condicion = true;
        boolean pinValido = false;
        Scanner teclado = new Scanner(System.in);
        Cuenta ct = new Cuenta(0, 0, "bs");
        Cliente c = new Cliente(null, 0);
        Beneficiario b = new Beneficiario(null, 0, 0, null);
        Transacciones transacciones = new Transacciones();
        Servicios ser = new Servicios();
        int pin = 0;
        c.clientesPredefinidos();

        while (!pinValido) {
            System.out.print("Pin: ");
            try {
                pin = teclado.nextInt();
                if (!c.equals(pin)) {
                    System.out.println("Cliente registrado.");
                    pinValido = true;
                } else {
                    System.out.println("Pin encontrado.");
                    pinValido = true;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Error al ingresar el pin. Por favor, ingrese un número válido.");
                teclado.nextLine();
            }
        }
        do {

            System.out.println(" Bienvenid@ " + c.buscarNombre(c.getPin()) + " al menú Bancorriendo");
            System.out.println("| 1.Cuentas                                 |");
            System.out.println("| 2.Transferir dinero                       |");  // por ahora no
            System.out.println("| 3.Beneficiarios                           |");
            System.out.println("| 4.Pagar Servicio                          |");
            System.out.println("| 5.Abono o Debito                          |");
            System.out.println("| 6.Extracto                                |");
            System.out.println("| 0.Cerrar Sesion                           |");
            System.out.println("|___________________________________________|");

            try {
                System.out.println("Elija la opción: ");
                int seleccion = Integer.parseInt(teclado.next());

                switch (seleccion) {
                    case 1:
                        ct.registrarCuentas(c);
                        break;
                    case 2:

                        break;
                    case 3:
                        System.out.println("Seleccione la acción:");
                        System.out.println("1. Crear Beneficiario");
                        System.out.println("2. Lista Beneficiarios");
                        System.out.println("3. Transferir dinero"); // en proceso
                        int op4 = teclado.nextInt();

                        switch (op4) {
                            case 1:
                                b.crearBeneficiario();
                                break;
                            case 2:
                                b.listarBeneficiarios();
                                break;
                            case 3:
                                b.transferirDinero(null);
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                        break;
                    case 4:
                        System.out.println("Seleccione que servicio desea pagar: ");
                        System.out.println("1.Agua ");
                        System.out.println("2.Luz ");
                        System.out.println("3.Telecomunicaciones");
                        System.out.println("4.Colegiatura");
                        int servicio = teclado.nextInt();

                        switch (servicio) {
                            case 1:
                                ser.calculoServicio(ct,150);
                                break;
                            case 2:
                                ser.calculoServicio(ct,400);
                                break;
                            case 3:
                                ser.calculoServicio(ct,300);
                                break;
                            case 4:
                                ser.calculoServicio(ct,7000);
                                break;
                            default:
                                System.out.println("Opcion no encontrada.");
                                break;
                        }
                        break;
                    case 5:
                        System.out.println("Seleccione la acción:");
                        System.out.println("1. Abonar");
                        System.out.println("2. Debitar");
                        int op6 = teclado.nextInt();

                        switch (op6) {
                            case 1:
                                transacciones.abonar(ct);
                                break;
                            case 2:
                                transacciones.debitar(ct);
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                        break;
                    case 6:
                        transacciones.extracto(ct);
                        break;
                    case 0:
                        condicion = false;
                        System.out.println("¡Hasta Luego!");
                        break;
                    default:
                        System.out.println("Error: Por favor seleccione una opción válida.");
                        break;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Error: Opción no encontrada, inténtelo de nuevo.");
            }

        } while (condicion);
    }

    public static void main(String[] args) {
        menuInicio();
    }

}
