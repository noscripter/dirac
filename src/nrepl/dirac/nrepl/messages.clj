(ns dirac.nrepl.messages
  (:require [dirac.nrepl.helpers :as helpers]))

(defn ^:dynamic make-missing-nrepl-message-msg []
  (str "Unable to bootstrap Dirac CLJS REPL because a relevant nREPL message is not available.\n"
       "This is likely caused by missing or misconfigured 'interruptible-eval' middleware which is normally included by default.\n"
       "Dirac nREPL middleware currently depends on eval behaviour in 'interruptible-eval' middleware."))

(defn ^:dynamic make-no-target-session-help-msg [info]
  (str "Your session joined Dirac but no connected Dirac session is \"" info "\".\n"
       "You can review the list of currently available Dirac sessions via `(dirac! :ls)`.\n"
       "You can join one of them with `(dirac! :join)`.\n"
       "See `(dirac! :help)` for more info."))

(defn ^:dynamic make-no-target-session-match-msg [_info]
  (str "No suitable Dirac session is connected to handle your command."))

(defn ^:dynamic make-nrepl-message-cannot-be-forwarded-msg [message-info]
  (str "Encountered an nREPL message which cannot be forwarded to joined Dirac session:\n"
       message-info))

(defn ^:dynamic make-no-forwarding-help-msg [op]
  (str "Your have a joined Dirac session and your nREPL client just sent an unsupported nREPL operation to it.\n"
       "Ask Dirac developers to implement '" op "' op: https://github.com/binaryage/dirac/issues."))

(defn ^:dynamic make-missing-compiler-msg [selected-compiler]
  (let [compiler (helpers/make-human-readable-selected-compiler selected-compiler)]
    (str "Selected compiler '" compiler "' is missing. It does not match any of available compilers.\n"
         "Use `(dirac! :ls)` to review current situation and "
         "`(dirac! :switch <compiler-id>)` to switch to an existing compiler.")))
