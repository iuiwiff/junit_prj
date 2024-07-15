package kr.co.sist.day0320;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.vo.LoginResultVO;
import kr.co.sist.vo.LoginVO;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginDesign extends Frame implements ActionListener {
	//anonymous를 사용하면 implements는 필요 없음
	
	private JTextField jtfId;
	private JPasswordField jpwfPW;
	private JLabel jlResult;
	
	Map<String, String>login;

	public LoginDesign() {
		super("숙제 받아랏.");
		
		component();
		
		login = new HashMap<String, String>();
		login.put("root", "1234");
		login.put("admin", "password");
		login.put("temp", "q1w2e3r4");
		
		jlResult.setForeground(Color.blue);
		jlResult.setText("아이디와 비밀번호를 입력해 주세요.");
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
		
	}
		
		//프레임 코드
		public void component() {
			
			jtfId = new JTextField();
			jpwfPW = new JPasswordField();
			jlResult = new JLabel();
			
			jtfId.addActionListener(this);
			jpwfPW.addActionListener(this);
			
			JScrollPane jsp1 = new JScrollPane(jtfId);
			JScrollPane jsp2 = new JScrollPane(jpwfPW );
			JScrollPane jsp3 = new JScrollPane(jlResult);
			
			jtfId.setBorder(new TitledBorder("아이디"));
			jpwfPW.setBorder(new TitledBorder("비밀번호"));
			jlResult.setBorder(new TitledBorder("결과"));
			
			setLayout(null);
			jsp1.setSize(300,100);
			jsp2.setSize(300,100);
			jsp3.setSize(300,100);
			
			jsp1.setLocation(30,33);
			jsp2.setLocation(30,133);
			jsp3.setLocation(30,233);
			
			add(jsp1);
			add(jsp2);
			add(jsp3);
			
			setSize(350,350);
			setVisible(true);
			setResizable(false);
			setLocation(700, 200);
		
		}
		
		
		@Override
		public void actionPerformed(ActionEvent ae) {
			//아이디에서 엔터가 눌려지든, 비밀번호에서 엔터가 눌려지든 호출됨
			String id = jtfId.getText().trim();
			String pass = String.valueOf(jpwfPW.getPassword()).trim();
		
			chkNull(id, pass);
		
		
		}//actionPerformed
		
		
		
		public void chkNull(String id, String pass) {
			
			if(id.isEmpty()) { //아이디가 없으면 아이디에 커서 이동
				jtfId.requestFocus();
				return;
			}//end if
			
			if(pass.isEmpty()) { //비번이 없으면 비번에 커서이동
				jpwfPW.requestFocus();
				return;
			}//end if
			login(id, pass);
		}//chkNull

		private String blockInjection(String str) {
			return str.replaceAll("'", "").replaceAll(" ", "");
		}//blockInjection
		
		
		public void login(String id, String pass) {
			
			StringBuilder output = new StringBuilder("로그인 실패");
			Color color = Color.RED;
			
			//DBMS를 연동하여 로그인 작업을 수행
			LoginDAO lDAO = LoginDAO.getInstance();
			LoginResultVO lrVO = null;
			
//			id = blockInjection(id);
//			id = blockInjection(pass);
			
			LoginVO lVO = new LoginVO(id, pass);
			
			try {
//				lrVO = lDAO.selectLogin(lVO);
				lrVO = lDAO.selectPreparedLogin(lVO);
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
			if(lrVO != null) { //객체가 존재하면 로그인 성공
				output.delete(0, output.length());
				output.append(lrVO.getName()).append("님 어서오고! (")
				.append(lrVO.getInput_date()).append(")");
				color = Color.BLUE;
			}//end if
			
			
			//실패 or 성공의 메시지와 색으로 Label을 설정
			jlResult.setText(output.toString());
			jlResult.setForeground(color);
		}//login
		
		
		public static void main(String[] args) {
			new LoginDesign();
	
		}//main

	
}//class







