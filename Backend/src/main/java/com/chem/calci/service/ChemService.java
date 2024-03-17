package com.chem.calci.service;

import java.util.List;

import com.chem.calci.model.MolarWeight;
import com.chem.calci.model.MolarityOrMolality;
import com.chem.calci.model.Molefraction;

public interface ChemService {

	public float calculateMolarity(MolarityOrMolality m) throws Exception;
	public float calculateMolality(Molefraction m) throws Exception;
	public List<Float> calculateMolefraction(Molefraction m) throws Exception;
	public float calculateMolarWeight(MolarWeight m) throws Exception;
	
}
