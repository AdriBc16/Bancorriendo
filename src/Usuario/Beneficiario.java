package Usuario;

import java.util.*;

public class Beneficiario {
    // Atributos
    String nombre;
    int nroCarnet;
    int numCuenta;
    String banco;
    double saldo;

    //Arreglos
    List<Beneficiario> beneficiarios= new ArrayList<>();

    // Variables aparte
    Scanner teclado = new Scanner(System.in);

    public Beneficiario(String nombre, int nroCarnet, int numCuenta, String banco) {
        this.nombre = nombre;
        this.nroCarnet = nroCarnet;
        this.numCuenta = numCuenta;
        this.banco = banco;
    }

    //Controlar las excepcione en todos los metdos necesarios
    // y tambien validar el nro carnet
    public void crearBeneficiario() {
        System.out.print("Nombre: ");
        nombre = teclado.next().toLowerCase();
        System.out.print("Número de Carnet: ");
        nroCarnet = teclado.nextInt();
        System.out.print("Número de cuenta: ");
        numCuenta = (int) (Math.random()*3001 + 1000);
        System.out.print("Banco: ");
        banco = teclado.next();

        Beneficiario nuevoBeneficiario = new Beneficiario(nombre,nroCarnet,numCuenta,banco);
        beneficiarios.add(nuevoBeneficiario);
        System.out.println("Beneficiario " + nuevoBeneficiario.nombre + " agregado con éxito.");
    }

    public void listarBeneficiarios() {
        if (beneficiarios.isEmpty()) {
            System.out.println("No hay beneficiarios registrados.");
        } else {
            System.out.println("Lista de beneficiarios:");
            for (Beneficiario b : beneficiarios) {
                System.out.println("Nombre: " + b.nombre + ", Banco: " + b.banco + ", Número de cuenta: " + b.numCuenta);
            }
        }
    }

    public void recibirDinero(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println(nombre + " ha recibido " + monto + ". Saldo actual: " + this.saldo);
        } else {
            System.out.println("El monto a recibir debe ser mayor a 0.");
        }
    }

    public void transferirDinero(Beneficiario destinatario) {
        System.out.print("Ingrese el monto que desea transferir: ");
        double monto = teclado.nextDouble();
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            destinatario.recibirDinero(monto);
            System.out.println(nombre + " ha transferido " + monto + " a " + destinatario.nombre + ". Saldo actual: " + this.saldo);
        } else if (monto <= 0) {
            System.out.println("El monto a transferir debe ser mayor a 0.");
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia.");
        }
    }



}
