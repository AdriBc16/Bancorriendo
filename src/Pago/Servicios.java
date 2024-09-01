package Pago;

import Usuario.Cliente;
import Usuario.Cuenta;

import java.text.SimpleDateFormat;
import java.util.*;

public class Servicios {
    // Atributos
    int nroMedidor;
    int deuda;
    String mes;
    public double colegiatura = 7000;

    // Arreglos
    public List<Servicios> agua =new ArrayList<>();
    public List<Servicios> luz =new ArrayList<>();
    Scanner teclado = new Scanner(System.in);
    Cliente client = new Cliente(null, 0);

    //Constructor

    public Servicios(int nroMedidor, int deuda, String mes) {
        this.nroMedidor = nroMedidor;
        this.deuda = deuda;
        this.mes = mes;
    }

    public void serviosPre(){
        Servicios a1 = new Servicios(1212,150,"agosto");
        Servicios a2 = new Servicios(1212,200,"julio");
        Servicios l1 = new Servicios(2349,400,"agosto");
        Servicios l2 = new Servicios(2349,450,"julio");
        agua.add(a1);
        agua.add(a2);
        luz.add(l1);
        luz.add(l2);


    }
    // Controlar excepciones.
    public void calculoServicio(Cuenta cuentaCreada, Servicios deudaArreglo) {
        boolean nroMedidorValido = false;
        System.out.println("Selecciona el numero de cuenta: ");
        cuentaCreada.listarCuenta(client);

        Cuenta cuentaSelec = null;
        System.out.print("Ingrese el numero de la cuenta ");
        int num = teclado.nextInt();
        for (Cuenta cuenta : cuentaCreada.cuentas) {
            if (cuenta.numCuenta == num) {
                cuentaSelec = cuenta;
                break;
            }
        }

        if (cuentaSelec != null) {
            System.out.println("Seleccione el nro del medidor que desea pagar: ");
            listaDeudas(client);
            int nrM = teclado.nextInt();

            Servicios aguaDeuda = null;
            for (Servicios deudasServicio : deudaArreglo.agua) {
                if (deudasServicio.nroMedidor == nrM) {
                    nroMedidorValido = true;
                    aguaDeuda = deudasServicio;
                    break;
                }
            }

            if(aguaDeuda!= null){
                System.out.println("Ingrese el mes que desea pagar:");
                String mes = teclado.next().toLowerCase().trim();

                System.out.println("Mes ingresado: " + mes );
                System.out.println("Mes registrado: " + aguaDeuda.mes.toLowerCase().trim());

                if (aguaDeuda.equals(mes.toLowerCase().trim())) {
                        if (cuentaSelec.saldo >= aguaDeuda.deuda) {
                            cuentaSelec.saldo = cuentaSelec.saldo - aguaDeuda.deuda;

                            String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                            String transaccion = "Deuda Agua de " + aguaDeuda.deuda + " " + cuentaSelec.moneda + " el " + fecha;
                            cuentaSelec.agregarTransaccion(transaccion);

                            System.out.println("Deuda pagada de " + aguaDeuda.deuda + " " + cuentaSelec.moneda + " de la cuenta " + num);
                            aguaDeuda.deuda = 0;
                        } else {
                            System.out.println("Saldo insuficiente.");
                        }
                }else{
                    System.out.println("Mes no encontrado.");
                }
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }

        if(!nroMedidorValido){
            System.out.println("Numero de medidor no encontrado.");
        }

    }






    public void listaDeudas(Cliente cliente){
        if(agua.isEmpty()){
            System.out.println("No tiene deudas por pagar.");
        }else{
            System.out.println("Deudas: ");
            for (Servicios servicios : agua) {
                System.out.println(servicios);
            }
        }
    }

    @Override
    public String toString() {
        return "Servicios:" +
                "NroMedidor=" + nroMedidor +
                ", Deuda=" + deuda +
                ", Mes='" + mes;
    }

    public int getNroMedidor() {
        return nroMedidor;
    }

    public void setNroMedidor(int nroMedidor) {
        this.nroMedidor = nroMedidor;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}

