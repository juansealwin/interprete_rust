(ns proyecto-01.core-test
  (:require [clojure.test :refer :all]
            [proyecto-01.core :refer :all]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; LISTAR: 
; user=> (listar (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "Hola, mundo!" (symbol ")") (symbol "}")))
; fn main ( )
; {
;   println! ( "Hola, mundo!" )
; }
; 
; nil
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest listar-test
  (testing "Prueba de la función: listar"
    (is (= (str 'fn \space 'main \space (symbol "(") \space (symbol ")") \space \newline (symbol "{") \newline \tab 'println! \space (symbol "(") \space (str "\"") "Hola, mundo!" (str "\"") \space (symbol ")") \newline (symbol "}") \newline) (with-out-str (listar (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "Hola, mundo!" (symbol ")") (symbol "}"))))))
  )
)


 (println (str 'fn \space 'main \space (symbol "(") \space (symbol ")") \space \newline (symbol "{") 
 \newline \space \tab \space 'println! \space (symbol "(") \space (str "\"") "Hola, mundo!" (str "\"") 
 \space (symbol ")") \space \newline (symbol "}") \newline))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; AGREGAR-PTOCOMA: 
; user=> (agregar-ptocoma (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'if 'x '< '0 (symbol "{") 'x '= '- 'x (symbol ";") (symbol "}") 'renglon '= 'x (symbol ";") 'if 'z '< '0 (symbol "{") 'z '= '- 'z (symbol ";") (symbol "}") (symbol "}") 'fn 'foo (symbol "(") (symbol ")") (symbol "{") 'if 'y '> '0 (symbol "{") 'y '= '- 'y (symbol ";") (symbol "}") 'else (symbol "{") 'x '= '- 'y (symbol ";") (symbol "}") (symbol "}")))
; (fn main ( ) { if x < 0 { x = - x ; } ; renglon = x ; if z < 0 { z = - z ; } } fn foo ( ) { if y > 0 { y = - y ; } else { x = - y ; } })
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest agregar-ptocoma-test 
  (testing "Prueba de la función: agregar-ptocoma"
    (is (= 
      (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'if 'x '< '0 (symbol "{") 'x '= '- 'x (symbol ";") (symbol "}") (symbol ";") 'renglon '= 'x (symbol ";") 'if 'z '< '0 (symbol "{") 'z '= '- 'z (symbol ";") (symbol "}") (symbol "}") 'fn 'foo (symbol "(") (symbol ")") (symbol "{") 'if 'y '> '0 (symbol "{") 'y '= '- 'y (symbol ";") (symbol "}") 'else (symbol "{") 'x '= '- 'y (symbol ";") (symbol "}") (symbol "}")) 
      (agregar-ptocoma (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'if 'x '< '0 (symbol "{") 'x '= '- 'x (symbol ";") (symbol "}") 'renglon '= 'x (symbol ";") 'if 'z '< '0 (symbol "{") 'z '= '- 'z (symbol ";") (symbol "}") (symbol "}") 'fn 'foo (symbol "(") (symbol ")") (symbol "{") 'if 'y '> '0 (symbol "{") 'y '= '- 'y (symbol ";") (symbol "}") 'else (symbol "{") 'x '= '- 'y (symbol ";") (symbol "}") (symbol "}")))))
  )
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; PALABRA-RESERVADA?: 
; user=> (palabra-reservada? 'while)
; true
; user=> (palabra-reservada? 'until)
; false
; user=> (palabra-reservada? 13)
; false
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest palabra-reservada?-test
  (testing "Prueba de la función: palabra-reservada?"
    (is (= true (palabra-reservada? 'while)))
    (is (= false (palabra-reservada? 'until)))
    (is (= false (palabra-reservada? 13)))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; IDENTIFICADOR?: 
; user=> (identificador? 'boolean)
; true
; user=> (identificador? 'bool)
; false
; user=> (identificador? 'e120)
; true
; user=> (identificador? '12e0)
; false
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest identificador?-test
  (testing "Prueba de la función: identificador?"
    (is (= true (identificador? 'boolean)))
    (is (= false (identificador? 'bool)))
    (is (= true (identificador? 'e120)))
    (is (= false (identificador? '12e0)))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; DUMP: 
; user=> (dump '[[POPREF 2] [PUSHFI 2] MUL [PUSHFI 1] ADD NEG])
; 0 [POPREF 2]
; 1 [PUSHFI 2]
; 2 MUL
; 3 [PUSHFI 1]
; 4 ADD
; 5 NEG
; nil
; user=> (dump '[HLT])
; 0 HLT
; nil
; user=> (dump nil)
; 0 nil
; nil
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest dump-test
  (testing "Prueba de la función: dump"
    (is (= 
      (str 0 \space ['POPREF 2] \newline 1 \space ['PUSHFI 2] \newline 2 \space 'MUL \newline 3 \space ['PUSHFI 1] \newline 4 \space 'ADD \newline 5 \space 'NEG \newline) 
      (with-out-str(dump '[[POPREF 2] [PUSHFI 2] MUL [PUSHFI 1] ADD NEG]))))
    (is (= "0 HLT\n" (with-out-str(dump '[HLT]))))
    (is (= "0 nil\n" (with-out-str(dump nil))))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; YA-DECLARADO-LOCALMENTE?:
; user=> (ya-declarado-localmente? 'Write [[0] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])
; true
; user=> (ya-declarado-localmente? 'Read [[0] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])
; false
; user=> (ya-declarado-localmente? 'Write [[0 1] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])
; true
; user=> (ya-declarado-localmente? 'Write [[0 2] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])
; false
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest ya-declarado-localmente?-test 
  (testing "Prueba de la función: ya-declarado-localmente?"
    (is (= true (ya-declarado-localmente? 'Write [[0] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])))
    (is (= false (ya-declarado-localmente? 'Read [[0] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])))
    (is (= true (ya-declarado-localmente? 'Write [[0 1] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])))
    (is (= false (ya-declarado-localmente? 'Write [[0 2] [['io ['lib '()] 0] ['Write ['lib '()] 0] ['entero_a_hexa ['fn [(list ['n (symbol ":") 'i64]) 'String]] 2]]])))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CARGAR-CONST-EN-TABLA: 
; user=> (cargar-const-en-tabla [(symbol ";") (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "{}" (symbol ",") 'TRES (symbol ")") (symbol "}")) ['use 'std (symbol "::") 'io (symbol ";") 'const 'TRES (symbol ":") 'i64 (symbol "=") 3] 8 [[0] [['io ['lib '()] 0]]] 0 [['CAL 0] 'HLT] []])
; [; (fn main ( ) { println! ( "{}" , TRES ) }) [use std :: io ; const TRES : i64 = 3] 8 [[0] [[io [lib ()] 0]]] 0 [[CAL 0] HLT] []]
;                                               ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ^ ^^^^^^^^^^^^^^^^^^^^^^^ 
; user=> (cargar-const-en-tabla [(symbol ";") (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "{}" (symbol ",") 'TRES (symbol ")") (symbol "}")) ['use 'std (symbol "::") 'io (symbol ";") 'const 'TRES (symbol ":") 'i64 (symbol "=") 3] :sin-errores [[0] [['io ['lib '()] 0]]] 0 [['CAL 0] 'HLT] []])
; [; (fn main ( ) { println! ( "{}" , TRES ) }) [use std :: io ; const TRES : i64 = 3] :sin-errores [[0] [[io [lib ()] 0] [TRES [const i64] 3]]] 0 [[CAL 0] HLT] []]
;                                               ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ^^^^^^^^^^^^ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(deftest cargar-const-en-tabla-test
  (testing "Prueba de la función: cargar-const-en-tabla"
    (is (= (vector (symbol ";") (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "{}" (symbol ",") 'TRES (symbol ")") (symbol "}")) ['use 'std (symbol "::") 'io (symbol ";") 'const 'TRES (symbol ":") 'i64 (symbol "=") 3] 8 [[0] [['io ['lib '()] 0]]] 0 [['CAL 0] 'HLT] []) (cargar-const-en-tabla [(symbol ";") (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "{}" (symbol ",") 'TRES (symbol ")") (symbol "}")) ['use 'std (symbol "::") 'io (symbol ";") 'const 'TRES (symbol ":") 'i64 (symbol "=") 3] 8 [[0] [['io ['lib '()] 0]]] 0 [['CAL 0] 'HLT] []])))
    (is (= (vector (symbol ";") (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "{}" (symbol ",") 'TRES (symbol ")") (symbol "}")) ['use 'std (symbol "::") 'io (symbol ";") 'const 'TRES (symbol ":") 'i64 (symbol "=") 3] :sin-errores [[0] [['io ['lib ()] 0] ['TRES ['const 'i64] 3]]] 0 [['CAL 0] 'HLT] []) (cargar-const-en-tabla [(symbol ";") (list 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'println! (symbol "(") "{}" (symbol ",") 'TRES (symbol ")") (symbol "}")) ['use 'std (symbol "::") 'io (symbol ";") 'const 'TRES (symbol ":") 'i64 (symbol "=") 3] :sin-errores [[0] [['io ['lib '()] 0]]] 0 [['CAL 0] 'HLT] []])))
  )
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; INICIALIZAR-CONTEXTO-LOCAL:
; user=> (inicializar-contexto-local [(symbol "{") (list 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")")] 8 [[0] [['main ['fn [() ()]] 2]]] 0 [['CAL 2] 'HLT] []])
; [{ (let x : i64 = 10 ; println! ( "{}" , x ) }) [fn main ( )] 8 [[0] [[main [fn [() ()]] 2]]] 0 [[CAL 2] HLT] []]
;                                                               ^ 
; user=> (inicializar-contexto-local [(symbol "{") (list 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")")] :sin-errores [[0] [['main ['fn [() ()]] 2]]] 0 [['CAL 2] 'HLT] []])
; [{ (let x : i64 = 10 ; println! ( "{}" , x ) }) [fn main ( )] :sin-errores [[0 1] [[main [fn [() ()]] 2]]] 0 [[CAL 2] HLT] []]
;                                                               ^^^^^^^^^^^^     ^  ^^^^^^^^^^^^^^^^^^^^^^^
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest inicializar-contexto-local-test
  (testing "Prueba de la función: inicializar-contexto-local"
    (is (= (vector (symbol "{") (list 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")")] 8 [[0] [['main ['fn [() ()]] 2]]] 0 [['CAL 2] 'HLT] []) (inicializar-contexto-local [(symbol "{") (list 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")")] 8 [[0] [['main ['fn [() ()]] 2]]] 0 [['CAL 2] 'HLT] []])))
    (is (= (vector (symbol "{") (list 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")")] :sin-errores [[0 1] [['main ['fn [() ()]] 2]]] 0 [['CAL 2] 'HLT] []) (inicializar-contexto-local [(symbol "{") (list 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")")] :sin-errores [[0] [['main ['fn [() ()]] 2]]] 0 [['CAL 2] 'HLT] []])))
  )
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; RESTAURAR-CONTEXTO-ANTERIOR: 
; user=> (restaurar-contexto-anterior ['EOF () ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'let 'y (symbol ":") 'i64 (symbol "=") 20 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x '+ 'y (symbol ")") (symbol "}")] 8 [[0 1] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0] ['y ['var-inmut 'i64] 1]]] 2 [['CAL 2] 'HLT ['PUSHFI 10] ['POP 0] ['PUSHFI 20] ['POP 1] ['PUSHFI "{}"] ['PUSHFM 0] ['PUSHFM 1] 'ADD ['PUSHFI 2] 'OUT 'NL] [[2 ['i64 nil] ['i64 nil]]]])
; [EOF () [fn main ( ) { let x : i64 = 10 ; let y : i64 = 20 ; println! ( "{}" , x + y ) }] 8 [[0 1] [[main [fn [() ()]] 2] [x [var-inmut i64] 0] [y [var-inmut i64] 1]]] 2 [[CAL 2] HLT [PUSHFI 10] [POP 0] [PUSHFI 20] [POP 1] [PUSHFI "{}"] [PUSHFM 0] [PUSHFM 1] ADD [PUSHFI 2] OUT NL] [[2 [i64 nil] [i64 nil]]]]
;                                                                                           ^ 
; user=> (restaurar-contexto-anterior ['EOF () ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'let 'y (symbol ":") 'i64 (symbol "=") 20 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x '+ 'y (symbol ")") (symbol "}")] :sin-errores [[0 1] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0] ['y ['var-inmut 'i64] 1]]] 2 [['CAL 2] 'HLT ['PUSHFI 10] ['POP 0] ['PUSHFI 20] ['POP 1] ['PUSHFI "{}"] ['PUSHFM 0] ['PUSHFM 1] 'ADD ['PUSHFI 2] 'OUT 'NL] [[2 ['i64 nil] ['i64 nil]]]])
; [EOF () [fn main ( ) { let x : i64 = 10 ; let y : i64 = 20 ; println! ( "{}" , x + y ) }] :sin-errores [[0] [[main [fn [() ()]] 2]]] 2 [[CAL 2] HLT [PUSHFI 10] [POP 0] [PUSHFI 20] [POP 1] [PUSHFI "{}"] [PUSHFM 0] [PUSHFM 1] ADD [PUSHFI 2] OUT NL] [[2 [i64 nil] [i64 nil]]]]
;                                                                                           ^^^^^^^^^^^^  ^^^ ^^^^^^^^^^^^^^^^^^^^^^^
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest restaurar-contexto-anterior-test
  (testing "Prueba de la función: restaurar-contexto-anterior"
    (is (= (vector 'EOF () ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'let 'y (symbol ":") 'i64 (symbol "=") 20 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x '+ 'y (symbol ")") (symbol "}")] 8 [[0 1] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0] ['y ['var-inmut 'i64] 1]]] 2 [['CAL 2] 'HLT ['PUSHFI 10] ['POP 0] ['PUSHFI 20] ['POP 1] ['PUSHFI "{}"] ['PUSHFM 0] ['PUSHFM 1] 'ADD ['PUSHFI 2] 'OUT 'NL] [[2 ['i64 nil] ['i64 nil]]]) (restaurar-contexto-anterior ['EOF () ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'let 'y (symbol ":") 'i64 (symbol "=") 20 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x '+ 'y (symbol ")") (symbol "}")] 8 [[0 1] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0] ['y ['var-inmut 'i64] 1]]] 2 [['CAL 2] 'HLT ['PUSHFI 10] ['POP 0] ['PUSHFI 20] ['POP 1] ['PUSHFI "{}"] ['PUSHFM 0] ['PUSHFM 1] 'ADD ['PUSHFI 2] 'OUT 'NL] [[2 ['i64 nil] ['i64 nil]]]])))
    (is (= (vector 'EOF () ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'let 'y (symbol ":") 'i64 (symbol "=") 20 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x '+ 'y (symbol ")") (symbol "}")] :sin-errores [[0] [['main ['fn [() ()]] 2]]] 2 [['CAL 2] 'HLT ['PUSHFI 10] ['POP 0] ['PUSHFI 20] ['POP 1] ['PUSHFI "{}"] ['PUSHFM 0] ['PUSHFM 1] 'ADD ['PUSHFI 2] 'OUT 'NL] [[2 ['i64 nil] ['i64 nil]]]) (restaurar-contexto-anterior ['EOF () ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol "=") 10 (symbol ";") 'let 'y (symbol ":") 'i64 (symbol "=") 20 (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x '+ 'y (symbol ")") (symbol "}")] :sin-errores [[0 1] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0] ['y ['var-inmut 'i64] 1]]] 2 [['CAL 2] 'HLT ['PUSHFI 10] ['POP 0] ['PUSHFI 20] ['POP 1] ['PUSHFI "{}"] ['PUSHFM 0] ['PUSHFM 1] 'ADD ['PUSHFI 2] 'OUT 'NL] [[2 ['i64 nil] ['i64 nil]]]])))
  )
)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; BUSCAR-TIPO-DE-RETORNO: 
; user=> (buscar-tipo-de-retorno [(symbol ";") (list 'println! (symbol "(") "La suma de 5 mas 7 es {}" (symbol ",") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")") (symbol ")") (symbol ";") (symbol "}")) ['fn 'suma (symbol "(") 'x (symbol ":") 'i64 (symbol ",") 'y (symbol ":") 'i64 (symbol ")") (symbol "->") 'i64 (symbol "{") 'x '+ 'y (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")")] :sin-errores [[0 2] [['suma ['fn [(list ['x (symbol ":") 'i64] ['y (symbol ":") 'i64]) 'i64]] 2] ['main ['fn [() ()]] 8]]] 0 [['CAL 8] 'HLT ['POPARG 1] ['POPARG 0] ['PUSHFM 0] ['PUSHFM 1] 'ADD 'RET ['PUSHFI 5] ['PUSHFI 7] ['CAL 2]] [[2 ['i64 nil] ['i64 nil]] [8]]] 2)
; i64                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ^^^   ^                                                                                                                                                                          ^
; user=> (buscar-tipo-de-retorno [(symbol ";") (list 'println! (symbol "(") "La suma de 5 mas 7 es {}" (symbol ",") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")") (symbol ")") (symbol ";") (symbol "}")) ['fn 'suma (symbol "(") 'x (symbol ":") 'i64 (symbol ",") 'y (symbol ":") 'i64 (symbol ")") (symbol "->") 'i64 (symbol "{") 'x '+ 'y (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")")] :sin-errores [[0 2] [['suma ['fn [(list ['x (symbol ":") 'i64] ['y (symbol ":") 'i64]) 'i64]] 2] ['main ['fn [() ()]] 8]]] 0 [['CAL 8] 'HLT ['POPARG 1] ['POPARG 0] ['PUSHFM 0] ['PUSHFM 1] 'ADD 'RET ['PUSHFI 5] ['PUSHFI 7] ['CAL 2]] [[2 ['i64 nil] ['i64 nil]] [8]]] 8)
; ()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ^^   ^                                                                                                                                                  ^
; user=> (buscar-tipo-de-retorno [(symbol ";") (list 'println! (symbol "(") "La suma de 5 mas 7 es {}" (symbol ",") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")") (symbol ")") (symbol ";") (symbol "}")) ['fn 'suma (symbol "(") 'x (symbol ":") 'i64 (symbol ",") 'y (symbol ":") 'i64 (symbol ")") (symbol "->") 'i64 (symbol "{") 'x '+ 'y (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")")] :sin-errores [[0 2] [['suma ['fn [(list ['x (symbol ":") 'i64] ['y (symbol ":") 'i64]) 'i64]] 2] ['main ['fn [() ()]] 8]]] 0 [['CAL 8] 'HLT ['POPARG 1] ['POPARG 0] ['PUSHFM 0] ['PUSHFM 1] 'ADD 'RET ['PUSHFI 5] ['PUSHFI 7] ['CAL 2]] [[2 ['i64 nil] ['i64 nil]] [8]]] 1)
; nil
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest buscar-tipo-de-retorno-test
  (testing "Prueba de la función: buscar-tipo-de-retorno"
    (is (= 'i64 (buscar-tipo-de-retorno [(symbol ";") (list 'println! (symbol "(") "La suma de 5 mas 7 es {}" (symbol ",") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")") (symbol ")") (symbol ";") (symbol "}")) ['fn 'suma (symbol "(") 'x (symbol ":") 'i64 (symbol ",") 'y (symbol ":") 'i64 (symbol ")") (symbol "->") 'i64 (symbol "{") 'x '+ 'y (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")")] :sin-errores [[0 2] [['suma ['fn [(list ['x (symbol ":") 'i64] ['y (symbol ":") 'i64]) 'i64]] 2] ['main ['fn [() ()]] 8]]] 0 [['CAL 8] 'HLT ['POPARG 1] ['POPARG 0] ['PUSHFM 0] ['PUSHFM 1] 'ADD 'RET ['PUSHFI 5] ['PUSHFI 7] ['CAL 2]] [[2 ['i64 nil] ['i64 nil]] [8]]] 2)))
    (is (= () (buscar-tipo-de-retorno [(symbol ";") (list 'println! (symbol "(") "La suma de 5 mas 7 es {}" (symbol ",") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")") (symbol ")") (symbol ";") (symbol "}")) ['fn 'suma (symbol "(") 'x (symbol ":") 'i64 (symbol ",") 'y (symbol ":") 'i64 (symbol ")") (symbol "->") 'i64 (symbol "{") 'x '+ 'y (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")")] :sin-errores [[0 2] [['suma ['fn [(list ['x (symbol ":") 'i64] ['y (symbol ":") 'i64]) 'i64]] 2] ['main ['fn [() ()]] 8]]] 0 [['CAL 8] 'HLT ['POPARG 1] ['POPARG 0] ['PUSHFM 0] ['PUSHFM 1] 'ADD 'RET ['PUSHFI 5] ['PUSHFI 7] ['CAL 2]] [[2 ['i64 nil] ['i64 nil]] [8]]] 8)))
    (is (= nil (buscar-tipo-de-retorno [(symbol ";") (list 'println! (symbol "(") "La suma de 5 mas 7 es {}" (symbol ",") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")") (symbol ")") (symbol ";") (symbol "}")) ['fn 'suma (symbol "(") 'x (symbol ":") 'i64 (symbol ",") 'y (symbol ":") 'i64 (symbol ")") (symbol "->") 'i64 (symbol "{") 'x '+ 'y (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'suma (symbol "(") 5 (symbol ",") 7 (symbol ")")] :sin-errores [[0 2] [['suma ['fn [(list ['x (symbol ":") 'i64] ['y (symbol ":") 'i64]) 'i64]] 2] ['main ['fn [() ()]] 8]]] 0 [['CAL 8] 'HLT ['POPARG 1] ['POPARG 0] ['PUSHFM 0] ['PUSHFM 1] 'ADD 'RET ['PUSHFI 5] ['PUSHFI 7] ['CAL 2]] [[2 ['i64 nil] ['i64 nil]] [8]]] 1)))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; GENERAR-REF:
; user=> (generar-ref [(symbol ")") (list (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'v (symbol ")") (symbol ";") (symbol "}")) ['fn 'inc (symbol "(") 'v (symbol ":") (symbol "&") 'mut 'i64 (symbol ")") (symbol "{") '* 'v (symbol "+=") 1 (symbol ";") (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'mut 'v (symbol ":") 'i64 (symbol "=") 5 (symbol ";") 'inc (symbol "(") (symbol "&") 'mut 'v] 8 [[0 2] [['inc ['fn [(list ['v (symbol ":") (symbol "&") 'mut 'i64]) ()]] 2] ['main ['fn [() ()]] 6] ['v ['var-mut 'i64] 0]]] 1 [['CAL 6] 'HLT ['POPARG 0] ['PUSHFI 1] ['POPADDREF 0] 'RETN ['PUSHFI 5] ['POP 0]] [[2 ['i64 nil]] [6 ['i64 nil]]]])
; [) (; println! ( "{}" , v ) ; }) [fn inc ( v : & mut i64 ) { * v += 1 ; } fn main ( ) { let mut v : i64 = 5 ; inc ( & mut v] 8 [[0 2] [[inc [fn [([v : & mut i64]) ()]] 2] [main [fn [() ()]] 6] [v [var-mut i64] 0]]] 1 [[CAL 6] HLT [POPARG 0] [PUSHFI 1] [POPADDREF 0] RETN [PUSHFI 5] [POP 0]] [[2 [i64 nil]] [6 [i64 nil]]]]
;                                                                                                                              ^
; user=> (generar-ref [(symbol ")") (list (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'v (symbol ")") (symbol ";") (symbol "}")) ['fn 'inc (symbol "(") 'v (symbol ":") (symbol "&") 'mut 'i64 (symbol ")") (symbol "{") '* 'v (symbol "+=") 1 (symbol ";") (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'mut 'v (symbol ":") 'i64 (symbol "=") 5 (symbol ";") 'inc (symbol "(") (symbol "&") 'mut 'v] :sin-errores [[0 2] [['inc ['fn [(list ['v (symbol ":") (symbol "&") 'mut 'i64]) ()]] 2] ['main ['fn [() ()]] 6] ['v ['var-mut 'i64] 0]]] 1 [['CAL 6] 'HLT ['POPARG 0] ['PUSHFI 1] ['POPADDREF 0] 'RETN ['PUSHFI 5] ['POP 0]] [[2 ['i64 nil]] [6 ['i64 nil]]]])
; [) (; println! ( "{}" , v ) ; }) [fn inc ( v : & mut i64 ) { * v += 1 ; } fn main ( ) { let mut v : i64 = 5 ; inc ( & mut v] :sin-errores [[0 2] [[inc [fn [([v : & mut i64]) ()]] 2] [main [fn [() ()]] 6] [v [var-mut i64] 0]]] 1 [[CAL 6] HLT [POPARG 0] [PUSHFI 1] [POPADDREF 0] RETN [PUSHFI 5] [POP 0] [PUSHADDR 0]] [[2 [i64 nil]] [6 [i64 nil]]]]
;                                                                                                                           ^  ^^^^^^^^^^^^                                                                    ^^^^^^^^^^^^^^^^^                                                                               ^^^^^^^^^^^^
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest generar-ref-test 
  (testing "Prueba de la función: generar-ref"
    (is (= 
      (vector (symbol ")") (list (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'v (symbol ")") (symbol ";") (symbol "}")) ['fn 'inc (symbol "(") 'v (symbol ":") (symbol "&") 'mut 'i64 (symbol ")") (symbol "{") '* 'v (symbol "+=") 1 (symbol ";") (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'mut 'v (symbol ":") 'i64 (symbol "=") 5 (symbol ";") 'inc (symbol "(") (symbol "&") 'mut 'v] 8 [[0 2] [['inc ['fn [(list ['v (symbol ":") (symbol "&") 'mut 'i64]) ()]] 2] ['main ['fn [() ()]] 6] ['v ['var-mut 'i64] 0]]] 1 [['CAL 6] 'HLT ['POPARG 0] ['PUSHFI 1] ['POPADDREF 0] 'RETN ['PUSHFI 5] ['POP 0]] [[2 ['i64 nil]] [6 ['i64 nil]]])
      (generar-ref [(symbol ")") (list (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'v (symbol ")") (symbol ";") (symbol "}")) ['fn 'inc (symbol "(") 'v (symbol ":") (symbol "&") 'mut 'i64 (symbol ")") (symbol "{") '* 'v (symbol "+=") 1 (symbol ";") (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'mut 'v (symbol ":") 'i64 (symbol "=") 5 (symbol ";") 'inc (symbol "(") (symbol "&") 'mut 'v] 8 [[0 2] [['inc ['fn [(list ['v (symbol ":") (symbol "&") 'mut 'i64]) ()]] 2] ['main ['fn [() ()]] 6] ['v ['var-mut 'i64] 0]]] 1 [['CAL 6] 'HLT ['POPARG 0] ['PUSHFI 1] ['POPADDREF 0] 'RETN ['PUSHFI 5] ['POP 0]] [[2 ['i64 nil]] [6 ['i64 nil]]]])))
    (is (= 
      (vector (symbol ")") (list (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'v (symbol ")") (symbol ";") (symbol "}")) ['fn 'inc (symbol "(") 'v (symbol ":") (symbol "&") 'mut 'i64 (symbol ")") (symbol "{") '* 'v (symbol "+=") 1 (symbol ";") (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'mut 'v (symbol ":") 'i64 (symbol "=") 5 (symbol ";") 'inc (symbol "(") (symbol "&") 'mut 'v] :sin-errores [[0 2] [['inc ['fn [(list ['v (symbol ":") (symbol "&") 'mut 'i64]) ()]] 2] ['main ['fn [() ()]] 6] ['v ['var-mut 'i64] 0]]] 1 [['CAL 6] 'HLT ['POPARG 0] ['PUSHFI 1] ['POPADDREF 0] 'RETN ['PUSHFI 5] ['POP 0] ['PUSHADDR 0]] [[2 ['i64 nil]] [6 ['i64 nil]]])
      (generar-ref [(symbol ")") (list (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'v (symbol ")") (symbol ";") (symbol "}")) ['fn 'inc (symbol "(") 'v (symbol ":") (symbol "&") 'mut 'i64 (symbol ")") (symbol "{") '* 'v (symbol "+=") 1 (symbol ";") (symbol "}") 'fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'mut 'v (symbol ":") 'i64 (symbol "=") 5 (symbol ";") 'inc (symbol "(") (symbol "&") 'mut 'v] :sin-errores [[0 2] [['inc ['fn [(list ['v (symbol ":") (symbol "&") 'mut 'i64]) ()]] 2] ['main ['fn [() ()]] 6] ['v ['var-mut 'i64] 0]]] 1 [['CAL 6] 'HLT ['POPARG 0] ['PUSHFI 1] ['POPADDREF 0] 'RETN ['PUSHFI 5] ['POP 0]] [[2 ['i64 nil]] [6 ['i64 nil]]]])))
  )
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; FIXUP: 
; user=> (fixup [(symbol "{") (list 'x '= 20 (symbol ";") (symbol "}") (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol ";") 'if false (symbol "{") 'x '= 10 (symbol ";") (symbol "}") 'else] 8 [[0 1 2] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0]]] 1 [['CAL 2] 'HLT ['PUSHFI false] ['JC 5] ['JMP '?] ['PUSHFI 10] ['POP 0] ['JMP '?]] [[2 ['i64 nil]]]] 4)
; [{ (x = 20 ; } ; println! ( "{}" , x ) }) [fn main ( ) { let x : i64 ; if false { x = 10 ; } else] 8 [[0 1 2] [[main [fn [() ()]] 2] [x [var-inmut i64] 0]]] 1 [[CAL 2] HLT [PUSHFI false] [JC 5] [JMP ?] [PUSHFI 10] [POP 0] [JMP ?]] [[2 [i64 nil]]]]
;                                                                                                    ^ 
; user=> (fixup [(symbol "{") (list 'x '= 20 (symbol ";") (symbol "}") (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol ";") 'if false (symbol "{") 'x '= 10 (symbol ";") (symbol "}") 'else] :sin-errores [[0 1 2] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0]]] 1 [['CAL 2] 'HLT ['PUSHFI false] ['JC 5] ['JMP '?] ['PUSHFI 10] ['POP 0] ['JMP '?]] [[2 ['i64 nil]]]] 4)
; [{ (x = 20 ; } ; println! ( "{}" , x ) }) [fn main ( ) { let x : i64 ; if false { x = 10 ; } else] :sin-errores [[0 1 2] [[main [fn [() ()]] 2] [x [var-inmut i64] 0]]] 1 [[CAL 2] HLT [PUSHFI false] [JC 5] [JMP 8] [PUSHFI 10] [POP 0] [JMP ?]] [[2 [i64 nil]]]]
;                                                                                                    ^^^^^^^^^^^^                                                           ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^: tamano 8                                                                                                                                                                                                                                        ^ ubicacion de JMP ? en contexto
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest fixup-test
  (testing "Prueba de la función: fixup"
    (is (= (vector (symbol "{") (list 'x '= 20 (symbol ";") (symbol "}") (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol ";") 'if false (symbol "{") 'x '= 10 (symbol ";") (symbol "}") 'else] 8 [[0 1 2] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0]]] 1 [['CAL 2] 'HLT ['PUSHFI false] ['JC 5] ['JMP '?] ['PUSHFI 10] ['POP 0] ['JMP '?]] [[2 ['i64 nil]]]) (fixup [(symbol "{") (list 'x '= 20 (symbol ";") (symbol "}") (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol ";") 'if false (symbol "{") 'x '= 10 (symbol ";") (symbol "}") 'else] 8 [[0 1 2] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0]]] 1 [['CAL 2] 'HLT ['PUSHFI false] ['JC 5] ['JMP '?] ['PUSHFI 10] ['POP 0] ['JMP '?]] [[2 ['i64 nil]]]] 4)))
    (is (= (vector (symbol "{") (list 'x '= 20 (symbol ";") (symbol "}") (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol ";") 'if false (symbol "{") 'x '= 10 (symbol ";") (symbol "}") 'else] :sin-errores [[0 1 2] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0]]] 1 [['CAL 2] 'HLT ['PUSHFI false] ['JC 5] ['JMP 8] ['PUSHFI 10] ['POP 0] ['JMP '?]] [[2 ['i64 nil]]]) (fixup [(symbol "{") (list 'x '= 20 (symbol ";") (symbol "}") (symbol ";") 'println! (symbol "(") "{}" (symbol ",") 'x (symbol ")") (symbol "}")) ['fn 'main (symbol "(") (symbol ")") (symbol "{") 'let 'x (symbol ":") 'i64 (symbol ";") 'if false (symbol "{") 'x '= 10 (symbol ";") (symbol "}") 'else] :sin-errores [[0 1 2] [['main ['fn [() ()]] 2] ['x ['var-inmut 'i64] 0]]] 1 [['CAL 2] 'HLT ['PUSHFI false] ['JC 5] ['JMP '?] ['PUSHFI 10] ['POP 0] ['JMP '?]] [[2 ['i64 nil]]]] 4)))
  )
)  

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CONVERTIR-FORMATO-IMPRESION:
; user=> (convertir-formato-impresion '("Hola, mundo!"))
; ("Hola, mundo!")
; user=> (convertir-formato-impresion '("- My name is {}, James {}.\n- Hello, {}{}{}!" "Bond" "Bond" 0 0 7))
; ("- My name is %s, James %s.\n- Hello, %d%d%d!" "Bond" "Bond" 0 0 7)
; user=> (convertir-formato-impresion '("{} elevado a la {} es\t{}" 2.0 2 4.0))
; ("%.0f elevado a la %d es\t%.0f" 2.0 2 4.0)
; user=> (convertir-formato-impresion '("Las raices cuadradas de {} son +{:.8} y -{:.8}" 4.0 1.999999999985448 1.999999999985448))
; ("Las raices cuadradas de %.0f son +%.8f y -%.8f" 4.0 1.999999999985448 1.999999999985448)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest convertir-formato-impresion-test 
  (testing "Prueba de la función: convertir-formato-impresion"
    (is (= (list "Hola, mundo!") (convertir-formato-impresion '("Hola, mundo!"))))
    (is (= (list "- My name is %s, James %s.\n- Hello, %d%d%d!" "Bond" "Bond" 0 0 7) (convertir-formato-impresion '("- My name is {}, James {}.\n- Hello, {}{}{}!" "Bond" "Bond" 0 0 7))))
    (is (= (list "%.0f elevado a la %d es\t%.0f" 2.0 2 4.0) (convertir-formato-impresion '("{} elevado a la {} es\t{}" 2.0 2 4.0))))
    (is (= (list "Las raices cuadradas de %.0f son +%.8f y -%.8f" 4.0 1.999999999985448 1.999999999985448) (convertir-formato-impresion '("Las raices cuadradas de {} son +{:.8} y -{:.8}" 4.0 1.999999999985448 1.999999999985448))))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; DIVIDIR:
; user=> (dividir 12 3)
; 4
; user=> (dividir 12.0 3)
; 4.0
; user=> (dividir 12 3.0)
; 4.0
; user=> (dividir 12.0 3.0)
; 4.0
; user=> (dividir 1 2)
; 0
; user=> (dividir 1 2.0)
; 0.5
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(deftest dividir-test
  (testing "Prueba de la función: dividir"
    (is (= 4 (dividir 12 3)))
    (is (= 4.0 (dividir 12.0 3)))
    (is (= 4.0 (dividir 12 3.0)))
    (is (= 4.0 (dividir 12.0 3.0)))
    (is (= 0 (dividir 1 2)))
    (is (= 0.5 (dividir 1 2.0)))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; COMPATIBLES?: 
; user=> (compatibles? 'i64 5)
; true
; user=> (compatibles? 'i64 5.0)
; false
; user=> (compatibles? 'i64 [5.0])
; true
; user=> (compatibles? 'f64 5.0)
; true
; user=> (compatibles? 'String "Hola")
; true
; user=> (compatibles? 'bool true)
; true
; user=> (compatibles? 'bool 1)
; false
; user=> (compatibles? 'usize 1)
; true
; user=> (compatibles? 'char \a)
; true
; user=> (compatibles? 'char 'a)
; false
; user=> (compatibles? 'char ['a])
; true
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest compatibles?-test
  (testing "Prueba de la función: compatibles?"
    (is (= true (compatibles? 'i64 5)))
    (is (= false (compatibles? 'i64 5.0)))
    (is (= true (compatibles? 'i64 [5.0])))
    (is (= true (compatibles? 'f64 5.0)))
    (is (= true (compatibles? 'String "Hola")))
    (is (= true (compatibles? 'bool true)))
    (is (= false (compatibles? 'bool 1)))
    (is (= true (compatibles? 'usize 1)))
    (is (= true (compatibles? 'char \a)))
    (is (= false (compatibles? 'char 'a)))
    (is (= true (compatibles? 'char ['a])))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; PASAR-A-INT: 
; user=> (pasar-a-int "10")
; 10
; user=> (pasar-a-int 10.0)
; 10
; user=> (pasar-a-int 10)
; 10
; user=> (pasar-a-int 'a)
; a
; user=> (pasar-a-int [10.0])
; [10.0]
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest pasar-a-int-test
  (testing "Prueba de la función: pasar-a-int"
    (is (= 10 (pasar-a-int "10")))
    (is (= 10 (pasar-a-int 10.0)))
    (is (= 10 (pasar-a-int 10)))
    (is (= 'a (pasar-a-int 'a)))
    (is (= [10.0] (pasar-a-int [10.0])))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; PASAR-A-FLOAT: 
; user=> (pasar-a-float "10")
; 10.0
; user=> (pasar-a-float 10)
; 10.0
; user=> (pasar-a-float 10.0)
; 10.0
; user=> (pasar-a-float 'a)
; a
; user=> (pasar-a-float [10])
; [10]
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest pasar-a-float-test 
  (testing "Prueba de la función: pasar-a-float"
    (is (= 10.0 (pasar-a-float "10")))
    (is (= 10.0 (pasar-a-float 10)))
    (is (= 10.0 (pasar-a-float 10.0)))
    (is (= 'a (pasar-a-float 'a)))
    (is (= [10] (pasar-a-float [10])))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CARGAR-EN-ULT-REG:
; user=> (cargar-en-ult-reg [[['String "2"] ['i64 6] ['i64 2] ['i64 3] ['i64 0]] [['i64 nil] ['i64 nil]]] 1 'i64 0)
; [[[String "2"] [i64 6] [i64 2] [i64 3] [i64 0]] [[i64 nil] [i64 0]]]
;                                                             ^^^ ^
; user=> (cargar-en-ult-reg [[['String "2"] ['i64 6] ['i64 2] ['i64 3] ['i64 0]] [['i64 nil] ['i64 0]]] 0 'f64 3)
; [[[String "2"] [i64 6] [i64 2] [i64 3] [i64 0]] [[f64 3] [i64 0]]]
;                                                   ^^^ ^
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest cargar-en-ult-reg-test
  (testing "Prueba de la función: cargar-en-ult-reg"
    (is (= (vector [['String "2"] ['i64 6] ['i64 2] ['i64 3] ['i64 0]] [['i64 nil] ['i64 0]]) (cargar-en-ult-reg [[['String "2"] ['i64 6] ['i64 2] ['i64 3] ['i64 0]] [['i64 nil] ['i64 nil]]] 1 'i64 0)))
    (is (= (vector [['String "2"] ['i64 6] ['i64 2] ['i64 3] ['i64 0]] [['f64 3] ['i64 0]]) (cargar-en-ult-reg [[['String "2"] ['i64 6] ['i64 2] ['i64 3] ['i64 0]] [['i64 nil] ['i64 0]]] 0 'f64 3)))
  )
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; CARGAR-EN-REG-DEST:
; user=> (cargar-en-reg-dest [[['String "2"] ['i64 6] ['i64 2] ['i64 2] ['i64 2]] [['i64 6] ['i64 2] ['i64 [0 3]] ['i64 [0 4]] ['i64 2] ['i64 2]]] [0 4] 'i64 0)
; [[[String "2"] [i64 6] [i64 2] [i64 2] [i64 0]] [[i64 6] [i64 2] [i64 [0 3]] [i64 [0 4]] [i64 2] [i64 2]]]
;                                         ^^^ ^
; user=> (cargar-en-reg-dest [[['String "2"] ['i64 6] ['i64 2] ['i64 2] ['i64 0]] [['i64 6] ['i64 2] ['i64 [0 3]] ['i64 [0 4]] ['i64 2] ['i64 2]]] [0 3] 'f64 3)
; [[[String "2"] [i64 6] [i64 2] [f64 3] [i64 0]] [[i64 6] [i64 2] [i64 [0 3]] [i64 [0 4]] [i64 2] [i64 2]]]
;                                 ^^^ ^
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest cargar-en-reg-dest-test 
  (testing "Prueba de la función: cargar-en-reg-dest"
    (is (= (vector [['String "2"] ['i64 6] ['i64 2] ['i64 2] ['i64 0]] [['i64 6] ['i64 2] ['i64 [0 3]] ['i64 [0 4]] ['i64 2] ['i64 2]]) (cargar-en-reg-dest [[['String "2"] ['i64 6] ['i64 2] ['i64 2] ['i64 2]] [['i64 6] ['i64 2] ['i64 [0 3]] ['i64 [0 4]] ['i64 2] ['i64 2]]] [0 4] 'i64 0)))
    (is (= (vector [['String "2"] ['i64 6] ['i64 2] ['f64 3] ['i64 0]] [['i64 6] ['i64 2] ['i64 [0 3]] ['i64 [0 4]] ['i64 2] ['i64 2]]) (cargar-en-reg-dest [[['String "2"] ['i64 6] ['i64 2] ['i64 2] ['i64 0]] [['i64 6] ['i64 2] ['i64 [0 3]] ['i64 [0 4]] ['i64 2] ['i64 2]]] [0 3] 'f64 3)))
  )
)