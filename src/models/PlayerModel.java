/**
 * 
 */
package models;

import java.util.ArrayList;

import utils.EnumClass.Color;

/**
 * Player Model class to save the player information.
 */
public class PlayerModel {

	private Color color;
	private ArrayList<CountryModel> countiresList;
	private ArrayList<ContinentModel> continentsList;
	private ArrayList<UnitModel> army;
	private ArrayList<CardModel> deck;
	private boolean isActive;

	/**
	 * 
	*/
	public PlayerModel(Color color) {
		this.setColor(color);
		this.setActive(true);
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the colour
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the colour to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the countryModels
	 */
	public ArrayList<CountryModel> getCountries() {
		return countiresList;
	}

	/**
	 * @param countryModels the countryModels to set
	 */
	public void setCountries(ArrayList<CountryModel> countiresList) {
		this.countiresList = countiresList;
	}

	/**
	 * @return the continentModels
	 */
	public ArrayList<ContinentModel> getContinents() {
		return continentsList;
	}

	/**
	 * @param continentModels the continentModels to set
	 */
	public void setContinents(ArrayList<ContinentModel> continentsList) {
		this.continentsList = continentsList;
	}

	/**
	 * @return the army
	 */
	public ArrayList<UnitModel> getArmy() {
		return army;
	}

	/**
	 * @param army the army to set
	 */
	public void setArmy(ArrayList<UnitModel> army) {
		this.army = army;
	}

	public void addCountryToPlayer(CountryModel country) {
		if (this.getCountries() == null) {
			this.setCountries(new ArrayList<CountryModel>());
		}
		this.countiresList = this.getCountries();
		this.countiresList.add(country);
		this.setCountries(countiresList);
	}

	public void removeCountryFromPlayer(CountryModel country) {
		if (this.getCountries() != null) {
			this.countiresList = this.getCountries();
			this.countiresList.remove(country);
			this.setCountries(countiresList);
		}
	}

	/**
	 * @return the deck
	 */
	public ArrayList<CardModel> getDeck() {
		return deck;
	}

	/**
	 * @param deck the deck to set
	 */
	public void setDeck(ArrayList<CardModel> deck) {
		this.deck = deck;
	}

}