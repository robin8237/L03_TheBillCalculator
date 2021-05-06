package sg.edu.rp.c346.id20019018.l03thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tvAmt;
    EditText etAmt;
    TextView tvNum;
    EditText etNum;
    ToggleButton tbSVS;
    ToggleButton tbGST;
    TextView tvDis;
    EditText etDis;
    RadioGroup rgPayment;
    RadioButton rbCash;
    RadioButton rbPayNow;
    Button btnSpl;
    Button btnRes;
    TextView tvTotalBill;
    TextView tvEachPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAmt = findViewById(R.id.tvAmt);
        etAmt = findViewById(R.id.etAmt);
        tvNum = findViewById(R.id.tvNum);
        etNum = findViewById(R.id.etNum);
        tbSVS = findViewById(R.id.tbSVS);
        tbGST = findViewById(R.id.tbGST);
        tvDis = findViewById(R.id.tvDis);
        etDis = findViewById(R.id.etDis);
        rgPayment = findViewById(R.id.rgPayment);
        rbCash = findViewById(R.id.rbCash);
        rbPayNow = findViewById(R.id.rbPayNow);
        btnSpl = findViewById(R.id.btnSpl);
        btnRes = findViewById(R.id.btnRes);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        tvEachPay = findViewById(R.id.tvEachPay);

        btnSpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAmt.getText().toString().trim().length() != 0
                        && etNum.getText().toString().trim().length() != 0)
                {
                    double billAmt = 0.0;
                    double firstAmt = Double.parseDouble(etAmt.getText().toString());

                    if(!tbGST.isChecked() && !tbSVS.isChecked())
                    {
                        billAmt = firstAmt;
                    }
                    else if (tbGST.isChecked() && !tbSVS.isChecked())
                    {
                        billAmt = firstAmt * 1.07;
                    }
                    else if (tbSVS.isChecked() && !tbGST.isChecked())
                    {
                        billAmt = firstAmt * 1.10;
                    }
                    else
                    {
                        billAmt = firstAmt * 1.17;
                    }

                    if(etDis.getText().toString().trim().length() !=0)
                    {
                        billAmt = billAmt - (billAmt * Double.parseDouble(etDis.getText().toString()) / 100);
                    }

                    String outputMode = "";
                    if(rgPayment.getCheckedRadioButtonId() == R.id.rbPayNow)
                    {
                        outputMode = " via PayNow to 912345678";
                    }
                    else
                    {
                        outputMode = " in cash";
                    }

                    tvTotalBill.setText("Total Bill: $" + String.format("%.2f", billAmt));
                    int noPerson = Integer.parseInt(etNum.getText().toString());
                    if(noPerson != 1)
                    {
                        tvEachPay.setText("Each Pays: $" + String.format("%.2f", billAmt / noPerson) + outputMode);
                    }
                    else
                    {
                        tvEachPay.setText("Each Pays: $" + String.format("%.2f", billAmt) + outputMode);
                    }
                }
            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmt.setText("");
                etNum.setText("");
                tbSVS.setChecked(false);
                tbGST.setChecked(false);
                etDis.setText("");
                rgPayment.check(R.id.rbCash);
                tvTotalBill.setText("");
                tvEachPay.setText("");
            }
        });
    }
}