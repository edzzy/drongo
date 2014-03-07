package fr.pfgen.lims.web.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by edouard on 26/02/14.
 */
@Component
@Scope("singleton")
public class RedirectBean implements Serializable{

    static final String REDIRECT = "?faces-redirect=true";



/**
    private String listDevices;
    private String listEquipments;
    private String listInterventions;
    private String listSmallEquipments;
 private String listEquipments = "/pages/equipments/listEquipments.jsf";

    private String showDevice;
    private String showEquipment;
    private String showIntervention;
    private String showSmallEquipment;

    private String editDevice;
    private String editEquipment;
    private String editInterventions;
    private String editSmallEquipment;

    private String createDevice;
    private String createEquipment;
    private String createIntervention;
    private String createSmallEquipment;

    private String templateEquipment;

    private String mainEquipment;


    private String indexEquipment;
**/

    //PAGES

    //LIST
    public String getListEquipments() {

        return "/pages/equipments/listEquipments.jsf" + REDIRECT;
    }

    public String getListInterventions() {
        return "/pages/equipments/listInterventions.jsf" + REDIRECT + REDIRECT;
    }

    public String getListDevices() {
        return "/pages/equipments/listDevices.jsf" + REDIRECT + REDIRECT;
    }

    public String getListSmallEquipments() {
        return "/pages/equipments/listSmallEquipments.jsf" + REDIRECT;
    }

    //SHOW

    public String getShowEquipment() {
        return "/pages/equipments/showEquipment.jsf" + REDIRECT;
    }

    public String getShowIntervention() {
        return "/pages/equipments/showIntervention.jsf" + REDIRECT;
    }

    public String getShowDevice() {
        return "/pages/equipments/showDevice.jsf" + REDIRECT;
    }

    public String getShowSmallEquipment() {
        return "/pages/equipments/showSmallEquipment.jsf" + REDIRECT;
    }

    //EDIT

    public String getEditEquipment() {
        return "/pages/equipments/editEquipment.jsf" + REDIRECT;
    }

    public String getEditIntervention() {
        return "/pages/equipments/editIntervention.jsf" + REDIRECT;
    }

    public String getEditDevice() {
        return "/pages/equipments/editDevice.jsf" + REDIRECT;
    }

    public String getEditSmallEquipment() {
        return "/pages/equipments/editSmallEquipment.jsf" + REDIRECT;
    }

    //CREATE

    public String getCreateEquipment() {
        return "/pages/equipments/createEquipment.jsf" + REDIRECT;
    }

    public String getCreateIntervention() {
        return "/pages/equipments/createIntervention.jsf" + REDIRECT;
    }

    public String getCreateDevice() {
        return "/pages/equipments/createDevice.jsf" + REDIRECT;
    }

    public String getCreateSmallEquipment() {
        return "/pages/equipments/createSmallEquipment.jsf" + REDIRECT;
    }

    //INDEX

    public String getIndexEquipment() {
        return "/pages/equipments/indexEquipment.jsf" + REDIRECT;
    }

    //TEMPLATES

    public String getTemplateEquipment() {
        return "/pages/equipments/templateEquipment.xhtml";

    }



    public String getTemplateHeader(){
        return "/templates/e_header.xhtml";

    }


}
