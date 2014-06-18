/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface EquipmentRepository extends JpaSpecificationExecutor<Equipment>, JpaRepository<Equipment, Long>{
    
    public Equipment findBySerialNumber(String serial);
    
    public Equipment findByItx(String itx);
    
    @Query("select distinct manufacturer from Equipment")
    public List<String> findAllManufacturers();

    public  Equipment findById(Long id);
    
    
    public List<Equipment> findByStatus(EquipmentStatus status);
    
}
