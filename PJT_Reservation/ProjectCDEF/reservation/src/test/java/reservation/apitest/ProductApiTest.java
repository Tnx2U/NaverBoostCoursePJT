package reservation.apitest;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import chw.intern.nts.reservation.dao.DisplayInfoDao;

public class ProductApiTest {
	static Scanner scan = new Scanner(System.in);
	
	@Autowired
	static
	DisplayInfoDao displayInfoDao;
	
	public static void main(String[] args) {
		checkDisplayDao();
	}

	private static void checkDisplayDao() {
		checkGetProductIdByDisplayInfoId();
	}

	private static void checkGetProductIdByDisplayInfoId() {
		System.out.println("Check DisplayDao : GetProductIdByDisplayInfoId()");
		int displayInfoId = scan.nextInt();
		System.out.println(displayInfoDao.selectProductIdById(displayInfoId));		
	}
}
