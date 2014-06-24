package com.example.ProjetBowlingAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation extends Activity {
	
	private static int idEquipe;
	
	private OnClickListener printButtonListener = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(Confirmation.this, Scores.class);
			intent.putExtra("idEquipe", Confirmation.getIdEquipe());
			startActivity(intent);
		}
	};



	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		Button vButtonWeb = (Button)findViewById(R.id.buttonConfirmation);
		vButtonWeb.setOnClickListener(printButtonListener);

		String tempsAttente = null;
		Bundle b = getIntent().getExtras();
		String infoConfirm = b.getString("infoConfirm");
		String[] infoConfirmParsee = infoConfirm.split("\\,");
		this.setIdEquipe(Integer.valueOf(infoConfirmParsee[2]));
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



	public static int getIdEquipe() {
		return idEquipe;
	}



	public static void setIdEquipe(int idEquipe) {
		Confirmation.idEquipe = idEquipe;
	}





}

