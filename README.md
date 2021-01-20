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
Tramite questa rotta si ottengono le le statistiche (media e varianza) sulla velocità del vento e sulla nuvolosità delle città nello storico suddivise in base al periodo. L'utente dovrà fornire un Body sotto forma di JSONObject per filtrare i dati per tipo, per periodicità e per nome (si può anche inserire un JSONObject vuoto del tipo "{ }"). Il tipo delle statistiche ("type") può essere specificato dall'utente e può essere "clouds" (per ottenere statistiche della nuvolosità), "wind" (per ottenere statistiche della velocità del vento) o "all" (per ottenere entrambe). Nel caso in cui il tipo non sia specificato verrà settato automaticamente su "all".  La periodicità ("period") può essere specificata dall'utente ed è espressa in giorni (deve essere un intero). La periodicità può essere omessa se non si vogliono filtrare i dati per periodo. Si potrà inserire nel JSONObject un vettore di stringhe composto da un elenco di nomi di città chiamato "names" se si vogliono avere esclusivamente le statistiche di queste città. Nel caso in cui alcune delle città specificate non siano presenti nello storico il programma le ignorerà ma se tutte le città specificate non sono presenti nello storico verrà lanciata un'eccezione. Saranno specificate nella risposta la città con la media più alta, quella con la media più bassa e quella con la varianza massima. È anche specificato il range di tempo a cui fanno riferimento i dati e il tipo dei dati (nuvolosità o velocità del vento). Di seguito degli esempi.

Un esempio di tale chiamata con un JSONObject vuoto:

