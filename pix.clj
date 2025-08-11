(defn pix-entrada [conta valor descricao]
    (let [novo-saldo (+(:saldo conta) valor)
        nova-mov (conj (:movientacoes conta)
                        {:tipo :entrada
                         :valor valor
                         :descricao descricao
                         :data (java.time.LocalDate/now)})]
                         (assoc conta :saldo novo-saldo :movimentacoes nova-mov)))
(defn pix-saida [conta valor descricao]
    (if (>= (:saldo conta) valor) 
        (let [novo-saldo (- (:saldo conta) valor)
             nova-mov(conj (:movimentacoes conta)
             {:tipo :saida 
              :valor valor
              :descricao descricao
              :data (java.time.LocalDate/now)})]
         (assoc conta :saldo novo-saldo :movimentacoes nova-mov))
        (throw (Exception. "Saldo insuficiente para realizar a transação!"))))

        (def conta-inicial {:saldo 1000 :movimentacoes []})
        (def conta-depois-deposito (pix-entrada conta-inicial 500 "PIX recebido de Ana"))
        (def conta-depois-pagamento (pix-saida conta-depois-deposito 200 "Pix pago para supermercado"))

        (println "Saldo inicial: R$" (:saldo conta-inicial))
        (println "Saldo após depósito: R$" (:saldo conta-depois-deposito))
        (println "Saldo após pagamento:" (:saldo conta-depois-pagamento))

        (println "\nHistórico de movimentações:")
        (doseq [mov (:movimentacoes conta-depois-pagamento)]
        
         (println (format "%s - %s: R$%.2f - %s"
                   (:data mov)
                   (if (= (:tipo mov) :entrada) "Entrada" "Saída")
                   (double (:valor mov))
                   (:descricao mov))))
