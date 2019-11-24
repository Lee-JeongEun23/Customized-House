package kr.or.bit.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.SaleImageDao;
import kr.or.bit.dto.SaleImage;

public class GetSaleImgService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String aptNum = request.getParameter("aptNum"); //매물 번호 받아오기
		System.out.println("매매애매매애애애물 번호"+aptNum);
		List<SaleImage> saleImgList = new ArrayList<SaleImage>();
		try {
			SaleImageDao dao = new SaleImageDao();
			saleImgList = dao.getSaleImgList(aptNum);
			if(saleImgList!=null) {
				System.out.println("매물 사진 가져오기 성공");
				request.setAttribute("saleImgList", saleImgList);
				forward.setPath("SaleEdit2.jsp");
				System.out.println("가자~~!");
			}
			
			
		}catch(Exception e) {
			
		}
		
		return forward;
	}

}
