package com.example.ProjetBowlingAndroid;

import envoiScores.threadEnvoiScoresPrx;
import envoiScores.threadEnvoiScoresPrxHelper;

public class receptionScores extends Thread {

	private String scores;
	
	private int idEquipe;
	
	public receptionScores(int idEquipe) {
		this.idEquipe = idEquipe;
		System.out.println("id equipe 2 1   "   + String.valueOf(this.idEquipe));
		
		System.out.println("oki1");
		
	}

	public void run() {		
		Ice.Communicator ic = Ice.Util.initialize();	
		System.out.println("oki2");
		Ice.ObjectPrx base1 = ic.stringToProxy("envoiScores :tcp -h 192.168.1.10 -p 10020");
		System.out.println("oki3");
		threadEnvoiScoresPrx envoiScores = threadEnvoiScoresPrxHelper.checkedCast(base1);
		if (envoiScores == null)
			System.out.println("Invalid proxy");
		while(true){
			System.out.println("oki4");
			this.scores = envoiScores.getScores(this.idEquipe);
			System.out.println("oki5" + this.scores);
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("oki6");
				e.printStackTrace();
			}
		}
		

	}


	public String getScores(){
		return this.scores;
	}

}
