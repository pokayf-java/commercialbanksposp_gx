package com.poka.app.anno.base.service;

import java.util.List;




public interface IBankAndNetRepService {

	public List findBankRepDataList(String tabName) throws Exception;

	public int getCount(String tabName) throws Exception;
	
}
