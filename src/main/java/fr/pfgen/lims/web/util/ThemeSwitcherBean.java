/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util;
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.TreeMap;  
import javax.annotation.PostConstruct;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 
import javax.inject.Inject;
  
@ManagedBean
@SessionScoped
public class ThemeSwitcherBean {

   private Map<String, String> themes;
   private List<Theme> advancedThemes;
   private String theme;
   private GuestPreferences gp = new GuestPreferences();

   @Inject
   public void setGp(GuestPreferences gp) {
      this.gp = gp;
   }

   public Map<String, String> getThemes() {
      return themes;
   }

   public String getTheme() {
      return theme;
   }

   public void setTheme(String theme) {
      this.theme = theme;
   }

   @PostConstruct
   public void init() {   
      theme = gp.getTheme();
      
      advancedThemes = new ArrayList<Theme>();
      advancedThemes.add(new Theme("afterdark", "afterdark.png"));
      advancedThemes.add(new Theme("afternoon", "afternoon.png"));
      advancedThemes.add(new Theme("afterwork", "afterwork.png"));
      advancedThemes.add(new Theme("aristo", "aristo.png"));
      advancedThemes.add(new Theme("black-tie", "black-tie.png"));
      advancedThemes.add(new Theme("blitzer", "blitzer.png"));
      advancedThemes.add(new Theme("bluesky", "bluesky.png"));
      advancedThemes.add(new Theme("bootstrap", "bootstrap.png"));
      advancedThemes.add(new Theme("casablanca", "casablanca.png"));
      advancedThemes.add(new Theme("cruze", "cruze.png"));
      advancedThemes.add(new Theme("cupertino", "cupertino.png"));
      advancedThemes.add(new Theme("dark-hive", "dark-hive.png"));
      advancedThemes.add(new Theme("dot-luv", "dot-luv.png"));
      advancedThemes.add(new Theme("eggplant", "eggplant.png"));
      advancedThemes.add(new Theme("excite-bike", "excite-bike.png"));
      advancedThemes.add(new Theme("flick", "flick.png"));
      advancedThemes.add(new Theme("glass-x", "glass-x.png"));
      advancedThemes.add(new Theme("home", "home.png"));
      advancedThemes.add(new Theme("hot-sneaks", "hot-sneaks.png"));
      advancedThemes.add(new Theme("humanity", "humanity.png"));
      advancedThemes.add(new Theme("le-frog", "le-frog.png"));
      advancedThemes.add(new Theme("midnight", "midnight.png"));
      advancedThemes.add(new Theme("mint-choc", "mint-choc.png"));
      advancedThemes.add(new Theme("overcast", "overcast.png"));
      advancedThemes.add(new Theme("pepper-grinder", "pepper-grinder.png"));
      advancedThemes.add(new Theme("redmond", "redmond.png"));
      advancedThemes.add(new Theme("rocket", "rocket.png"));
      advancedThemes.add(new Theme("sam", "sam.png"));
      advancedThemes.add(new Theme("smoothness", "smoothness.png"));
      advancedThemes.add(new Theme("south-street", "south-street.png"));
      advancedThemes.add(new Theme("start", "start.png"));
      advancedThemes.add(new Theme("sunny", "sunny.png"));
      advancedThemes.add(new Theme("swanky-purse", "swanky-purse.png"));
      advancedThemes.add(new Theme("trontastic", "trontastic.png"));
      advancedThemes.add(new Theme("ui-darkness", "ui-darkness.png"));
      advancedThemes.add(new Theme("ui-lightness", "ui-lightness.png"));
      advancedThemes.add(new Theme("vader", "vader.png"));      
   }

   public void saveTheme() {
      gp.setTheme(theme);
   }

   public List<Theme> getAdvancedThemes() {
      return advancedThemes;
   }
}