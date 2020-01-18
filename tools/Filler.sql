--inscrire('IN nom varchar('45')',' IN prenom varchar('45')',' IN mdp varchar('45')',' IN role varchar('10')')
call inscrire('JOBARD','Alex','ceciestunmdp','Etudiant');
call inscrire('HERT','Sébastien','lesel45','Etudiant');
call inscrire('PARIS','Dejan','priority','Etudiant');
call inscrire('RIVIERE','Adam','colucci','Etudiant');
call inscrire('LEPERCQ','Thomas','joker2019','Etudiant');
call inscrire('EULER','Leonard','eipimoins1egal0','Enseignant');
call inscrire('BONAPARTE','Napoléon','waterlost','Enseignant');
call inscrire('PIVOT','Bernard','apostrophe','Enseignant');
call inscrire('DEVOS','Raymond','louiedeloiedelouis','Enseignant');
call inscrire('NEESON','Liam','iwillfindyou','Enseignant');
call inscrire('FERRY','Jules','obligatoire','Scolarite');
call inscrire('DDE','Admin','motdepassesécurisé','DDE');

--modifierFiliereEtudiant ('IN newFiliere VARCHAR('45')',' IN loginEtu VARCHAR('45')')
call modifierFiliereEtudiant('INFO','ajobard');
call modifierFiliereEtudiant('SNUM','shert');
call modifierFiliereEtudiant('PHOT','dparis');
call modifierFiliereEtudiant('PHOT','ariviere');
call modifierFiliereEtudiant('INFO','tlepercq');

--assignerGroupeTD ('IN numero INT',' IN loginEtu VARCHAR('45')')
call assignerGroupeTD(1,'ajobard');
call assignerGroupeTD(2,'shert');
call assignerGroupeTD(1,'dparis');
call assignerGroupeTD(2,'ariviere');
call assignerGroupeTD(2,'tlepercq');

--assignerGroupeTP ('IN numero INT',' IN loginEtu VARCHAR('45')')
call assignerGroupeTP(1,'ajobard');
call assignerGroupeTP(2,'shert');
call assignerGroupeTP(1,'dparis');
call assignerGroupeTP(3,'ariviere');
call assignerGroupeTP(2,'tlepercq');

--creerUE('IN nomUE varchar('45')',' IN loginRespo varchar('45')',' IN nbCredits int',' IN filiere varchar('45')')
call creerUE('Sciences','leuler',6,'INFO');
call creerUE('Sciences','leuler',6,'SNUM');
call creerUE('Education Civique','nbonaparte',6,'INFO');
call creerUE('Langues','bpivot',6,'PHOT');
call creerUE('Langues','bpivot',6,'SNUM');

--creerModule('IN nomModule varchar('45')',' IN loginRespo varchar('45')')
call creerModule('Maths','leuler');
call creerModule('Histoire','nbonaparte');
call creerModule('Francais','bpivot');
call creerModule('Theatre','rdevos');
call creerModule('Anglais','lneeson');

--enseigne('IN loginU varchar('45')',' IN nomModule varchar('45')',' IN role varchar(2)',' IN idGroupe int')
call enseigne('leuler','Maths','CM',1);
call enseigne('leuler','Maths','TD',1);
call enseigne('leuler','Maths','TP',1);
call enseigne('leuler','Maths','CM',2);
call enseigne('leuler','Maths','TD',2);
call enseigne('leuler','Maths','TP',2);
call enseigne('nbonaparte','Histoire','CM',1);
call enseigne('rdevos','Theatre','TP',2);
call enseigne('bpivot','Francais','TD',1);
call enseigne('lneeson','Anglais','TP',1);

--assiste('IN loginU varchar('45')',' IN nomModule varchar('45')')
call assiste('ajobard','Histoire');
call assiste('ajobard','Theatre');
call assiste('shert','Maths');
call assiste('shert','Anglais');
call assiste('dparis','Francais');
call assiste('dparis','Anglais');
call assiste('ariviere','Francais');
call assiste('tlepercq','Maths');
call assiste('tlepercq','Theatre');

--constitue ('IN nomModule VARCHAR('45')',' IN titreUE VARCHAR('45')',' IN coefficient INT',' IN fil VARCHAR('45')')
call constitue('Maths','Sciences',1,'INFO');
call constitue('Maths','Sciences',1,'SNUM');
call constitue('Histoire','Education Civique',2,'INFO');
call constitue('Theatre','Education Civique',1,'INFO');
call constitue('Francais','Langues',2,'PHOT');
call constitue('Francais','Langues',2,'SNUM');
call constitue('Anglais','Langues',3,'PHOT');
call constitue('Anglais','Langues',3,'SNUM');