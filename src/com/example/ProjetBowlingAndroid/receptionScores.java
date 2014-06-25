package com.example.ProjetBowlingAndroid;

import envoiScores.threadEnvoiScoresPrx;
import envoiScores.threadEnvoiScoresPrxHelper;

public class receptionScores extends Thread {

	private String scores;
	
	private int idEquipe;
	private boolean partieCommencee = false;
	private boolean partieFinie = false;
	public receptionScores(int idEquipe) {
		this.idEquipe = idEquipe;	
	}

	public void run() {		
		Ice.Communicator ic = Ice.Util.initialize();	
		Ice.ObjectPrx base1 = ic.stringToProxy("envoiScores :tcp -h 192.168.1.10 -p 10020");
		threadEnvoiScoresPrx envoiScores = threadEnvoiScoresPrxHelper.checkedCast(base1);
		if (envoiScores == null)
			System.out.println("Invalid proxy");
		while(!this.partieFinie){
			System.out.println("oki4");
			this.scores = envoiScores.getScores(this.idEquipe);
			if (!this.scores.equals("0")){
				this.partieCommencee = true;
				System.out.println("la partie commence");
			}
			if (this.partieCommencee && this.scores.equals("0")){
				System.out.println("la partie est finie");
				this.partieFinie = true;
			}
			System.out.println("oki5" + this.scores);
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("oki6");
				e.printStackTrace();
			}
			
		}
		System.out.println("thread termine");

	}

	public boolean getPartieFinie(){
		return this.partieFinie;
	}
	
	public boolean getPartieCommencee(){
		return this.partieCommencee;
	}
	
	public String getScores(){
		return this.scores;
	}

}
