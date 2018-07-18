package com.poka.app.anno.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poka.app.anno.base.dao.impl.PayBagDao;
import com.poka.app.anno.base.dao.impl.PayBundleDao;
import com.poka.app.anno.base.dao.impl.PayOrderDao;
import com.poka.app.anno.base.dao.impl.PayOrderDetailDao;
import com.poka.app.anno.base.dao.impl.QryApplyDao;
import com.poka.app.anno.base.dao.impl.ReserveDetailDao;
import com.poka.app.anno.base.dao.impl.ReserveMainDao;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.anno.enity.PayOrder;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.anno.enity.ReserveMain;
import com.poka.app.enumtype.OrderType;
import com.poka.app.enumtype.StateType;
import com.poka.app.vo.AppointmenResult;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@Service
public class OrderInfoService extends BaseService<OrderInfo, Integer> {
	private ReserveMainDao reserveMainDao;
	private ReserveDetailDao reserveDetailDao;
	
	private PayOrderDao payOrderDao;
	private PayOrderDetailDao payOrderDetailDao;
	private PayBagDao  payBagDao;
	private PayBundleDao payBundleDao;
	
	private QryApplyDao qryApplyDao;
	

	
	@Autowired
	public void setPayOrderDao(PayOrderDao payOrderDao) {
		this.payOrderDao = payOrderDao;
	}
	
	@Autowired
	public void setQryApplyDao(QryApplyDao qryApplyDao) {
		this.qryApplyDao = qryApplyDao;
	}
	@Autowired
	public void setPayOrderDetailDao(PayOrderDetailDao payOrderDetailDao) {
		this.payOrderDetailDao = payOrderDetailDao;
	}
	@Autowired
	public void setPayBagDao(PayBagDao payBagDao) {
		this.payBagDao = payBagDao;
	}
	@Autowired
	public void setPayBundleDao(PayBundleDao payBundleDao) {
		this.payBundleDao = payBundleDao;
	}
	
	@Autowired
	public void setReserveMainDao(ReserveMainDao reserveMainDao) {
		this.reserveMainDao = reserveMainDao;
	}
	@Autowired
	public void setReserveDetailDao(ReserveDetailDao reserveDetailDao) {
		this.reserveDetailDao = reserveDetailDao;
	}
    
	public List<OrderInfo> getUnsendOrder(OrderType type){
		String hql = "from OrderInfo o where o.orderType = :orderType and o.state=0";
		Map<String, OrderType> map = new HashMap();
		map.put("orderType", type);
		//根据hql返回一个list<OrderInfo>
		return this.getBaseDao().find(hql,map);
	}
	
	public AppointmentVo getAppointmentVo(String orderId){
		AppointmentVo vo = new AppointmentVo();
		String hql = "from ReserveMain r where r.rsvNo=:orderId";
		Map<String, Object> map = new HashMap();
		map.put("orderId", orderId);
		vo.setReserveMain((ReserveMain)reserveMainDao.findUnique(hql, map));
		String hql2 = "from ReserveDetail r where r.rsvNo=:orderId";
		vo.setReserveDetails(reserveDetailDao.getDetails(hql2, map));
		return vo;
	}
	
	/**
	 * 获取预约结果
	 * @param orderId
	 * @return 返回一个AppointmentResult
	 */
	public AppointmenResult getAppointmenResult(String orderId){
		AppointmenResult vo = new AppointmenResult();
		String hql = "from ReserveMain r where r.rsvNo=:orderId";
		Map<String, Object> map = new HashMap();
		map.put("orderId", orderId);
		ReserveMain main = (ReserveMain)reserveMainDao.findUnique(hql, map);
		vo.setCheckRemark(main.getCheckRemark());
		vo.setState(main.getState());
		vo.setOrderId(orderId);
		return vo;
	}
	
	/**
	 * 更新订单信息状态
	 * @param order
	 * @param state
	 */
	public void updateOrderInfoState(OrderInfo order,StateType state){
//		String hql = " update OrderInfo o set o.state=:state where o.orderId=:orderId and o.orderType=:orderType  ";
		String hql = " update OrderInfo o set o.state=:state, o.finishDate=:finishDate where o.orderId=:orderId and o.orderType=:orderType";
		Map<String, Object> map = new HashMap();
		map.put("orderId", order.getOrderId());
		map.put("state", state);
		map.put("orderType", order.getOrderType());
		map.put("finishDate",getTimestamp());
		this.getBaseDao().batchExecute(hql,map);
	}
	
	/**
	 * 保存预约结果
	 * @param appointment
	 * @return boolean
	 */
	public boolean saveAppointmentResult(AppointmenResult appointment){
		if(appointment == null)return false;
		String hql = "from ReserveMain r where r.rsvNo=:orderId";
		Map<String, String> map = new HashMap();
		map.put("orderId", appointment.getOrderId());
		ReserveMain apply = reserveMainDao.findUnique(hql, map);
		if(apply == null) return false;
		apply.setState(appointment.getState());
		apply.setCheckRemark(appointment.getCheckRemark());
		reserveMainDao.save(apply);
		return true;
	}
	
	/**
	 * 
	 * 2017年6月20日
	 * @return PaymentVo
	 * @param orderId
	 * 
	 */
	public PaymentVo getPaymentVo(String orderId){
		PaymentVo vo = new PaymentVo();
		String hql = "from PayOrder r where r.orderId=:orderId";
		Map<String, String> map = new HashMap();
		map.put("orderId", orderId);
//		System.out.println(payOrderDao.findUnique(hql, map));
		vo.setPayOrder((PayOrder)payOrderDao.findUnique(hql, map));
		String hql2 = "from PayOrderDetail r where r.orderId=:orderId";
		vo.setPayOrderDetails(payOrderDetailDao.getDetails(hql2, map));
		String hql3 = "from PayBag r where r.orderId=:orderId";
		vo.setPayBags(payBagDao.getDetails(hql3, map));
		String hql4 = "from PayBundle r where r.orderId=:orderId";
		vo.setPayBundles(payBundleDao.getDetails(hql4, map));
		return vo;
	}
	
	public QryApply getQryApply(String orderId){
		QryApply vo = new QryApply();
		String hql = "from QryApply q where q.orderId=:orderId";
		Map<String, String> map = new HashMap();
		map.put("orderId", orderId);
		vo = qryApplyDao.findUnique(hql,map);
		return vo;
	}
	
	 // 返回当前日期-时间
    public static java.sql.Timestamp getTimestamp() {
        try {
            java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            String mystrdate = myFormat.format(calendar.getTime());
            return java.sql.Timestamp.valueOf(mystrdate);
        }
        catch (Exception e) {
            return null;
        }
    }
}
