package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.AdminDao;
import kr.or.bit.dao.SaleDao;
import kr.or.bit.dto.GenericUser;
import kr.or.bit.dto.Sale;

public class InsertSaleService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		//1. 데이터 받기 id, pwd, name, frontResNum, backResNum, phoneNum, addr
		//String aptNum = request.getParameter("aptNum");
		
		String aptSize = "";		
		String[] aptSizes = request.getParameterValues("aptSize");
		
		for(String value : aptSizes) {
			aptSize = value;
		}
		//System.out.println("면적" + aptSize);
		String type = "";
		String[] types = request.getParameterValues("type");
		for(String value : types) {
			type = value;
		}
		String addr = request.getParameter("addr");
		String aptName = request.getParameter("aptName");
		String aptDong = request.getParameter("aptDong");
		String aptHo = request.getParameter("aptHo");
		int price = Integer.parseInt(request.getParameter("price"));
		String directions[] = request.getParameterValues("direction");
		String direction = "";
		for(String value : directions) {
			direction = value;
		}
		String etc = request.getParameter("etc");
		String isContract = request.getParameter("isContract");
		String id = (String) session.getId(); //reaId받아오기
		

		//2. 객체에 데이터 저장
		
		Sale sale = new Sale(); 
		sale.setAptSize(aptSize); 
		sale.setType(type);
		sale.setAddr(addr); 
		sale.setAptName(aptName);
		sale.setAptDong(aptDong);
		sale.setAptHo(aptHo); 
		sale.setPrice(price); 
		sale.setDirection(direction);
		sale.setEtc(etc); 
		sale.setIsContract(isContract);
		sale.setId(id);
		
		
		System.out.println("데이터" + sale.toString());
		
		try {
			SaleDao dao = new SaleDao();
			dao.insertSale(sale);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("insertSaleService 실패");
		}
		
		return forward;
	}
}