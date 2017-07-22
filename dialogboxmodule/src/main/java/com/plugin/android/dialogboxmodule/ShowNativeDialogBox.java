package com.plugin.android.dialogboxmodule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.ContextThemeWrapper;

import com.unity3d.player.UnityPlayer;

/**
 * Created by nipundavid on 6/21/2017.
 */

public class ShowNativeDialogBox {
    public static void ShowDialogPopup(String title, String message, String yesButtonText, String noButtonText) {
        AlertDialog.Builder dialogPopupBuilder = new AlertDialog.Builder(new ContextThemeWrapper(UnityPlayer.currentActivity, GetTheme()));
        dialogPopupBuilder.setTitle(title);
        dialogPopupBuilder.setMessage(message);
        dialogPopupBuilder.setPositiveButton(yesButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidCallBacks", "HandleCallBacks", "0");
            }
        });
        dialogPopupBuilder.setNegativeButton(noButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UnityPlayer.UnitySendMessage("AndroidCallBacks", "HandleCallBacks", "1");
            }
        });
        dialogPopupBuilder.setCancelable(false);
        dialogPopupBuilder.show();
    }

    private static int GetTheme(){
        int theme = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            theme = android.R.style.Theme_Material_Light_Dialog;
        } else {
            theme = android.R.style.Theme_Holo_Dialog;
        }
        return theme;
    }
}
