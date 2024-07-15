package kr.co.sist.test.day0320;
import kr.co.sist.day0320.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestLotto {

	@DisplayName("로또번호 테스트")
	@Test
	void testCreateLotto() {
		//given
		kr.co.sist.day0320.TestLotto tl = new kr.co.sist.day0320.TestLotto();
		int money = 1000;
		//when
		List<Integer> list = tl.createLotto(money);
		//then
//		assertEquals(list.size(), 6);
		assertThrows(RuntimeException.class, ()-> {System.out.println("예외");});
	}

//	@Disabled
	@DisplayName("금액 유효 테스트")
	void testIsValidMoney() {
		//given
		kr.co.sist.day0320.TestLotto tl = new kr.co.sist.day0320.TestLotto();
		int money = 1000;
		//when
		boolean flag = tl.isValidMoney(money);
		//then
		assertTrue(flag);
	}


}
