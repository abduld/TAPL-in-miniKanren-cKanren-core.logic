(ns tapl.test.stlc_deriv
  (:use [tapl.stlc_deriv]
        clojure.test :reload)
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :exclude [is] :as l]
        [clojure.core.logic.nominal :exclude [fresh hash] :as nom])
  (:require [clojure.pprint :as pp]))

(deftest test-typingo
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-deriv ['typingo c () `(~'fn ~(nom/tie x x)) t] d)
                   (== q [c t d]))))))
        '([T-Abs [:=> _0 _0] ([[typingo T-Abs () (fn  [a_1] a_1) [:=> _0 _0]] <-- ([[typingo T-Var ([a_2 _0]) a_2 _0] <-- ()])])])))
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-deriv ['typingo c () `((~'fn ~(nom/tie x x)) (~'fn ~(nom/tie x x))) t] d)
                   (== q [c t d]))))))
        '([T-App [:=> _0 _0] ([[typingo T-App () ((fn [a_1] a_1) (fn [a_1] a_1)) [:=> _0 _0]] <-- ([[typingo T-Abs () (fn [a_1] a_1) [:=> [:=> _0 _0] [:=> _0 _0]]] <-- ([[typingo T-Var ([a_2 [:=> _0 _0]]) a_2 [:=> _0 _0]] <-- ()])] [[typingo T-Abs () (fn [a_1] a_1) [:=> _0 _0]] <-- ([[typingo T-Var ([a_3 _0]) a_3 _0] <-- ()])])])])))
  (is (= (run* [q]
           (fresh [c t d]
             (nom/fresh [x]
               (typingo-deriv ['typingo c () `(~'fn ~(nom/tie x [x x])) t] d))))
        ())))

(deftest test-typingo-only-debug
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-only-debug ['typingo c () `(~'fn ~(nom/tie x x)) t] d)
                   (== q [c t d]))))))
        '([T-Abs [:=> _0 _0] ([[typingo T-Abs () (fn  [a_1] a_1) [:=> _0 _0]] <-- ([[typingo T-Var ([a_2 _0]) a_2 _0] <-- ()])])])))
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-only-debug ['typingo c () `((~'fn ~(nom/tie x x)) (~'fn ~(nom/tie x x))) t] d)
                   (== q [c t d]))))))
        '([T-App [:=> _0 _0] ([[typingo T-App () ((fn [a_1] a_1) (fn [a_1] a_1)) [:=> _0 _0]] <-- ([[typingo T-Abs () (fn [a_1] a_1) [:=> [:=> _0 _0] [:=> _0 _0]]] <-- ([[typingo T-Var ([a_2 [:=> _0 _0]]) a_2 [:=> _0 _0]] <-- ()])] [[typingo T-Abs () (fn [a_1] a_1) [:=> _0 _0]] <-- ([[typingo T-Var ([a_3 _0]) a_3 _0] <-- ()])])])]))))

(deftest test-typingo-debug
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-debug ['typingo c () `(~'fn ~(nom/tie x x)) t] d)
                   (== q [c t d]))))))
        '([T-Abs [:=> _0 _0] ([[typingo T-Abs () (fn [a_1] a_1) [:=> _0 _0]] <-- ([[typingo T-Var ([a_2 _0]) a_2 _0] <-- ([[lookupo ([a_2 _0]) a_2 _0] <-- ()])] [[== [:=> _0 _0] [:=> _0 _0]] <-- ()])])])))
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-debug ['typingo c () `((~'fn ~(nom/tie x x)) (~'fn ~(nom/tie x x))) t] d)
                   (== q [c t d]))))))
        '([T-App [:=> _0 _0] ([[typingo T-App () ((fn [a_1] a_1) (fn [a_1] a_1)) [:=> _0 _0]] <-- ([[typingo T-Abs () (fn [a_1] a_1) [:=> [:=> _0 _0] [:=> _0 _0]]] <-- ([[typingo T-Var ([a_2 [:=> _0 _0]]) a_2 [:=> _0 _0]] <-- ([[lookupo ([a_2 [:=> _0 _0]]) a_2 [:=> _0 _0]] <-- ()])] [[== [:=> [:=> _0 _0] [:=> _0 _0]] [:=> [:=> _0 _0] [:=> _0 _0]]] <-- ()])] [[typingo T-Abs () (fn [a_1] a_1) [:=> _0 _0]] <-- ([[typingo T-Var ([a_3 _0]) a_3 _0] <-- ([[lookupo ([a_3 _0]) a_3 _0] <-- ()])] [[== [:=> _0 _0] [:=> _0 _0]] <-- ()])] [[== [:=> [:=> _0 _0] [:=> _0 _0]] [:=> [:=> _0 _0] [:=> _0 _0]]] <-- ()] [[== [:=> _0 _0] [:=> _0 _0]] <-- ()] [[== [:=> _0 _0] [:=> _0 _0]] <-- ()])])])))
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x]
                   (typingo-debug ['typingo c () `(~'fn ~(nom/tie x [x x])) t] d)
                   (== q [c t d]))))))
        '([T-Abs [:=> [:=> _0 _1] _1] ([[typingo T-Abs () (fn [a_2] [a_2 a_2]) [:=> [:=> _0 _1] _1]] <-- ([[typingo T-App ([a_3 [:=> _0 _1]]) (a_3 a_3) _1] <-- ([[typingo T-Var ([a_3 [:=> _0 _1]]) a_3 [:=> _0 _1]] <-- ([[lookupo ([a_3 [:=> _0 _1]]) a_3 [:=> _0 _1]] <-- ()])] [[typingo T-Var ([a_3 [:=> _0 _1]]) a_3 [:=> _0 _1]] <-- ([[lookupo ([a_3 [:=> _0 _1]]) a_3 [:=> _0 _1]] <-- ()])] [[== [:=> _0 _1] [:=> _0 _1]] <-- ()] [[== [:=> _0 _1] _0] error] [[== _1 _1] <-- ()])] [[== [:=> [:=> _0 _1] _1] [:=> [:=> _0 _1] _1]] <-- ()])])])))
  (is (= (read-string
           (prn-str
             (run* [q]
               (fresh [c t d]
                 (nom/fresh [x y]
                   (typingo-debug ['typingo c () `(~'fn ~(nom/tie x y)) t] d)
                   (== q [c t d]))))))
        '([T-Abs [:=> _0 _1] ([[typingo T-Abs () (fn [a_2] a_3) [:=> _0 _1]] <-- ([[typingo T-Var ([a_4 _0]) a_3 _1] <-- ([[lookupo ([a_4 _0]) a_3 _1] error])] [[== [:=> _0 _1] [:=> _0 _1]] <-- ()])])]))))
