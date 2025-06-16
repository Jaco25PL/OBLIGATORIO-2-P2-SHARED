/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidadorFechaHora {
    
    private static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
    
    // Validar formato fecha
    public static void validarFecha(String fecha) throws Exception{
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new Exception("La fecha no puede estar vacía");
        }
        
        try {
            LocalDate.parse(fecha, formatoFecha);
        } catch (DateTimeParseException e){
            throw new Exception("La fecha debe tener formato dd/MM/yyyy (ej: 25/12/2024)");
        }
    }
    
    // Validar formato hora
    public static void validarHora(String hora) throws Exception{
        if (hora == null || hora.trim().isEmpty()) {
            throw new Exception("La hora no puede estar vacía");
        }
        
        try {
            LocalTime.parse(hora, formatoHora);
        } catch (DateTimeParseException e){
            throw new Exception("La hora debe tener formato HH:mm de 24 horas (ej: 14:30)");
        }
    }
    
    // Validar fecha y hora posterior
    public static void validarFechaHoraPosterior(String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida) throws Exception{
        
        //valido formatos
        validarFecha(fechaEntrada);
        validarHora(horaEntrada);
        validarFecha(fechaSalida);
        validarHora(horaSalida);
        
        try {
            LocalDate fechaEntradaParsed = LocalDate.parse(fechaEntrada, formatoFecha);
            LocalTime horaEntradaParsed = LocalTime.parse(horaEntrada, formatoHora);
            LocalDate fechaSalidaParsed = LocalDate.parse(fechaSalida, formatoFecha);
            LocalTime horaSalidaParsed = LocalTime.parse(horaSalida, formatoHora);
            
            // Combinar fecha y hora en una variable para comparar todo de una
            java.time.LocalDateTime fechaHoraEntrada = java.time.LocalDateTime.of(fechaEntradaParsed, horaEntradaParsed);
            java.time.LocalDateTime fechaHoraSalida = java.time.LocalDateTime.of(fechaSalidaParsed, horaSalidaParsed);
            
            // Si es el mismo dia, validar que la hora sea posterior
            if (fechaHoraSalida.isBefore(fechaHoraEntrada) || fechaHoraSalida.equals(fechaHoraEntrada)) {
                throw new Exception("La fecha y hora de salida debe ser posterior a la de entrada");
            }
        } catch (DateTimeParseException e) {
            throw new Exception("Error al validar las fechas y horas");
        }
    }
    
    public static String getFechaActual(){
        return LocalDate.now().format(formatoFecha);
    }
    
    public static String getHoraActual(){
        return LocalTime.now().format(formatoHora);
    }
}
