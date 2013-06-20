/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.repository;

import fr.pfgen.lims.domain.equipments.Device;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eric
 */
@Repository
public interface DeviceRepository extends JpaSpecificationExecutor<Device>, JpaRepository<Device, Long>{
    
    public Device findByItx(String itx);
    
    public Device findByName(String name);
    
    public Device findBySerial(String serial);
    
    public List<Device> findByRoom(String room);
}
