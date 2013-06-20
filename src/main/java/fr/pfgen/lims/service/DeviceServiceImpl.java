/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Device;
import fr.pfgen.lims.repository.DeviceRepository;
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
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public long countAllDevices() {
        return deviceRepository.count();
    }

    @Override
    public void deleteDevice(Device device) {
        deviceRepository.delete(device);
    }

    @Override
    public Device findDevice(Long id) {
        return deviceRepository.findOne(id);
    }

    @Override
    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> findDeviceEntries(int firstResult, int maxResults) {
        return deviceRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }

    @Override
    public void saveDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public Device findDeviceByName(String name) {
        return deviceRepository.findByName(name);
    }

    @Override
    public Device findDeviceByItx(String itx) {
        return deviceRepository.findByItx(itx);
    }

    @Override
    public Device findDeviceBySerial(String serial) {
        return deviceRepository.findBySerial(serial);
    }

    @Override
    public List<Device> findDevicesByRoom(String room) {
        return deviceRepository.findByRoom(room);
    }
}
