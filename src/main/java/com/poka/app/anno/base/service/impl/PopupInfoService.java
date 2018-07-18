package com.poka.app.anno.base.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.PopupInfo;

import oracle.jdbc.OracleTypes;

@Service
public class PopupInfoService extends BaseService<PopupInfo, String> {
	
	/**
	 * 调用存储过程获取冠字号详细信息
	 * 2017年10月19日
	 * @author Enma.ai
	 * @return List<PopupInfo>
	 * @param mon
	 * @return
	 * @throws Exception
	 */
	public List<PopupInfo> findPopupInfoList(String mon) throws Exception {
		if (mon == null) {
			throw new Exception();
		}
		String sql = "{CALL P_MonInfo(?,?)}";
		List<PopupInfo> popupList = new ArrayList<PopupInfo>();
		
		/**
		 * 使用Hibernate方法调用带参数，返回游标的存储过程
		 */
		Connection connection = null;
		CallableStatement sp = null;
		try {
			/*从Hibernate中获取数据库连接*/
			connection = SessionFactoryUtils.getDataSource(this.getBaseDao().getSessionFactory()).getConnection();
			/*创建数据库调用的Statement*/
			sp = connection.prepareCall(sql);
			//设置参数1表示参数的位置和第一个“？”对应，
			//registerOutParameter是设置输出类型参数的所调用的方法，OracleTypes.CURSOR 是游标类型
			sp.setString(1, mon);
			sp.registerOutParameter(2, OracleTypes.CURSOR);
			//参数设置好了以后，执行
			sp.execute();
			// 获取返回的对象,再将对象转为记录集  1代表第一个参数
			ResultSet rs = (ResultSet) sp.getObject(2); 
			//因为第一个返回类型为游标，里面保存的是返回的结果集，获取游标的元数据，本质是list
			ResultSetMetaData metaData = rs.getMetaData();
			//再获取元数据的（list）的大小，即结果集的列数
			int columnCount = metaData.getColumnCount();
			//设置好时间函数
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//遍历元数据
			while (rs.next()) {
				//储存查询到的数据
				PopupInfo popupInfo = new PopupInfo();
				for (int i = 1; i <= columnCount; i++) {
			        //columnName就是对应字段的名字 
			        String columnName =metaData.getColumnLabel(i);
			        //rs.getString(columnName)就是该字段对应的值
			        switch (i) {
					case 1:
						popupInfo.setOperdate(sdf.format(rs.getDate(columnName)));
						break;
					case 2:
						popupInfo.setOpertype(rs.getString(columnName));
						
						break;
					case 3:
						popupInfo.setMemo(rs.getString(columnName));
						break;
					case 4:
						popupInfo.setOperator(rs.getString(columnName));
						break;
					case 5:
						popupInfo.setChecker(rs.getString(columnName));
						break;
					}
				}
				popupList.add(popupInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (null != connection) {
				connection.close();
			}
		}
		return popupList;
	}

}
