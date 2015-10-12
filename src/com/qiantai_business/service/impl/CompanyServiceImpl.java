package com.qiantai_business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiantai_business.dao.CompanyDao;
import com.qiantai_business.po.CompanyPo;
import com.qiantai_business.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao comapnyDao;

	public void insertCompanyInfo(CompanyPo companyinfo) {

		comapnyDao.insertCompanyInfo(companyinfo);
		
	}

	public List<CompanyPo> getCompanyInfo() {
		return comapnyDao.getCompanyInfo();
	}

	public void updateCompanyIntro(String company_intro) {

		comapnyDao.updateCompanyIntro(company_intro);
	}

	public void updateCompanyCulture(String company_cluture) {

		comapnyDao.updateCompanyCulture(company_cluture);
	}

	public void updateCompanyHistory(String company_history) {

		comapnyDao.updateCompanyHistory(company_history);
	}

	public void updateCompanyFaxnum(String company_faxnum) {

		comapnyDao.updateCompanyFaxnum(company_faxnum);
	}

}
