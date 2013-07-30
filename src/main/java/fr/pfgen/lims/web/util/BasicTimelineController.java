/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pfgen.lims.web.util;

import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@ManagedBean
@Controller
@Scope("view")
public class BasicTimelineController implements Serializable {

	private TimelineModel model;

	private boolean selectable = true;
	private boolean zoomable = true;
	private boolean moveable = true;
	private boolean stackEvents = true;
	private String eventStyle = "box";
	private boolean axisOnTop;
	private boolean showCurrentTime = true;
	private boolean showNavigation = false;

	@PostConstruct
	protected void initialize() {
		model = new TimelineModel();

		Calendar cal = Calendar.getInstance();
		cal.set(2011, Calendar.MAY, 10, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.1", cal.getTime()));

		cal.set(2012, Calendar.JANUARY, 23, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.2.0", cal.getTime()));

		cal.set(2012, Calendar.APRIL, 2, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.3.0", cal.getTime()));

		cal.set(2012, Calendar.APRIL, 16, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.4.0", cal.getTime()));

		cal.set(2012, Calendar.JUNE, 10, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.5.0", cal.getTime()));

		cal.set(2012, Calendar.JUNE, 19, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.5.1", cal.getTime()));

		cal.set(2012, Calendar.SEPTEMBER, 26, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.6.0", cal.getTime()));

		cal.set(2012, Calendar.OCTOBER, 13, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.6.1", cal.getTime()));

		cal.set(2012, Calendar.DECEMBER, 16, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.6.2", cal.getTime()));

		cal.set(2013, Calendar.FEBRUARY, 3, 0, 0, 0);
		model.add(new TimelineEvent("Primefaces-Extensions 0.6.3", cal.getTime()));
	}

	public void onSelect(TimelineSelectEvent e) {
		TimelineEvent timelineEvent = e.getTimelineEvent();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:", timelineEvent.getData().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public TimelineModel getModel() {
		return model;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isZoomable() {
		return zoomable;
	}

	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isStackEvents() {
		return stackEvents;
	}

	public void setStackEvents(boolean stackEvents) {
		this.stackEvents = stackEvents;
	}

	public String getEventStyle() {
		return eventStyle;
	}

	public void setEventStyle(String eventStyle) {
		this.eventStyle = eventStyle;
	}

	public boolean isAxisOnTop() {
		return axisOnTop;
	}

	public void setAxisOnTop(boolean axisOnTop) {
		this.axisOnTop = axisOnTop;
	}

	public boolean isShowCurrentTime() {
		return showCurrentTime;
	}

	public void setShowCurrentTime(boolean showCurrentTime) {
		this.showCurrentTime = showCurrentTime;
	}

	public boolean isShowNavigation() {
		return showNavigation;
	}

	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}
}
            