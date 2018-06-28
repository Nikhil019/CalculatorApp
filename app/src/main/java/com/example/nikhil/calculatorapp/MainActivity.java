package com.example.nikhil.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button zero,one,two,three,four,five,six,seven,eight,nine,add,sub,mul,div,del,clear,dot,equal;
    private EditText text;
    private TextView res;

    double a,b;
    char op=' ';
    int e1=0;
    int d=0,e=0,f=0,negative=0,result=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ids();
        hintSet();
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);

/*        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(text.getText().toString()+"0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(text.getText().toString()+"1");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(text.getText().toString()+"+");
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp =text.getText().parseInt();

            }
        });*/

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result=0;
                if(negative==1){
                    b=-b;
                    negative=0;}
                if(op=='+') {
                    a=a+b;
                }
                else if (op=='-') {
                    a=a-b;
                }
                else if(op=='X') {
                    a=a*b;
                }
                else if(op=='/') {
                    a=a/b;
                }
                b=0;
                op=' ';
                res.setText(""+a);
                //tv2.setText(""+a);
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e==0) {
                    res.setText(text.getText().toString() + '.');
                    e++;
                }
                else {
                    Toast.makeText(MainActivity.this,"More than two decimal points not allowed",Toast.LENGTH_LONG).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f++;
                e1=e;
                equal.callOnClick();
                op='+';
                d++;
                res.setText(text.getText().toString()+op);
                e=0;
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f++;
                e1=e;
                if(op!=' '&&result==0) {
                    negative = 1;
                    res.setText(text.getText().toString()+"-");
                }
                else{
                    equal.callOnClick();
                    op='-';
                    res.setText(text.getText().toString()+op);}
                d++;
                e=0;
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f++;
                e1=e;
                equal.callOnClick();
                d++;
                op='*';
                res.setText(text.getText().toString()+op);
                e=0;
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f++;
                e1=e;
                equal.callOnClick();
                d++;
                op='/';
                res.setText(text.getText().toString()+op);
                e=0;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
                res.setText("");
                res.setHint("");
                a=0;b=0;d=0;e=0;
                op=' ';
            }
        });

        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                del.callOnClick();
                return true;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=text.getText().toString();
                if(d==0) {
                    if(e==0)
                    {
                        a=(a-(a%10))/10;
                    }
                    else
                    {
                        if(s.charAt(s.length()-1)!='.')
                        {
                            e--;
                            a=a*Math.pow(10,e);
                            a=a-(a%10);
                            a=a/Math.pow(10,e);
                        } else {
                            a=a*Math.pow(10,e);
                            a=a/Math.pow(10,e);
                            e=0;
                        }
                    }
                } else {
                    if(e==0) {
                        if(s.charAt(s.length()-1)!='+'&&s.charAt(s.length()-1)!='-'&&s.charAt(s.length()-1)!='*'&&s.charAt(s.length()-1)!='/') {
                            b = (b - (b % 10)) / 10;
                        }
                        else {
                            if(s.charAt(s.length()-1)!='-'||negative==0){
                                op=' ';
                                d--;
                                e=e1;}
                            else{
                                negative=0;
                            }
                        }
                    } else {
                        if(s.charAt(s.length()-1)!='.') {
                            e--;
                            b=b*Math.pow(10,e);
                            b=b-(b%10);
                            b=b/Math.pow(10,e);
                        }
                        else {
                            b=b*Math.pow(10,e);
                            b=b/Math.pow(10,e);
                            e=0;
                        }
                    }
                }
                if(!s.isEmpty()) {
                    s = s.substring(0, s.length() - 1);
                    res.setText(s);
                    hintSet();
                } else {
                    del.callOnClick();
                }
            }
        });

    }

    public void onClick(View v) {

        Button btn=findViewById(v.getId());
        Double y=Double.parseDouble(btn.getText().toString());
        res.setText(text.getText().toString()+btn.getText());

        if(e==0) {
            if (d == 0)
                a = 10 * a + y;
            else {
                b = 10 * b + y;
                result = 1;
            }
        } else
        {
            if(d==0) {
                a=a+y/Math.pow(10,e);
            } else {
                b=b+y/Math.pow(10,e);
                result=1;
            }
            e++;
        }
        hintSet();
    }

    public void ids(){
        zero=findViewById(R.id.b0);
        one=findViewById(R.id.b1);
        two=findViewById(R.id.b2);
        three=findViewById(R.id.b3);
        four=findViewById(R.id.b4);
        five=findViewById(R.id.b5);
        six=findViewById(R.id.b6);
        seven=findViewById(R.id.b7);
        eight=findViewById(R.id.b8);
        nine=findViewById(R.id.b9);
        add=findViewById(R.id.badd);
        mul=findViewById(R.id.bmul);
        div=findViewById(R.id.bdiv);
        sub=findViewById(R.id.bmin);
        dot=findViewById(R.id.bdot);
        del=findViewById(R.id.del);
        clear=findViewById(R.id.clear);
        equal=findViewById(R.id.bequ);
        text=findViewById(R.id.text);
        res=findViewById(R.id.res);
    }

    public void hintSet() {
        double c=a;
        if(negative==1)
            b=-b;
        if(op=='+') {
            c = a+b;
        }
        else if (op=='-') {
            c=a-b;
        }
        else if(op=='*') {
            c=a*b;
        }
        else if(op=='/') {
            c=a/b;
        }
        if(negative==1)
            b=-b;
        res.setText(""+c);
    }
}
