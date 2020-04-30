package utn.dds.tpAnual.compra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utn.dds.tpAnual.*;

public class OperacionEfectuadaTest {

    @Test
    public void getTotalDeOperacionEfectuada() {
    	Egreso egreso = new Egreso(0, 0, null, null, null);
    	Item itemTest = new Item(null, null);
    	DetalleOperacion d1 = new DetalleOperacion(itemTest, 50F, 3); // 150
    	DetalleOperacion d2 = new DetalleOperacion(itemTest, 10F, 4); // 40
    	DetalleOperacion d3 = new DetalleOperacion(itemTest, 100F, 2); //200
    	
    	egreso.addDetalleOperacion(d1);
    	egreso.addDetalleOperacion(d2);
    	egreso.addDetalleOperacion(d3);
    	
    	assertTrue(egreso.getTotal().equals(390F));
    }
    
    
    @Test
    public void getTotalDeOperacionEfectuadaSiNoHayDetalles() {
    	Egreso egreso = new Egreso(0, 0, null, null, null);
    	assertTrue(egreso.getTotal().equals(0F));
    }
}
