package gameplayer.model;
import java.util.HashMap;
import java.util.Observable;

import gameplayer.loader.*;
import gameplayer.view.GridGUI;

public class GamePlayData  extends Observable{
	private GamePlayerFactory factory;
	private int currentLevel;

	private int cellSize;
	private Grid grid;
	private Cell[][] gridArray;
	private int gridX;
	private int gridY;
	private double gold;
	private double lives;
	private double numLevels; // reach level number winning the game
	


	public GamePlayData(GamePlayerFactory factory){
		this.factory = factory;
	}
	
	/**
	 * could be used when start another game
	 * 
	 * @param factory
	 */
	public void initializeGameSetting() {
		HashMap<String, Double> settingInfo = this.factory.getGameSetting();
		this.numLevels = settingInfo.get("totalNumberOfLevels");  // put into property file
		this.gold = settingInfo.get("gold");
		this.lives = settingInfo.get("lives");
		this.currentLevel = 0;
	}
	
	public void initializeLevelInfo() {
		setLevel(this.currentLevel++);
		this.grid = this.factory.getGrid(this.currentLevel);
		gridArray = this.grid.getGrid();
		this.gridX = this.gridArray.length;
		this.gridY = this.gridArray[0].length;
	}

	public GamePlayerFactory getFactory(){
		return this.factory;
	}
	
	public int getRow() {
		return this.gridX;
	}

	public int getColumns() {
		return this.gridY;
	}


	public Grid getGrid() {
		System.out.println("Successfully got grid");
		return this.grid;
	}
	
	public Cell[][] getGridArray() {
		return this.gridArray;
	}

	public int getLevelNumber() {
		return (int) this.numLevels;
	}

	public double getGold() {
		return gold;
	}

	public void setGold(double gold) {
		this.gold = gold;
		setChanged();
		notifyObservers();
	}

	public double getLife() {
		return this.lives;
	}

	// used by enemymodel
	public void setLife(double life) {
		this.lives = life;
		setChanged();
		notifyObservers();
	}

	public void setLevel(int d) {
		this.currentLevel = d;
		setChanged();
		notifyObservers();
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

	public double getCellWidth() {
		return GridGUI.GRID_WIDTH / this.getColumns();
	}

	public double getCellHeight() {
		return GridGUI.GRID_WIDTH / this.getRow();
	}

	public double cellToCoordinate(double d) {
		return (d + 0.5) * cellSize;
	}
	
	
	public int getCellSize(){
		return this.cellSize;
	}
	
	/*
	 * utility methods for each single classes
	 */
	public Boolean coordinateInBound(double d, double e) {
		return (d < this.gridX * cellSize && e < this.gridY * cellSize);
	}

	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}


}