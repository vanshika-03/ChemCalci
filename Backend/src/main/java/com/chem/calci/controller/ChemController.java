package com.chem.calci.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chem.calci.exception.ResourceNotFoundException;
import com.chem.calci.model.MolarWeight;
import com.chem.calci.model.MolarityOrMolality;
import com.chem.calci.model.Molefraction;
import com.chem.calci.model.Response;
import com.chem.calci.service.ChemService;

@RestController
@RequestMapping("/chem")
public class ChemController {
	@Autowired
	ChemService chemService;
	@PostMapping("/molarity")
	public ResponseEntity<Response> calculateMolarity(@RequestBody MolarityOrMolality m) throws Exception {
		System.out.println(m.getsolution());
		Response res=new Response();
		try {
			float mol=chemService.calculateMolarity(m);
			
			List<Float> mols=new ArrayList();
			mols.add(mol);
			res.setResponse(mols);
			res.setStatusCode(200);
		}
		catch(ResourceNotFoundException e) {
			res.setError("Could not find compound");
			return new ResponseEntity<Response>(res,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(res,HttpStatus.OK);
	}
	@PostMapping("/molality")
	public ResponseEntity<Response> calculateMolality(@RequestBody Molefraction m) throws Exception {
		System.out.println(m.getSolute());
		Response res=new Response();
		try {
			float mol=chemService.calculateMolality(m);
			
			List<Float> mols=new ArrayList();
			mols.add(mol);
			res.setResponse(mols);
			res.setStatusCode(200);
		}
		catch(ResourceNotFoundException e) {
			res.setError("Could not find compound");
			return new ResponseEntity<Response>(res,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(res,HttpStatus.OK);
	}
	@PostMapping("/molefraction")
	public ResponseEntity<Response> calculateMolefraction(@RequestBody Molefraction m) throws Exception {
		System.out.println(m.getSolute());
		System.out.println(m.getSolventWeight());
		Response res=new Response();
		try {
			List<Float> mol = chemService.calculateMolefraction(m);
		
			res.setResponse(mol);
			res.setStatusCode(200);
		}
		catch(ResourceNotFoundException e) {
			res.setError("Could not find compound");
			return new ResponseEntity<Response>(res,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(res,HttpStatus.OK);
	}
	
	@PostMapping("/molarweight")
	public ResponseEntity<Response> calculateMolarWeight(@RequestBody MolarWeight m) throws Exception {
		System.out.println(m.getCompound());
		Response res=new Response();
		try {
			float mol = chemService.calculateMolarWeight(m);
			System.out.println(mol);
			List<Float> mols=new ArrayList();
			mols.add(mol);
			res.setResponse(mols);
			res.setStatusCode(200);
		}
		catch(ResourceNotFoundException e) {
			res.setError("Could not find compound");
			return new ResponseEntity<Response>(res,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Response>(res,HttpStatus.OK);
	}
}

