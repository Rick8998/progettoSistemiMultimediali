# progettoSistemiMultimediali

## Progetto laurea magistrale corso sistemi multimediali

### Eseguire l'animazione di un'immagine rotante
Il progetto può essere visto come un'operazione in due fasi:
1. Data un'immagine B/N o a colori, scrivere una classe per eseguire la sua rotazione di un dato grado Ɵ
2. Utilizzare la classe implementata al punto 1 per eseguire l'animazione continua dell'immagine data in una finestra mobile

Operazione di rotazione di un'immagine attorno ad un punto centrale, dato un grado di rotazione Ɵ
Considerando il centro di rotazione nel punto (0, 0), un'immagine può essere ruotata utilizzando la seguente matrice di rotazione:

![immagine](https://user-images.githubusercontent.com/46086592/171801781-20be7263-6f89-44a1-b3fe-c3210c8cb90e.png)

Il centro di rotazione deve essere spostato in una posizione (Xc, Yc) = (larghezza/2, altezza/2)

Durante la rotazione, si verifica il ritaglio dei pixels. Bisogna fare attenzione a non tracciare i pixel al di fuori dell'immagine di destinazione

Dopo la rotazione, potrebbero esserci pixel mancanti nell'immagine di destinazione
Ci sono due possibili soluzioni:
1. Sovracampionare l'immagine originale, ruotare la versione sovracampionata in un buffer temporaneo e infine sottocampionare il risultato ruotato
2. invertire il problema: e per ogni pixel di destinazione (X', Y'), trovare quale pixel dell'immagine originale (X, Y) deve essere copiato in (X', Y')

![immagine](https://user-images.githubusercontent.com/46086592/171802239-508d1a20-dfaa-472f-92df-45f46aa2c3b7.png)

La rotazione sarà eseguita da una nuova classe, implementando l'interfaccia Transform

![immagine](https://user-images.githubusercontent.com/46086592/171802357-1b29365e-a252-4416-b76c-1aba62a0311e.png)

L'obiettivo finale è visualizzare una sequenza di rotazioni nella stessa finestra, in modo da ottenere un'animazione.

##### Sintetizzando:
1. Imposta Theha = 0
2. Crea un nuovo Jframe JF e riempilo con l'immagine iniziale (originalImage)
3. Theta = Theta + ΔT (imposta ΔT come preferisci)
4. roatedImage = ruota (immagine originale, Theta)
5. Attendere un periodo di tempo fisso
6. Riempi JF con l'immagine ruotata
7. Torna al punto 3

Lavorare su un'immagine mentre è attualmente visualizzata in JF porta a aretfacts non corretti. Per evitare questo problema utilizziamo 2 immagini ruotate (buffer)
– Mentre è visualizzato il buffer 1, modifichiamo il buffer 2 e, alla fine, sostituiremo l'immagine in JF con l'immagine nel buffer 2
– Mentre è visualizzato il buffer 2, modifichiamo il buffer 1 e, alla fine, sostituiremo l'immagine in JF con l'immagine nel buffer 1

##### Algorithm Double Buffer
1. Imposta Theha = 0
2. Crea un nuovo Jframe JF e riempilo con l'immagine iniziale (originalImage)
3. Crea 2 buffer: B [0..1]
4. currBuf= 0
5. Theta = Theta + ΔT (imposta ΔT come preferisci)
6. B[currBuf] = ruota (immagine originale, Theta)
7. Attendere un periodo di tempo fisso
8. Riempi JF con B[currBuf]
9. Scambia i buffer (modifica di conseguenza currBuf)
10. Torna al punto 5



### Approfondimento sulla rotazione di una figura geometrica in un piano cartesiano
![immagine](https://user-images.githubusercontent.com/46086592/175050921-35ad5656-f5c8-4642-b972-447fc7428b89.png)

![immagine](https://user-images.githubusercontent.com/46086592/175051101-a8100218-c09d-4b41-bb68-005f355c1ebc.png)

![immagine](https://user-images.githubusercontent.com/46086592/175051210-476cf685-28aa-40fd-8fb5-f4f0ebfec486.png)

Fonte
(https://www.youmath.it/domande-a-risposte/view/6230-rotazione.html)


