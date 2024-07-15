package kr.co.sist.test.day0320;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kr.co.sist.day0320.LoginDAO;
import kr.co.sist.day0320.LoginResultVO;
import kr.co.sist.day0320.LoginVO;

class TestLogin {

	  @DisplayName("객체가 얻어지는지 테스트")
	  @Test
	  void testGetInstance() {
	    // given
	    LoginDAO lDAO = LoginDAO.getInstance();
	    // when
	    // then
	    assertNotNull(lDAO);
	  }

	  // @Disabled
	  @DisplayName("로그인 결과")
	  @Test
	  void testSelectLogin() throws SQLException {
	    // given 배경 지식 : 뭘 줬냐!
	    LoginDAO lDAO = LoginDAO.getInstance();
	    // when
	    LoginVO lVO = new LoginVO("jin", "1234");
	    LoginResultVO lrVO = lDAO.selectLogin(lVO);
	    String name = lrVO.getName();
	    System.out.println("name : " + name);
	    // then 결과 : 맞냐 틀리냐 같냐 안같냐
	    // assertNotNull(lrVO);
//	     assertEquals(name, "진수현");//같은지?
	     assertSame(name, name);
//	    assertNotEquals(name, "진수현");// 같지않은지?
	  }// testSelectLogin

	  @BeforeEach
	  void beforeEach() {
	    System.out.println("test method가 호출되기 전 실행되어야 할 코드");
	  }

	  @BeforeEach
	  void afterEach() {
	    System.out.println("test method가 호출된 후 실행되어야 할 코드");
	  }

	  @BeforeAll
	  static void beforeAll() {
	    System.out.println("모든 test method가 실행되기 전 한번만 호출되어야 할 코드");
	  }

	  @AfterAll
	  static void afterAll() {
	    System.out.println("모든 test method가 실행된 후 한번만 호출되어야 할 코드");
	  }

}
