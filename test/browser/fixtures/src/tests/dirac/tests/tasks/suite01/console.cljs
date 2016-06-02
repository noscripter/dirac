(ns dirac.tests.tasks.suite01.console
  (:require [cljs.core.async]
            [cljs.test :refer-macros [is testing]]
            [dirac.automation :refer-macros [<!* go-task with-scenario with-devtools] :as a]))

(go-task
  (with-scenario "normal"
    (with-devtools
      (<!* a/switch-to-console-panel!)
      ; -------------------------------------------------------------------------------------------------------------------------
      (testing "welcome message should be present by default"
        (<!* a/wait-for-devtools-match "displayWelcomeMessage"))
      (<!* a/enable-console-feedback!)
      ; -------------------------------------------------------------------------------------------------------------------------
      (testing "keyboard shortcuts for switching between prompts"
        (<!* a/simulate-console-action! "CTRL+.")
        (<!* a/wait-for-prompt-switch-to-dirac)
        (<!* a/wait-for-prompt-to-enter-edit-mode)
        (<!* a/simulate-console-action! "CTRL+.")
        (<!* a/wait-for-prompt-switch-to-js)
        (<!* a/simulate-console-action! "CTRL+.")
        (<!* a/wait-for-prompt-switch-to-dirac)
        (<!* a/simulate-console-action! "CTRL+,")
        (<!* a/wait-for-prompt-switch-to-js)
        (<!* a/simulate-console-action! "CTRL+.")
        (<!* a/simulate-console-action! "CTRL+.")
        (<!* a/wait-for-prompt-switch-to-js)
        (<!* a/simulate-console-action! "CTRL+,")
        (<!* a/wait-for-prompt-switch-to-dirac))
      ; -------------------------------------------------------------------------------------------------------------------------
      (testing "prompt input simulation and feedback"
        (<!* a/simulate-console-input! "hello!")
        (is (= (<!* a/print-prompt!) "hello!"))
        (<!* a/clear-console-prompt!)
        (is (= (<!* a/get-prompt-representation) "")))
      (<!* a/close-devtools!)
      ; -------------------------------------------------------------------------------------------------------------------------
      (testing "welcome message should not be present when disabled in options"
        (<!* a/set-option! :welcome-message false)
        (<!* a/open-devtools!)
        (<!* a/switch-to-console-panel!)
        (<!* a/wait-for-devtools-match "!dirac.hasWelcomeMessage")
        (<!* a/close-devtools!)
        (<!* a/set-option! :welcome-message true))
      ; -------------------------------------------------------------------------------------------------------------------------
      (testing "parinfer should not be present when disabled in options"
        (<!* a/set-option! :enable-parinfer false)
        (<!* a/open-devtools!)
        (<!* a/switch-to-console-panel!)
        (<!* a/wait-for-devtools-match "use-parinfer? false")
        (<!* a/set-option! :enable-parinfer true)))))