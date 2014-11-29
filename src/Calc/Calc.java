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
		calFrame = new Frame("�ڹٰ���");// ������ ����
		middlePan = new Panel(new BorderLayout());// ���ǳ�
		displayPan = new Panel();// �����ǳ�
		displayTf = new TextField(20);// �����ؽ�Ʈ�ʵ�
		displayPan.add(displayTf);// �����ǳڿ� �����ؽ�Ʈ�ʵ� ���̱�
		operAndnum = new Button[16];// ���ڹ�ư ����
		for (int i = 0; i < 16; i++) {
			operAndnum[i] = new Button();
			operAndnum[i].addActionListener(this);
			if (i < 10)
				operAndnum[i].setLabel("" + i);// ���ڹ�ư�� ���� ���̱�
		}
		operAndnum[10].setLabel("C");
		operAndnum[11].setLabel("=");
		operAndnum[12].setLabel("+");
		operAndnum[13].setLabel("-");
		operAndnum[14].setLabel("*");
		operAndnum[15].setLabel("/");
		operPan = new Panel(new GridLayout(4, 4));// �׸��� ���̾ƿ����� �����е� ����
		for (int i = 0; i < 3; i++) {
			operPan.add(operAndnum[i + 7]);// �����е忡 ��ư ���̱�
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
		calFrame.add(middlePan, BorderLayout.CENTER);// �������ӿ� ���ǳ� ���̱�
		middlePan.add(displayPan, BorderLayout.NORTH);// ���ǳڿ� �����ǳ� ���̱�
		middlePan.add(operPan, BorderLayout.CENTER); // ���ǳڿ� �����е� ���̱�
		calFrame.setSize(280, 280);// ������ ũ�� ����
		calFrame.setVisible(true);// ������ ���̱�
		displayTf.setEnabled(false);// �����ǳ� ������ �Ұ��ϰ�
		String emptyStr = " ";
		for (int i = 0; i < 35; i++)
			emptyStr += " ";
		displayTf.setText(emptyStr + 0);// �����ؽ�Ʈ�ʵ忡 0���� �ʱⰪ����
		calFrame.addWindowListener(new WindowAdapter() {// �����ӿ� ������ �̺�Ʈ ���
			public void windowClosing(WindowEvent e) {// �������� ������ �̺�Ʈ
				calFrame.dispose();// ���ҽ� Ǯ��
				calFrame.setVisible(false);// ������ ����
			}
		});
		sb = new StringBuffer();// ��Ʈ������ ����
	}

	public static void main(String[] args) {
	
		Calc cal = new Calc();// ��ü ����
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {// ���ڹ�ư���� ������ �߻��� �̺�Ʈ
	
		String eventLabel = ((Button) arg0.getSource()).getLabel();

		switch (eventLabel) {// ������ ��ư�� ���� ó��
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

	private void calculate(String oper) {// ���� ��� �޼ҵ�
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
