package specific;

import general.SimulationA;

public class PathSimulation extends SimulationA {
	
	private int max_ind, init_pop;
	private boolean final_point_hit;
	private double sensivity, death, reproduction, move;
	
	
	public int getMax_ind() {
		return max_ind;
	}

	public void setMax_ind(int max_ind) {
		this.max_ind = max_ind;
	}

	public int getInit_pop() {
		return init_pop;
	}

	public void setInit_pop(int init_pop) {
		this.init_pop = init_pop;
	}

	public boolean isFinal_point_hit() {
		return final_point_hit;
	}

	public void setFinal_point_hit(boolean final_point_hit) {
		this.final_point_hit = final_point_hit;
	}

	public double getSensivity() {
		return sensivity;
	}

	public void setSensivity(double sensivity) {
		this.sensivity = sensivity;
	}

	public double getDeath() {
		return death;
	}

	public void setDeath(double death) {
		this.death = death;
	}

	public double getReproduction() {
		return reproduction;
	}

	public void setReproduction(double reproduction) {
		this.reproduction = reproduction;
	}

	public double getMove() {
		return move;
	}

	public void setMove(double move) {
		this.move = move;
	}


	@Override
	public void simulate() {
		// TODO Auto-generated method stub

	}
	

}
