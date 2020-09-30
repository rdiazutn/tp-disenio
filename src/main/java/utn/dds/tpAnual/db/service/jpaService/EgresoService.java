package utn.dds.tpAnual.db.service.jpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import utn.dds.tpAnual.db.dto.pageable.PageableRequest;
import utn.dds.tpAnual.db.entity.entidad.EntidadJuridica;
import utn.dds.tpAnual.db.entity.transaccion.Egreso;
import utn.dds.tpAnual.db.repository.EgresoRepository;
import utn.dds.tpAnual.db.entity.entidad.Entidad;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
    public class EgresoService extends CustomJPAService<Egreso> {

    @Autowired
    private EgresoRepository egresoRepository;

    @Autowired
    private EntidadService entidadService;

    @Override
    public JpaRepository<Egreso, Long> getRepository() {
        return egresoRepository;
    }

    public List<Egreso> getEgresosVinculablesByEntidadRealizadora(Entidad entidad){
        return egresoRepository.getEgresosByEntidadRealizadora(entidad);
    }

    public List<Egreso> getEgresosSinValidar(){
        return egresoRepository.getEgresosSinValidar();
    }

    public Egreso getEgresoById(Long operacionId) {
        List<Egreso> egresos = egresoRepository.getEgresoById(operacionId);
        return egresos.isEmpty() ? null : egresos.get(0);
    }

    @Override
    public void save(Egreso entity) {
        entidadService.saveIfNotExists(entity.getEntidadRealizadora());
        super.save(entity);
    }

    @Override
    public void saveAll(Collection<Egreso> entities) {
        entities.forEach(this::save);
    }

    public List<Egreso> getEgresosByCompaniasAndFecha(List<EntidadJuridica> entidadesJuridicas, LocalDate from, LocalDate to){
        return egresoRepository.getEgresoByCompanias(entidadesJuridicas, entidadesJuridicas.isEmpty(), from, to);
    }

    public Page<Egreso> getEgresos(PageableRequest pageableRequest){
        Pageable pageable = PageRequest.of(pageableRequest.getPage().intValue(), pageableRequest.getItemsPerPage().intValue()
            , Sort.by(Sort.Order.desc("fechaOperacion")));
        return egresoRepository.findAll(pageable);
    }
}
