/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.domain.equipments.SmallEquipment;
import java.util.List;

/**
 *
 * @author eric
 */
public interface EquipmentService {
    
    //methods for equipments
    public abstract long countAllEquipments();

    public abstract void deleteEquipment(Equipment equipment);

    public abstract Equipment findEquipment(Long id);

    public abstract List<Equipment> findAllEquipments();

    public abstract List<Equipment> findEquipmentEntries(int firstResult, int maxResults);

    public abstract void saveEquipment(Equipment equipment);

    public abstract Equipment updateEquipment(Equipment equipment);
    
    public abstract Equipment findEquipmentBySerial(String serial);
    
    public abstract Equipment findEquipmentByItx(String itx);
    
    
    //methods for devices
    public abstract void saveRunDevice(RunDevice device);
    
    public abstract RunDevice updateRunDevice(RunDevice device);
    
    public abstract List<RunDevice> findAllDevices();
    
    public abstract long countAllRunDevices();
    
    //methods for small equipments
    public abstract List<SmallEquipment> findAllSmallEquipments();
    
    public abstract long countAllSmallEquipments();
    
    //methods for equipment categories
    public abstract List<EquipmentCategory> findAllEquipmentCategories();
    
    public abstract EquipmentCategory findEquipmentCategoryByName(String name);
    
    public abstract void saveEquipmentCategory(EquipmentCategory category);
}
