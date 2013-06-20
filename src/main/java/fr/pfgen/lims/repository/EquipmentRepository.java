/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface EquipmentRepository extends JpaSpecificationExecutor<Equipment>, JpaRepository<Equipment, Long>{
    
    public List<Equipment> findByCategory(EquipmentCategory category);
    
    public List<Equipment> findByRoom(String room);
    
    public Equipment findByItx(String itx);
}
