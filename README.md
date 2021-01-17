# Progetto esame

# Descrizione del progetto:
Il progetto un servizio meteo che, scegliendo un box di coordinate geografiche, permette di ottenere tutte le informazioni relative al vento e alla nuvolosità delle città interne al box. Il servizio, una volta avviato, salva informazioni ogni due ore riguardo alle città interne a un box di coordinate che è già stato impostato ma può essere modificato attraverso la rotta apposita. Le informazioni saranno salvante nello storico (un file CSV).
È possiblie ottenere statistiche riguardanti la velocità del vento e la nuvolosità calcolate sui dati presenti nello storico. Queste statistiche possono essere filtrate in base alla periodicità, quindi è possibile ottenere statistiche giornaliere e settimanali oppure si può scegliere una periodicità personalizzata (espressa in giorni).

# Riassunto varie rotte:
Nella tabella sono riassunte le varie rotte dell'applicazione, più avanti sono spiegate nel dettaglio.


# GET dati attuali
Specificando un box di coordinate, otterrete i dati riguardanti la velocità del vento, l'angolazione del vento e la nuvolosità delle città interne al box. Sarà specificata la data in cui sono stati calcolati tali valori. 
Di seguito è riportato un esempio.


# GET statistiche
Tramite questa rotta si ottengono le statistiche (media e varianza) sulla velocità del vento e sulla nuvolosità delle città nello storico. Il tipo delle statistiche deve essere specificato e può essere "clouds" (per ottenere statistiche della nuvolosità), "wind" (per ottenere statistiche della velocità del vento) o "all" (per ottenere entrambe). Saranno inoltre specificate nella risposta la città con la media più alta, quella con la media più bassa e quella con la varianza massima. È anche specificato il range di tempo a cui fanno riferimento i dati e il tipo dei dati (nuvolosità o velocità del vento).
Di seguito un esempio.


# GET statistiche periodiche (periodo scelto dall'utente)
Tramite questa rotta si ottengono le stesse statistiche della rotta precedente ma suddivise in base al periodo. Il periodo deve essere specificato dall'utente ed è espresso in giorni (deve essere un intero).
Un esempio di tale chiamata:
localhost:8080/PeriodicalStats?type=All&period=3


# GET statistiche giornaliere
Tramite questa rotta si ottengono statistiche periodiche ma con periodicità fissata a 1 (non sarà quindi necessario specificarla).
Un esempio di tale chiamata:
localhost:8080/DailyStats?type=clouds

# GET statistiche settimanali
Tramite questa rotta si ottengono statistiche periodiche ma con periodicità fissata a 7 (non sarà quindi necessario specificarla).
Un esempio di tale chiamata:
localhost:8080/WeeklyStats?type=wind


# POST cambia box di coordinate
Questa rotta serve per cambiare il box di coordinate che indica le città da cui raccogliere i dati per popolare lo storico. Si otterrà una risposta che confermerà la riuscita dell'operazione. Il box dovrà essere scritto nella forma riportata nell'esempio.



# Use case diagram:

![usecase](https://user-images.githubusercontent.com/75088977/103486074-57cb3f80-4dfb-11eb-9566-d021d7e72ce6.png)

# Class diagram:

![com esame](https://user-images.githubusercontent.com/75088977/103486067-56017c00-4dfb-11eb-91e0-31f1acac994e.png)
![com esame controller](https://user-images.githubusercontent.com/75088977/103486076-57cb3f80-4dfb-11eb-88e8-37d2b3e0ba7d.png)
![com esame service](https://user-images.githubusercontent.com/75088977/103486068-569a1280-4dfb-11eb-898f-a093c75631e8.png)
![com esame model](https://user-images.githubusercontent.com/75088977/103486066-56017c00-4dfb-11eb-894b-3378e1a73ce3.png)
![com esame filter](https://user-images.githubusercontent.com/75088977/103486065-5568e580-4dfb-11eb-8030-7c839d6c8949.png)
![com esame stats](https://user-images.githubusercontent.com/75088977/103486069-569a1280-4dfb-11eb-9d30-c8e42eec276c.png)


# Sequence diagram:

![s1](https://user-images.githubusercontent.com/75088977/103486070-5732a900-4dfb-11eb-8609-75b2262cdc61.png)
![s2](https://user-images.githubusercontent.com/75088977/103486071-5732a900-4dfb-11eb-81dc-07421483d14e.png)
![s3](https://user-images.githubusercontent.com/75088977/103486072-5732a900-4dfb-11eb-889e-0270191e8223.png)





