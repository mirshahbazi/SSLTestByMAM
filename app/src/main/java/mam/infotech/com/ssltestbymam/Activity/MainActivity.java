package mam.infotech.com.ssltestbymam.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;

import mam.infotech.com.ssltestbymam.Connection.mSSLSocket;
import mam.infotech.com.ssltestbymam.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                testConnection(view);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testConnection(final View view) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    byte[] TPDU = new byte[]{(byte) 0x60, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x88};//ssl real
                    mSSLSocket mamsocket = new mSSLSocket();
                    SSLSocket client = mamsocket.getInstance(MainActivity.this, "92.242.223.117", 20443);
                    client.setUseClientMode(true);
                    client.setSoTimeout(35000);
                    //socket send data
                    client.getOutputStream().write(TPDU);
                    client.getOutputStream().flush();
                    //recive data
                    byte[] arrOutut = new byte[1024];
                    int count = client.getInputStream().read(arrOutut, 0, 1024);

                    String clientRequest = "";
                    for (int outputCount = 0; outputCount < count; outputCount++) {
                        char response = (char) arrOutut[outputCount];
                        clientRequest = clientRequest + response;
                    }

                    //close connection
                    client.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, "MAM Connection Is OK Now", Snackbar.LENGTH_LONG)
                                    .setAction("SSLHandshakeException", null).show();
                        }
                    });


                } catch (final SSLHandshakeException sslex) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, sslex.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("SSLHandshakeException", null).show();
                        }
                    });


                } catch (final IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("IOException", null).show();
                        }
                    });

                } catch (final CertificateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("CertificateException", null).show();
                        }
                    });

                } catch (final NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("NoSuchAlgorithmException", null).show();
                        }
                    });

                } catch (final UnrecoverableKeyException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("UnrecoverableKeyException", null).show();
                        }
                    });

                } catch (final KeyStoreException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("KeyStoreException", null).show();
                        }
                    });

                } catch (final KeyManagementException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Snackbar.make(view, e.toString(), Snackbar.LENGTH_LONG)
                                    .setAction("KeyManagementException", null).show();
                        }
                    });

                }

            }

    }).start();
}
}
