package utn.dds.tpAnual.compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utn.dds.tpAnual.entidad.Entidad;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */
public abstract class OperacionEfectuada extends Operacion {

	protected List<DetalleOperacion> detallesOperacion;
	protected LocalDate fechaOperacion;
	protected MedioPago medioPago;
	
	public OperacionEfectuada(DocumentoComercial documentoComercial, Entidad entidadRealizadora,
			List<DetalleOperacion> detallesOperacion, LocalDate fechaOperacion, MedioPago medioPago) {
		super(documentoComercial, entidadRealizadora);
		this.detallesOperacion = detallesOperacion;
		this.fechaOperacion = fechaOperacion;
		this.medioPago = medioPago;
	}

	public Float getTotal(){
		Float total = 0F;
		if(detalles!=null) {
			for (DetalleOperacion detalle : detalles) {
				total += detalle.getTotal();
			}
		}
		return total;
	}
	
	public List<DetalleOperacion> getDetallesOperacion() {
		return detallesOperacion;
	}
	
	public void addDetalleOperacion(DetalleOperacion detalle) {
		if(detallesOperacion == null) {
			detallesOperacion = new ArrayList<DetalleOperacion>();
		}
		detallesOperacion.add(detalle);
	}
}