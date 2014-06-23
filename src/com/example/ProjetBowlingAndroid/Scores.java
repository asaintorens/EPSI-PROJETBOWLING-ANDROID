package com.example.ProjetBowlingAndroid;

import java.util.ArrayList;
import java.util.List;

import receptionJoueurs.threadReceptionJoueursPrxHelper;
import envoiScores.threadEnvoiScoresPrxHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.widget.EditText;
import android.widget.TextView;

public class Scores extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		Bundle b = getIntent().getExtras();
		int idEquipe = b.getInt("idEquipe");
		boolean partieFinie = false;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize();	
			Ice.ObjectPrx base1 = ic.stringToProxy("envoiScores :tcp -h 192.168.1.10 -p 10020");
			envoiScores.threadEnvoiScoresPrx envoiScores = threadEnvoiScoresPrxHelper.checkedCast(base1);
			if (envoiScores == null)
				throw new Error("Invalid proxy");

			TextView affichageScores = ((TextView)findViewById(R.id.scoresTextview));
			String affichage = "";
			while (!partieFinie){
				String scores = envoiScores.getScores(idEquipe);
				if (!scores.equals("0")){
					String[] scoresParsee = scores.split("\\,");
					for (String ligne : scoresParsee){
						affichage = affichage + ligne + "\n";
					}
				}
				affichageScores.setText(affichage);
			}

		} catch (Ice.LocalException e) {
			e.printStackTrace();

		} catch (Exception e) {
			System.err.println(e.getMessage());

		}
		if (ic != null) {
			//Clean up
			//
			System.out.println("ok4" );
			try {
				System.out.println("ok5" );
				ic.destroy();
			} catch (Exception e) {
				//  System.out.println("ok6" );
				//System.out.println(e.getCause());

			}
		}

	}

}


