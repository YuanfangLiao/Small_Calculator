package com.ISAS.calculatorproject;



import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn18,btn19,btn20;
    private EditText editText;
    private String opt = "+";
    private double n1 = 0.0, n2 = 0.0;//涓や釜鎿嶄綔鏁�
    private TextView textView;//鏄剧ず绠楀紡
    
    
    private OnClickListener lisenter = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub            
            editText = (EditText)findViewById(R.id.editText1);
            textView = (TextView) findViewById(R.id.textView1);
            String s = editText.getText().toString();//鑾峰彇瀛楃涓�
            Button btn =(Button)v;
            try{
    
            switch(btn.getId())
            {
                case R.id.button1://1
                {                                    
                    String str = editText.getText().toString();
                    editText.setText(str + 1);
                    str = editText.getText().toString();
                    textView.setText(str);                
                    break;
                }
                case R.id.button2://+
                {
                    String str = editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    opt = "+";                    
                    textView.setText(n1 + opt);
                    editText.setText("");
                    break;
                }
                case R.id.button3://2
                {                    
                    String str = editText.getText().toString();
                    editText.setText(str + 2);    
                    str = editText.getText().toString();
                    textView.setText(str);                
                    break;
                }
                case R.id.button4://鎿嶄綔绗�=
                {                    
                    if(opt == "+")
                    {                        
                        String str = editText.getText().toString();
                        n2 = Double.parseDouble(str);
                        textView.setText(n1 + opt + n2 );
                        editText.setText((n1 + n2) + "");
                    }
                    else if(opt == "-")
                    {
                        String str = editText.getText().toString();
                        n2 = Double.parseDouble(str);
                        textView.setText(n1 + opt + n2 );
                        editText.setText((n1 - n2) + "");
                    }
                    else if(opt == "*")
                    {
                        String str = editText.getText().toString();
                        n2 = Double.parseDouble(str);
                        textView.setText(n1 + opt + n2 );
                        editText.setText((n1 * n2) + "");
                    }
                    else if(opt == "/")
                    {
                        String str = editText.getText().toString();
                        n2 = Double.parseDouble(str);
                        if(n2 == 0)
                        {
                            editText.setText("");
                            textView.setText("闄ゆ暟涓嶈兘涓�0");
                            break;
                        }                        
                        else
                        {
                            textView.setText(n1 + opt + n2 );
                            editText.setText((n1 / n2) + "");
                        }
                    }
    
                    break;
                }
                case R.id.button5://3
                {                    
                    editText.setText(editText.getText().toString() + 3);
                    String str = editText.getText().toString();
                    textView.setText(str);    
                    break;
                }
                case R.id.button6://4
                {
                    editText.setText(editText.getText().toString() + 4);
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button7://5
                {
                    editText.setText(editText.getText().toString() + 5);
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button8://6
                {
                    editText.setText(editText.getText().toString() + 6);
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button9://7
                {
                    editText.setText(editText.getText().toString() + 7);
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button10://8
                {
                    editText.setText(editText.getText().toString() + 8);    
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button11://9
                {
                    editText.setText(editText.getText().toString() + 9);
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button12://0
                {                    
                    textView.setText(n1 + opt + 10);
                    editText.setText(editText.getText().toString() + 0);
                    String str = editText.getText().toString();
                    textView.setText(str);
                    break;
                }
                case R.id.button13://.
                {
                    String str = editText.getText().toString();
                    if(str.indexOf(".") != -1) //鍒ゆ柇瀛楃涓蹭腑鏄惁宸茬粡鍖呭惈浜嗗皬鏁扮偣锛屽鏋滄湁灏变粈涔堜篃涓嶅仛
                    {
                        
                    } 
                    else //濡傛灉娌℃湁灏忔暟鐐� 
                    {        
                        if(str.equals("0"))//濡傛灉寮�濮嬩负0锛�
                            editText.setText(("0" + ".").toString());
                        else if(str.equals(""))//濡傛灉鍒濇椂鏄剧ず涓虹┖锛屽氨浠�涔堜篃涓嶅仛
                        {    
                            
                        }
                        else
                            editText.setText(str + ".");
                    }
                    break;
                }
                case R.id.button14://鎿嶄綔绗� /
                {                
                    String str = editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    opt = "/";    
                    editText.setText("");
                    textView.setText(n1 + opt);
                    break;
                }
                case R.id.button15://鎿嶄綔绗�*
                {        
                    String str = editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    opt = "*";    
                    editText.setText("");
                    textView.setText(n1 + opt);
                    break;
                }
                case R.id.button16://鎿嶄綔绗�-
                {                    
                    String str = editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    opt = "-";    
                    editText.setText("");    
                    textView.setText(n1 + opt);
                    break;
                }
                
                case R.id.button18://+/-
                {
                    String str =editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    if(str.length() > 0)
                        editText.setText(-n1 + "");
                    textView.setText(-n1 + "");
                    break;
                }
                case R.id.button19://CE
                {
                    String str =editText.getText().toString();
                    if(str.length() > 0)
                        editText.setText("");
                    break;
                }
                case R.id.button20://%
                {
                	String str=editText.getText().toString();
                	n1 = Double.parseDouble(str);
                    opt = "%";    
                    editText.setText("");    
                    textView.setText((double) (n1/100)+"");
                	
                	
                }

                    
            }
            }catch(Exception e){}    
        }    
    };
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
        //鑾峰彇鎸夐挳鐨刬d
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6= (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btn10 = (Button) findViewById(R.id.button10);
        btn11 = (Button) findViewById(R.id.button11);
        btn12 = (Button) findViewById(R.id.button12);
        btn13 = (Button) findViewById(R.id.button13);
        btn14 = (Button) findViewById(R.id.button14);
        btn15 = (Button) findViewById(R.id.button15);
        btn16 = (Button) findViewById(R.id.button16);
        btn18 = (Button) findViewById(R.id.button18);
        btn19 = (Button) findViewById(R.id.button19);
        btn20 = (Button) findViewById(R.id.button20);
    
        btn1.setOnClickListener(lisenter);
        btn2.setOnClickListener(lisenter);
        btn3.setOnClickListener(lisenter);
        btn4.setOnClickListener(lisenter);
        btn5.setOnClickListener(lisenter);
        btn6.setOnClickListener(lisenter);
        btn7.setOnClickListener(lisenter);
        btn8.setOnClickListener(lisenter);
        btn9.setOnClickListener(lisenter);
        btn10.setOnClickListener(lisenter);
        btn11.setOnClickListener(lisenter);
        btn12.setOnClickListener(lisenter);
        btn13.setOnClickListener(lisenter);
        btn14.setOnClickListener(lisenter);
        btn15.setOnClickListener(lisenter);
        btn16.setOnClickListener(lisenter);
        btn18.setOnClickListener(lisenter);
        btn19.setOnClickListener(lisenter);
        btn20.setOnClickListener(lisenter);
   
        
        



     if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){ 

    	 Intent i = new Intent(MainActivity.this,SecondActivity.class);
    	 startActivity(i);
    	 finish();
    	 //setContentView(R.layout.activity_second);}// 只切换界面
     }
    }  


       
//     public void gotoa(View v) {
//    	 
//    	 Intent i = new Intent(MainActivity.this,SecondActivity.class);
//    	 startActivity(i);
//    	setContentView(R.layout.activity_second);
//    }
//
//    public void gotob(View v) {
//    	
//    	setContentView(R.layout.activity_first);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
        return true;
    }
    return super.onOptionsItemSelected(item);
}

/**
 * A placeholder fragment containing a simple view.
 */
public static class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

   
    }


}
