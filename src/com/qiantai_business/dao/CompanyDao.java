package com.qiantai_business.dao;

import java.util.List;

import com.qiantai_business.po.CompanyPo;

public interface CompanyDao {
	
	public void insertCompanyInfo(CompanyPo companyinfo);
	
	public List<CompanyPo> getCompanyInfo();
	
	public void updateCompanyIntro(String company_intro);
	
	public void updateCompanyCulture(String company_cluture);
	
	public void updateCompanyHistory(String company_history);
	
	public void updateCompanyFaxnum(String company_faxnum);

}
