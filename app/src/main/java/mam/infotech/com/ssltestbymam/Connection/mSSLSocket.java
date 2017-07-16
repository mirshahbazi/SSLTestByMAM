package mam.infotech.com.ssltestbymam.Connection;

import android.content.Context;



import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import mam.infotech.com.ssltestbymam.R;


// Created by mmirshahbazi on 11/15/2016.

public class mSSLSocket {

    private SSLSocket ourInSslContext;

    public SSLSocket getInstance(Context ctx, String ipAddress, int portNumber) throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {

        if (ourInSslContext == null){
            SSLContext sslContext = SSLContext.getInstance("TLS");
            KeyStore trustSt = KeyStore.getInstance("BKS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            InputStream trustStoreStream = ctx.getResources().openRawResource(R.raw.sslv3);
            trustSt.load(trustStoreStream,"infotech1395".toCharArray());
            trustManagerFactory.init(trustSt);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory
                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(trustSt, "infotech1395".toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            SSLSocketFactory sf = sslContext.getSocketFactory();
            ourInSslContext = (SSLSocket) sf.createSocket(ipAddress,portNumber);
        }
        return ourInSslContext;
    }

    public mSSLSocket() {

    }
}
