package Modelos;
// Generated 28-feb-2025 19:00:43 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Facturas generated by hbm2java
 */
public class Facturas  implements java.io.Serializable {


     private Integer id;
     private String numeroFactura;
     private Set ventases = new HashSet(0);

    public Facturas() {
    }

	
    public Facturas(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public Facturas(String numeroFactura, Set ventases) {
       this.numeroFactura = numeroFactura;
       this.ventases = ventases;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumeroFactura() {
        return this.numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public Set getVentases() {
        return this.ventases;
    }
    
    public void setVentases(Set ventases) {
        this.ventases = ventases;
    }




}


