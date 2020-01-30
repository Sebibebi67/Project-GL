-- Sourcing this file fills the database with data for testing purposes
-- Author : Thomas LEPERCQ

-- Generating Users
-- inscrire('IN nom varchar('45')',' IN prenom varchar('45')',' IN mdp varchar('45')',' IN role varchar('10')')
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

-- Setting courses for a Student
-- modifierFiliereEtudiant ('IN newFiliere VARCHAR('45')',' IN loginEtu VARCHAR('45')')
call modifierFiliereEtudiant('INFO2','ajobard');
call modifierFiliereEtudiant('SNUM2','shert');
call modifierFiliereEtudiant('PHOT2','dparis');
call modifierFiliereEtudiant('PHOT2','ariviere');
call modifierFiliereEtudiant('INFO2','tlepercq');

-- Setting groups (TD,TP) for a Student
-- assignerGroupeTD ('IN numero INT',' IN loginEtu VARCHAR('45')')
call assignerGroupeTD(1,'ajobard');
call assignerGroupeTD(2,'shert');
call assignerGroupeTD(1,'dparis');
call assignerGroupeTD(2,'ariviere');
call assignerGroupeTD(2,'tlepercq');

-- assignerGroupeTP ('IN numero INT',' IN loginEtu VARCHAR('45')')
call assignerGroupeTP(1,'ajobard');
call assignerGroupeTP(2,'shert');
call assignerGroupeTP(1,'dparis');
call assignerGroupeTP(3,'ariviere');
call assignerGroupeTP(2,'tlepercq');

-- Generating Teaching Units
-- creerUE('IN nomUE varchar('45')',' IN loginRespo varchar('45')',' IN nbCredits int',' IN filiere varchar('45')')
call creerUE('Sciences','leuler',6,'INFO2');
call creerUE('Sciences','leuler',6,'SNUM2');
call creerUE('Education Civique','nbonaparte',6,'INFO2');
call creerUE('Langues','bpivot',6,'PHOT2');
call creerUE('Langues','bpivot',6,'SNUM2');

-- Generating Modules
-- creerModule('IN nomModule varchar('45')',' IN loginRespo varchar('45')')
call creerModule('Maths','leuler');
call creerModule('Histoire','nbonaparte');
call creerModule('Francais','bpivot');
call creerModule('Theatre','rdevos');
call creerModule('Anglais','lneeson');

-- Setting taught Modules for a Teacher
-- enseigne('IN loginU varchar('45')',' IN nomModule varchar('45')',' IN role varchar(2)',' IN idGroupe int')
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

-- Setting sitted Modules for a Student
-- assiste('IN loginU varchar('45')',' IN nomModule varchar('45')')
call assiste('ajobard','Histoire');
call assiste('ajobard','Theatre');
call assiste('shert','Maths');
call assiste('shert','Anglais');
call assiste('dparis','Francais');
call assiste('dparis','Anglais');
call assiste('ariviere','Francais');
call assiste('tlepercq','Maths');
call assiste('tlepercq','Theatre');

-- Setting Modules in Teaching Units
-- constitue ('IN nomModule VARCHAR('45')',' IN titreUE VARCHAR('45')',' IN coefficient INT',' IN fil VARCHAR('45')')
call constitue('Maths','Sciences',1,'INFO2');
call constitue('Maths','Sciences',1,'SNUM2');
call constitue('Histoire','Education Civique',2,'INFO2');
call constitue('Theatre','Education Civique',1,'INFO2');
call constitue('Francais','Langues',2,'PHOT2');
call constitue('Francais','Langues',2,'SNUM2');
call constitue('Anglais','Langues',3,'PHOT2');
call constitue('Anglais','Langues',3,'SNUM2');

-- Author : Dejan PARIS
-- Generating marks, exams
-- note ('IN nomNote VARCHAR('45')',' IN valeur INT',' IN coeff INT',' IN jour DATE', 'IN loginU VARCHAR('45')', 'IN nomModule VARCHAR('45')')
call note('DS1',4,1,'2019-12-18','tlepercq','Maths');
call note('DS2',10,1,'2020-01-10','tlepercq','Maths');
call note('DS1',8,1,'2019-12-18','shert','Maths');
call note('Composition',13,4,'2020-07-01','ajobard','Histoire');
call note('TOEIC',19,1,'2019-11-26','dparis','Anglais');
call note('Synthèse',2,3,'2019-12-07','dparis','Francais');
call note("Comedia del'ENSSAT",16,1,'2020-03-22','ajobard','Theatre');

-- Author : Thomas LEPERCQ
-- Generating Satisfaction surveys
-- satisfaction(IN loginU VARCHAR(45), IN nomModule VARCHAR(45), IN questionnaire TEXT, IN note INT)
call satisfaction('ajobard','Histoire','Beaucoup de dates à retenir, mais les sujets abordés sont intéressants.',4);
call satisfaction('shert','Anglais','Very confusing, but I passed it anyway.',2);
call satisfaction('dparis','Anglais','Yes',4);
call satisfaction('dparis','Francais','Extrêmement important, la maîtrise orthographique est essentielle. Au passage, le nom du module lui-même est mal orthographié, je mets donc 0 par principe.',0);
call satisfaction('ariviere','Francais','Cours bien structuré, mon voisin semblait cependant un peu TROP passionné',5);
call satisfaction('tlepercq','Maths','Le grappin',2);
