package Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cuenta {
    //atributos
    public int numCuenta ;
    public double saldo;
    public String moneda;
    // arreglos
    public List<String> transacciones;
    public List<Cuenta> cuentas =new ArrayList<>();

    //variables aparte
    Scanner teclado = new Scanner(System.in);
    int opcionMoneda;
    //Constructor
    public Cuenta(int numCuenta, double saldo, String moneda) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.moneda = moneda;
        this.transacciones = new ArrayList<>();
    }

    //Metodos
    public void registrarCuentas(Cliente cliente) {
        System.out.println("1. Crear Cuenta");
        System.out.println("2. Cuentas Creadas");
        int opcion = teclado.nextInt();
        switch (opcion) {
            case 1:
                crearCuenta(cliente);
                break;
            case 2:
                listarCuenta(cliente);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    // Controlar Excepciones en todos los metodos necesario
    public void crearCuenta(Cliente cliente){
        numCuenta = (int) (Math.random()*3001 + 1000);
        System.out.println("Número de cuenta: " + numCuenta);
        System.out.print("Ingrese el saldo inicial: ");
        saldo = teclado.nextDouble();
        System.out.println("Seleccionar moneda: ");
        System.out.println("1.Bs");
        System.out.println("2.$");
        opcionMoneda = teclado.nextInt();
        while(opcionMoneda < 0 || opcionMoneda > 2) {
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
    }

    public void listarCuenta(Cliente cliente){
        if(cuentas.isEmpty()){
            System.out.println("No tiene ninguna cuenta creada.");
        }else{
            System.out.println("Cuentas: ");
            for (Cuenta cuenta : cuentas) {
                System.out.println(cuenta);
            }
        }
    }

    public void agregarTransaccion(String transaccion) {
        transacciones.add(transaccion);
    }

    @Override
    public String toString() {
        return "NumeroCuenta=" + numCuenta +
                ", Saldo=" + saldo+ moneda;
    }

    public void mostrarTransacciones() {
        if (transacciones.isEmpty()) {
            System.out.println("No hay transacciones recientes.");
        } else {
            System.out.println("Transacciones recientes:");
            for (String transaccion : transacciones) {
                System.out.println(transaccion);
            }
        }
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}





