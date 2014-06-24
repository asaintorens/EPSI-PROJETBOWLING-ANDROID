package com.example.ProjetBowlingAndroid;

import java.util.ArrayList;
import java.util.List;

import receptionJoueurs.threadReceptionJoueursPrxHelper;
import envoiScores.threadEnvoiScoresPrxHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;

public class Scores extends Activity {

	private TextView affichageScores;
	private int idEquipe;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("ok1");
		setContentView(R.layout.scores);
		System.out.println("ok2");
		Bundle b = getIntent().getExtras();
		System.out.println("ok3");
		this.idEquipe = b.getInt("idEquipe");
		System.out.println("ok4");
		System.out.println("identifiant equipe:   " + String.valueOf(idEquipe));
		System.out.println("ok5");
		System.out.println("ok6");
		this .affichageScores = ((TextView)findViewById(R.id.scoresTextview));
		System.out.println("ok7");
		traitement();
		
	}
		public void traitement(){
			receptionScores reception = new receptionScores(this.idEquipe);
			reception.start();
			String scores ="";
			boolean partieFinie = false;
			boolean partieCommencee = false;
			String affichage = "";
		while (!partieFinie){
			System.out.println("debut");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scores = reception.getScores();
			System.out.println("getscores");
			System.out.println("scores:::::::::::::::::::::::::" + scores);
			if (scores != null){
			if (!scores.equals("0")){
				System.out.println("normal");
				partieCommencee = true;
				String[] scoresParsee = scores.split("\\,");
				for (String ligne : scoresParsee){
					affichage = affichage + ligne + "\n";
				}
				this.affichageScores.setText(affichage);
			}
			else{
				if (!partieCommencee){
					System.out.println("partie as commence");
					affichage = "La partie n'a pas commence";
					this.affichageScores.setText(affichage);
				}
				else{
					affichage = "La partie est finie.";
					partieFinie = true;
					System.out.println("partie finie");
					this.affichageScores.setText(affichage);
					reception.stop();
				}
			}
			}
		}

		
		
			

	}

}


