//package module6;
//
//import de.fhpotsdam.unfolding.marker.MultiMarker;
//import de.fhpotsdam.unfolding.marker.SimplePolygonMarker;
//import processing.core.PConstants;
//import processing.core.PGraphics;
//import de.fhpotsdam.unfolding.data.Feature;
//import de.fhpotsdam.unfolding.data.PointFeature;
//import de.fhpotsdam.unfolding.geo.Location;
//
//public class CountryMarker extends MultiMarker {
//	
//	public CountryMarker(Feature country) {
//		
//		super();
//		
//	}
//	
//	public void showTitle(PGraphics pg, float x, float y)
//	{
//		String title = getTitle();
//		pg.pushStyle();
//		
//		pg.rectMode(PConstants.CORNER);
//		
//		pg.stroke(110);
//		pg.fill(255,255,255);
//		pg.rect(x, y + 15, pg.textWidth(title) +6, 18, 5);
//		
//		pg.textAlign(PConstants.LEFT, PConstants.TOP);
//		pg.fill(0);
//		pg.text(title, x + 3 , y +18);
//		
//		
//		pg.popStyle();
//		
//	}
//	
//	public void draw(PGraphics pg, float x, float y) {
//		// For starter code just drawMaker(...)
//		super.draw(pg, x, y);
////		drawMarker(pg, x, y);
////		if (selected) {
////			showTitle(pg, x, y);
////		}
//	}
//	
//	public String getTitle() {
//		return (String) getProperty("title");	
//		
//	}
//
//}
