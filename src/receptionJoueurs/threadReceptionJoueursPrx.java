// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `receptionJoueurs.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package receptionJoueurs;

public interface threadReceptionJoueursPrx extends Ice.ObjectPrx
{
    public int inscriptionJoueur(String[] maListe);

    public int inscriptionJoueur(String[] maListe, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_inscriptionJoueur(String[] maListe);

    public Ice.AsyncResult begin_inscriptionJoueur(String[] maListe, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_inscriptionJoueur(String[] maListe, Ice.Callback __cb);

    public Ice.AsyncResult begin_inscriptionJoueur(String[] maListe, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_inscriptionJoueur(String[] maListe, Callback_threadReceptionJoueurs_inscriptionJoueur __cb);

    public Ice.AsyncResult begin_inscriptionJoueur(String[] maListe, java.util.Map<String, String> __ctx, Callback_threadReceptionJoueurs_inscriptionJoueur __cb);

    public int end_inscriptionJoueur(Ice.AsyncResult __result);
}