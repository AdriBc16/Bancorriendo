package Usuario;
import java.util.*;

public class Cliente {
    // atributos
    public String nombre;
    public int pin;

    // listas
    public List<Cliente>clientes = new ArrayList<>();

    // variables aparte
    public boolean validacionPin = false;
    Scanner teclado = new Scanner(System.in);

    //Constructor
    public Cliente(String nombre, int pin){
        this.nombre = nombre;
        this.pin = pin;
    }

    //Metodos
    public void clientesPredefinidos(){
        Cliente c1 = new Cliente("Alejandro16",1606);
        Cliente c2 = new Cliente("Rose", 2051);
        clientes.add(c1);
        clientes.add(c2);
    }

    // Controlar o dejarlo asi pero tener cuidado
    public String buscarNombre(int pin) {
        for (Cliente cliente : clientes) {
            if (cliente.getPin() == pin) {
                String nombre = cliente.getNombre();
                System.out.println(nombre);
                return cliente.nombre != null ? nombre.toUpperCase() : "Nombre no encontrado";
            }
        }
        return "Nombre no encontrado";
    }

    @Override
    public boolean equals(Object pin) { // creamos un objeto pin
        if (pin == null || !(pin instanceof Integer)){ // si mi pin es null
            // o si pin no es una instancia de Integer (es decir, no es un número entero).
            //retorna falso.
            return false;
        }

        //creo una variable para hacer el cast y así convertir el objeto pin en un entero
        int pinIngresado = (Integer) pin;

        // creo una instancia cliente y el for recorre todo mi arreglo de clientes convirtiendo cada elemento en un objeto de tipo Cliente.
        // luego pregunta si el pin de mi objeto cliente es igual a mi pin y devuelve true.
        for (Cliente cliente : clientes) {
            if (cliente.pin == pinIngresado) {
                return true;
            }
        }

        System.out.println("Pin no encontrado, por favor registrese.");
        registrarCliente();
        return false;
    }

    public void registrarCliente(){
            System.out.print("Nombre Usuario:");
            nombre = teclado.next().toUpperCase();
            while (!validacionPin) {
                System.out.print("Pin:");
                try {
                pin = teclado.nextInt();
                validacionPin = true;
            } catch(InputMismatchException e){
                System.out.println("Por favor,ingrese un pin valido.");
                validacionPin = false;
                teclado.nextLine();
            }
        }
        Cliente nuevoCliente = new Cliente(nombre, pin);
        clientes.add(nuevoCliente);
        System.out.println(nuevoCliente);

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
}






























