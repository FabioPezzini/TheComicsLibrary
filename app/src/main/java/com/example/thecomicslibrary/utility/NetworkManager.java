package com.example.thecomicslibrary.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import androidx.annotation.NonNull;
import com.example.thecomicslibrary.R;

public class NetworkManager {
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;
    private AlertDialog alertDialog;


    public NetworkManager(@NonNull final Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkRequest request = new NetworkRequest.Builder().build();

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onLost(@NonNull Network network) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert)
                        .setTitle("No Internet")
                        .setMessage("Connection Lost...")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int pid = android.os.Process.myPid();
                                android.os.Process.killProcess(pid);
                                System.exit(0);
                            }
                        });
                alertDialog = builder.show();
            }

            @Override
            public void onAvailable(@NonNull Network network) {
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
            }
        };

        connectivityManager.registerNetworkCallback(request,networkCallback);
    }

}
