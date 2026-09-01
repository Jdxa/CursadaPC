package TrabajoPractico2;
public class TesteoRecurso{
    public static void main(String[] args) {
        Recurso unRecurso = new Recurso();

        Cliente juan = new Cliente(unRecurso);
        juan.setName("Juan Lopez");
        Cliente ines = new Cliente(unRecurso);
        ines.setName("Ines Garcia");

        ines.start();
        juan.start();
        

        
    }
    //hay 3 hilos: el main y Juan e Ines 
    //el metodo uso recupera el hilo en ejecucion y muestra su nombre
    // uso() a final del main muestra el hilo q esta en ejecucion q es el
    //Cambiar el orden no tiene impacto, la cpu decide que hilo hace uso de su computo
    //SALIDAS
//soy: Juan Lopez
// soy: Ines Garcia
// en Recurso:  Soy Juan Lopez
// en Recurso:  Soy Ines Garcia

// soy: Ines Garcia
// soy: Juan Lopez
// en Recurso:  Soy Ines Garcia
// en Recurso:  Soy Juan Lopez
} 