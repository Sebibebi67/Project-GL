--inscrire(IN nom varchar(45), IN prenom varchar(45), IN mdp varchar(45), IN role varchar(10))
inscrire(JOBARD,Alex,ceciestunmdp,Etudiant);
inscrire(HERT,Sébastien,lesel45,Etudiant);
inscrire(PARIS,Dejan,priority,Etudiant);
inscrire(RIVIERE,Adam,colucci,Etudiant);
inscrire(LEPERCQ,Thomas,joker2019,Etudiant);
inscrire(EULER,Leonard,eipimoins1egal0,Enseignant);
inscrire(BONAPARTE,Napoléon,waterlost,Enseignant);
inscrire(PIVOT,Bernard,apostrophe,Enseignant);
inscrire(DEVOS,Raymond,louiedeloiedelouis,Enseignant);
inscrire(NEESON,Liam,iwillfindyou,Enseignant);
inscrire(FERRY,Jules,obligatoire,Scolarite);
inscrire(DDE,Admin,motdepassesécurisé,DDE);

--modifierFiliereEtudiant (IN newFiliere VARCHAR(45), IN loginEtu VARCHAR(45))
modifierFiliereEtudiant(INFO,ajobard);
modifierFiliereEtudiant(SNUM,shert);
modifierFiliereEtudiant(PHOT,dparis);
modifierFiliereEtudiant(PHOT,ariviere);
modifierFiliereEtudiant(INFO,tlepercq);

--assignerGroupeTD (IN numero INT, IN loginEtu VARCHAR(45))
assignerGroupeTD(1,ajobard);
assignerGroupeTD(2,shert);
assignerGroupeTD(1,dparis);
assignerGroupeTD(2,ariviere);
assignerGroupeTD(2,tlepercq);

--assignerGroupeTP (IN numero INT, IN loginEtu VARCHAR(45))
assignerGroupeTP(1,ajobard);
assignerGroupeTP(2,shert);
assignerGroupeTP(1,dparis);
assignerGroupeTP(3,ariviere);
assignerGroupeTP(2,tlepercq);

--creerUE(IN nomUE varchar(45), IN loginRespo varchar(45), IN nbCredits int, IN filiere varchar(45))
creerUE(Sciences,leuler,6,INFO);
creerUE(Sciences,leuler,6,SNUM);
creerUE(Education Civique,nbonaparte,6,INFO);
creerUE(Langues,bpivot,6,PHOT);
creerUE(Langues,bpivot,6,SNUM);

--creerModule(IN nomModule varchar(45), IN loginRespo varchar(45))
creerModule(Maths,leuler);
creerModule(Histoire,nbonaparte);
creerModule(Francais,bpivot);
creerModule(Theatre,rdevos);
creerModule(Anglais,lneeson);

--enseigne(IN loginU varchar(45), IN nomModule varchar(45), IN role varchar(2), IN idGroupe int)
enseigne(leuler,Maths,CM,1);
enseigne(leuler,Maths,TD,1);
enseigne(leuler,Maths,TP,1);
enseigne(leuler,Maths,CM,2);
enseigne(leuler,Maths,TD,2);
enseigne(leuler,Maths,TP,2);
enseigne(nbonaparte,Histoire,CM,1);
enseigne(rdevos,Theatre,TP,2);
enseigne(bpivot,Francais,TD,1);
enseigne(lneeson,Anglais,TP,1);

--assiste(IN loginU varchar(45), IN nomModule varchar(45))
assiste(ajobard,Histoire);
assiste(ajobard,Theatre);
assiste(shert,Maths);
assiste(shert,Anglais);
assiste(dparis,Francais);
assiste(dparis,Anglais);
assiste(ariviere,Francais);
assiste(tlepercq,Maths);
assiste(tlepercq,Theatre);

--constitue (IN nomModule VARCHAR(45), IN titreUE VARCHAR(45), IN coefficient INT, IN fil VARCHAR(45))
constitue(Maths,Sciences,1,INFO);
constitue(Maths,Sciences,1,SNUM);
constitue(Histoire,Education Civique,2,INFO);
constitue(Theatre,Education Civique,1,INFO);
constitue(Francais,Langues,2,PHOT);
constitue(Francais,Langues,2,SNUM);
constitue(Anglais,Langues,3,PHOT);
constitue(Anglais,Langues,3,SNUM);