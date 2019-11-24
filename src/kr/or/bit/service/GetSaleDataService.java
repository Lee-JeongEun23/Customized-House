package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.REAUserDao;
import kr.or.bit.dao.SaleDao;
import kr.or.bit.dto.REAUser;
import kr.or.bit.dto.Sale;

public class GetSaleDataService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String aptNum =request.getParameter("aptNum");
		System.out.println("아파트 매물 번호"+aptNum);
		Sale sale = null;		
		try {
			SaleDao dao = new SaleDao();
			sale = new Sale();
			sale = dao.getSaleDetail(aptNum);
			if(sale !=null) {
				request.setAttribute("saleData", sale);
				forward.setPath("GetSaleDataServiceOk.d4b");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
