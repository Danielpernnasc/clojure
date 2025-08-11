(ns carro.carroacao.carroMovimentoAtom)
; ...existing code...
(def carr (atom {:velocidade 0 :marcha 0 :ligado false}))


(defn marchaVelocidade [velocidade]
  (cond 
    (< velocidade 9) 1
    (< velocidade 19) 2
    (< velocidade 29) 3
    (< velocidade 39) 4
    :else 5)) ;; se quiser 6ª marcha

; ...existing code...

;; Versão atual (ok)
(defn ligar-carro []
  (swap! carr assoc :velocidade 0 :marcha 0 :ligado true))

(defn desligar-carro []
  (swap! carr assoc :velocidade 0 :marcha 0 :ligado false))

  ; ...existing code...
(defn engatar-primeira []
  (swap! carr
    (fn [c]
      (if (and (:ligado c) (zero? (:velocidade c)) (zero? (:marcha c)))
        (assoc c :marcha 1)
        c))))
; ...existing code...
(defn acelerar [incremento]
  (swap! carr
    (fn [c]
      (if (:ligado c)
        (let [c  (if (and (zero? (:velocidade c)) (zero? (:marcha c)))
                   (assoc c :marcha 1)
                   c)
              nv (+ (:velocidade c) incremento)
              nm (if (zero? nv) 0 (marchaVelocidade nv))]
          (assoc c :velocidade nv :marcha nm))
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
  (let [{:keys [velocidade marcha ligado]} @carr]
    (println (str etapa ": Velocidade = " velocidade " km/h, Marcha = " marcha ", Ligado = " ligado))))
; ...existing code...


(defn mostrar [etapa]
  (let [{:keys [velocidade marcha ligado]} @carr]
    (println (str etapa ": Velocidade = " velocidade " km/h, Marcha = " marcha ", Ligado = " ligado))))
; ...existing code...

(defn mostrar [etapa]
  (println (str etapa ": Velocidade = " (:velocidade @carr)
                " km/h, Marcha = " (:marcha @carr)
                ", Ligado = " (:ligado @carr))))

;; Opcional: impedir desligar em movimento (não desliga se velocidade > 0)
; (defn desligar-carro []
;   (swap! carr
;     (fn [c]
;       (if (pos? (:velocidade c))
;         c
;         (assoc c :velocidade 0 :marcha 0 :ligado false)))))