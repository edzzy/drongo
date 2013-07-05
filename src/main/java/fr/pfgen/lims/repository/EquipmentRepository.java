/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface EquipmentRepository extends JpaSpecificationExecutor<Equipment>, JpaRepository<Equipment, Long>{
    
    public Equipment findBySerialNumber(String serial);
    
    public Equipment findByItx(String itx);
}
