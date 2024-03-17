package com.chem.calci.model;

public class Molefraction {
	private String solute;
	private String solvent;
	private float soluteWeight;
	private float solventWeight;
	public Molefraction(String solute, String solvent, float soluteWeight, float solventWeight) {
		

		this.solute = solute;
		this.solvent = solvent;
		this.soluteWeight = soluteWeight;
		this.solventWeight = solventWeight;
	}
	
	public Molefraction() {}
	public String getSolute() {
		return solute;
	}
	public void setSolute(String solute) {
		this.solute = solute;
	}
	public String getSolvent() {
		return solvent;
	}
	public void setSolvent(String solvent) {
		this.solvent = solvent;
	}
	public float getSoluteWeight() {
		return soluteWeight;
	}
	public void setSoluteWeight(float soluteWeight) {
		this.soluteWeight = soluteWeight;
	}
	public float getSolventWeight() {
		return solventWeight;
	}
	public void setSolventWeight(float solventWeight) {
		this.solventWeight = solventWeight;
	}
	
	
	
	}


