(ns carro.simulacao
  (:require [carro.carroacao.carroMovimentoAtom :as carro]))

(defn -main []
  (carro/ligar-carro)
  (carro/mostrar "Ligou o carro")
  (carro/engatar-primeira)
  (carro/mostrar "Engatou o carro")
  (carro/acelerar 10)
  (carro/mostrar "Acelerou 10 km/h")

  (carro/acelerar 10)
(carro/mostrar "Acelerou 20 km/h")

(carro/acelerar 10)
(carro/mostrar "Acelerou 30 km/h")

(carro/acelerar 10)
(carro/mostrar "Acelerou 40 km/h")


(carro/frear 20)
(carro/mostrar "Freou 10 km/h")

(carro/frear 20)
(carro/mostrar "Freou 10 km/h")

(carro/lombada)
(carro/mostrar "Passou na lombada")

(carro/semaforo)
(carro/mostrar "Parou no semáforo")

(carro/engatar-primeira)
(carro/mostrar "Engatou a primeira marcha")

(carro/acelerar 10)
(carro/mostrar "Acelerou 10 km/h")

(carro/acelerar 10)
(carro/mostrar "Acelerou 20 km/h")

(carro/acelerar 10)
(carro/mostrar "Acelerou 30 km/h")

(carro/desacelerar 10)
(carro/mostrar "Desacelerou 10 km/h")

(carro/frear 10)
(carro/mostrar "Freou 10 km/h")

(carro/parar)
(carro/mostrar "Parou o carro")

(carro/estacionar)
(carro/mostrar "Estacionou e Desligou o carro")

)