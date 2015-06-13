package br.com.imovelhunterweb.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.imovelhunterweb.entitys.Imovel;
import br.com.imovelhunterweb.util.UtilSession;

@ManagedBean(name ="markerView")
@ViewScoped
public class MarkerView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7515266740790642850L;
	 private MapModel draggableModel;
	 private Marker marker;
	 private Imovel imovel;
	 
	@PostConstruct
	public void init(){		
		draggableModel = new DefaultMapModel();
		this.imovel = (Imovel) UtilSession.getHttpSessionObject("EnderecoImovel");
        
        //Shared coordinates
        LatLng coord1 = new LatLng(-8.1174558, -34.9010799);
          
        //Draggable
        draggableModel.addOverlay(new Marker(coord1, "Fernando Simões Barbosa"));
          
        for(Marker premarker : draggableModel.getMarkers()) {
            premarker.setDraggable(true);
        }
	}
	
    public MapModel getDraggableModel() {
        return draggableModel;
    }
      
    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
          
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
    }        
	
	


}
