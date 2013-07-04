/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.domain.equipments.SmallEquipment;
import fr.pfgen.lims.repository.EquipmentCategoryRepository;
import fr.pfgen.lims.repository.EquipmentRepository;
import fr.pfgen.lims.repository.RunDeviceRepository;
import fr.pfgen.lims.repository.SmallEquipmentRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eric
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    RunDeviceRepository runDeviceRepository;
    @Autowired
    SmallEquipmentRepository smallEquipmentRepository;
    @Autowired
    EquipmentCategoryRepository equipmentCategoryRepository;

    @Override
    public long countAllEquipments() {
        return equipmentRepository.count();
    }

    @Override
    public void deleteEquipment(Equipment equipment) {
        equipmentRepository.delete(equipment);
    }

    @Override
    public Equipment findEquipment(Long id) {
        return equipmentRepository.findOne(id);
    }

    @Override
    public List<Equipment> findAllEquipments() {
        return equipmentRepository.findAll();
    }

    @Override
    public List<Equipment> findEquipmentEntries(int firstResult, int maxResults) {
        return equipmentRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveEquipment(Equipment equipment) {

        // get the latest internal number in DB.
        Pattern p = Pattern.compile("-?\\d+");
        Integer newIn = 0;
        for (Equipment e : findAllEquipments()) {
            Matcher m = p.matcher(e.getInternalNumber());
            while (m.find()) {
                Integer i = Integer.parseInt(m.group());
                if (i > newIn) {
                    newIn = i;
                }
            }
        }
        
        // condition is always true (for the moment)
        if (equipment.getInternalNumber()==null || equipment.getInternalNumber().isEmpty()){
            equipment.setInternalNumber("PfG" + String.format("%04d", newIn + 1));
        }
        
        // set ITX number to null instead of empty since field must be unique in DB.
        if (equipment.getItx().trim().isEmpty()) equipment.setItx(null);
        equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        if (equipment.getItx().trim().isEmpty()) equipment.setItx(null);
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment findEquipmentBySerial(String serial) {
        return equipmentRepository.findBySerialNumber(serial);
    }

    @Override
    public Equipment findEquipmentByItx(String itx) {
        return equipmentRepository.findByItx(itx);
    }

    //methods for run devices
    @Override
    public List<RunDevice> findAllDevices() {
        return runDeviceRepository.findAll();
    }

    @Override
    public long countAllRunDevices() {
        return runDeviceRepository.count();
    }

    @Override
    public void saveRunDevice(RunDevice device) {
        saveEquipment(device);
    }

    @Override
    public RunDevice updateRunDevice(RunDevice device) {
        return (RunDevice) updateEquipment(device);
    }

    //methods for small equipments
    @Override
    public List<SmallEquipment> findAllSmallEquipments() {
        return smallEquipmentRepository.findAll();
    }

    @Override
    public long countAllSmallEquipments() {
        return smallEquipmentRepository.count();
    }

    //methods for equipment categories
    @Override
    public List<EquipmentCategory> findAllEquipmentCategories() {
        return equipmentCategoryRepository.findAll();
    }

    @Override
    public EquipmentCategory findEquipmentCategoryByName(String name) {
        return equipmentCategoryRepository.findByName(name);
    }

    @Override
    public void saveEquipmentCategory(EquipmentCategory category) {
        equipmentCategoryRepository.save(category);
    }
}
