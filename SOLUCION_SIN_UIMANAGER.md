# Solución Sin UIManager: Modo Claro/Oscuro para JOptionPane y JDialog

## 🎯 Problema
Los `JOptionPane` no cambiaban de tema cuando se alternaba entre modo claro y oscuro.

## ✅ Solución usando solo conceptos de clase

### 🔧 Cómo funciona (sin UIManager):

1. **Extendimos `actualizarVentanas()`** para que también busque `JDialog` (los `JOptionPane` son `JDialog` especiales)

2. **Creamos `aplicarModoADialog()`** que funciona igual que `aplicarModo(JFrame)` pero para `JDialog`

3. **Agregamos métodos utilitarios** que llaman a `JOptionPane` normal y luego aplican el tema

### 🚀 Código agregado a `ClaroOscuro.java`:

```java
// En actualizarVentanas() - busca TODOS los tipos de ventana
private static void actualizarVentanas(){
    Window[] ventanas = Window.getWindows();
    
    for(int i = 0; i < ventanas.length; i++){
        Window ventana = ventanas[i];
        if (ventana instanceof JFrame) {
            aplicarModo((JFrame) ventana);
        } else if (ventana instanceof JDialog) {  // ← ESTO ES NUEVO
            aplicarModoADialog((JDialog) ventana);
        }
    }
}

// Nuevo método - igual lógica que aplicarModo(JFrame) pero para JDialog
private static void aplicarModoADialog(JDialog dialog) {
    Color colorFondo = getEstaModoClaro() ? Color.WHITE : Color.BLACK;
    Color colorTexto = getEstaModoClaro() ? Color.BLACK : Color.WHITE;
    
    // Aplicar al contenido (igual que con JFrame)
    dialog.getContentPane().setBackground(colorFondo);
    dialog.getContentPane().setForeground(colorTexto);
    
    // Aplicar a componentes recursivamente (reutilizamos método existente)
    aplicarModoAComponentes(dialog.getContentPane(), colorFondo, colorTexto);
    
    dialog.repaint(); // Forzar actualización visual
}
```

### 🎯 Métodos utilitarios agregados:

```java
// Reemplaza: JOptionPane.showMessageDialog(this, "mensaje");
public static void mostrarMensaje(Component parent, Object message) {
    JOptionPane.showMessageDialog(parent, message);
    aplicarTemaADialogosAbiertos(); // ← Aplica tema después
}

// Reemplaza: JOptionPane.showMessageDialog(this, "error", "Error", JOptionPane.ERROR_MESSAGE);
public static void mostrarMensaje(Component parent, Object message, String title, int type) {
    JOptionPane.showMessageDialog(parent, message, title, type);
    aplicarTemaADialogosAbiertos(); // ← Aplica tema después
}

// Reemplaza: JOptionPane.showConfirmDialog(this, "¿Seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
public static int mostrarConfirmacion(Component parent, Object message, String title, int optionType) {
    int resultado = JOptionPane.showConfirmDialog(parent, message, title, optionType);
    aplicarTemaADialogosAbiertos(); // ← Aplica tema después
    return resultado;
}
```

## 📝 Cómo cambiar el código existente:

### ANTES (JOptionPane normal):
```java
JOptionPane.showMessageDialog(this, "Cliente agregado con éxito");
```

### DESPUÉS (con tema automático):
```java
ClaroOscuro.mostrarMensaje(this, "Cliente agregado con éxito");
```

### ANTES (mensaje de error):
```java
JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
```

### DESPUÉS (con tema automático):
```java
ClaroOscuro.mostrarMensaje(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
```

### ANTES (confirmación):
```java
int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
if (confirmacion == JOptionPane.YES_OPTION) {
    // ...
}
```

### DESPUÉS (con tema automático):
```java
int confirmacion = ClaroOscuro.mostrarConfirmacion(this, "¿Está seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
if (confirmacion == JOptionPane.YES_OPTION) {
    // ...
}
```

## 🧠 Conceptos de clase utilizados:

✅ **Arrays y bucles**: `Window[] ventanas = Window.getWindows()`

✅ **instanceof**: Para distinguir entre `JFrame` y `JDialog`

✅ **Recursión**: `aplicarModoAComponentes()` se llama a sí mismo

✅ **Polimorfismo**: `Window` puede ser `JFrame` o `JDialog`

✅ **Reutilización de código**: `aplicarModoAComponentes()` funciona para ambos tipos

✅ **Patrón Observer**: Usando `PropertyChangeSupport` (ya estaba)

## 🎉 Ventajas de esta solución:

✅ **No usa conceptos avanzados** como `UIManager`

✅ **Reutiliza la lógica existente** de `aplicarModoAComponentes()`

✅ **Mantiene el patrón MVC** 

✅ **Es fácil de explicar** en clase

✅ **Funciona con cualquier JDialog**, no solo `JOptionPane`

## 🔍 ¿Por qué funciona?

1. Los `JOptionPane` son **JDialog especiales** que se crean automáticamente
2. `Window.getWindows()` **encuentra todos los JDialog abiertos**
3. Aplicamos **la misma lógica** que ya usábamos para `JFrame`
4. `SwingUtilities.invokeLater()` asegura que el diálogo esté completamente abierto antes de aplicar el tema

¡Es la misma lógica que ya tenías, solo extendida para manejar `JDialog`! 🎯
