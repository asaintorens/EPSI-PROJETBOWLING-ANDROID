package com.example.ProjetBowlingAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.widget.EditText;
import android.widget.TextView;

public class Confirmation extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		String tempsAttente = null;
		Bundle b = getIntent().getExtras();
		String infoConfirm = b.getString("infoConfirm");
		String[] infoConfirmParsee = infoConfirm.split("\\,");
		tempsAttente = infoConfirmParsee[0];
		if (tempsAttente.equals("0")){
			tempsAttente = "maintenant";
		}
		else{
			tempsAttente = "dans " + Integer.valueOf(tempsAttente) / 60 + " minute(s)";
		}
		((TextView)findViewById(R.id.infoConfirmationTextview)).setText("Veuillez vous rendre à la piste n° " 
				+ infoConfirmParsee[1] + " " + tempsAttente );
		
		
	}

}
