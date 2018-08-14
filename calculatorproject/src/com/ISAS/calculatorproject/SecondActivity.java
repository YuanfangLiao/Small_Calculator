package com.ISAS.calculatorproject;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

import android.os.Bundle;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private Button[] btn = new Button[10];
	private EditText input;
	private TextView mem, _drg, tip;
	private Button div, mul, sub, add, equal, sin, cos, tan, log, ln, sqrt,
			square, factorial, bksp, left, right, dot, exit, drg, mc, c;
	public String str_old;
	public String str_new;
	public boolean vbegin = true;
	public boolean drg_flag = true;
	public double pi = 4 * Math.atan(1);
	public boolean tip_lock = true;
	public boolean equals_flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		InitWigdet();
		AllWigdetListener();
	    if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){ 

	    	Intent i = new Intent(SecondActivity.this,MainActivity.class);
	   	    startActivity(i);
	   	    finish();}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	private void InitWigdet() {
	
		input = (EditText) findViewById(R.id.input);
		mem = (TextView) findViewById(R.id.mem);
		tip = (TextView) findViewById(R.id.tip);
		_drg = (TextView) findViewById(R.id._drg);
		btn[0] = (Button) findViewById(R.id.zero);
		btn[1] = (Button) findViewById(R.id.one);
		btn[2] = (Button) findViewById(R.id.two);
		btn[3] = (Button) findViewById(R.id.three);
		btn[4] = (Button) findViewById(R.id.four);
		btn[5] = (Button) findViewById(R.id.five);
		btn[6] = (Button) findViewById(R.id.six);
		btn[7] = (Button) findViewById(R.id.seven);
		btn[8] = (Button) findViewById(R.id.eight);
		btn[9] = (Button) findViewById(R.id.nine);
		div = (Button) findViewById(R.id.divide);
		mul = (Button) findViewById(R.id.mul);
		sub = (Button) findViewById(R.id.sub);
		add = (Button) findViewById(R.id.add);
		equal = (Button) findViewById(R.id.equal);
		sin = (Button) findViewById(R.id.sin);
		cos = (Button) findViewById(R.id.cos);
		tan = (Button) findViewById(R.id.tan);
		log = (Button) findViewById(R.id.log);
		ln = (Button) findViewById(R.id.ln);
		sqrt = (Button) findViewById(R.id.sqrt);
		square = (Button) findViewById(R.id.square);
		factorial = (Button) findViewById(R.id.factorial);
		bksp = (Button) findViewById(R.id.bksp);
		left = (Button) findViewById(R.id.left);
		right = (Button) findViewById(R.id.right);
		dot = (Button) findViewById(R.id.dot);
		exit = (Button) findViewById(R.id.exit);
		drg = (Button) findViewById(R.id.drg);
		mc = (Button) findViewById(R.id.mc);
		c = (Button) findViewById(R.id.c);
	}


	private void AllWigdetListener() {
	
		for (int i = 0; i < 10; i++) {
			btn[i].setOnClickListener(actionPerformed);
		}
		div.setOnClickListener(actionPerformed);
		mul.setOnClickListener(actionPerformed);
		sub.setOnClickListener(actionPerformed);
		add.setOnClickListener(actionPerformed);
		equal.setOnClickListener(actionPerformed);
		sin.setOnClickListener(actionPerformed);
		cos.setOnClickListener(actionPerformed);
		tan.setOnClickListener(actionPerformed);
		log.setOnClickListener(actionPerformed);
		ln.setOnClickListener(actionPerformed);
		sqrt.setOnClickListener(actionPerformed);
		square.setOnClickListener(actionPerformed);
		factorial.setOnClickListener(actionPerformed);
		bksp.setOnClickListener(actionPerformed);
		left.setOnClickListener(actionPerformed);
		right.setOnClickListener(actionPerformed);
		dot.setOnClickListener(actionPerformed);
		exit.setOnClickListener(actionPerformed);
		drg.setOnClickListener(actionPerformed);
		mc.setOnClickListener(actionPerformed);
		c.setOnClickListener(actionPerformed);
	}

	
	String[] TipCommand = new String[500];
	int tip_i = 0;
	private OnClickListener actionPerformed = new OnClickListener() {

		public void onClick(View v) {
			
			String command = ((Button) v).getText().toString();
			
			String str = input.getText().toString();
			
			if (equals_flag == false
					&& "0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1) {
				
				if (right(str)) {
					if ("+-×÷√^)".indexOf(command) != -1) {
						for (int i = 0; i < str.length(); i++) {
							TipCommand[tip_i] = String.valueOf(str.charAt(i));
							tip_i++;
						}
						vbegin = false;
					}
				} else {
					input.setText("0");
					vbegin = true;
					tip_i = 0;
					tip_lock = true;
					tip.setText("Powered by CORN！");
				}

				equals_flag = true;
			}
			if (tip_i > 0)
				TipChecker(TipCommand[tip_i - 1], command);
			else if (tip_i == 0) {
				TipChecker("#", command);
			}
			if ("0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1
					&& tip_lock) {
				TipCommand[tip_i] = command;
				tip_i++;
			}


			if ("0123456789.()sincostanlnlogn!+-×÷√^".indexOf(command) != -1
					&& tip_lock) { 
				print(command);
			} else if (command.compareTo("DRG") == 0 && tip_lock) {
				if (drg_flag == true) {
					drg_flag = false;
					_drg.setText("   RAD");
				} else {
					drg_flag = true;
					_drg.setText("   DEG");
				}
				
			} else if (command.compareTo("Bksp") == 0 && equals_flag) {
				
				if (TTO(str) == 3) {
					if (str.length() > 3)
						input.setText(str.substring(0, str.length() - 3));
					else if (str.length() == 3) {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
						tip.setText("Powered by CORN！");
					}
					
				} else if (TTO(str) == 2) {
					if (str.length() > 2)
						input.setText(str.substring(0, str.length() - 2));
					else if (str.length() == 2) {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
						tip.setText("Powered by CORN！");
					}
					
				} else if (TTO(str) == 1) {
					
					if (right(str)) {
						if (str.length() > 1)
							input.setText(str.substring(0, str.length() - 1));
						else if (str.length() == 1) {
							input.setText("0");
							vbegin = true;
							tip_i = 0;
							tip.setText("Powered by CORN！");
						}
						
					} else {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
						tip.setText("Powered by CORN！");
					}
				}
				if (input.getText().toString().compareTo("-") == 0
						|| equals_flag == false) {
					input.setText("0");
					vbegin = true;
					tip_i = 0;
					tip.setText("Powered by CORN！");
				}
				tip_lock = true;
				if (tip_i > 0)
					tip_i--;
				
			} else if (command.compareTo("Bksp") == 0 && equals_flag == false) {
				
				input.setText("0");
				vbegin = true;
				tip_i = 0;
				tip_lock = true;
				tip.setText("Powered by CORN！");
				
			} else if (command.compareTo("C") == 0) {
				
				input.setText("0");
				
				vbegin = true;
				
				tip_i = 0;
				
				tip_lock = true;
				
				equals_flag = true;
				tip.setText("Powered by CORN！");
				
			} else if (command.compareTo("MC") == 0) {
				mem.setText("0");
				
			} else if (command.compareTo("exit") == 0) {
			
				finish();
				
			} else if (command.compareTo("=") == 0 && tip_lock && right(str)
					&& equals_flag) {
				tip_i = 0;
				
				tip_lock = false;
				
				equals_flag = false;
				
				str_old = str;
				
				str = str.replaceAll("sin", "s");
				str = str.replaceAll("cos", "c");
				str = str.replaceAll("tan", "t");
				str = str.replaceAll("log", "g");
				str = str.replaceAll("ln", "l");
				str = str.replaceAll("n!", "!");
				
				vbegin = true;
				
				str_new = str.replaceAll("-", "-1×");
				
				new calc().process(str_new);
			}
			
			tip_lock = true;
		}

		private void finishActivity(OnClickListener onClickListener) {
			// TODO Auto-generated method stub
			
		}

	};

	private void TipChecker(String tipcommand1, String tipcommand2) {

		
		int Tipcode1 = 0, Tipcode2 = 0;
		
		int tiptype1 = 0, tiptype2 = 0;
		
		int bracket = 0;
		
		if (tipcommand1.compareTo("#") == 0
				&& (tipcommand2.compareTo("÷") == 0
						|| tipcommand2.compareTo("×") == 0
						|| tipcommand2.compareTo("+") == 0
						|| tipcommand2.compareTo(")") == 0
						|| tipcommand2.compareTo("√") == 0 || tipcommand2
						.compareTo("^") == 0)) {
			Tipcode1 = -1;
		}
		
		else if (tipcommand1.compareTo("#") != 0) {
			if (tipcommand1.compareTo("(") == 0) {
				tiptype1 = 1;
			} else if (tipcommand1.compareTo(")") == 0) {
				tiptype1 = 2;
			} else if (tipcommand1.compareTo(".") == 0) {
				tiptype1 = 3;
			} else if ("0123456789".indexOf(tipcommand1) != -1) {
				tiptype1 = 4;
			} else if ("+-×÷".indexOf(tipcommand1) != -1) {
				tiptype1 = 5;
			} else if ("√^".indexOf(tipcommand1) != -1) {
				tiptype1 = 6;
			} else if ("sincostanloglnn!".indexOf(tipcommand1) != -1) {
				tiptype1 = 7;
			}
			
			if (tipcommand2.compareTo("(") == 0) {
				tiptype2 = 1;
			} else if (tipcommand2.compareTo(")") == 0) {
				tiptype2 = 2;
			} else if (tipcommand2.compareTo(".") == 0) {
				tiptype2 = 3;
			} else if ("0123456789".indexOf(tipcommand2) != -1) {
				tiptype2 = 4;
			} else if ("+-×÷".indexOf(tipcommand2) != -1) {
				tiptype2 = 5;
			} else if ("√^".indexOf(tipcommand2) != -1) {
				tiptype2 = 6;
			} else if ("sincostanloglnn!".indexOf(tipcommand2) != -1) {
				tiptype2 = 7;
			}

			switch (tiptype1) {
			case 1:
			
				if (tiptype2 == 2
						|| (tiptype2 == 5 && tipcommand2.compareTo("-") != 0)
						|| tiptype2 == 6)
					Tipcode1 = 1;
				break;
			case 2:
				
				if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4
						|| tiptype2 == 7)
					Tipcode1 = 2;
				break;
			case 3:
				
				if (tiptype2 == 1 || tiptype2 == 7)
					Tipcode1 = 3;
				
				if (tiptype2 == 3)
					Tipcode1 = 8;
				break;
			case 4:
				
				if (tiptype2 == 1 || tiptype2 == 7)
					Tipcode1 = 4;
				break;
			case 5:
				
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6)
					Tipcode1 = 5;
				break;
			case 6:
				
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6
						|| tiptype2 == 7)
					Tipcode1 = 6;
				break;
			case 7:
				
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6
						|| tiptype2 == 7)
					Tipcode1 = 7;
				break;
			}
		}
		
		if (Tipcode1 == 0 && tipcommand2.compareTo(".") == 0) {
			int tip_point = 0;
			for (int i = 0; i < tip_i; i++) {
				
				if (TipCommand[i].compareTo(".") == 0) {
					tip_point++;
				}
			
				if (TipCommand[i].compareTo("sin") == 0
						|| TipCommand[i].compareTo("cos") == 0
						|| TipCommand[i].compareTo("tan") == 0
						|| TipCommand[i].compareTo("log") == 0
						|| TipCommand[i].compareTo("ln") == 0
						|| TipCommand[i].compareTo("n!") == 0
						|| TipCommand[i].compareTo("√") == 0
						|| TipCommand[i].compareTo("^") == 0
						|| TipCommand[i].compareTo("÷") == 0
						|| TipCommand[i].compareTo("×") == 0
						|| TipCommand[i].compareTo("-") == 0
						|| TipCommand[i].compareTo("+") == 0
						|| TipCommand[i].compareTo("(") == 0
						|| TipCommand[i].compareTo(")") == 0) {
					tip_point = 0;
				}
			}
			tip_point++;
			
			if (tip_point > 1) {
				Tipcode1 = 8;
			}
		}
	
		if (Tipcode1 == 0 && tipcommand2.compareTo(")") == 0) {
			int tip_right_bracket = 0;
			for (int i = 0; i < tip_i; i++) {
				
				if (TipCommand[i].compareTo("(") == 0) {
					tip_right_bracket++;
				}
				
				if (TipCommand[i].compareTo(")") == 0) {
					tip_right_bracket--;
				}
			}
			
			if (tip_right_bracket == 0) {
				Tipcode1 = 10;
			}
		}
		
		if (Tipcode1 == 0 && tipcommand2.compareTo("=") == 0) {
			
			int tip_bracket = 0;
			for (int i = 0; i < tip_i; i++) {
				if (TipCommand[i].compareTo("(") == 0) {
					tip_bracket++;
				}
				if (TipCommand[i].compareTo(")") == 0) {
					tip_bracket--;
				}
			}
			
			if (tip_bracket > 0) {
				Tipcode1 = 9;
				bracket = tip_bracket;
			} else if (tip_bracket == 0) {
				
				if ("√^sincostanloglnn!".indexOf(tipcommand1) != -1) {
					Tipcode1 = 6;
				}
			
				if ("+-×÷".indexOf(tipcommand1) != -1) {
					Tipcode1 = 5;
				}
			}
		}
		if (tipcommand2.compareTo("MC") == 0)
			Tipcode2 = 1;
		if (tipcommand2.compareTo("C") == 0)
			Tipcode2 = 2;
		if (tipcommand2.compareTo("DRG") == 0)
			Tipcode2 = 3;
		if (tipcommand2.compareTo("Bksp") == 0)
			Tipcode2 = 4;
		if (tipcommand2.compareTo("sin") == 0)
			Tipcode2 = 5;
		if (tipcommand2.compareTo("cos") == 0)
			Tipcode2 = 6;
		if (tipcommand2.compareTo("tan") == 0)
			Tipcode2 = 7;
		if (tipcommand2.compareTo("log") == 0)
			Tipcode2 = 8;
		if (tipcommand2.compareTo("ln") == 0)
			Tipcode2 = 9;
		if (tipcommand2.compareTo("n!") == 0)
			Tipcode2 = 10;
		if (tipcommand2.compareTo("√") == 0)
			Tipcode2 = 11;
		if (tipcommand2.compareTo("^") == 0)
			Tipcode2 = 12;
	
		TipShow(bracket, Tipcode1, Tipcode2, tipcommand1, tipcommand2);

	}

	
	private void TipShow(int bracket, int tipcode1, int tipcode2,
			String tipcommand1, String tipcommand2) {

		String tipmessage = "";
		if (tipcode1 != 0)
			tip_lock = false;
		switch (tipcode1) {
		case -1:
			tipmessage = tipcommand2 + "It can't be the first operator";
			break;
		case 1:
			tipmessage = tipcommand1 + "Input :numbers/(/./-/function)  ";
			break;
		case 2:
			tipmessage = tipcommand1 + " Input:(/operator  ";
			break;
		case 3:
			tipmessage = tipcommand1 + "  Input：)/numbers/operator ";
			break;
		case 4:
			tipmessage = tipcommand1 + "  Input：)/./numbers/operator ";
			break;
		case 5:
			tipmessage = tipcommand1 + "  Input：(/./numbers/function ";
			break;
		case 6:
			tipmessage = tipcommand1 + "  Input：(/./numbers ";
			break;
		case 7:
			tipmessage = tipcommand1 + "  Input：(/./numbers ";
			break;
		case 8:
			tipmessage = "The decimal point repeated";
			break;
		case 9:
			tipmessage = "The lack of " + bracket + "  )";
			break;
		case 10:
			tipmessage = "Don't need  )";
			break;
		}
		switch (tipcode2) {
		case 1:
			tipmessage = tipmessage + "[MC:Remove the memory MEM]";
			break;
		case 2:
			tipmessage = tipmessage + "[C:Come to zero]";
			break;
		case 3:
			tipmessage = tipmessage + "[DRG:Choose DEG Or  RAD]";
			break;
		case 4:
			tipmessage = tipmessage + "[Bksp:backspace]";
			break;
		case 5:
			tipmessage = tipmessage + "sin:put in a angle";
			break;
		case 6:
			tipmessage = tipmessage + "cos:put in a angle";
			break;
		case 7:
			tipmessage = tipmessage + "tan:put in a angle";
			break;
		case 8:
			tipmessage = tipmessage + "log:put in a positive";
			break;
		case 9:
			tipmessage = tipmessage + "ln:put in a positive";
			break;
		case 10:
			tipmessage = tipmessage + "n!:put in a positive";
			break;
		case 11:
			tipmessage = tipmessage + "√:put in a positive";
			break;
		case 12:
			tipmessage = tipmessage + "^:put in a number";
			break;
		}
	
		tip.setText(tipmessage);

	}

	
	private void print(String str) {
		
		if (vbegin) {
			input.setText(str);
		} else {
			input.append(str);
		}
		vbegin = false;
	}


	private int TTO(String str) {
		if ((str.charAt(str.length() - 1) == 'n'
				&& str.charAt(str.length() - 2) == 'i' && str.charAt(str
				.length() - 3) == 's')
				|| (str.charAt(str.length() - 1) == 's'
						&& str.charAt(str.length() - 2) == 'o' && str
						.charAt(str.length() - 3) == 'c')
				|| (str.charAt(str.length() - 1) == 'n'
						&& str.charAt(str.length() - 2) == 'a' && str
						.charAt(str.length() - 3) == 't')
				|| (str.charAt(str.length() - 1) == 'g'
						&& str.charAt(str.length() - 2) == 'o' && str
						.charAt(str.length() - 3) == 'l')) {
			return 3;
		} else if ((str.charAt(str.length() - 1) == 'n' && str.charAt(str
				.length() - 2) == 'l')
				|| (str.charAt(str.length() - 1) == '!' && str.charAt(str
						.length() - 2) == 'n')) {
			return 2;
		} else {
			return 1;
		}
	}


	private boolean right(String str) {
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '0' && str.charAt(i) != '1'
					&& str.charAt(i) != '2' && str.charAt(i) != '3'
					&& str.charAt(i) != '4' && str.charAt(i) != '5'
					&& str.charAt(i) != '6' && str.charAt(i) != '7'
					&& str.charAt(i) != '8' && str.charAt(i) != '9'
					&& str.charAt(i) != '.' && str.charAt(i) != '-'
					&& str.charAt(i) != '+' && str.charAt(i) != '×'
					&& str.charAt(i) != '÷' && str.charAt(i) != '√'
					&& str.charAt(i) != '^' && str.charAt(i) != 's'
					&& str.charAt(i) != 'i' && str.charAt(i) != 'n'
					&& str.charAt(i) != 'c' && str.charAt(i) != 'o'
					&& str.charAt(i) != 't' && str.charAt(i) != 'a'
					&& str.charAt(i) != 'l' && str.charAt(i) != 'g'
					&& str.charAt(i) != '(' && str.charAt(i) != ')'
					&& str.charAt(i) != '!')
				break;
		}
		if (i == str.length()) {
			return true;
		} else {
			return false;
		}
	}

	
	public class calc {

		public calc() {

		}

		final int MAXLEN = 500;

		public void process(String str) {
			int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
			
			int weight[]; 
			double number[]; 
			char ch, ch_gai, operator[];
			String num;
			weight = new int[MAXLEN];
			number = new double[MAXLEN];
			operator = new char[MAXLEN];
			String expression = str;
			StringTokenizer expToken = new StringTokenizer(expression,
					"+-×÷()sctgl!√^");
			int i = 0;
			while (i < expression.length()) {
				ch = expression.charAt(i);
				
				if (i == 0) {
					if (ch == '-')
						flag = -1;
				} else if (expression.charAt(i - 1) == '(' && ch == '-')
					flag = -1;
			
				if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E') {
					num = expToken.nextToken();
					ch_gai = ch;
					Log.e("guojs", ch + "--->" + i);
					
					while (i < expression.length()
							&& (ch_gai <= '9' && ch_gai >= '0' || ch_gai == '.' || ch_gai == 'E')) {
						ch_gai = expression.charAt(i++);
						Log.e("guojs", "The value of i is ：" + i);
					}
				
					if (i >= expression.length())
						i -= 1;
					else {
						i -= 2;
					}
					if (num.compareTo(".") == 0)
						number[topNum++] = 0;
					
					else {
						number[topNum++] = Double.parseDouble(num) * flag;
						flag = 1;
					}
				}
				
				if (ch == '(')
					weightPlus += 4;
				if (ch == ')')
					weightPlus -= 4;
				if (ch == '-' && flag == 1 || ch == '+' || ch == '×'
						|| ch == '÷' || ch == 's' || ch == 'c' || ch == 't'
						|| ch == 'g' || ch == 'l' || ch == '!' || ch == '√'
						|| ch == '^') {
					switch (ch) {
				
					case '+':
					case '-':
						weightTemp = 1 + weightPlus;
						break;
			
					case '×':
					case '÷':
						weightTemp = 2 + weightPlus;
						break;
				
					case 's':
					case 'c':
					case 't':
					case 'g':
					case 'l':
					case '!':
						weightTemp = 3 + weightPlus;
						break;
				
					default:
						weightTemp = 4 + weightPlus;
						break;
					}
				
					if (topOp == 0 || weight[topOp - 1] < weightTemp) {
						weight[topOp] = weightTemp;
						operator[topOp] = ch;
						topOp++;
						
					} else {
						while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
							switch (operator[topOp - 1]) {
							
							case '+':
								number[topNum - 2] += number[topNum - 1];
								break;
							case '-':
								number[topNum - 2] -= number[topNum - 1];
								break;
							case '×':
								number[topNum - 2] *= number[topNum - 1];
								break;
						
							case '÷':
								if (number[topNum - 1] == 0) {
									showError(1, str_old);
									return;
								}
								number[topNum - 2] /= number[topNum - 1];
								break;
							case '√':
								if (number[topNum - 1] == 0
										|| (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
									showError(2, str_old);
									return;
								}
								number[topNum - 2] = Math.pow(
										number[topNum - 2],
										1 / number[topNum - 1]);
								break;
							case '^':
								number[topNum - 2] = Math.pow(
										number[topNum - 2], number[topNum - 1]);
								break;
							
							case 's':
								if (drg_flag == true) {
									number[topNum - 1] = Math
											.sin((number[topNum - 1] / 180)
													* pi);
								} else {
									number[topNum - 1] = Math
											.sin(number[topNum - 1]);
								}
								topNum++;
								break;
							
							case 'c':
								if (drg_flag == true) {
									number[topNum - 1] = Math
											.cos((number[topNum - 1] / 180)
													* pi);
								} else {
									number[topNum - 1] = Math
											.cos(number[topNum - 1]);
								}
								topNum++;
								break;
							
							case 't':
								if (drg_flag == true) {
									if ((Math.abs(number[topNum - 1]) / 90) % 2 == 1) {
										showError(2, str_old);
										return;
									}
									number[topNum - 1] = Math
											.tan((number[topNum - 1] / 180)
													* pi);
								} else {
									if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
										showError(2, str_old);
										return;
									}
									number[topNum - 1] = Math
											.tan(number[topNum - 1]);
								}
								topNum++;
								break;
							
							case 'g':
								if (number[topNum - 1] <= 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = Math
										.log10(number[topNum - 1]);
								topNum++;
								break;
						
							case 'l':
								if (number[topNum - 1] <= 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = Math
										.log(number[topNum - 1]);
								topNum++;
								break;
							
							case '!':
								if (number[topNum - 1] > 170) {
									showError(3, str_old);
									return;
								} else if (number[topNum - 1] < 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = N(number[topNum - 1]);
								topNum++;
								break;
							}
						
							topNum--;
							topOp--;
						}
					
						weight[topOp] = weightTemp;
						operator[topOp] = ch;
						topOp++;
					}
				}
				i++;
			}
			
			while (topOp > 0) {
				switch (operator[topOp - 1]) {
				case '+':
					number[topNum - 2] += number[topNum - 1];
					break;
				case '-':
					number[topNum - 2] -= number[topNum - 1];
					break;
				case '×':
					number[topNum - 2] *= number[topNum - 1];
					break;
				
				case '÷':
					if (number[topNum - 1] == 0) {
						showError(1, str_old);
						return;
					}
					number[topNum - 2] /= number[topNum - 1];
					break;
				case '√':
					if (number[topNum - 1] == 0
							|| (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
						showError(2, str_old);
						return;
					}
					number[topNum - 2] = Math.pow(number[topNum - 2],
							1 / number[topNum - 1]);
					break;
				case '^':
					number[topNum - 2] = Math.pow(number[topNum - 2],
							number[topNum - 1]);
					break;
				
				case 's':
					if (drg_flag == true) {
						number[topNum - 1] = Math
								.sin((number[topNum - 1] / 180) * pi);
					} else {
						number[topNum - 1] = Math.sin(number[topNum - 1]);
					}
					topNum++;
					break;
			
				case 'c':
					if (drg_flag == true) {
						number[topNum - 1] = Math
								.cos((number[topNum - 1] / 180) * pi);
					} else {
						number[topNum - 1] = Math.cos(number[topNum - 1]);
					}
					topNum++;
					break;
				
				case 't':
					if (drg_flag == true) {
						if ((Math.abs(number[topNum - 1]) / 90) % 2 == 1) {
							showError(2, str_old);
							return;
						}
						number[topNum - 1] = Math
								.tan((number[topNum - 1] / 180) * pi);
					} else {
						if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
							showError(2, str_old);
							return;
						}
						number[topNum - 1] = Math.tan(number[topNum - 1]);
					}
					topNum++;
					break;
			
				case 'g':
					if (number[topNum - 1] <= 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = Math.log10(number[topNum - 1]);
					topNum++;
					break;
				case 'l':
					if (number[topNum - 1] <= 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = Math.log(number[topNum - 1]);
					topNum++;
					break;
				
				case '!':
					if (number[topNum - 1] > 170) {
						showError(3, str_old);
						return;
					} else if (number[topNum - 1] < 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = N(number[topNum - 1]);
					topNum++;
					break;
				}
			
				topNum--;
				topOp--;
			}
			
			if (number[0] > 7.3E306) {
				showError(3, str_old);
				return;
			}
			input.setText(String.valueOf(FP(number[0])));
			tip.setText("Finished calculcate,please press 'C'!");
			mem.setText(str_old + "=" + String.valueOf(FP(number[0])));
		}

	
		public double FP(double n) {
		
			DecimalFormat format = new DecimalFormat("0.#############");
			return Double.parseDouble(format.format(n));
		}

		
		public double N(double n) {
			int i = 0;
			double sum = 1;
		
			for (i = 1; i <= n; i++) {
				sum = sum * i;
			}
			return sum;
		}

		public void showError(int code, String str) {
			String message = "";
			switch (code) {
			case 1:
				message = "zero can't be divisor";
				break;
			case 2:
				message = "Function format error";
				break;
			case 3:
				message = "The value is too big";
			}
			input.setText("\"" + str + "\"" + ": " + message);
			tip.setText(message + "\n" + "Finished calculate,please press 'C'");
		}
	}

}

//package com.ISAS.calculatorproject;
//
//
//
//import android.os.Bundle;
//import android.app.Activity;
//import android.app.Fragment;
//import android.content.Intent;
//import android.content.res.Configuration;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class SecondActivity extends Activity {
//
//    private Button btn71,btn72,btn73,btn74,btn75,btn81,btn82,btn83,btn84,btn85,btn91,btn92,btn93,btn94,btn95,btn101,btn102,btn103,btn104,btn105;
//    private EditText editText;//显示输入的数字
//    private String opt = "+";//操作符
//    private double n1 = 0.0, n2 = 0.0;//两个操作数
//    private TextView textView;//显示算式
//    
//    //跟据被选择按钮的id设置监听器
//    private OnClickListener lisenter = new OnClickListener() {
//        
//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub            
//            editText = (EditText)findViewById(R.id.editText2);
//            textView = (TextView) findViewById(R.id.textView2);
//            String s = editText.getText().toString();//获取字符串
//            Button btn =(Button)v;
//            try{ 
//    
//            switch(btn.getId())
//            {
//                case R.id.Button74://1
//                {                                    
//                    String str = editText.getText().toString();
//                    editText.setText(str + 1);
//                    str = editText.getText().toString();
//                    textView.setText(str);                
//                    break;
//                }
//                case R.id.Button104://+
//                {
//                    String str = editText.getText().toString();
//                    n1 = Double.parseDouble(str);
//                    opt = "+";                    
//                    textView.setText(n1 + opt);
//                    editText.setText("");
//                    break;
//                }
//                case R.id.Button84://2
//                {                    
//                    String str = editText.getText().toString();
//                    editText.setText(str + 2);    
//                    str = editText.getText().toString();
//                    textView.setText(str);                
//                    break;
//                }
//                case R.id.Button105://操作符=
//                {                    
//                    if(opt == "+")
//                    {                        
//                        String str = editText.getText().toString();
//                        n2 = Double.parseDouble(str);
//                        textView.setText(n1 + opt + n2 );
//                        editText.setText((n1 + n2) + "");
//                    }
//                    else if(opt == "-")
//                    {
//                        String str = editText.getText().toString();
//                        n2 = Double.parseDouble(str);
//                        textView.setText(n1 + opt + n2 );
//                        editText.setText((n1 - n2) + "");
//                    }
//                    else if(opt == "*")
//                    {
//                        String str = editText.getText().toString();
//                        n2 = Double.parseDouble(str);
//                        textView.setText(n1 + opt + n2 );
//                        editText.setText((n1 * n2) + "");
//                    }
//                    else if(opt == "/")
//                    {
//                        String str = editText.getText().toString();
//                        n2 = Double.parseDouble(str);
//                        if(n2 == 0)
//                        {
//                            editText.setText("");
//                            textView.setText("除数不能为0");
//                            break;
//                        }                        
//                        else
//                        {
//                            textView.setText(n1 + opt + n2 );
//                            editText.setText((n1 / n2) + "");
//                        }
//                    }
//    
//                    break; 
//                }
//                case R.id.Button94://3
//                {                    
//                    editText.setText(editText.getText().toString() + 3);
//                    String str = editText.getText().toString();
//                    textView.setText(str);    
//                    break;
//                }
//                case R.id.Button73://4
//                {
//                    editText.setText(editText.getText().toString() + 4);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button83://5
//                {
//                    editText.setText(editText.getText().toString() + 5);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button93://6
//                {
//                    editText.setText(editText.getText().toString() + 6);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button72://7
//                {
//                    editText.setText(editText.getText().toString() + 7);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button82://8
//                {
//                    editText.setText(editText.getText().toString() + 8);    
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button92://9
//                {
//                    editText.setText(editText.getText().toString() + 9);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button85://0
//                {                    
//                    textView.setText(n1 + opt + 10);
//                    editText.setText(editText.getText().toString() + 0);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
//                    break;
//                }
//                case R.id.Button95://.
//                {
//                    String str = editText.getText().toString();
//                    if(str.indexOf(".") != -1) //判断字符串中是否已经包含了小数点，如果有就什么也不做
//                    {
//                        
//                    } 
//                    else //如果没有小数点 
//                    {        
//                        if(str.equals("0"))//如果开始为0，
//                            editText.setText(("0" + ".").toString());
//                        else if(str.equals(""))//如果初时显示为空，就什么也不做
//                        {    
//                            
//                        }
//                        else
//                            editText.setText(str + ".");
//                    }
//                    break;
//                }
//                case R.id.button101://操作符 /
//                {                
//                    String str = editText.getText().toString();
//                    n1 = Double.parseDouble(str);
//                    opt = "/";    
//                    editText.setText("");
//                    textView.setText(n1 + opt);
//                    break;
//                }
//                case R.id.Button102://操作符*
//                {        
//                    String str = editText.getText().toString();
//                    n1 = Double.parseDouble(str);
//                    opt = "*";    
//                    editText.setText("");
//                    textView.setText(n1 + opt);
//                    break;
//                }
//                case R.id.Button103://操作符-
//                {                    
//                    String str = editText.getText().toString();
//                    n1 = Double.parseDouble(str);
//                    opt = "-";    
//                    editText.setText("");    
//                    textView.setText(n1 + opt);
//                    break;
//                }
//                
//                case R.id.button81://+/-
//                {
//                    String str =editText.getText().toString();
//                    n1 = Double.parseDouble(str);
//                    if(str.length() > 0)
//                        editText.setText(-n1 + "");
//                    textView.setText(-n1 + "");
//                    break;
//                }
//                case R.id.button71://CE
//                {
//                    String str =editText.getText().toString();
//                    if(str.length() > 0)
//                        editText.setText("");
//                    break;
//                }
//                case R.id.button91://%
//                {
//                	String str=editText.getText().toString();
//                	n1 = Double.parseDouble(str);
//                    opt = "%";    
//                    editText.setText("");    
//                    textView.setText((double) (n1/100)+"");
//                	
//                	
//                }
//
//                    
//            }
//            }catch(Exception e){}    
//        }    
//    };
//     
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
//        //first
//        //获取按钮的id
//        btn71 = (Button) findViewById(R.id.button71);
//        btn72 = (Button) findViewById(R.id.Button72);
//        btn73 = (Button) findViewById(R.id.Button73);
//        btn74 = (Button) findViewById(R.id.Button74);
//        btn81=  (Button) findViewById(R.id.button81);
//        btn82 = (Button) findViewById(R.id.Button82);
//        btn83 = (Button) findViewById(R.id.Button83);
//        btn84 = (Button) findViewById(R.id.Button84);
//        btn85 = (Button) findViewById(R.id.Button85);
//        btn91 = (Button) findViewById(R.id.button91);
//        btn92 = (Button) findViewById(R.id.Button92);
//        btn93 = (Button) findViewById(R.id.Button93);
//        btn94 = (Button) findViewById(R.id.Button94);
//        btn95 = (Button) findViewById(R.id.Button95);
//        btn101 = (Button) findViewById(R.id.button101);
//        btn102 = (Button) findViewById(R.id.Button102);
//        btn103 = (Button) findViewById(R.id.Button103);
//        btn104 = (Button) findViewById(R.id.Button104);
//        btn105 = (Button) findViewById(R.id.Button105);
//    
//        btn71.setOnClickListener(lisenter);
//        btn72.setOnClickListener(lisenter);
//        btn73.setOnClickListener(lisenter);
//        btn74.setOnClickListener(lisenter);
//        btn81.setOnClickListener(lisenter);
//        btn82.setOnClickListener(lisenter);
//        btn83.setOnClickListener(lisenter);
//        btn84.setOnClickListener(lisenter);
//        btn85.setOnClickListener(lisenter);
//        btn91.setOnClickListener(lisenter);
//        btn92.setOnClickListener(lisenter);
//        btn93.setOnClickListener(lisenter);
//        btn94.setOnClickListener(lisenter);
//        btn95.setOnClickListener(lisenter);
//        btn101.setOnClickListener(lisenter);
//        btn102.setOnClickListener(lisenter);
//        btn103.setOnClickListener(lisenter);
//        btn104.setOnClickListener(lisenter);
//        btn105.setOnClickListener(lisenter);
//    
//    
//
//    if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){ 
//
//    	Intent i = new Intent(SecondActivity.this,MainActivity.class);
//   	    startActivity(i);
//    }
//    
////	setContentView(R.layout.activity_second);}// 横屏 
//	}  
//
////    public void gotoa(View v) {
////    	
////   	setContentView(R.layout.activity_second);
////   }
////
////    public void gotob(View v) {
////    	
////    	setContentView(R.layout.activity_second);
////    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    
//public boolean onOptionsItemSelected(MenuItem item) {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    int id = item.getItemId();
//    if (id == R.id.action_settings) {
//        return true;
//    }
//    return super.onOptionsItemSelected(item);
//}
//
///**
// * A placeholder fragment containing a simple view.
// */
//public static class PlaceholderFragment extends Fragment {
//
//    public PlaceholderFragment() {
//    }
//
//   
//    }
//
//
//	protected void onCreate1(Bundle savedInstanceState){
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_second);
//	}
//}
//		
