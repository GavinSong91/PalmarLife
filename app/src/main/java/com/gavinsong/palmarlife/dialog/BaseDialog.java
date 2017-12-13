package com.gavinsong.palmarlife.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import com.gavinsong.palmarlife.R;


public abstract class BaseDialog extends Dialog{

    private Context context;
    private static boolean isShow = false;

    public BaseDialog(Context context) {
        super(context, R.style.confirmDialogStyle);
        this.context = context;
    }

    @Override
    public final void show() {
        if (isShow) {
            return;
        }
        super.show();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        int width = (int) (d.getWidth() * 0.8);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width;
        getWindow().setAttributes(lp);

        isShow = true;
    }

    @Override
    public final void dismiss() {
        super.dismiss();
        isShow = false;
    }
}
