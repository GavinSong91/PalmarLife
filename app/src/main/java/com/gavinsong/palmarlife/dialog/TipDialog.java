package com.gavinsong.palmarlife.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gavinsong.palmarlife.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author gavinSong
 * @date 2017/12/13
 * @describe
 */

public class TipDialog extends BaseDialog {
    private final Context mContext;
    @BindView(R.id.cancelbtn)
    TextView cancelbtn;
    @BindView(R.id.confirmbtn)
    TextView confirmbtn;

    public TipDialog(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_tip, null);
        setContentView(view);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.cancelbtn)
    public void cancel(View view) {
        this.dismiss();
    }

    @OnClick(R.id.confirmbtn)
    public void confirm(View view) {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
        }
        this.dismiss();
    }


    public static void builde(Context context) {
        TipDialog tipDialog = new TipDialog(context);
        tipDialog.show();
    }
}
