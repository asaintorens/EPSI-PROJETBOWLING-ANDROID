package com.example.ProjetBowlingAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MonBowling extends Activity {
	
	 private OnClickListener printButtonListener = new OnClickListener() {
	        public void onClick(View v) {
	            // Capture our edit text from layout
	            EditText log = (EditText) findViewById(R.id.editText1);
	            log.append("Lets call the printer class !!!\n");
	            
	            //Lets do basic ICE stuff
	            Ice.Communicator ic = null;
	            try {
	                log.append("Initializing ICE\n");
	                ic = Ice.Util.initialize();
	                log.append("Initialized ICE, creating Object proxy\n");
	                
	                Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:default -p 10000");
	                log.append("Created Object proxy, casting to Printer proxy\n");
	                
	                receptionJoueurs.threadReceptionJoueursPrx receptionJoueur = receptionJoueurs.threadReceptionJoueursPrxHelper.checkedCast(base);
	                log.append("Casted to Printer proxy\n");
	                
	                if (receptionJoueur == null) {
	                    log.append("Cast to Printer proxy FAILED\n");
	                    throw new Error("Invalid proxy");
	                }
	 
	                log.append("Cast to Printer proxy SUCCESS, calling method\n");
	                String[] equipe = new String[]{"Johan","Edouard"};
	                log.append(String.valueOf(receptionJoueur.inscriptionJoueur(equipe)));
	                
	            } catch (Ice.LocalException e) {
	                e.printStackTrace();
	                log.append("EXCEPTION: " + e.getMessage());
	            } catch (Exception e) {
	                System.err.println(e.getMessage());
	                log.append("EXCEPTION: " + e.getMessage());
	            }
	            if (ic != null) {
	                // Clean up
	                try {
	                    log.append(" Destroying ic\n");
	                    ic.destroy();
	                } catch (Exception e) {
	                    System.err.println(e.getMessage());
	                    log.append("EXCEPTION: " + e.getMessage());
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
