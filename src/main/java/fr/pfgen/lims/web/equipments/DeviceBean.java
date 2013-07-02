/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.equipments;

import fr.pfgen.lims.domain.equipments.Equipment;
import fr.pfgen.lims.domain.equipments.RunDevice;
import fr.pfgen.lims.service.EquipmentService;
import fr.pfgen.lims.web.util.FacesUtils;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author eric
 */
@Controller
@Scope("view")
@ManagedBean
public class DeviceBean implements Serializable {

    @Autowired
    EquipmentService equipmentService;
    private RunDevice device;
    private String wizStep;
    private boolean isNewDevice;
    private boolean hasImage;

    public void initDevice() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

            device = (RunDevice) sessionMap.get("device");
            wizStep = (String) sessionMap.get("wizStep");
            sessionMap.remove("wizStep");
            if (wizStep == null) {
                wizStep = "descriptionTab"; //first tab
            }

            if (device == null) {
                device = new RunDevice();
                isNewDevice = true;
                hasImage = false;
                sessionMap.put("device", device);
            } else {
                isNewDevice = false;
                hasImage = true;
            }
        }
    }

    public String saveDevice() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (device.getId() == null) {
                equipmentService.saveRunDevice(device);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("newDevice_added"), device.toString(), FacesMessage.SEVERITY_INFO);

            } else {
                equipmentService.updateRunDevice(device);
                FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_done"), device.toString(), FacesMessage.SEVERITY_INFO);
            }
            context.getExternalContext().getFlash().setKeepMessages(true);
            return "devices?faces-redirect=true";
        } catch (Exception e) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_error"), e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    public String cancelDevice() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (device.getId() == null) {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("label_createCanceled"), null, FacesMessage.SEVERITY_INFO);
        } else {
            FacesUtils.addMessage(null, FacesUtils.getI18nValue("edit_cancelled"), device.toString(), FacesMessage.SEVERITY_INFO);
        }
        context.getExternalContext().getFlash().setKeepMessages(true);
        return "devices?faces-redirect=true";
    }

    public void validateSerial(FacesContext context, UIComponent component, Object value) {
        String serial = (String) value;

        Equipment existingEquipment = equipmentService.findEquipmentBySerial(serial);

        if ((existingEquipment != null && existingEquipment.getId() != device.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\"" + serial + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void validateItx(FacesContext context, UIComponent component, Object value) {
        String itx = (String) value;

        Equipment existingEquipment = equipmentService.findEquipmentByItx(itx);

        if ((existingEquipment != null && existingEquipment.getId() != device.getId())) {
            ((UIInput) component).setValid(false);
            FacesUtils.addMessage(component.getClientId(context), FacesUtils.getI18nValue("edit_error"), "\"" + itx + "\" " + FacesUtils.getI18nValue("label_alreadyExists"), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void handleImageUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            device.setImage(event.getFile().getContents());
            hasImage = true;
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (event.getOldStep().equalsIgnoreCase("otherTab") && event.getNewStep().equalsIgnoreCase("confirmTab")) {
            if (!hasImage) {
                FacesUtils.addMessage("deviceForm:uploadImage", "validation error", "image is required", FacesMessage.SEVERITY_ERROR);
                RequestContext.getCurrentInstance().update("deviceForm:uploadImage");
                return event.getOldStep();
            }
        }
        return event.getNewStep();
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public boolean isIsNewDevice() {
        return isNewDevice;
    }

    public void setIsNewDevice(boolean isNewDevice) {
        this.isNewDevice = isNewDevice;
    }

    public RunDevice getDevice() {
        return device;
    }

    public void setDevice(RunDevice device) {
        this.device = device;
    }

    public String getWizStep() {
        return wizStep;
    }

    public void setWizStep(String wizStep) {
        this.wizStep = wizStep;
    }

    public String getSaveOrEditLabel() {
        if (device.getId() != null) {
            return FacesUtils.getI18nValue("label_edit");
        } else {
            return FacesUtils.getI18nValue("label_save");
        }
    }

    public String getPlusOrPen() {
        if (device.getId() != null) {
            return "ui-icon-pencil";
        } else {
            return "ui-icon-disk";
        }
    }
}
