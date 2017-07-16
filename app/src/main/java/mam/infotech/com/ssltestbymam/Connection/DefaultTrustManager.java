package mam.infotech.com.ssltestbymam.Connection;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by mmirshahbazi on 9/4/2016.
 */
public class DefaultTrustManager implements X509TrustManager {
    private boolean debug = false;

    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        if (debug) {
            // System.err.println("default trust manager is called to validate certs ...");
            // System.err.println("returning 'true' for isServerTrusted call ...");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
