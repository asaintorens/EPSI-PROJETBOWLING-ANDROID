package com.example.ProjetBowlingAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MonBowling extends Activity {

    private static final int DIALOG_INITIALIZING = 1;
    private static final int DIALOG_ERROR = 2;
    private static final int DIALOG_FATAL = 3;

    private static final String DEFAULT_HOST = "10.0.2.2";
    private static final String HOSTNAME_KEY = "host";

    private static final String BUNDLE_KEY_TIMEOUT = "zeroc:timeout";
    private static final String BUNDLE_KEY_DELAY = "zeroc:delay";
    private static final String BUNDLE_KEY_FLUSH_ENABLED = "zeroc:flush";
    private static final String BUNDLE_KEY_LAST_ERROR = "zeroc:lastError";

    private Ice.Communicator _communicator = null;
    private DeliveryMode _deliveryMode;

    private Button _sayHelloButton;
    private Button _shutdownButton;

    private TextView _status;



    private Button _flushButton;

    private String _lastError = "";

    enum DeliveryMode
    {
        TWOWAY,
        TWOWAY_SECURE,
        ONEWAY,
        ONEWAY_BATCH,
        ONEWAY_SECURE,
        ONEWAY_SECURE_BATCH,
        DATAGRAM,
        DATAGRAM_BATCH;

        Ice.ObjectPrx apply(Ice.ObjectPrx prx)
        {
            switch (this)
            {
                case TWOWAY:
                    prx = prx.ice_twoway();
                    break;
                case TWOWAY_SECURE:
                    prx = prx.ice_twoway().ice_secure(true);
                    break;
                case ONEWAY:
                    prx = prx.ice_oneway();
                    break;
                case ONEWAY_BATCH:
                    prx = prx.ice_batchOneway();
                    break;
                case ONEWAY_SECURE:
                    prx = prx.ice_oneway().ice_secure(true);
                    break;
                case ONEWAY_SECURE_BATCH:
                    prx = prx.ice_batchOneway().ice_secure(true);
                    break;
                case DATAGRAM:
                    prx = prx.ice_datagram();
                    break;
                case DATAGRAM_BATCH:
                    prx = prx.ice_batchDatagram();
                    break;
            }
            return prx;
        }

        public boolean isBatch()
        {
            return this == ONEWAY_BATCH || this == DATAGRAM_BATCH || this == ONEWAY_SECURE_BATCH;
        }
    }
	
	 private OnClickListener printButtonListener = new OnClickListener() {
	        public void onClick(View v) {

                int status = 0;
                Ice.Communicator ic = null;
                try {
                    ic = Ice.Util.initialize();
                    System.out.println("ok1" );
                    Ice.ObjectPrx base = ic.stringToProxy("receptionJoueur :tcp -h 192.168.1.69 -p 10020");
                    System.out.println("ok2" );
                    receptionJoueurs.threadReceptionJoueursPrx receptionJoueur = receptionJoueurs.threadReceptionJoueursPrxHelper.checkedCast(base);
                    System.out.println("ok3" );
                    if (receptionJoueur == null)
                        throw new Error("Invalid proxy");

                    List<String> equipe = new ArrayList<String>();
                    List<EditText> editTextList = new ArrayList<EditText>();
                    editTextList.add((EditText)findViewById(R.id.editText1));
                    editTextList.add((EditText)findViewById(R.id.editText2));
                    editTextList.add((EditText)findViewById(R.id.editText3));
                    editTextList.add((EditText)findViewById(R.id.editText4));
                    editTextList.add((EditText)findViewById(R.id.editText5));
                    editTextList.add((EditText)findViewById(R.id.editText6));

                    for(int i = 0; i < editTextList.size(); i++)
                    {
                        String value = editTextList.get(i).getText().toString().trim();
                        if(!value.isEmpty())
                            equipe.add(value);
                    }

                } catch (Ice.LocalException e) {
                    e.printStackTrace();
                    status = 1;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    status = 1;
                }
                if (ic != null) {
                    //Clean up
                    //
                    System.out.println("ok4" );
                    try {
                        System.out.println("ok5" );
                      ic.destroy();
                    } catch (Exception e) {
                        System.out.println("ok6" );
                        System.out.println(e.getCause());
                        status = 1;
                    }
                }

	        }
	    };
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView vTextView = (TextView)findViewById(R.id.textView1);
        Button vButtonWeb = (Button)findViewById(R.id.button1);
        vButtonWeb.setOnClickListener(printButtonListener);
        /*{
        	public void onClick(View v)
        	{
        		int status = 0;
        		Ice.Communicator ic = null;
        		try {
        			ic = Ice.Util.initialize();
        			Ice.ObjectPrx base = ic
        					.stringToProxy("receptionJoueur:default -p 10020");
        			receptionJoueurs.threadReceptionJoueursPrx receptionJoueur = receptionJoueurs.threadReceptionJoueursPrxHelper.checkedCast(base);
        			if (receptionJoueur == null)
        				throw new Error("Invalid proxy");
        			
        			String[] equipe = new String[]{"Johan","Edouard"};
        			
        			vTextView.setText((String.valueOf(receptionJoueur.inscriptionJoueur(equipe))));
        			
        		} catch (Ice.LocalException e) {
        			e.printStackTrace();
        			status = 1;
        		} catch (Exception e) {
        			System.err.println(e.getMessage());
        			status = 1;
        		}
        		if (ic != null) {
        			// Clean up
        			//
        			try {
        				ic.destroy();
        			} catch (Exception e) {
        				System.err.println(e.getMessage());
        				status = 1;
        			}
        		}
        		System.exit(status);
        	}
        });*/
    }
}
