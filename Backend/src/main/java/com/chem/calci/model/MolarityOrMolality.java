package com.chem.calci.model;

public class MolarityOrMolality {
	private String solution;
	private float solutionWeight;
	private String solute;
	private float soluteWeight;
	private float molarityOrMolality;
	private String type;
	private float specificGravity;
	
	public MolarityOrMolality() {}

	public MolarityOrMolality(String solution, float solutionWeight, String solute, float soluteWeight,
			float molarityOrMolality, String type, float specificGravity) {
		super();
		this.solution = solution;
		this.solutionWeight = solutionWeight;
		this.solute = solute;
		this.soluteWeight = soluteWeight;
		this.molarityOrMolality = molarityOrMolality;
		this.type = type;
		this.specificGravity = specificGravity;
	}





	public String getsolution() {
		return solution;
	}
	public void setsolution(String solution) {
		this.solution = solution;
	}
	public float getsolutionWeight() {
		return solutionWeight;
	}
	public void setsolutionWeight(float solutionWeight) {
		this.solutionWeight = solutionWeight;
	}
	public String getSolute() {
		return solute;
	}
	public void setSolute(String solute) {
		this.solute = solute;
	}
	public float getSoluteWeight() {
		return soluteWeight;
	}
	public void setSoluteWeight(float soluteWeight) {
		this.soluteWeight = soluteWeight;
	}
	public float getmolarityOrMolality() {
		return molarityOrMolality;
	}
	public void setmolarityOrMolality(float molarityOrMolality) {
		this.molarityOrMolality = molarityOrMolality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public float getSpecificGravity() {
		return specificGravity;
	}


	public void setSpecificGravity(float specificGravity) {
		this.specificGravity = specificGravity;
	}
	
	
}
