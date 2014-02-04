/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.Intervention;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edouard
 */
@Repository
public interface InterventionRepository extends JpaSpecificationExecutor<Intervention>, JpaRepository<Intervention, Long>{
    
    public List<Intervention> findByEquipment(Equipment equipment);
        
    
}
