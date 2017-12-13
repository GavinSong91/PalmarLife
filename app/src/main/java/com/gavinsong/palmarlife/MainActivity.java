package com.gavinsong.palmarlife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gavinsong.palmarlife.core.FingerprintCore;
import com.gavinsong.palmarlife.dialog.FingerprintDialog;
import com.gavinsong.palmarlife.dialog.TipDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fingerprintbtn)
    ImageView fingerprintbtn;
    private FingerprintCore fingerprintCore;

    @OnClick(R.id.fingerprintbtn)
    public void fingerPrintBtn(View view) {
        showDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showDialog();
    }

    /**
     * 判断显示那个dialog
     */
    public void showDialog() {
        if (fingerprintCore == null) {
            fingerprintCore = new FingerprintCore(this);
        }
        if (fingerprintCore.isSupport()) {
            if (fingerprintCore.isHasEnrolledFingerprints()) {
                FingerprintDialog.builde(this);
            } else {
                TipDialog.builde(this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fingerprintCore != null) {
            fingerprintCore.onDestroy();
            fingerprintCore = null;
        }
    }
}
