package data;

public class WaveManager {

	private float timeSinceLastWeve, timeBetweenEnemies;
	private int waveNumber, enemiesPerWave; 
	private Enemy enemyType;
	private Wave currentWave;
	
	public WaveManager(Enemy enemyType, float timeBetweenEnemies, int enemiesParWave) {
		
		this.timeSinceLastWeve = 0;
		this.waveNumber = 0;
		this.enemyType = enemyType;
		this.enemiesPerWave = enemiesParWave;
		this.timeBetweenEnemies = timeBetweenEnemies;
		
		this.currentWave = null;
		
		newWave();
	}
	
	public void update() {
		if(!currentWave.isCompleted()) 
			currentWave.update();
		else {
			System.out.println("wave is over!");
			newWave();
		}
	}
	
	private void newWave() {
		currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
		waveNumber++;
	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}
 	
}
