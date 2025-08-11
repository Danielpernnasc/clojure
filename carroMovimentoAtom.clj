(def carr (atom {:velocidade 0 :marcha 0 :ligado false}))


(defn marchaVelocidade [velocidade]
  (cond 
    (< velocidade 9) 1
    (< velocidade 19) 2
    (< velocidade 29) 3
    (< velocidade 39) 4
    :else 5)) ;; se quiser 6ª marcha



(defn ligar-carro []
  (swap! carr assoc :velocidade 0 :marcha 0 :ligado true))

(defn desligar-carro []
    (swap! carr assoc :velocidade 0 :marcha 0 :ligado false))

(defn engatar-primeira []
    (swap! carr 
        (fn [c]
            (if (and  (:ligado c) (zero? (:velocidade c)) (zero? (:marcha c)))
            (assoc c :marcha 1)
            c))))

(defn acelerar [incremento]
   (swap! carr
        (fn [c]
            (if (:ligado c)
                (let [c (if (and (zero? (:velocidade c)) (zero? (:marcha  c)))
                          (assoc c :marcha 1)
                          c)
                          nova-velocidade (+ (:velocidade c) incremento)
                          nova-marcha (marchaVelocidade nova-velocidade)]
                         (assoc c :velocidade nova-velocidade :marcha nova-marcha)
                          )
                          c))))

(defn desacelerar [decremento]
   (swap! carr
        (fn [c]
            (if (:ligado c)
                (let [c (if (and (zero? (:velocidade c)) (zero? (:marcha  c)))
                          (assoc c :marcha 1)
                          c)
                          nova-velocidade (- (:velocidade c) decremento)
                          nova-marcha (marchaVelocidade nova-velocidade)]
                         (assoc c :velocidade nova-velocidade :marcha nova-marcha)
                          )
                          c))))

(defn parar []
    (swap! carr assoc :velocidade 0 :marcha 0))

(defn frear [decremento]
  (swap! carr
    (fn [c]
      (let [nova-velocidade (max 0 (- (:velocidade c) decremento))
            nova-marcha (if (zero? nova-velocidade) 0 (marchaVelocidade nova-velocidade))]
        (assoc c :velocidade nova-velocidade :marcha nova-marcha)))))


(defn mostrar [etapa]
  (println (str etapa ": Velocidade = " (:velocidade @carr)
                " km/h, Marcha = " (:marcha @carr)
                ", Ligado = " (:ligado @carr))))


(defn semaforo []
    (parar))

(defn lombada []
    (frear 5))

(defn estacionar []
    (desligar-carro))

(defn rodovia [velocidade]
    (swap! carr
        (fn [c]
            (if (:ligado c)
               (let [c (if (and (zero? (:velocidade c)) (zero? (:marcha c)))
                        (assoc c :marcha 1)
                        c)
                        nova-marcha (marchaVelocidade velocidade)]
                    (assoc c :velocidade velocidade :marcha nova-marcha))
                    c))))

  (defn mostrar [etapa]
    (println (str etapa ": Velocidade = " (:velocidade @carr) " km/h, Marcha = " (:marcha @carr) ", Ligado = " (:ligado @carr))))
  
(ligar-carro)
(mostrar "Ligou o carro")

(engatar-primeira)
(mostrar "Engatou o carro")


(acelerar 10)
(mostrar "Iniciou o movimento Acelerou 10 km/h")

(acelerar 10)
(mostrar "Acelerou 20 km/h")

(acelerar 10)
(mostrar "Acelerou 30 km/h")

(acelerar 10)
(mostrar "Acelerou 40 km/h")


(frear 20)
(mostrar "Freou 10 km/h")

(frear 20)
(mostrar "Freou 10 km/h")



(lombada)
(mostrar "Passou na lombada")

(semaforo)
(mostrar "Parou no semáforo")
(engatar-primeira)
(mostrar "Engatou a primeira marcha")
(acelerar 10)
(mostrar "Acelerou 10 km/h")
(acelerar 10)
(mostrar "Acelerou 20 km/h")
(acelerar 10)
(mostrar "Acelerou 30 km/h")

(desacelerar 10)
(mostrar "Desacelerou 10 km/h")

(frear 10)
(mostrar "Freou 10 km/h")
(parar)
(mostrar "Parou o carro")

(estacionar)
(mostrar "Estacionou e Desligou o carro")