package com.example.ProjetBowlingAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class MonBowling extends Activity {

	private Ice.Communicator ic = null;
	
	 private OnClickListener printButtonListener = new OnClickListener() {
	        public void onClick(View v) {

      
                try {
                    ic = Ice.Util.initialize();
                    Ice.ObjectPrx base = ic.stringToProxy("receptionJoueur :tcp -h 192.168.1.10 -p 10020");
                    receptionJoueurs.threadReceptionJoueursPrx receptionJoueur = receptionJoueurs.threadReceptionJoueursPrxHelper.checkedCast(base);
                    if (receptionJoueur == null)
                        throw new Error("Invalid proxy");                   
                    String equipe[] = new String[6];
                    List<EditText> editTextList = new ArrayList<EditText>();
                    editTextList.add((EditText)findViewById(R.id.infoConfirm));
                    editTextList.add((EditText)findViewById(R.id.editText2));
                    editTextList.add((EditText)findViewById(R.id.editText3));
                    editTextList.add((EditText)findViewById(R.id.editText4));
                    editTextList.add((EditText)findViewById(R.id.editText5));
                    editTextList.add((EditText)findViewById(R.id.editText6));
                    for(int i = 0; i < editTextList.size(); i++)
                    {
                        String value = editTextList.get(i).getText().toString().trim();
                        if(!value.isEmpty())
                            equipe[i] = value;
                    }
                    String infoConfirm = receptionJoueur.inscriptionJoueur(equipe);
                    Intent intent = new Intent(MonBowling.this, Confirmation.class);
                    intent.putExtra("infoConfirm", infoConfirm);
                    startActivity(intent);
                    

                } catch (Ice.LocalException e) {
                    e.printStackTrace();
                 
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                   
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
        Button vButtonWeb = (Button)findViewById(R.id.button1);
        vButtonWeb.setOnClickListener(printButtonListener);
    }
    

}
