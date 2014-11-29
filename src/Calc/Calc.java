package Calc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calc implements ActionListener {

	Frame calFrame;
	Panel displayPan, operPan, middlePan;
	TextField displayTf;
	Button[] operAndnum;
	StringBuffer sb;
	int leftNum, rightNum, resultNum;
	String oper = "";

	Calc() {
		calFrame = new Frame("자바계산기");// 프레임 생성
		middlePan = new Panel(new BorderLayout());// 주판넬
		displayPan = new Panel();// 수식판넬
		displayTf = new TextField(20);// 수식텍스트필드
		displayPan.add(displayTf);// 수식판넬에 수식텍스트필드 붙이기
		operAndnum = new Button[16];// 숫자버튼 생성
		for (int i = 0; i < 16; i++) {
			operAndnum[i] = new Button();
			operAndnum[i].addActionListener(this);
			if (i < 10)
				operAndnum[i].setLabel("" + i);// 숫자버튼에 숫자 붙이기
		}
		operAndnum[10].setLabel("C");
		operAndnum[11].setLabel("=");
		operAndnum[12].setLabel("+");
		operAndnum[13].setLabel("-");
		operAndnum[14].setLabel("*");
		operAndnum[15].setLabel("/");
		operPan = new Panel(new GridLayout(4, 4));// 그리드 레이아웃으로 숫자패드 생성
		for (int i = 0; i < 3; i++) {
			operPan.add(operAndnum[i + 7]);// 숫자패드에 버튼 붙이기
		}
		operPan.add(operAndnum[15]);
		for (int i = 0; i < 3; i++) {
			operPan.add(operAndnum[i + 4]);
		}
		operPan.add(operAndnum[14]);
		for (int i = 0; i < 3; i++) {
			operPan.add(operAndnum[i + 1]);
		}
		operPan.add(operAndnum[13]);
		operPan.add(operAndnum[0]);
		operPan.add(operAndnum[10]);
		operPan.add(operAndnum[11]);
		operPan.add(operAndnum[12]);
		calFrame.add(middlePan, BorderLayout.CENTER);// 주프레임에 주판넬 붙이기
		middlePan.add(displayPan, BorderLayout.NORTH);// 주판넬에 수식판넬 붙이기
		middlePan.add(operPan, BorderLayout.CENTER); // 주판넬에 숫자패드 붙이기
		calFrame.setSize(280, 280);// 프렝임 크기 설정
		calFrame.setVisible(true);// 프레임 보이기
		displayTf.setEnabled(false);// 수식판넬 편집을 불가하게
		String emptyStr = " ";
		for (int i = 0; i < 35; i++)
			emptyStr += " ";
		displayTf.setText(emptyStr + 0);// 수식텍스트필드에 0값을 초기값으로
		calFrame.addWindowListener(new WindowAdapter() {// 프레임에 윈도우 이벤트 등록
			public void windowClosing(WindowEvent e) {// 프레임을 닫을때 이벤트
				calFrame.dispose();// 리소스 풀기
				calFrame.setVisible(false);// 프레임 종료
			}
		});
		sb = new StringBuffer();// 스트링버퍼 생성
	}

	public static void main(String[] args) {
	
		Calc cal = new Calc();// 객체 생성
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {// 숫자버튼들을 눌러서 발생할 이벤트
	
		String eventLabel = ((Button) arg0.getSource()).getLabel();

		switch (eventLabel) {// 눌러진 버튼에 따른 처리
		case "C":
			leftNum = 0;
			rightNum = 0;
			resultNum = 0;
			oper = "";
			if (sb.length() > 0)
				sb.delete(0, sb.length());
			String emptyStr = " ";
			for (int i = 0; i < 35; i++)
				emptyStr += " ";
			displayTf.setText(emptyStr + 0);
			break;
		case "+":
			callCal(oper);
			oper = "+";
			break;
		case "-":
			callCal(oper);
			oper = "-";
			break;
		case "*":
			callCal(oper);
			oper = "*";
			break;
		case "/":
			callCal(oper);
			oper = "/";
			break;
		case "=":
			if (sb.length() > 0) {
				rightNum = Integer.parseInt(sb.toString().trim());
																
				sb.delete(0, sb.length());
				calculate(oper);
				String displayResult = Integer.toString(resultNum);
				String emptyStr1 = " ";
				for (int i = 0; i < 36 - displayResult.length(); i++)
					emptyStr1 += " ";
				displayTf.setText(emptyStr1 + displayResult);
			}
			leftNum = 0;
			rightNum = 0;
			oper = "";
			break;
		default:
			sb.append(eventLabel);
			String emptyStr2 = " ";
			for (int i = 0; i < 36 - sb.length(); i++)
				emptyStr2 += " ";
			displayTf.setText(emptyStr2 + sb.toString());
			break;
		}
	}

	private void callCal(String oper) {
		if (oper != "") {
			if (sb.length() > 0) {
				rightNum = Integer.parseInt(sb.toString().trim());
				calculate(oper);
				String displayResult = Integer.toString(resultNum);
				String emptyStr = " ";
				for (int i = 0; i < 36 - displayResult.length(); i++)
					emptyStr += " ";
				displayTf.setText(emptyStr + displayResult);
				leftNum = resultNum;
			}
		} else {
			if (sb.length() > 0)
				leftNum = Integer.parseInt(sb.toString().trim());
		}
		sb.delete(0, sb.length());
	}

	private void calculate(String oper) {// 실제 계산 메소드
		switch (oper) {
		case "+":
			resultNum = leftNum + rightNum;
			break;
		case "-":
			resultNum = leftNum - rightNum;
			break;
		case "*":
			resultNum = leftNum * rightNum;
			break;
		case "/":
			resultNum = leftNum / rightNum;
			break;
		}
	}
}
