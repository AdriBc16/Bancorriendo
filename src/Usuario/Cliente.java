package Usuario;
import java.util.*;

public class Cliente {
    // atributos
    public  String nombre;
    public  int pin;
    public double saldo;
    String moneda = null;
    // listas
    public static List<Cliente> clientes = new ArrayList<>();
    public List<Cuenta> cuentas = new ArrayList<>();
    public List<Beneficiario> beneficiarios = new ArrayList<>();

    // Medidores especificos

    // Variables aparte
    public boolean validacionPin = false;
    public static Scanner teclado = new Scanner(System.in);

    //Constructor
    public Cliente(String nombre, int pin){
        this.nombre = nombre;
        this.pin = pin;
    }

    //Metodos
    public static void clientesPredefinidos(){
        clientes.add(new Cliente("ALEJANDRO", 1606));
        clientes.add(new Cliente("ROSE", 2051));
    }


    public static  Cliente buscarClientePorPin(int pin) {
           for (Cliente cliente : clientes) {
            if (cliente.getPin() == pin) {
                return cliente;
            }
        }
        return null;
    }

    public static Cliente registrarCliente() {
        try {
            System.out.print("Bot: ¿Cuál es tu nombre completo? ");
            String nombre = teclado.nextLine().toUpperCase();

            int pin = 0;
            boolean pinValido = false;

            while (!pinValido) {
                System.out.print("Bot: Ingrese un PIN de 4 dígitos: ");
                try {
                    pin = teclado.nextInt();
                    teclado.nextLine();
                    if (pin >= 1000 && pin <= 9999) {
                        if (buscarClientePorPin(pin) != null) {
                            System.out.println("Error: Este PIN ya está en uso. Intente con otro PIN.");
                        } else {
                            pinValido = true;
                        }
                    } else {
                        System.out.println("Error: El PIN debe ser de 4 dígitos.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no válida, por favor ingrese un número.");
                    teclado.nextLine();
                }
            }

            Cliente nuevoCliente = new Cliente(nombre, pin);
            clientes.add(nuevoCliente);
            return nuevoCliente;
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
            teclado.nextLine();
            return null;
        }
    }

    public static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Bot: No hay clientes registrados.");
        } else {
            System.out.println("Lista de clientes registrados:");
            for (Cliente cliente : clientes) {
                System.out.println("Nombre: " + cliente.getNombre() + ", PIN: " + cliente.getPin());
            }
        }
    }



    //Cuentas
    public void crearCuenta() {
        try {
            int numCuenta = (int) (Math.random() * 3001 + 1000);
            System.out.println("Número de cuenta: " + numCuenta);
            System.out.print("Bot: Ingrese el saldo inicial: ");
            saldo = teclado.nextDouble();
            teclado.nextLine();

            System.out.println("Bot :Seleccionar moneda: ");
            System.out.println("1.Bolivianos (Bs)");
            System.out.println("2.Dolares ($)");
            int opcionMoneda = teclado.nextInt();


            while (moneda == null) {
                try {
                    while (opcionMoneda < 0 || opcionMoneda > 2) {
                        System.out.println("Por favor, ingrese una opcion valida.");
                        opcionMoneda = teclado.nextInt();
                    }
                    switch (opcionMoneda) {
                        case 1:
                            moneda = "Bs";
                            break;
                        case 2:
                            moneda = "$";
                            break;
                        default:
                            System.out.println("Opcion no encontrada.");
                            break;
                    }

                    Cuenta nuevaCuenta = new Cuenta(numCuenta, saldo, moneda);
                    cuentas.add(nuevaCuenta);
                    System.out.println("Cuenta creada exitosamente.");

                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
                    teclado.nextLine();

                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
            teclado.nextLine();
        }
    }

    public void listarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("Bot: No tienes ninguna cuenta creada.");
        } else {
            System.out.println("Cuentas:");
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    public Cuenta buscarCuentaPorNumero(int numCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumCuenta() == numCuenta) {
                return cuenta;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Cliente:" +
                "Nombre='" + nombre + '\'' +
                ", Pin=" + pin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void setClientes(List<Cliente> clientes) {
        Cliente.clientes = clientes;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }


    public boolean isValidacionPin() {
        return validacionPin;
    }

    public void setValidacionPin(boolean validacionPin) {
        this.validacionPin = validacionPin;
    }

    public void agregarBeneficiario(Beneficiario beneficiario) {
        beneficiarios.add(beneficiario);
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cuenta getCuenta(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null; // Retorna null si no encuentra la cuenta
    }
}






























