package com.example.dell.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button calcultorButton;
    private EditText weightEditText;
    private CheckBox manCheckBox;
    private CheckBox womanChckBox;
    private TextView resultTextView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcultorButton=(Button)findViewById(R.id.calculator);
        weightEditText=(EditText)findViewById(R.id.weight);
        manCheckBox=(CheckBox)findViewById(R.id.man);
        womanChckBox=(CheckBox)findViewById(R.id.woman);
        resultTextView=(TextView)findViewById(R.id.result);
    }
    protected void onStart(){
        super.onStart();
        registerEvent();
    }

    public MainActivity() {
    }


    private double evaluateHeight(double weight, String sex){
        double height;
        if (sex=="男"){
            height =170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }

private void showMessage(String message){
    AlertDialog alert=new AlertDialog.Builder(this).create();
    alert.setTitle("系统信息");
    alert.setMessage(message);
    alert.setButton("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int whichButton) {

        }
    });
    alert.show();
}
private void registerEvent() {
    calcultorButton.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {


            if (!weightEditText.getText().toString().trim().equals(""))

            {

                if (manCheckBox.isChecked() || womanChckBox.isChecked()) {
                    double weight = 0;
                    weight = Double.parseDouble(weightEditText.getText().toString());
                    StringBuffer sb = new StringBuffer();
                    sb.append("-----------评估结果----------\n");
                    if (manCheckBox.isChecked()) {
                        sb.append("男性标准身高：");
                        double result = evaluateHeight(weight, "男");
                        sb.append((int) result + "(厘米)");
                    }
                    if (womanChckBox.isChecked()) {
                        sb.append("女性标准身高：");
                        double result = evaluateHeight(weight, "女");
                        sb.append((int) result + "(厘米)");
                    }
                    String result = sb.toString();
                    TextView resultTextView = (TextView) findViewById(R.id.result);
                    resultTextView.setText(result);
                } else {
                    showMessage("请选择性别！");
                }
            } else {
                showMessage("请输入体重！");
            }
        }
        });
}


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
