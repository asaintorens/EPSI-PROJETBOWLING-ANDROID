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
	private String scores = "";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		Bundle b = getIntent().getExtras();
		this.idEquipe = b.getInt("idEquipe");
		System.out.println("identifiant equipe:   " + String.valueOf(idEquipe));
		this.affichageScores = ((TextView)findViewById(R.id.scoresTextview));
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
								//updateScores(getReception().getScores());
								updateScores();
							}
						});
					}
				} catch (InterruptedException e) {
				}
			}
		};
		t.start();

	}


	private void updateScores(){
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
					this.scores = scoreFormate;
				}
				this.affichageScores.setText(scoreFormate);
			}
			else{
				this.affichageScores.setText("La partie est finie" + "\n" + this.scores);
			}
		}
	}

	private receptionScores getReception(){
		return this.reception;
	}

	
	public void setScores (String scores){
		this.scores = scores;
	}
	

}
