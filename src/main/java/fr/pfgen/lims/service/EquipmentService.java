/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.*;
import fr.pfgen.lims.domain.people.Organism;

import javax.faces.model.SelectItem;
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
    
    public abstract List<Intervention> findAllInterventions();
    
    public abstract List<Intervention> findAllInterventionsByEquipment(Equipment equipment);
    
    
    public abstract List<String> findAllManufacturers();
    
    public abstract void saveIntervention(Intervention intervention);


    //methods for fundings

    public abstract List<Funding> findAllFundings();

    public abstract List<Funding> findAllFundingsByEquipments(Equipment equipment);

    public abstract List<Funding> findAllFundingsByOrganisms(Organism organism);

    public abstract Intervention updateIntervention(Intervention intervention);

    public abstract Intervention findInterventionById(Long id);

    public abstract Equipment findEquipmentById(Long aLong);
}
