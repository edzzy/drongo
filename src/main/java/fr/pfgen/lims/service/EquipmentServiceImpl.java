/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.EquipmentCategory;
import fr.pfgen.lims.repository.EquipmentCategoryRepository;
import fr.pfgen.lims.repository.EquipmentRepository;
import java.util.List;
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
    EquipmentCategoryRepository equipmentCategoryService;

    //Impl for Equipment Categories
    @Override
    public long countAllEquipmentCategories() {
        return equipmentCategoryService.count();
    }

    @Override
    public void deleteEquipmentCategory(EquipmentCategory equipmentCategory) {
        equipmentCategoryService.delete(equipmentCategory);
    }

    @Override
    public EquipmentCategory findEquipmentCategory(Long id) {
        return equipmentCategoryService.findOne(id);
    }

    @Override
    public List<EquipmentCategory> findAllEquipmentCategories() {
        return equipmentCategoryService.findAll();
    }

    @Override
    public List<EquipmentCategory> findEquipmentCategoryEntries(int firstResult, int maxResults) {
        return equipmentCategoryService.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveEquipmentCategory(EquipmentCategory equipmentCategory) {
        equipmentCategoryService.save(equipmentCategory);
    }

    @Override
    public EquipmentCategory updateEquipmentCategory(EquipmentCategory equipmentCategory) {
        return equipmentCategoryService.save(equipmentCategory);
    }

    @Override
    public EquipmentCategory findEquipmentCategoryByName(String name) {
        return equipmentCategoryService.findByName(name);
    }

    //Impl for Equipments
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
        equipmentRepository.save(equipment);
    }

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> findEquipmentsByCategory(EquipmentCategory category) {
        return equipmentRepository.findByCategory(category);
    }

    @Override
    public List<Equipment> findEquipmentsByRoom(String room) {
        return equipmentRepository.findByRoom(room);
    }

    @Override
    public Equipment findEquipmentByItx(String itx) {
        return equipmentRepository.findByItx(itx);
    }
}
