package kr.co.sist.test.day0320;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.day0320.Work;

class TestWork {
//	@DisplayName("0~9까지 난수 테스트")
//	@Test
//	void testRandomNum() {
//		//given
//		Work work = new Work();
//		//when
//		int num = work.randomNum();
//		System.out.println(num);
//		//then
//		assertNotEquals(num, 0);
//	}//testRandomNum
	
	@DisplayName("0~11까지 난수 테스트")
	@Test
	void testRandomNum() {
		//given
		Work work = new Work();
		//when
//		int dataSize = work.randomNum(); //DB를 조회했더니만 몇개의 행이 나올지 모름
		int dataSize = 30;
		System.out.println(dataSize);
		//then
		assertEquals(dataSize, 10, 10); //발생한값, 결과값, 오차범위
		//발생한 값이 결과값까지 오차범위 안쪽이니? (발생한 20이 15까지 10내의 범위인가?) => true
		//발생한 26이 15까지 10내의 범위인가? => 16의 범위 false
		//중앙 기준 좌우로 -10, +10이 범위인데 dataSize가 범위에 속하는지
	}//testRandomNum


}//class
