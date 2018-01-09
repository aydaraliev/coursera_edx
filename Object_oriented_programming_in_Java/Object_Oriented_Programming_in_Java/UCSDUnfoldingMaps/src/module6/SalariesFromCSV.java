package module6;

import processing.core.PApplet;
import processing.core.PConstants;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.providers.Google.*;

import java.util.List;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;

import java.util.HashMap;


import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;

public class SalariesFromCSV extends PApplet {
	
	UnfoldingMap map;
	HashMap<String, Float> SalariesMap;
	List<Feature> countries;
	List<Marker> countryMarkers;
	private Marker lastSelected;
	
	public void setup() {
		size(1600, 1200, OPENGL);
		map = new UnfoldingMap(this, 0, 0, 1600, 1200, new OpenStreetMap.OpenStreetMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		SalariesMap = ParseFeed.loadSalariesFromCSV(this,"wage_and_salary_ILO.csv");
		
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		System.out.println(countryMarkers.get(0).getId());
		
		shadeCountries();

	}
	
	public void draw() {
		// Draw map tiles and country markers
		map.draw();
		for (Marker m : countryMarkers) {
			if (m.isSelected()) {
				String cID = m.getId();
				float salariedWorkers = (float) 9.9;
				try {
					salariedWorkers = SalariesMap.get(cID);
				}
				catch (NullPointerException npe) {
					salariedWorkers = (float) -9.9;	
				}
				String toDisplay = cID + 
						": " + Float.toString(salariedWorkers);
				
				pushStyle();
				
				rectMode(PConstants.CORNER);
				
				textSize(26);
				
				stroke(110);
				fill(255,255,255);
				rect(mouseX+30, mouseY + 15, textWidth(toDisplay) +6, 31, 5);
				
				textAlign(PConstants.LEFT, PConstants.TOP);
				fill(0);
				text(toDisplay, mouseX + 32 , mouseY +18);
				
				
				popStyle();
			}
		}
	}
	
	private void shadeCountries() {
		for (Marker marker : countryMarkers) {
			// Find data for country of the current marker
			String countryId = marker.getId();
			System.out.println(countryId);
			System.out.println(SalariesMap.containsKey(countryId));
			if (SalariesMap.containsKey(countryId)) {
				float salariedWorkers = SalariesMap.get(countryId);
				// Encode value as brightness (values range: 40-90)
				int colorLevel = (int) map(salariedWorkers, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
	
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(countryMarkers);
		//loop();
	}
	
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers)
	{
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			Marker marker = m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}

}
