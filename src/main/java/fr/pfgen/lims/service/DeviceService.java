/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.service;

import fr.pfgen.lims.domain.equipments.Device;
import java.util.List;

/**
 *
 * @author eric
 */
public interface DeviceService {
    
    public abstract long countAllDevices();

    public abstract void deleteDevice(Device device);

    public abstract Device findDevice(Long id);

    public abstract List<Device> findAllDevices();

    public abstract List<Device> findDeviceEntries(int firstResult, int maxResults);

    public abstract void saveDevice(Device device);

    public abstract Device updateDevice(Device device);
    
    public abstract Device findDeviceByName(String name);
    
    public abstract Device findDeviceByItx(String itx);
    
    public abstract Device findDeviceBySerial(String serial);
    
    public abstract List<Device> findDevicesByRoom(String room);
}
