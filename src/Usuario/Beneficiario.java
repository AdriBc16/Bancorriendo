package Usuario;

import java.util.*;

public class Beneficiario {
    public static String nombre;
    public static int numCuenta;
    public static String banco;
    public static String moneda;

    public static Scanner teclado = new Scanner(System.in);

    public Beneficiario(String nombre, int numCuenta, String banco) {
        this.nombre = nombre;
        this.numCuenta = numCuenta;
        this.banco = banco;
    }



    public static void crearBeneficiario(Cliente cliente) {
        int numCuenta = (int) (Math.random() * 9000 + 1000);

        try {
            System.out.print("Ingrese el nombre del beneficiario: ");
            nombre = teclado.nextLine();


            if (isNumeric(nombre)) {
                throw new InputMismatchException("Se esperaba un texto, pero se ingresó un número.");
            }

            System.out.println("Número de cuenta: " + numCuenta);

            System.out.print("Ingrese el banco del beneficiario: ");
            banco = teclado.nextLine();


            if (isNumeric(banco)) {
                throw new InputMismatchException("Vuelva a intentarlo.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        Beneficiario nuevoBeneficiario = new Beneficiario(nombre, numCuenta, banco);
        cliente.agregarBeneficiario(nuevoBeneficiario);
    }
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Beneficiario: " +
                "Nombre='" + nombre + '\'' +
                ", Numero de cuenta: " + numCuenta + '\''+
                  ", Banco: " + banco;

    }
    public static void listarBeneficiarios(Cliente cliente) {
        List<Beneficiario> beneficiarios = cliente.getBeneficiarios();
        if (beneficiarios.isEmpty()) {
            System.out.println("No tienes beneficiarios registrados.");
        } else {
            System.out.println("Lista de beneficiarios:");
            for (Beneficiario b : beneficiarios) {
                System.out.println(b);
            }
        }
    }

    public static Beneficiario buscarBeneficiarioPorNumero(Cliente cliente, int numCuenta) {
        for (Beneficiario b : cliente.getBeneficiarios()) {
            if (b.getNumCuenta() == numCuenta) {
                return b;
            }
        }
        return null;
    }

    public static String getNombre() {
        return nombre;
    }
    public static int getNumCuenta() {
        return numCuenta;
    }



}







