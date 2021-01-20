# Progetto esame

# Descrizione del progetto:
Il progetto implementa un servizio meteo che, scegliendo un box di coordinate geografiche, permette di ottenere tutte le informazioni relative al vento e alla nuvolosità delle città interne al box. Il servizio, una volta avviato, salva informazioni ogni due ore sulle città interne a un box di coordinate che è già stato impostato ma può essere modificato attraverso la rotta apposita. Le informazioni saranno salvante nello storico (un file CSV).
È possiblie ottenere statistiche riguardanti la velocità del vento e la nuvolosità calcolate sui dati presenti nello storico. Queste statistiche possono essere filtrate in base alla periodicità, quindi è possibile ottenere statistiche giornaliere e settimanali oppure si può scegliere una periodicità personalizzata (espressa in giorni). È possibile ottenere le statistiche su un gruppo ristretto di città, se quest'ultimo è presente nello storico.

# Riassunto varie rotte:
Nella tabella sono riassunte le varie rotte dell'applicazione, più avanti sono spiegate nel dettaglio.
![tabella_rotte](https://user-images.githubusercontent.com/75088977/105095355-d388e580-5aa5-11eb-9d34-7aa6c0ef4946.png)

# GET dati attuali (/Data)
Specificando un box di coordinate come parametro "box", otterrete i dati riguardanti la velocità del vento, l'angolazione del vento e la nuvolosità delle città interne al box. Sarà specificata la data in cui sono stati calcolati tali valori. Il box è del tipo [lon-left,lat-bottom,lon-right,lat-top] e la sua dimensione è limitata a una superficie di 25 gradi. Nel caso in cui il box non sia del tipo corretto verrà lanciata un eccezione.
Di seguito è riportato un esempio.

![rotta_data](https://user-images.githubusercontent.com/75088977/104858086-18354500-591d-11eb-9fa6-88af5dc3688d.png)

# POST statistiche periodiche (periodo scelto dall'utente) (/PeriodicalStats)
Tramite questa rotta si ottengono le le statistiche (media e varianza) sulla velocità del vento e sulla nuvolosità delle città nello storico suddivise in base al periodo. L'utente dovrà fornire un Body sotto forma di JSONObject per filtrare i dati per tipo, per periodicità e per nome (si può anche inserire un JSONObject vuoto del tipo { }). Il tipo delle statistiche ("type") può essere specificato dall'utente e può essere "clouds" (per ottenere statistiche della nuvolosità), "wind" (per ottenere statistiche della velocità del vento) o "all" (per ottenere entrambe). Nel caso in cui il tipo non sia specificato verrà settato automaticamente su all.  La periodicità ("period) può essere specificata dall'utente ed è espressa in giorni (deve essere un intero). La periodicità può essere omessa se non si vogliono filtrare i dati per periodo. Si potrà inserire nel JSONObject un vettore di stringhe composto da un elenco di nomi di città chiamato "names" se si vogliono avere esclusivamente le statistiche di queste città. Nel caso in cui alcune delle città specificate non siano presenti nello storico il programma le ignorerà ma se tutte le città specificate non sono presenti nello storico verrà lanciata un'eccezione. Saranno specificate nella risposta la città con la media più alta, quella con la media più bassa e quella con la varianza massima. È anche specificato il range di tempo a cui fanno riferimento i dati e il tipo dei dati (nuvolosità o velocità del vento).
Un esempio di tale chiamata:



# POST statistiche giornaliere
Tramite questa rotta si ottengono statistiche periodiche ma con periodicità fissata a 1 (non sarà quindi possibile specificarla). Nel Body si potranno specificare comunque il tipo delle statistiche e i nomi delle città come nella rotta "/PeriodicalStats".
Un esempio di tale chiamata:



# POST statistiche settimanali
Tramite questa rotta si ottengono statistiche periodiche ma con periodicità fissata a 7 (non sarà quindi necessario specificarla).  Nel Body si potranno specificare comunque il tipo delle statistiche e i nomi delle città come nella rotta "/PeriodicalStats".
Un esempio di tale chiamata:



# GET cambia box di coordinate
Questa rotta serve per cambiare il box di coordinate che indica le città da cui raccogliere i dati per popolare lo storico. Si otterrà una risposta che confermerà la riuscita dell'operazione, altrimenti verrà lanciata un'eccezione. Il box dovrà essere scritto nella forma riportata nell'esempio.

![rotta_ChangeBox2](https://user-images.githubusercontent.com/75088977/104858085-18354500-591d-11eb-8c00-c2a8f517c8b3.png)


# Use case diagram:

![usecase](https://user-images.githubusercontent.com/75088977/103486074-57cb3f80-4dfb-11eb-9566-d021d7e72ce6.png)

# Class diagram:

![com esame](https://user-images.githubusercontent.com/75088977/103486067-56017c00-4dfb-11eb-91e0-31f1acac994e.png)
![com esame controller](https://user-images.githubusercontent.com/75088977/105095381-dd124d80-5aa5-11eb-9045-3d7fa540f780.png)
![com esame service](https://user-images.githubusercontent.com/75088977/105095380-dd124d80-5aa5-11eb-8431-bbb34e636fa4.png)
![com esame model](https://user-images.githubusercontent.com/75088977/103486066-56017c00-4dfb-11eb-894b-3378e1a73ce3.png)
![com esame filter](https://user-images.githubusercontent.com/75088977/105095377-dc79b700-5aa5-11eb-8b8a-848e7a34e447.png)
![com esame stats](https://user-images.githubusercontent.com/75088977/103486069-569a1280-4dfb-11eb-9d30-c8e42eec276c.png)
![com esame exceptions](https://user-images.githubusercontent.com/75088977/105095375-db488a00-5aa5-11eb-8251-d8b95b01a9aa.png)


# Sequence diagram:

![s1](https://user-images.githubusercontent.com/75088977/103486070-5732a900-4dfb-11eb-8609-75b2262cdc61.png)
![seq2](https://user-images.githubusercontent.com/75088977/105095351-d2f04f00-5aa5-11eb-96c0-c6d940405b78.png)
![seq3](https://user-images.githubusercontent.com/75088977/105095354-d388e580-5aa5-11eb-9688-7bcfe6fd188d.png)





