package com.chem.calci.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chem.calci.dao.ChemDao;
import com.chem.calci.model.MolarWeight;
import com.chem.calci.model.MolarityOrMolality;
import com.chem.calci.model.Molefraction;

@Service
public class ChemServiceImpl implements ChemService{
	@Autowired
	ChemDao chemDao;
	
	@Override
	public float calculateMolarity(MolarityOrMolality m) throws Exception {
		System.out.println("inside service");
		return chemDao.calculateMolarity(m);
		
	}

	@Override
	public float calculateMolality(Molefraction m) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("inside service");
		return chemDao.calculateMolality(m);
	}

	@Override
	public List<Float> calculateMolefraction(Molefraction m) throws Exception {
		// TODO Auto-generated method stub
		return chemDao.calculateMolefraction(m);
	}

	@Override
	public float calculateMolarWeight(MolarWeight m) throws Exception {
		
		return chemDao.calculateMolarWeight(m);
	}
	
	
}
