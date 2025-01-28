package Main;

import java.util.Scanner;
import java.util.ArrayList;

public class GestioneContabilita {

    // Classe Dipendente
    static class Dipendente {
        private String nome;
        private String ruolo;
        private double stipendio;

        public Dipendente(String nome, String ruolo, double stipendio) {
            this.nome = nome;
            this.ruolo = ruolo;
            this.stipendio = stipendio;
        }

        public void stampaDettagli() {
            System.out.println("Nome: " + nome + ", Ruolo: " + ruolo + ", Stipendio: " + stipendio);
        }

        public double getStipendio() {
            return stipendio;
        }
    }

    // Classe Contabilita (gestione bilancio aziendale)
    static class Contabilita {
        private double entrate;
        private double uscite;

        public Contabilita() {
            this.entrate = 0;
            this.uscite = 0;
        }

        public void aggiungiEntrata(double importo) {
            entrate += importo;
        }

        public void aggiungiUscita(double importo) {
            uscite += importo;
        }

        public double calcolaBilancio() {
            return entrate - uscite;
        }

        public void stampaBilancio() {
            System.out.println("Entrate: " + entrate);
            System.out.println("Uscite: " + uscite);
            System.out.println("Bilancio: " + calcolaBilancio());
        }
    }

    // Metodo principale
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Dipendente> dipendenti = new ArrayList<>();
        Contabilita contabilita = new Contabilita();

        // Aggiunta di dipendenti
        boolean continua = true;
        while (continua) {
            System.out.println("Inserisci nome del dipendente:");
            String nome = scanner.nextLine();

            System.out.println("Inserisci ruolo del dipendente:");
            String ruolo = scanner.nextLine();

            // Gestione corretta dell'input per stipendio
            double stipendio = 0;
            boolean stipendioValido = false;
            while (!stipendioValido) {
                System.out.println("Inserisci stipendio del dipendente:");
                try {
                    stipendio = scanner.nextDouble();
                    scanner.nextLine();  // Consuma la newline residua
                    stipendioValido = true; // L'input è valido
                } catch (Exception e) {
                    System.out.println("Errore: inserisci un numero valido per lo stipendio.");
                    scanner.nextLine();  // Consuma l'input non valido
                }
            }

            Dipendente dipendente = new Dipendente(nome, ruolo, stipendio);
            dipendenti.add(dipendente);

            System.out.println("Vuoi aggiungere un altro dipendente? (si/no)");
            String risposta = scanner.nextLine();
            if (risposta.equalsIgnoreCase("no")) {
                continua = false;
            }
        }

        // Calcolo e stampa degli stipendi totali
        double stipendioTotale = 0;
        System.out.println("\nLista dei Dipendenti:");
        for (Dipendente d : dipendenti) {
            d.stampaDettagli();
            stipendioTotale += d.getStipendio();
        }
        System.out.println("\nTotale Stipendi da pagare: " + stipendioTotale);

        // Gestione delle entrate e delle uscite
        continua = true;
        while (continua) {
            System.out.println("\nInserisci tipo di operazione (entrata/uscita):");
            String tipoOperazione = scanner.nextLine();

            // Gestione corretta dell'input per operazioni
            double importo = 0;
            boolean importoValido = false;
            while (!importoValido) {
                System.out.println("Inserisci importo:");
                try {
                    importo = scanner.nextDouble();
                    scanner.nextLine();  // Consuma la newline residua
                    importoValido = true; // L'input è valido
                } catch (Exception e) {
                    System.out.println("Errore: inserisci un numero valido per l'importo.");
                    scanner.nextLine();  // Consuma l'input non valido
                }
            }

            if (tipoOperazione.equalsIgnoreCase("entrata")) {
                contabilita.aggiungiEntrata(importo);
            } else if (tipoOperazione.equalsIgnoreCase("uscita")) {
                contabilita.aggiungiUscita(importo);
            }

            System.out.println("Vuoi aggiungere un'altra operazione? (si/no)");
            String risposta = scanner.nextLine();
            if (risposta.equalsIgnoreCase("no")) {
                continua = false;
            }
        }

        // Stampa il bilancio aziendale
        System.out.println("\nBilancio Aziendale:");
        contabilita.stampaBilancio();

        scanner.close();
    }
}


