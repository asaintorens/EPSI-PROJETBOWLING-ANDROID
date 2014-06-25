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

	private receptionScores reception;
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
		this.affichageScores = ((TextView)findViewById(R.id.scoresTextview));
		System.out.println("ok7");
		traitement();

	}
	public void traitement(){
		this.reception = new receptionScores(this.idEquipe);
		this.reception.start();

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					while (!getReception().getPartieFinie()) {
						Thread.sleep(1000);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								updateScores(getReception().getScores());
							}
						});
					}
				} catch (InterruptedException e) {
				}
			}
		};
		t.start();

	}


	private void updateScores(String scores){
		if (!this.reception.getPartieCommencee()){
			this.affichageScores.setText("La partie n'a pas commence");
		}
		else{
			if(!this.reception.getPartieFinie()){
				String[] scoresParJoueur;
				String scoreFormate = "";
				scoresParJoueur = this.reception.getScores().split(",");
				for (String ligne : scoresParJoueur){
					scoreFormate = scoreFormate + ligne + "\n";
				}
				this.affichageScores.setText(scoreFormate);
			}
			else{
				this.affichageScores.setText("La partie est finie");
			}
		}
	}

	private receptionScores getReception(){
		return this.reception;
	}


}
