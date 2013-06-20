/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import java.util.List;

/**
 *
 * @author eric
 */
public interface EquipmentService {
    
    //methods for Equipment Categories
    public abstract long countAllEquipmentCategories();

    public abstract void deleteEquipmentCategory(EquipmentCategory equipmentCategory);

    public abstract EquipmentCategory findEquipmentCategory(Long id);

    public abstract List<EquipmentCategory> findAllEquipmentCategories();

    public abstract List<EquipmentCategory> findEquipmentCategoryEntries(int firstResult, int maxResults);

    public abstract void saveEquipmentCategory(EquipmentCategory equipmentCategory);

    public abstract EquipmentCategory updateEquipmentCategory(EquipmentCategory equipmentCategory);
    
    public abstract EquipmentCategory findEquipmentCategoryByName(String name);
    
    
    //methods for Equipments
    public abstract long countAllEquipments();

    public abstract void deleteEquipment(Equipment equipment);

    public abstract Equipment findEquipment(Long id);

    public abstract List<Equipment> findAllEquipments();

    public abstract List<Equipment> findEquipmentEntries(int firstResult, int maxResults);

    public abstract void saveEquipment(Equipment equipment);

    public abstract Equipment updateEquipment(Equipment equipment);
    
    public abstract List<Equipment> findEquipmentsByCategory(EquipmentCategory category);
    
    public abstract List<Equipment> findEquipmentsByRoom(String room);
    
    public abstract Equipment findEquipmentByItx(String itx);
}
