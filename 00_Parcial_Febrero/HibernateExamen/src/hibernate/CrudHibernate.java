/**
 * Crud para realizar laas consultas mediante HQL a la base de datos Farmacia
 * 
 * Esta clase contienen 4 métodos.
 * 
 * - Inicio de sesión para conectarse a base de datos.
 * - Finalizar sesión para evitar que surjan errores.
 * - Buscar por código de medicamento.
 * - Buscar registros que conttenga los caracteres indicados.
 * 
 * @author DMIAPER (Diógens Miaja Pérez)
 * 
 * Fecha 11/02/2025
 * Versión 1.0.0
 * 
 */
package hibernate;

import Modelos.Libros;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class CrudHibernate {

    //Se instancia una sesion privada.
    private Session sesion;
    
    //Método que permitirá inciar la conexión con la BD
    public void inicioSesion(){
        //Se configura la sesión
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        //Se inicia la sesión
        sesion = sessionFactory.openSession();
        sesion.getTransaction().begin();
    }
    
    /**
     * Método para finalizar la sesión
     */
    public void finalizarSesion(){
        //SE finaliza la sesión
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    /**
     * Método que devuelve el libro del ISBN que se ha indicado.
     * @param isbn recibe un string con el ISBN del libro
     * @return se devuelve el registro obtenido de la busqueda.
     */
    public List<Libros> obtenerLibro(String isbn){
        String hql = "From Libros Where isbn = "+isbn;
        Query query = sesion.createQuery(hql);
        List<Libros> libro = query.list();
        //Se devuelve el resultado obtenido
        return libro;
    }
    
    /**
     * Método para realizar la busqueda de los libros por título según las letras 
     * que se vayan introduciendo en el campo buscar por título
     * @param buscar parámetro de tipo String para buscar el libro
     * @return se devuelve una lista con el resultado de los objetos obtenidos.
     */
    public List<Libros> listaLibros(String buscar){
        // Consulta HQL con parámetro
        String hql = "FROM Libros WHERE LOWER(Titulo) LIKE LOWER(:titulo)";
        System.out.println(buscar);
        // Crear la consulta
        Query query = sesion.createQuery(hql);
        // Concatenar los comodines (%) alrededor del valor buscado
        String parametro = "%" + buscar + "%";
        //se paramétriza la consulta
        query.setParameter("titulo", parametro);
        //Se obtienen los registros que cumple con los parámetros.
        List<Libros> resultado = query.list();
        // Se devuelve la lista.
        return resultado;
    }
    
}
