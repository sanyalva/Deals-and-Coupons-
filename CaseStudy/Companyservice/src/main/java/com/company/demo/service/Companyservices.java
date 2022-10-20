package com.company.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.demo.entities.Company;
import com.company.demo.repository.CompanyRepos;

public class Companyservices {
	
	
	@Autowired
	public CompanyRepos companyrepos;
	
	public ResponseEntity<?> servicecreatecoupons(Company com)
	{
		try {
			
			Company savedcomp = this.companyrepos.save(com);
			return new ResponseEntity<>("Added successfully",HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<String> servicedeleteCouponsBycompanyId(int company_id){
	companyrepos.deleteById(company_id);
	return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
	}
	
	public List<Company> servicegettotalorders() {
		return companyrepos.findAll();
		}
	
	
	public ResponseEntity<?> serviceupdateById(Company company,int company_id){
		Optional<Company> comp= companyrepos.findById(company_id);
		try {
		if(comp.isPresent()) {
			company.setCompany_id(company_id);
	        companyrepos.save(company);
	        //return company;
	        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
		
			} 
			else {
				return new ResponseEntity<>("Record Not found",HttpStatus.OK);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public List<?> servicegetCouponByCompanyName(String company_name) {
		
		return companyrepos.findByName(company_name);}
	
	
	public List<?> servicegetCouponByProductCategory(String product_category) {
		
		return companyrepos.findByProductCategory(product_category);}
	
}
