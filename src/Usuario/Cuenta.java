package Usuario;

import java.util.*;

public class Cuenta {
    //atributos
    public int numCuenta ;
    public double saldo;
    public String moneda ;
    // arreglos
    public List<String> transacciones;

    //variables aparte
    public static Scanner teclado = new Scanner(System.in);

    //Constructor
    public Cuenta(int numCuenta, double saldo, String moneda) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.moneda = moneda;
        this.transacciones = new ArrayList<>();
    }




    public void agregarTransaccion(String transaccion) {
        transacciones.add(transaccion);
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
    @Override
    public String toString() {
        return "NumeroCuenta=" + numCuenta +
                ", Saldo=" + saldo+ moneda;
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

    public List<String> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<String> transacciones) {
        this.transacciones = transacciones;
    }

}





