/**
 * Clase FileHandler
 * 
 * Esta clase se encargar� de manejar el fichero donde se almacenar�n los n�meros que 
 * se escriba el usuario.
 * 
 * Cuando se llame con el bot�n mostar los n�meros se leer� el fichero y se mostr�n
 * los n�mero almacenados en la consola.
 * 
 * M�todo para crear el fichero.
 * M�todo para escribir en el fichero.
 * M�todo para leer y devolver los datos del fichero.
 * 
 * No se controla el cierre del fichero, ya que se utiliza un try-catch-resource que cierra 
 * autom�ticamente el fichero al salir del try-catch
 * 
 * @Author DMIAPER (Di�genes Miaja P�rez)
 * 
 * Fecha: 27-04-2025
 * Versi�n: 1.0.0 
 */
package solicitarnumero;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author dmiap
 */
public class FileHandler {
 
    /**
     * M�todo para comprobar y/o crear el fichero en caso de que no exista.
     * 
     * @param fichero, se recibe como par�metro un File
     * @return se devuelve un string con el path (directorio) del fichero.
     */
    public static String fileHandler(File fichero){
        //se instancia a la clase para manejar el fichero 
        //se introduce un controlador de errores.
        try{
           //los primero que se har� es crear el fichero Numeros.txt para trabajar con el
           //si existe el fichero nos devuelve el mensaje informado que el fichero existe.
            if(fichero.exists()){
                System.out.println("El fichero "+fichero.getName()+" se encuentra dentro del directorio indicado");
            }else{
                //si no existe nos informa de que el fichero no existe.
                System.out.println("El fichero "+fichero.getName()+" no existe.");
                //se llama al m�todo para crear el fichero.
                if(fichero.createNewFile()){
                    System.out.println("Se ha creado satisfactoriamente el archivo: "+fichero.getName());
                }else{
                    System.out.println("No se ha podido crear el fichero");
                }
            }
                
        }catch(IOException ioe){
            System.out.println("Error: "+ ioe.getMessage());
        }
        
        return fichero.getPath();
    }
    
    
    /**
     * M�todo para guardr los n�meros que se introduzcan por consola dentro del 
     * fichero Numeros.txt
     * 
     * @param fichero se recibe el path de fichero donde se van a almacenar los n�meros
     * @param num se recibe el n�mero que se va a guardar dentro del fichero.
     */
    public static void guardarNumeros(String fichero, int num){
        //utilizamos un try-whit-resource para garantizar el cierre autom�tico despu�s de escribir datos.
        try(RandomAccessFile randomFile = new RandomAccessFile(fichero, "rw")){
            //Colocamos el puntero al final de registros
            randomFile.seek(randomFile.length());
            //Se escribe el n�mero con writeInt para cuando se lea se sepa que es de tipo entero
            randomFile.writeInt(num);           
            //Se muestra un mensaje informado que se ha realizar correctamente la escritura de los alumnos.
            System.out.println("Se ha realizado correctamente los registros en el fichero");
        }catch(IOException ioe){
            //En caso de error se mostrar� el mensaje de error.
            System.out.println("Error: "+ ioe.getMessage());
        }
    }
    
    /**
     * M�todo para leer el fichero y poder mostar por consola los datos que se han 
     * obtenido de Numeros.txt
     * @param fichero se recibe como par�metro el path del fichero
     */
    public static void leerFichero(String fichero){
        //Se lee el fichero.
        try(RandomAccessFile randomLeer = new RandomAccessFile(fichero, "r")){
                        
            //Se imprime un t�tulo de los n�meros intorucidos.
            System.out.println("N�meros introducidos:");
            //Se utiliza un while mejor que un for, debido a que no se sabe cuantos
            //registros se han registrado.
            //Se recurre el bucle mientras el puntero sea menor a la longitud total.
            while(randomLeer.getFilePointer() < randomLeer.length()){
                //Se imprime el registro por consola.
                System.out.println(randomLeer.readInt());
            }
        }catch(IOException ioe){
            System.out.println("Error: "+ioe.getMessage());
        }
    }
    
    
}