![PeriodicalStats{}](https://user-images.githubusercontent.com/75088977/105231846-c9c7b680-5b67-11eb-815e-6772cf4173d3.png)

Una possibile risposta:

[

    [
        {
            "Città con media più alta": "Ancona",            
            "Città con varianza massima": "Civitanova Marche",          
            "Città con media più bassa": "Macerata",           
            "tipo": "nuvolosità",         
            "periodo": "da 2021-01-03T20:08:46 a 2021-01-20T20:09:56",           
            "Statistiche": [          
                {
                
                    "name": "Osimo",
                    
                    "average": 36.55555555555556,
                    
                    "variance": 1002.1358024691358
                    
                },
                
                {
                
                    "name": "Ancona",
                    
                    "average": 46.0,
                    
                    "variance": 871.3333333333334
                    
                },
                
                {
                
                    "name": "Senigallia",
                    
                    "average": 40.55555555555556,
                    
                    "variance": 985.8024691358023
                    
                },
                
                {
                
                    "name": "Macerata",
                    
                    "average": 25.5,
                    
                    "variance": 1218.138888888889
                    
                },
                
                {
                
                    "name": "Civitanova Marche",
                    
                    "average": 39.94444444444444,
                   
                    "variance": 1443.9413580246912
                    
                },
                
                {
                
                    "name": "Tolentino",
                    
                    "average": 28.5,
                    
                    "variance": 1142.8055555555557
                    
                },
                
                {
                
                    "name": "Fermo",
                    
                    "average": 27.22222222222222,
                    
                    "variance": 1049.9506172839506
                    
                },
                
                {
                
                    "name": "Iesi",
                    
                    "average": 42.27777777777778,
                    
                    "variance": 1107.4228395061727
                    
                }
                
            ]
            
        },
        
        {
        
            "Città con media più alta": "Ancona",
            
            "Città con varianza massima": "Civitanova Marche",
            
            "Città con media più bassa": "Macerata",
            
            "tipo": "velocità del vento",
            
            "periodo": "da 2021-01-03T20:08:46 a 2021-01-20T20:09:56",
           
            "Statistiche": [
            
                {
                
                    "name": "Osimo",
                    
                    "average": 36.55555555555556,
                    
                    "variance": 1002.1358024691358
                    
                },
                
                {
                
                    "name": "Ancona",
                    
                    "average": 46.0,
                    
                    "variance": 871.3333333333334
                    
                },
                
                {
                
                    "name": "Senigallia",
                    
                    "average": 40.55555555555556,
                    
                    "variance": 985.8024691358023
                    
                },
                
                {
                
                    "name": "Macerata",
                    
                    "average": 25.5,
                    
                    "variance": 1218.138888888889
                    
                },
                
                {
                
                    "name": "Civitanova Marche",
                    
                    "average": 39.94444444444444,
                    
                    "variance": 1443.9413580246912
                    
                },
                
                {
                
                    "name": "Tolentino",
                    
                    "average": 28.5,
                    
                    "variance": 1142.8055555555557
                    
                },
                
                {
                
                    "name": "Fermo",
                    
                    "average": 27.22222222222222,
                    
                    "variance": 1049.9506172839506
                    
                },
                
                {
                
                    "name": "Iesi",
                    
                    "average": 42.27777777777778,
                    
                    "variance": 1107.4228395061727
                    
                }
                
            ]
            
        }
        
    ]
    
]



Un esempio con un JSONObject contenente una lista di nomi:

![PeriodicalStatsNames2](https://user-images.githubusercontent.com/75088977/105231837-c7fdf300-5b67-11eb-8b8e-2502bc89f4eb.png)

Una possibile risposta:

[
    [
        {
            "Città con media più alta": "Ancona",
            "Città con varianza massima": "Senigallia",
            "Città con media più bassa": "Senigallia",
            "tipo": "nuvolosità",
            "periodo": "da 2021-01-03T20:08:46 a 2021-01-20T19:11:18",
            "Statistiche": [
                {
                    "name": "Senigallia",
                    "average": 46.81818181818182,
                    "variance": 983.0578512396693
                },
                {
                    "name": "Ancona",
                    "average": 51.27272727272727,
                    "variance": 838.01652892562
                }
            ]
        },
        {
            "Città con media più alta": "Ancona",
            "Città con varianza massima": "Senigallia",
            "Città con media più bassa": "Senigallia",
            "tipo": "velocità del vento",
            "periodo": "da 2021-01-03T20:08:46 a 2021-01-20T19:11:18",
            "Statistiche": [
                {
                    "name": "Senigallia",
                    "average": 46.81818181818182,
                    "variance": 983.0578512396693
                },
                {
                    "name": "Ancona",
                    "average": 51.27272727272727,
                    "variance": 838.01652892562
                }
            ]
        }
    ]
]


Un esempio con un JSONObject che specifica nomi di città e tipo delle statistiche:

![PeriodicalStatsTypeNames](https://user-images.githubusercontent.com/75088977/105231839-c8968980-5b67-11eb-9569-59d21aad0847.png)

Una possibile risposta:

[
    [
        {
            "Città con media più alta": "Ancona",
            "Città con varianza massima": "Senigallia",
            "Città con media più bassa": "Senigallia",
            "tipo": "nuvolosità",
            "periodo": "da 2021-01-03T20:08:46 a 2021-01-20T19:11:18",
            "Statistiche": [
                {
                    "name": "Senigallia",
                    "average": 46.81818181818182,
                    "variance": 983.0578512396693
                },
                {
                    "name": "Ancona",
                    "average": 51.27272727272727,
                    "variance": 838.01652892562
                }
            ]
        }
    ]
]





Un esempio con un JSONObject che specifica nomi di città, tipo delle statistiche e periodicità:

![PeriodicalStatsTypePeriodNames](https://user-images.githubusercontent.com/75088977/105231840-c8968980-5b67-11eb-8de0-89cac1bf4023.png)

Una possibile risposta:

[
    [
        {
            "Città con media più alta": "Ancona",
            "Città con varianza massima": "Senigallia",
            "Città con media più bassa": "Senigallia",
            "tipo": "nuvolosità",
            "periodo": "da 2021-01-03T20:08:46 a 2021-01-13T17:41:02",
            "Statistiche": [
                {
                    "name": "Senigallia",
                    "average": 31.727272727272727,
                    "variance": 918.9256198347109
                },
                {
                    "name": "Ancona",
                    "average": 40.27272727272727,
                    "variance": 751.1074380165287
                }
            ]
        }
    ],
    [
        {
            "Città con media più alta": "Ancona",
            "Città con varianza massima": "Ancona",
            "Città con media più bassa": "Senigallia",
            "tipo": "nuvolosità",
            "periodo": "da 2021-01-14T15:08:50 a 2021-01-20T19:11:18",
            "Statistiche": [
                {
                    "name": "Senigallia",
                    "average": 61.90909090909091,
                    "variance": 591.7190082644628
                },
                {
                    "name": "Ancona",
                    "average": 62.27272727272727,
                    "variance": 682.9256198347107
                }
            ]
        }
    ]
]




# POST statistiche giornaliere (/DailyStats)
Tramite questa rotta si ottengono statistiche periodiche ma con periodicità fissata a 1 (non sarà quindi possibile specificarla). Nel Body si potranno specificare comunque il tipo delle statistiche e i nomi delle città come nella rotta "/PeriodicalStats".



# POST statistiche settimanali (/WeeklyStats)
Tramite questa rotta si ottengono statistiche periodiche ma con periodicità fissata a 7 (non sarà quindi necessario specificarla).  Nel Body si potranno specificare comunque il tipo delle statistiche e i nomi delle città come nella rotta "/PeriodicalStats".

Un esempio di tale chiamata:

![WeeklyStats](https://user-images.githubusercontent.com/75088977/105231843-c92f2000-5b67-11eb-8031-db5065330e31.png)

Una possibile risposta:

[
    [
        {
            "Città con media più alta": "Tolentino",
            "Città con varianza massima": "Macerata",
            "Città con media più bassa": "Macerata",
            "tipo": "velocità del vento",
            "periodo": "da 2021-01-03T20:08:47 a 2021-01-09T22:02:21",
            "Statistiche": [
                {
                    "name": "Tolentino",
                    "average": 2.605714285714286,
                    "variance": 0.014024489795918344
                },
                {
                    "name": "Macerata",
                    "average": 2.4728571428571433,
                    "variance": 0.06650612244897966
                }
            ]
        }
    ],
    [
        {
            "Città con media più alta": "Tolentino",
            "Città con varianza massima": "Tolentino",
            "Città con media più bassa": "Macerata",
            "tipo": "velocità del vento",
            "periodo": "da 2021-01-10T21:37:05 a 2021-01-17T15:21:02",
            "Statistiche": [
                {
                    "name": "Tolentino",
                    "average": 1.5574999999999999,
                    "variance": 0.44154375
                },
                {
                    "name": "Macerata",
                    "average": 1.135,
                    "variance": 0.361525
                }
            ]
        }
    ],
    [
        {
            "Città con media più alta": "Tolentino",
            "Città con varianza massima": "Tolentino",
            "Città con media più bassa": "Macerata",
            "tipo": "velocità del vento",
            "periodo": "da 2021-01-18T18:08:49 a 2021-01-20T20:08:58",
            "Statistiche": [
                {
                    "name": "Tolentino",
                    "average": 0.4766666666666666,
                    "variance": 0.001422222222222223
                },
                {
                    "name": "Macerata",
                    "average": 0.45,
                    "variance": 0.0
                }
            ]
        }
    ]
]




# GET cambia box di coordinate
Questa rotta serve per cambiare il box di coordinate che indica le città da cui raccogliere i dati per popolare lo storico. Si otterrà una risposta che confermerà la riuscita dell'operazione, altrimenti verrà lanciata un'eccezione. Il box dovrà essere scritto nella forma riportata nel seguente esempio.

![ChangeBox](https://user-images.githubusercontent.com/75088977/105231845-c92f2000-5b67-11eb-85f0-cd4a2daf0a67.png)



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





