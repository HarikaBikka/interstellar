/**
 * 
 */
package za.co.discovery.assignment.web.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import za.co.discovery.assignment.web.dto.PlanetDto;
import za.co.discovery.assignment.web.service.InterstellarService;

/**
 * @author Harika
 *
 */

@ManagedBean(name = "interstellarBean", eager = true)
@RequestScoped
public class InterstelllarManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sourcePlanet;
	private String destPlanet;
	private Double shortestPathDistance;
	private List<PlanetDto> shortesPath;
	private boolean traffic;
	private List<PlanetDto> planets;

	@Autowired
	InterstellarService interstellarService;

	@PostConstruct
	public void init() {
		WebApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		interstellarService = ctx.getBean(InterstellarService.class);
		planets = interstellarService.getAllPlanets();
	}

	public String getSourcePlanet() {
		return sourcePlanet;
	}

	public void setSourcePlanet(String sourcePlanet) {
		this.sourcePlanet = sourcePlanet;
	}

	public String getDestPlanet() {
		return destPlanet;
	}

	public void setDestPlanet(String destPlanet) {
		this.destPlanet = destPlanet;
	}

	public void calculateShortestPath() {

		Map<Double, List<PlanetDto>> shotestPathData = interstellarService.getShotestPath(this.sourcePlanet,
				this.destPlanet, this.traffic);

		// no need to worry about the loop below. It contains only one element.
		for (Entry<Double, List<PlanetDto>> entry : shotestPathData.entrySet()) {
			this.shortestPathDistance = entry.getKey();
			this.shortesPath = entry.getValue();
		}

	}

	public Double getShortestPathDistance() {
		return shortestPathDistance;
	}

	public void setShortestPathDistance(Double shortestPathDistance) {
		this.shortestPathDistance = shortestPathDistance;
	}

	public List<PlanetDto> getShortesPath() {
		return shortesPath;
	}

	public void setShortesPath(List<PlanetDto> shortesPath) {
		this.shortesPath = shortesPath;
	}

	public boolean isTraffic() {
		return traffic;
	}

	public void setTraffic(boolean traffic) {
		this.traffic = traffic;
	}

	public List<PlanetDto> getPlanets() {
		return planets;
	}

	public void setPlanets(List<PlanetDto> planets) {
		this.planets = planets;
	}

}
