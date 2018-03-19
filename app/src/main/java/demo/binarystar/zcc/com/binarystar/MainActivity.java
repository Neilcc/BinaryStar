package demo.binarystar.zcc.com.binarystar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import demo.binarystar.zcc.com.binarystar.databinding.ActivityMainBinding;
import demo.binarystar.zcc.com.binarystar.presenter.TestPresenter;

public class MainActivity extends AppCompatActivity implements TestContractView, View.OnClickListener {

    TestPresenter testPresenter;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        testPresenter = new TestPresenter(this);
        activityMainBinding.setEventListener(this);
    }

    @Override
    public void operationOne(String param) {
        Toast.makeText(this, "oo" + param, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void operationTwo(Integer code) {
        Toast.makeText(this, "ot" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(Integer data) {
        activityMainBinding.progressText.setText(data.toString());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                testPresenter.doTestTask("哈哈");
                break;
            case R.id.send_progress:
                testPresenter.doProgressTask("进度");
                break;
            default:
                break;
        }

    }
}
