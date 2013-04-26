/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

   private static final long serialVersionUID = 1L;
   private String theme = "afterdark";

   public String getTheme() {
      Map<String, String> params = FacesContext.getCurrentInstance()
            .getExternalContext().getRequestParameterMap();
      if (params.containsKey("theme")) {
         theme = params.get("theme");
      }
      return theme;
   }

   public void setTheme(String theme) {
      this.theme = theme;
   }
}