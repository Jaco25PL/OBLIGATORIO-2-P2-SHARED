/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */

package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Minijuego {

    // Juego -> Wordle

    private String palabraSecreta;
    private ArrayList<String> intentos;
    private int maxIntentos;
    private boolean juegoTerminado;
    private boolean victoria;
    
    public Minijuego (){
    
        this.intentos = new ArrayList<>();
        this.maxIntentos = 6;
        this.juegoTerminado = false;
        this.victoria = false;
        this.palabraSecreta = palabraAleatoria();
    }
    
    public String palabraAleatoria() {
        String[] palabras = {
            "MATEO",
            "ASADO",
            "CHIVO",
            "GORDO",
            "DULCE",
            "TANGO",
            "MURGA",
            "FAINA",
            "BIZCO",
            "ALFAS",
            "CHIMI",
            "EMPAS",
            "FRITA",
            "YERBA",
            "MANGO"
        };
        
        int random = (int) (Math.random() * palabras.length); 
        return palabras[random];
    } 

    public void reiniciarJuego() {
        this.intentos.clear();
        this.juegoTerminado = false;
        this.victoria = false;
        this.palabraSecreta = palabraAleatoria();
    }
    
    public boolean intentarPalabra(String intento) {
        
        boolean result = true;
        if (isJuegoTerminado()){
            result = false;
        }
        
        getIntentos().add(intento);
        
        if (intento.equals(getPalabraSecreta())){
            setVictoria(true);
            setJuegoTerminado(true);
        } else if (getIntentos().size() >= getMaxIntentos()){
            setJuegoTerminado(true);
        }
        
        return result;
    }
    

    public int[] verificarLetras(String intento) {
        int[] resultados = new int[5];

        // Convertir palabras a arreglos de caracteres para manipularlas más fácil
        char[] intentoChars = intento.toCharArray();
        char[] secretaChars = palabraSecreta.toCharArray();

        // Crear una copia para marcar letras ya usadas
        boolean[] letraSecretaUsada = new boolean[5];

        // Primero verificar letras en posiciones correctas (prioridad)
        for (int i = 0; i < 5; i++) {
            if (intentoChars[i] == secretaChars[i]) {
                resultados[i] = 2; // Verde - posición correcta
                letraSecretaUsada[i] = true;
            }
        }

        // Después verificar letras presentes pero en posiciones incorrectas
        for (int i = 0; i < 5; i++) {
            // Si ya se marcó como posición correcta, continuar
            if (resultados[i] == 2) continue;

            // Buscar la letra en la palabra secreta
            boolean encontrada = false;
            for (int j = 0; j < 5 && !encontrada; j++) {
                if (!letraSecretaUsada[j] && intentoChars[i] == secretaChars[j]) {
                    resultados[i] = 1; // Amarillo - letra correcta, posición incorrecta
                    letraSecretaUsada[j] = true;
                    encontrada = true;
                }
            }

            // Si no se encontró la letra, marcar como ausente
            if (!encontrada) {
                resultados[i] = 0; // Gris - letra no está en la palabra
            }
        }

        return resultados;
    }
    
    
    
    

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public ArrayList<String> getIntentos() {
        return intentos;
    }

    public int getMaxIntentos() {
        return maxIntentos;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public boolean isVictoria() {
        return victoria;
    }

    private void setPalabraSecreta(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
    }

    public void setJuegoTerminado(boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }

    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }
    
    
}
