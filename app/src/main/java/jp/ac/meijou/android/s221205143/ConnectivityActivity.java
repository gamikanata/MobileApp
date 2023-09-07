package jp.ac.meijou.android.s221205143;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s221205143.databinding.ActivityConectivityBinding;

public class ConnectivityActivity extends AppCompatActivity {

    private ActivityConectivityBinding binding;

    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConectivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
    }

    private void updateTransport(Network network) {
        var caps = connectivityManager.getNetworkCapabilities(network);
        if (caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            } else if(caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFI";
            } else {
                transport = "その他";
            }
            binding.transportName.setText(transport);
        }
    }

    private void updateIpAddress(Network network) {
        var linkProps = connectivityManager.getLinkProperties(network);
        if (linkProps != null) {
            var addresses = linkProps.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.ipAddressName.setText(addresses);
        }
    }
}