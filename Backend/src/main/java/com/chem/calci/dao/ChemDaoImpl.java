package com.chem.calci.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chem.calci.exception.ResourceNotFoundException;
import com.chem.calci.model.MolarWeight;
import com.chem.calci.model.MolarityOrMolality;
import com.chem.calci.model.Molefraction;

@Repository
public class ChemDaoImpl implements ChemDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	boolean checkNumeric(String str){
		boolean numeric = true;
		try {
            Double num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            numeric = false;
        }
		return numeric;
	}
	Map<String,Integer> getCompound(String sol) throws Exception{
		String temp="";
		Map<String,Integer> sols=new HashMap<String,Integer>();
		if(sol.length()>1) {
			String ele="";
			Integer mol = null;
			System.out.println("here is our compound");
		for(int i=0;i<sol.length();i++) {
			boolean putSol=false;
			if(checkNumeric(sol.charAt(i)+"")==false) {
				if((sol.charAt(i)+"").equals((sol.charAt(i)+"").toUpperCase())) {
					ele=sol.charAt(i)+"";
					if(i<sol.length()-1) {
							if(!((sol.charAt(i+1)+"").equals((sol.charAt(i+1)+"").toLowerCase()))
							&& !checkNumeric(sol.charAt(i+1)+"")) {
						System.out.println("here");
						mol=1;
						putSol=true;
					}
						
					}
					else {
						mol=1;
						putSol=true;
					}
				}
				if((sol.charAt(i)+"").equals((sol.charAt(i)+"").toLowerCase())) {
					if(i<sol.length()-1 && !checkNumeric(sol.charAt(i+1)+"") && (sol.charAt(i+1)+"").equals((sol.charAt(i+1)+"").toLowerCase())) {
						throw new ResourceNotFoundException();
					}
					ele+=sol.charAt(i);
					if(i==sol.length()-1) {
						mol=1;
						putSol=true;
					}
					else if(!checkNumeric(sol.charAt(i+1)+"")) {
						mol=1;
						putSol=true;
					}
				}
			}
			
			else if(checkNumeric(sol.charAt(i)+"")==true) {
				if(i!=sol.length()-1 && checkNumeric(sol.charAt(i+1)+"")==true) {
					temp+=sol.charAt(i);
				}
				else {
					temp+=sol.charAt(i);
					mol=Integer.parseInt(temp);
					putSol=true;
				}
				
			}
			System.out.println(ele);
			System.out.println(mol);
			if(putSol==true) {
				if(mol==null) {
					mol=1;
				}
				if(sols.get(ele) == null)
					sols.put(ele, mol);
				else
					sols.put(ele, mol+sols.get(ele));
				ele="";
				mol=null;
				putSol=false;
				temp="";
			}
		}
		for (Entry<String, Integer> entry : sols.entrySet()) 
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
    }
		return sols;
	}
	
	float getMolarWeight(Map<String,Integer> sols) throws Exception {
		float molarWeight = 0, weight;
		String sqlQuery="select MolecularWeight from elements where MolecularName=?";
		try {
			for (Entry<String, Integer> entry : sols.entrySet()) {
				weight=jdbcTemplate.queryForObject(sqlQuery, new Object[]{entry.getKey()}, Float.class);
				molarWeight+=weight*entry.getValue();
			
			}
		}
		catch(DataAccessException e) {
			throw new ResourceNotFoundException();
		}
		return molarWeight;
	}
	@Override
	public float calculateMolarity(MolarityOrMolality m) throws Exception {
		Map<String,Integer> sols;
		try {
		System.out.println("hello");
		String solute=m.getSolute();
		sols=getCompound(solute);
		}
		catch(DataAccessException e) {
			System.out.println("compound passed is wrong");
			throw new ResourceNotFoundException();
		}
		float molarWeight=getMolarWeight(sols);
		System.out.println("total molar weight: "+molarWeight);
		System.out.println(molarWeight);
		float moles;
		if(m.getType().equals("solid"))
			moles=m.getSoluteWeight()/molarWeight;
		else
			moles=m.getSpecificGravity()*m.getSoluteWeight()/molarWeight;
		System.out.println(moles);
		m.setmolarityOrMolality(1000*moles/m.getsolutionWeight());
		System.out.println(m.getmolarityOrMolality());
		
		return m.getmolarityOrMolality();
	}

	@Override
	public float calculateMolality(Molefraction m) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(m.getSolute());
		System.out.println();
		String solute=m.getSolute();
		float molality;
		try {
		Map<String,Integer> sols=getCompound(solute);
		float molarWeight=getMolarWeight(sols);
		System.out.println("total molar weight: "+molarWeight);
		System.out.println(molarWeight);
		System.out.println(m.getSoluteWeight()+""+m.getSolventWeight());
		float moles=m.getSoluteWeight()/molarWeight;
		System.out.println(moles);
		System.out.println(m.getSolventWeight());
		molality=(1000*moles/(m.getSolventWeight()));
		}
		catch(DataAccessException e) {
			System.out.println("compound passed is wrong");
			throw new ResourceNotFoundException();
		}
		System.out.println(molality);
		return molality;
	}

	@Override
	public List<Float> calculateMolefraction(Molefraction m) throws Exception {
		// TODO Auto-generated method stub
		String solute=m.getSolute();
		Map<String,Integer> solutes=getCompound(solute);
		String solvent=m.getSolvent();
		Map<String,Integer> solvents=getCompound(solvent);
		List<Float> molefraction;
		try {
		float molarWeightSolute=getMolarWeight(solutes);
		float molarWeightSolvent=getMolarWeight(solvents);
		float moleSolute=m.getSoluteWeight()/molarWeightSolute;
		float moleSolvent=m.getSolventWeight()/molarWeightSolvent;
		float moleFractionSolute = moleSolute/(moleSolute+moleSolvent);
		float moleFractionSolvent = moleSolvent/(moleSolute+moleSolvent);
		
		System.out.println("molarWeightSolute:"+molarWeightSolute);
		System.out.println("molarWeightSolvent:"+molarWeightSolvent);
		System.out.println("moleSolute:"+moleSolute);
		System.out.println("moleSolvent:"+moleSolvent);
		System.out.println("moleFractionSolute:"+moleFractionSolute);
		System.out.println("moleFractionSolvent:"+moleFractionSolvent);
		molefraction=new ArrayList<Float>();
		molefraction.add(moleFractionSolute);
		molefraction.add(moleFractionSolvent);
		}
		catch(Exception e) {
			System.out.println("compound passed is wrong");
			throw new ResourceNotFoundException();
		}
		return molefraction;
	}
	public float calculateMolarWeight(MolarWeight m) throws Exception {
		String comp=m.getCompound();
		Map<String,Integer> compound=getCompound(comp);
		float molarWeight;
		try {
		molarWeight=getMolarWeight(compound);
		}
		catch(DataAccessException e) {
			System.out.println("compound passed is wrong");
			throw new ResourceNotFoundException();
		}
		return molarWeight;
		
	}
	
}
