(defn marchaVelocidade [velocidade]
  (cond 
    (< velocidade 10) 1
    (< velocidade 20) 2
    (< velocidade 30) 3
    (< velocidade 40) 4
    (< velocidade 50) 5
    :else 5))

    (defn engatar-primeira [carro]
    (if (and (zero? (:velocidade carro)) (zero? (:marcha carro)))
      (assoc carro :marcha 1)
      carro))



(defn acelerar [carro incremento]
  (let [carro(engatar-primeira carro)
        nova-velocidade (+ (:velocidade carro) incremento)
        nova-marcha (marchaVelocidade nova-velocidade)]
    (assoc carro 
           :velocidade nova-velocidade 
           :marcha nova-marcha)))


(defn frear [carro decremento]
  (let [nova-velocidade (max 0 (- (:velocidade carro) decremento))
        nova-marcha (if (zero? nova-velocidade) 
                      0 ;; parado, marcha neutra (0) 
                      (marchaVelocidade nova-velocidade))]
    (assoc carro 
           :velocidade nova-velocidade 
           :marcha nova-marcha)))
           
(defn parar [carro]
  (assoc carro :velocidade 0 :marcha 0))
  
(defn mostrar-carro [carro etapa]
  (println (str etapa ": Velocidade = " (:velocidade carro) " km/h, Marcha = " (:marcha carro))))

(defn acelerarconst [carro velocidade]
  (let [carro (engatar-primeira carro)
        nova-velocidade velocidade
        nova-marcha (marchaVelocidade nova-velocidade)]
    (assoc carro 
           :velocidade nova-velocidade 
           :marcha nova-marcha)))

(defn reduz 
  ([carro] (frear carro 5))            ;; se só carro, reduz 5
  ([carro decremento] (frear carro decremento)))  ;; se passar decremento, usa ele


  (defn set-velocidade [carro velocidade]
  (let [vel (max 0 velocidade)
        marcha (if (zero? vel) 0 (marchaVelocidade vel))]
    (assoc carro :velocidade vel :marcha marcha)))

(defn carro-movimento [carro]
  ;; Reduz marcha em 1, mas não menos que 1 se estiver em movimento
  (let [m (max 1 (dec (:marcha carro)))]
    (assoc carro :marcha m)))

(defn semaforo [carro]
  ;; Para o carro: velocidade 0, marcha (0)
  (assoc carro :velocidade 0 :marcha 0))

(defn lombada [carro]
  ;; Exemplo: reduz velocidade em 5
  (frear carro 5))

(defn rodovia
  ;; Versão com 1 argumento
  ([carro]
   (acelerarconst carro 60))
  ;; Versão com 2 argumentos
  ([carro velocidade]
   (acelerarconst carro velocidade)))



;; --------------------------
;; Simulação de uso
;; --------------------------
(def carro-inicial {:velocidade 0 :marcha 0})

(def carromovimento (engatar-primeira carro-inicial))
(mostrar-carro carromovimento "Engatou primeira marcha")
(Thread/sleep 1000)

(def carro1 (acelerar carro-inicial 10))
(mostrar-carro carro1 "Acelerou para 10")
(Thread/sleep 1000)

(def carro2 (acelerar carro1 10))
(mostrar-carro carro2 "Acelerou para 25")
(Thread/sleep 1000)

(def carro3 (reduz carro2))
(mostrar-carro carro3 "Reduziu para 20")
(Thread/sleep 1000)

(def carro4 (reduz carro3))
(mostrar-carro carro4 "Reduziu para 15")
(Thread/sleep 1000)

(def carro5 (acelerar carro4 20))
(mostrar-carro carro5 "Acelerar para 30")
(Thread/sleep 1000)

(def carro6 (acelerar carro5 40))
(mostrar-carro carro5 "Acelerar para 40")
(Thread/sleep 1000)

(def carro7 (rodovia carro6))
(mostrar-carro carro7 "Entrou na rodovia a 60 km/h")
(Thread/sleep 1000)

(def carro8 (rodovia carro7 80))
(mostrar-carro carro8 "Aumentando a velocidade para  80 km/h")
(Thread/sleep 1000)

(def carro9 (rodovia carro8 90))
(mostrar-carro carro9 "Aumentando a velocidade para 90 km/h")
(Thread/sleep 1000)

(def carro10 (rodovia carro8 100))
(mostrar-carro carro10 "Aumentando a velocidade para 100 km/h")
(Thread/sleep 1000)

(def carro11 (set-velocidade carro10 95))
(mostrar-carro carro11 "Reduziu para 95 km/h")
(Thread/sleep 1000)


(def carro12 (set-velocidade carro11 70))
(mostrar-carro carro12 "Reduziu para 70 km/h")
(Thread/sleep 1000)

(def carro13 (set-velocidade carro12 50))
(mostrar-carro carro13 "Reduziu para 50 km/h")
(Thread/sleep 1000)


(def carro14 (set-velocidade carro13 30))
(mostrar-carro carro14 "Reduziu para 30 km/h")
(Thread/sleep 1000)

(def carro15 (set-velocidade carro14 10))
(mostrar-carro carro15 "Reduziu para 10 km/h e parou")
(Thread/sleep 1000)

(def carro16 (reduz carro15 0))
(mostrar-carro carro16 "O carro parou")
(Thread/sleep 1000)

(println "Carro estacionado!")

