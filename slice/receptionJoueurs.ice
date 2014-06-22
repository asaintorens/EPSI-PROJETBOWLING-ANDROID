module receptionJoueurs {
	sequence<string> listeJoueurs;
    interface threadReceptionJoueurs {
        string inscriptionJoueur(listeJoueurs maListe);
    };
};