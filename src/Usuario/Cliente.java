package Usuario;
import java.util.*;

public class Cliente {
    public  String nombre;
    public  int pin;
    public double saldo;
    String moneda = null;
    public static List<Cliente> clientes = new ArrayList<>();
    public List<Cuenta> cuentas = new ArrayList<>();
    public List<Beneficiario> beneficiarios = new ArrayList<>();
    public static Scanner teclado = new Scanner(System.in);

    public Cliente(String nombre, int pin){
        this.nombre = nombre;
        this.pin = pin;
    }

    public static void clientesPredefinidos(){
        clientes.add(new Cliente("ALEJANDRO", 1606));
        clientes.add(new Cliente("ROSE", 2051));
    }


    public static  Cliente buscarClientePorPin(int pin) {
           for (Cliente cliente : clientes) {
            if (cliente.getPin() == pin ) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return  true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Cliente otroCliente = (Cliente)obj;
        return this.pin == otroCliente.pin;
    }

    public static Cliente registrarCliente() {
        try {
            System.out.println("¿Cuál es tu nombre completo? ");
            String nombre = teclado.next().toUpperCase();

            int pin = 0;
            boolean pinValido = false;

            while (!pinValido) {
                System.out.println("Ingrese un PIN de 4 dígitos: ");
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
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("Lista de clientes registrados:");
            for (Cliente cliente : clientes) {
                System.out.println("Nombre: " + cliente.getNombre() + ", PIN: " + cliente.getPin());
            }
        }
    }



    public void crearCuenta() {
        try {
            int numCuenta = (int) (Math.random() * 3001 + 1000);
            System.out.println("Número de cuenta: " + numCuenta);
            System.out.print("Ingrese el saldo inicial: ");
            saldo = teclado.nextDouble();
            teclado.nextLine();

            System.out.println("Seleccionar moneda: ");
            System.out.println("1.Bolivianos (Bs)");
            System.out.println("2.Dolares ($)");
            int opcionMoneda = teclado.nextInt();

                try {
                    if (opcionMoneda < 0 || opcionMoneda > 2) {
                        System.out.println("Por favor, ingrese una opción valida.");
                        opcionMoneda = teclado.nextInt();
                    }
                    switch (opcionMoneda) {
                        case 1:
                            setMoneda("Bs");
                            break;
                        case 2:
                            setMoneda("$");
                            break;
                        default:
                            System.out.println("Opción no encontrada.");
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
                    teclado.nextLine();
                }

            Cuenta nuevaCuenta = new Cuenta(numCuenta, saldo, moneda);
            cuentas.add(nuevaCuenta);
            System.out.println("Cuenta creada exitosamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida, por favor intente nuevamente.");
            teclado.nextLine();
        }
    }

    public void listarCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas registradas.");
        } else {
            System.out.println("Lista de cuentas registradas:");
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
        return "Cliente: " +
                "Nombre='" + nombre + '\'' +
                ", Pin=" + pin;
    }

    public String getNombre() {
        return nombre;
    }
    public int getPin() {
        return pin;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }
    public void agregarBeneficiario(Beneficiario beneficiario) {
        beneficiarios.add(beneficiario);
    }

    public Cuenta getCuenta(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}






























