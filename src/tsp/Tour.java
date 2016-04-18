/*
 * Tour.java
 * Stores a candidate tour
 */

package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {

	// Holds our tour of cities
	private ArrayList<City> tour = new ArrayList<City>();
	// Cache
	private double fitness = 0;
	private int distance = 0;
	
	// Constructs a blank tour
	public Tour() {
		for (int i = 0; i < TourManager.numberOfCities(); i++) {
			tour.add(null);
		}
	}
	
	public Tour(ArrayList<City> tour) {
		this.tour = tour;
	}
	
	// Creates a random individual
	public void generateIndividual() {
		// Loop through all destination cities, add them to tour
		for (int cityIndex = 0; 
				cityIndex < TourManager.numberOfCities(); 
				cityIndex++) {
			setCity(cityIndex, TourManager.getCity(cityIndex));
		}
		// Randomly reorder tour
		Collections.shuffle(tour);
	}
	
	// Gets a city from the tour
	public City getCity(int tourPosition) {
		return (City)tour.get(tourPosition);
	}
	
	// Sets a city in a certain position within a tour
	public void setCity(int tourPosition, City city) {
		tour.set(tourPosition, city);
		// If the tour was altered, we need to reset fitness and distance
		fitness = 0;
		distance = 0;
	}
	
	// Gets tours fitness
	public double getFitness() {
		if (fitness == 0) {
			fitness = 1/(double)getDistance();
		}
		return fitness;
	}
	
	// Gets total distance of tour
	public int getDistance() {
		if (distance == 0) {
			int tourDistance = 0;
			// Loop through our tour's cities
			for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
				// Get city we're traveling from
				City fromCity = getCity(cityIndex);
				// City we're traveling to
				City destinationCity;
				// Check we're not on the last city, if so, 
				// set the next city to the starting city
				if (cityIndex + 1 < tourSize()) {
					destinationCity = getCity(cityIndex + 1);
				} else {
					destinationCity = getCity(0);
				}
				//Get distance between two cities
				tourDistance += fromCity.distanceTo(destinationCity);
			}
			distance = tourDistance;
		}
		return distance;
	}
	
	public int tourSize() {
		return tour.size();
	}
	
	// Check if tour contains a city
	public boolean containsCity(City city) {
		return tour.contains(city);
	}
	
	@Override
	public String toString() {
		String geneString = "|";
		for (int i = 0; i < tourSize(); i++) {
			geneString += getCity(i) + "|";
		}
		return geneString;
	}
}
