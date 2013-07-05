/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface EquipmentCategoryRepository extends JpaSpecificationExecutor<EquipmentCategory>, JpaRepository<EquipmentCategory, Long>{
    
    public EquipmentCategory findByName(String name);
}
