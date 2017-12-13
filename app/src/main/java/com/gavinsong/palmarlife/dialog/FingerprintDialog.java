package com.gavinsong.palmarlife.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gavinsong.palmarlife.R;
import com.gavinsong.palmarlife.core.FingerprintCore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author gavinSong
 * @date 2017/12/12
 * @describe
 */

public class FingerprintDialog extends BaseDialog {

    private static FingerprintCore fingerprintCore;
    @BindView(R.id.cancelbtn)
    TextView cancelbtn;
    @BindView(R.id.fingerprinttip)
    TextView fingerprinttip;
    @BindView(R.id.fingerprintflag)
    ImageView fingerprintflag;
    private Context mContext;

//    private FingerprintCore fingerprintCore;

    public FingerprintDialog(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_fingerprint, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        fingerprintCore = new FingerprintCore(mContext);
        fingerprintCore.setFingerprintManager(mIFingerprintResultListener);
        fingerprintCore.startAuthenticate();
        resetGuideViewState();
    }

    @OnClick(R.id.cancelbtn)
    public void cancelBtn(View view) {
        this.dismiss();
    }

    public static void builde(Context context) {
        FingerprintDialog dialog = new FingerprintDialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    private FingerprintCore.IFingerprintResultListener mIFingerprintResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            fingerprinttip.setText("指纹匹配");
            fingerprintflag.setImageResource(R.mipmap.checked_blue);
            fingerprintflag.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                    onDestory();
                }
            }, 1000);
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            fingerprinttip.setText("验证失败,\n请在试一次");
            fingerprintflag.setImageResource(R.mipmap.alarm);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            fingerprinttip.setText("验证失败,\n请在试一次");
            fingerprintflag.setImageResource(R.mipmap.alarm);
            fingerprintflag.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetGuideViewState();
                }
            }, 1000);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };

    private void resetGuideViewState() {
        fingerprinttip.setText("指纹识别");
        fingerprintflag.setImageResource(R.mipmap.fingerprint_grey);
    }

    public void onDestory() {
        if (fingerprintCore != null) {
            fingerprintCore.onDestroy();
            fingerprintCore = null;
        }
    }
}
