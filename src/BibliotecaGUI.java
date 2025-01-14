// Proyecto Mejorado Aplicando los Principios SOLID y con Patrones de
// Diseño Adicionales (Singleton, Factory Method, Observer)

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Clase Libro con patrón Builder
// **Principios SOLID**:
// - SRP (Single Responsibility Principle): Esta clase representa únicamente la entidad "Libro".
// **Patrones de diseño**:
// - Builder: Permite la creación controlada y flexible de objetos Libro.
class Libro {
    // Atributos de la clase (definen las características de un libro)
    private String codigo;          // Código único del libro
    private String titulo;          // Título del libro
    private String localizacion;    // Localización del libro en la biblioteca
    private String signatura;       // Identificador del libro (signatura)
    private Autor autor;            // Autor del libro
    private boolean disponible;     // Indica si el libro está disponible para préstamo

    // Clase interna estática Builder (patrón Builder)
    public static class Builder {
        private String codigo;          // Código del libro
        private String titulo;          // Título del libro
        private Autor autor;            // Autor del libro
        private String localizacion;    // Localización del libro
        private String signatura;       // Signatura del libro
        private boolean disponible;     // Disponibilidad del libro

        // Métodos del Builder para asignar valores a los atributos del libro (retornan el propio Builder)
        public Builder setCodigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder setAutor(Autor autor) {
            this.autor = autor;
            return this;
        }

        public Builder setLocalizacion(String localizacion) {
            this.localizacion = localizacion;
            return this;
        }

        public Builder setSignatura(String signatura) {
            this.signatura = signatura;
            return this;
        }

        public Builder setDisponible(boolean disponible) {
            this.disponible = disponible;
            return this;
        }
        // Metodo para construir un objeto Libro con los valores asignados en el Builder (retorna un objeto Libro)
        public Libro build() {
            return new Libro(this);
        }
    }
    // Constructor privado para usar el patrón Builder (inicializa los atributos del libro)
    private Libro(Builder builder) {
        this.codigo = builder.codigo;
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.localizacion = builder.localizacion;
        this.signatura = builder.signatura;
        this.disponible = builder.disponible;
    }

    // Métodos getter (devuelven los valores de los atributos) de la clase Libro (encapsulamiento)
    // **Principios SOLID**: Encapsulamiento (oculta los detalles de implementación)
    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public String getSignatura() {
        return signatura;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // Metodo toString (representación textual del objeto) de la clase Libro (para depuración)
    // **Principios SOLID**: Representación textual (permite visualizar los objetos de forma legible)
    @Override
    public String toString() {
        return "Libro{" + "codigo='" + codigo + '\'' + ", titulo='" + titulo + '\'' + ", autor=" + autor + ", localizacion='"
                + localizacion + '\'' + ", signatura='" + signatura + '\'' + ", disponible=" + disponible + '}';
    }
}

// Clase Autor
// **Principios SOLID**:
// - SRP: La clase representa únicamente un autor de libros.
class Autor {
    private String nombre; //nombre del autor

    // Constructor para inicializar el nombre del autor
    public Autor(String nombre) {
        this.nombre = nombre;
    }

    // Metodo getter para devolver el nombre del autor
    public String getNombre() {
        return nombre;
    }

    // Metodo toString para representar el objeto como cadena de texto

    @Override
    public String toString() {
        return "Autor{" + "nombre='" + nombre + '\'' + '}';
    }
}

// Clase Socio con principio SRP (Single Responsibility Principle)
// Representa a un socio de la biblioteca.
class Socio {
    private String numero;    // Número único del socio
    private String nombre;    // Nombre del socio
    private String direccion; // Dirección del socio

    public Socio(String numero, String nombre, String direccion) {
        this.numero = numero;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Métodos getter para devolver los valores de los atributos
    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    // Metodo toString para representar al socio como texto
    @Override
    public String toString() {
        return "Socio{" + "numero=" + numero + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
}

// Clase Prestamo con principio SRP (Single Responsibility Principle)
// Representa un préstamo realizado por un socio.
class Prestamo {
    private String numeroSocio;   // Número del socio que realiza el préstamo
    private String codigoLibro;   // Código del libro prestado
    private String fechaPrestamo; // Fecha del préstamo

    // Constructor para inicializar un préstamo
    public Prestamo(String numeroSocio, String codigoLibro, String fechaPrestamo) {
        this.numeroSocio = numeroSocio;// Inicializa los atributos del préstamo
        this.codigoLibro = codigoLibro;
        this.fechaPrestamo = fechaPrestamo;
    }

    // Métodos getter para devolver los valores de los atributos
    public String getNumeroSocio() {
        return numeroSocio;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    // Metodo toString para representar el préstamo como texto
    @Override
    public String toString() {
        // Devuelve una cadena de texto con los valores de los atributos
        return "Prestamo{" + "numeroSocio=" + numeroSocio + ", codigoLibro=" + codigoLibro + ", fechaPrestamo=" + fechaPrestamo + '}';
    }
}

// Interfaces de Repositorios con principio ISP (Interface Segregation Principle)
// Define un contrato genérico para repositorios.
interface Repositorio<T> {
    void guardar(T entidad); // Guarda una entidad en el repositorio
    ArrayList<T> obtenerTodos(); // Obtiene todas las entidades del repositorio
    void eliminar(String id); // Elimina una entidad del repositorio
    void agregarObservador(Observador observador); // Agrega un observador al repositorio
}

// Interfaz Observador para el Patrón Observer
// Permite notificar cambios a observadores registrados.
interface Observador {
    void actualizar();
}

// Implementación del Repositorio de Libros con Singleton y DAO
// **Principios SOLID**:
// - SRP: Gestiona exclusivamente la persistencia de los libros.
// - DIP: Depende de la interfaz genérica Repositorio<T>.
// **Patrones de diseño**:
// - Singleton: Garantiza una única instancia del repositorio.
// - Observer: Permite notificar cambios a observadores registrados.
// - DAO (Data Access Object): Separa la lógica de acceso a datos del resto de la aplicación.
class LibroRepositorio implements Repositorio<Libro> {
    private static LibroRepositorio instancia; // Singleton: Instancia única del repositorio
    private List<Observador> observadores = new ArrayList<>(); // Observadores registrados
    private static final String NOMBRE_ARCHIVO = "libros.txt"; // Nombre del archivo de libros

    // Constructor privado para implementar Singleton
    private LibroRepositorio() {}

    // Metodo para obtener la instancia única del repositorio
    public static LibroRepositorio getInstance() {
        if (instancia == null) {
            instancia = new LibroRepositorio();
        }
        return instancia;
    }

    // Implementación de métodos de la interfaz Repositorio, Metodo para guardar un libro en el archivo
    @Override
    public void guardar(Libro libro) {
        String contenido = libro.getCodigo() + ";" + libro.getTitulo() + ";" + libro.getAutor().getNombre() + ";"
                + libro.getLocalizacion() + ";" + libro.getSignatura() + ";" + libro.isDisponible() + "\n";
        UtilidadesArchivos.escribirEnArchivo(NOMBRE_ARCHIVO, contenido, true);
        notificarObservadores();
    }

    // Metodo para obtener todos los libros del archivo
    @Override
    public ArrayList<Libro> obtenerTodos() {
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<String> lineas = UtilidadesArchivos.leerArchivo(NOMBRE_ARCHIVO);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length == 6) {
                Autor autor = new Autor(datos[2]);
                libros.add(new Libro.Builder()
                        .setCodigo(datos[0])
                        .setTitulo(datos[1])
                        .setAutor(autor)
                        .setLocalizacion(datos[3])
                        .setSignatura(datos[4])
                        .setDisponible(Boolean.parseBoolean(datos[5]))
                        .build());
            }
        }
        return libros;
    }

    // Metodo para eliminar un libro por su código
    @Override
    public void eliminar(String codigoLibro) {
        ArrayList<Libro> libros = obtenerTodos();
        libros.removeIf(libro -> libro.getCodigo().equals(codigoLibro)); // Elimina el libro con el código especificado
        guardarTodos(libros); // Guarda los libros restantes en el archivo
        notificarObservadores(); // Notifica a los observadores registrados
    }

    // Metodo para agregar un observador al repositorio
    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Metodo para guardar todos los libros en el archivo
    private void guardarTodos(ArrayList<Libro> libros) {
        StringBuilder contenido = new StringBuilder();
        for (Libro libro : libros) {
            contenido.append(libro.getCodigo()).append(";")
                    .append(libro.getTitulo()).append(";")
                    .append(libro.getAutor().getNombre()).append(";")

                    .append(libro.getLocalizacion()).append(";")
                    .append(libro.getSignatura()).append(";")
                    .append(libro.isDisponible()).append("\n");
        }
        UtilidadesArchivos.escribirEnArchivo(NOMBRE_ARCHIVO, contenido.toString(), false);
    }

    // Metodo para notificar a los observadores registrados
    private void notificarObservadores() {
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }
}

// Implementación de SocioRepositorio y PrestamoRepositorio
// **Características**:
// - Implementan los mismos principios y patrones que LibroRepositorio.
// - Se ajustan a las entidades Socio y Prestamo respectivamente.
class SocioRepositorio implements Repositorio<Socio> {
    private static SocioRepositorio instancia; // Singleton: Instancia única del repositorio
    private List<Observador> observadores = new ArrayList<>();// Observadores registrados
    private static final String NOMBRE_ARCHIVO = "socios.txt";// Nombre del archivo de socios

    private SocioRepositorio() {}
    // Singleton: Instancia única del repositorio de socios
    public static SocioRepositorio getInstance() {
        if (instancia == null) {
            instancia = new SocioRepositorio();
        }
        return instancia;
    }
    // Implementación de métodos de la interfaz Repositorio
    // Metodo para guardar un socio en el archivo
    @Override
    public void guardar(Socio socio) {
        String contenido = socio.getNumero() + ";" + socio.getNombre() + ";" + socio.getDireccion() + "\n";
        UtilidadesArchivos.escribirEnArchivo(NOMBRE_ARCHIVO, contenido, true);
        notificarObservadores();
    }

    // Metodo para obtener todos los socios del archivo
    @Override
    public ArrayList<Socio> obtenerTodos() {
        ArrayList<Socio> socios = new ArrayList<>();
        ArrayList<String> lineas = UtilidadesArchivos.leerArchivo(NOMBRE_ARCHIVO);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length == 3) {
                socios.add(new Socio(datos[0], datos[1], datos[2]));
            }
        }
        return socios;
    }
    // Metodo para eliminar un socio por su número
    @Override
    public void eliminar(String numeroSocio) {
        ArrayList<Socio> socios = obtenerTodos();
        socios.removeIf(socio -> socio.getNumero().equals(numeroSocio));
        guardarTodos(socios);
        notificarObservadores();
    }
    // Metodo para agregar un observador al repositorio
    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }
    // Metodo para guardar todos los socios en el archivo
    private void guardarTodos(ArrayList<Socio> socios) {
        StringBuilder contenido = new StringBuilder();
        for (Socio socio : socios) {
            contenido.append(socio.getNumero()).append(";")
                    .append(socio.getNombre()).append(";")
                    .append(socio.getDireccion()).append("\n");
        }
        UtilidadesArchivos.escribirEnArchivo(NOMBRE_ARCHIVO, contenido.toString(), false);
    }
    // Metodo para notificar a los observadores registrados
    private void notificarObservadores() {
        // Patrón Observer: Notifica a todos los observadores registrados cuando hay un cambio.
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }
}

// Implementación del Repositorio de Préstamos con Singleton y DAO
// **Características**:
// - Implementa los mismos principios y patrones que LibroRepositorio.
// - Se ajusta a la entidad Prestamo.
class PrestamoRepositorio implements Repositorio<Prestamo> {
    private static PrestamoRepositorio instancia; // Singleton: Instancia única del repositorio
    private List<Observador> observadores = new ArrayList<>();
    private static final String NOMBRE_ARCHIVO = "prestamos.txt";

    private PrestamoRepositorio() {}
    // Singleton: Instancia única del repositorio
    public static PrestamoRepositorio getInstance() {
        if (instancia == null) {
            instancia = new PrestamoRepositorio();
        }
        return instancia;
    }
    // Implementación de métodos de la interfaz Repositorio para Prestamo (métodos similares a LibroRepositorio)
    @Override
    public void guardar(Prestamo prestamo) {
        String contenido = prestamo.getNumeroSocio() + ";" + prestamo.getCodigoLibro() + ";" + prestamo.getFechaPrestamo() + "\n";
        UtilidadesArchivos.escribirEnArchivo(NOMBRE_ARCHIVO, contenido, true);
        notificarObservadores();
    }
    // Metodo para obtener todos los préstamos del archivo
    @Override
    public ArrayList<Prestamo> obtenerTodos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        ArrayList<String> lineas = UtilidadesArchivos.leerArchivo(NOMBRE_ARCHIVO);
        for (String linea : lineas) {
            String[] datos = linea.split(";");
            if (datos.length == 3) {
                prestamos.add(new Prestamo(datos[0], datos[1], datos[2]));
            }
        }
        return prestamos;
    }
    // Metodo para eliminar un préstamo por el código del libro
    @Override
    public void eliminar(String id) {
        ArrayList<Prestamo> prestamos = obtenerTodos();
        prestamos.removeIf(prestamo -> prestamo.getCodigoLibro().equals(id));
        guardarTodos(prestamos);
        notificarObservadores();
    }
    // Metodo para agregar un observador al repositorio de préstamos
    @Override
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }
    // Metodo para guardar todos los préstamos en el archivo
    private void guardarTodos(ArrayList<Prestamo> prestamos) {
        StringBuilder contenido = new StringBuilder();
        for (Prestamo prestamo : prestamos) {
            contenido.append(prestamo.getNumeroSocio()).append(";")
                    .append(prestamo.getCodigoLibro()).append(";")
                    .append(prestamo.getFechaPrestamo()).append("\n");
        }
        UtilidadesArchivos.escribirEnArchivo(NOMBRE_ARCHIVO, contenido.toString(), false);
    }

    private void notificarObservadores() {
        // Patrón Observer: Notifica a todos los observadores registrados cuando hay un cambio.
        for (Observador observador : observadores) {
            observador.actualizar();
        }
    }
}

// Clase UtilidadesArchivos para manejo de archivos
// Principio SRP: Esta clase tiene una única responsabilidad que es la manipulación de archivos.
class UtilidadesArchivos {
    public static void escribirEnArchivo(String nombreArchivo, String contenido, boolean append) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nombreArchivo, append))) {
            bufferedWriter.write(contenido);
        } catch (IOException e) {
            System.out.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }
    // Metodo para leer un archivo y devolver las líneas como una lista de cadenas
    public static ArrayList<String> leerArchivo(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Excepción leyendo archivo: " + e.getMessage());
        }
        return lineas;
    }
}

// Clase ServicioBiblioteca que maneja la lógica de negocio
// **Principios SOLID**:
// - SRP: Gestiona la interacción entre repositorios y casos de uso de la biblioteca.
// - DIP: Depende de abstracciones (Repositorio<T>) en lugar de implementaciones concretas.
class ServicioBiblioteca {
    private Repositorio<Libro> libroRepositorio; // Repositorio de libros
    private Repositorio<Socio> socioRepositorio; // Repositorio de socios
    private Repositorio<Prestamo> prestamoRepositorio; // Repositorio de préstamos

    // Constructor para inyectar dependencias (Principio DIP)
    public ServicioBiblioteca(Repositorio<Libro> libroRepositorio, Repositorio<Socio> socioRepositorio, Repositorio<Prestamo> prestamoRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.socioRepositorio = socioRepositorio;
        this.prestamoRepositorio = prestamoRepositorio;
    }
    // Metodo para registrar un nuevo socio
    public void registrarSocio(String numero, String nombre, String direccion) {
        Socio nuevoSocio = new Socio(numero, nombre, direccion);
        socioRepositorio.guardar(nuevoSocio);
    }
    // Metodo para registrar un nuevo libro
    public void registrarLibro(String codigo, String titulo, String nombreAutor,String localizacion, String signatura, boolean disponible) {
        Autor autor = new Autor(nombreAutor); // Crear un objeto Autor
        Libro nuevoLibro = new Libro.Builder() // Crear un objeto Libro con Builder
                .setCodigo(codigo)
                .setTitulo(titulo)
                .setAutor(autor)
                .setLocalizacion(localizacion)
                .setSignatura(signatura)
                .setDisponible(disponible)
                .build();
        libroRepositorio.guardar(nuevoLibro); // Guardar el libro en el repositorio
    }

    // Metodo para registrar un nuevo préstamo
    public void registrarPrestamo(String numeroSocio, String codigoLibro, String fechaPrestamo) {
        Prestamo nuevoPrestamo = new Prestamo(numeroSocio, codigoLibro, fechaPrestamo);
        prestamoRepositorio.guardar(nuevoPrestamo);
    }
    // Metodo para eliminar un socio por su número
    public void eliminarSocio(String numero) {
        socioRepositorio.eliminar(numero);
    }
    // Método para eliminar un libro por su código
    public void eliminarLibro(String codigo) {
        libroRepositorio.eliminar(codigo);
    }
    // Metodo para mostrar todos los socios (en consola)
    public void verSocios() {
        ArrayList<Socio> socios = socioRepositorio.obtenerTodos(); // Obtener todos los socios
        System.out.println("Lista de Socios:");
        System.out.printf("%-10s %-20s %-30s%n", "Número", "Nombre", "Dirección");
        System.out.println("---------------------------------------------------------------");
        for (Socio socio : socios) {
            System.out.printf("%-10s %-20s %-30s%n", socio.getNumero(), socio.getNombre(), socio.getDireccion());
        }
    }
    // Metodo para mostrar todos los libros (en consola)
    public void verLibros() {
        ArrayList<Libro> libros = libroRepositorio.obtenerTodos();
        System.out.println("Lista de Libros:");
        System.out.printf("%-10s %-30s %-20s %-20s %-15s %-10s%n", "Código", "Título", "Autor", "Localización", "Signatura", "Disponible");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        for (Libro libro : libros) {
            System.out.printf("%-10s %-30s %-20s %-20s %-15s %-10s%n", libro.getCodigo(), libro.getTitulo(), libro.getAutor().getNombre(), libro.getLocalizacion(), libro.getSignatura(), libro.isDisponible() ? "Sí" : "No");
        }
    }
    // Metodo para mostrar todos los préstamos (en consola)
    public void verPrestamos() {
        ArrayList<Prestamo> prestamos = prestamoRepositorio.obtenerTodos();
        System.out.println("Lista de Préstamos:");
        System.out.printf("%-15s %-15s %-20s%n", "Número Socio", "Código Libro", "Fecha Préstamo");
        System.out.println("-----------------------------------------------");
        for (Prestamo prestamo : prestamos) {
            System.out.printf("%-15s %-15s %-20s%n", prestamo.getNumeroSocio(), prestamo.getCodigoLibro(), prestamo.getFechaPrestamo());
        }
    }
}

// Clase BibliotecaGUI (Interfaz gráfica)
// **Principios SOLID**:
// - SRP: Gestiona exclusivamente la interacción del usuario mediante la GUI.
// **Patrones de diseño**:
// - Observer: Implementa la interfaz Observador para reaccionar a cambios en los repositorios.
public class BibliotecaGUI extends JFrame implements Observador {
    // Atributo para el servicio de la biblioteca
    private ServicioBiblioteca servicioBiblioteca;
    // Constructor de la GUI
    public BibliotecaGUI(ServicioBiblioteca servicioBiblioteca) {
        this.servicioBiblioteca = servicioBiblioteca;

        // Configuración de la apariencia de la GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuración del JFrame principal
        setTitle("Gestión de Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.addActionListener(e -> System.exit(0));
        menuArchivo.add(menuItemSalir);
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        // Crear panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel de registro
        JPanel panelRegistro = new JPanel(new GridLayout(3, 1));
        panelRegistro.add(createRegistroSocioPanel());
        panelRegistro.add(createRegistroLibroPanel());
        panelRegistro.add(createRegistroPrestamoPanel());
        tabbedPane.addTab("Registrar", panelRegistro);

        // Panel de eliminación
        JPanel panelEliminacion = new JPanel(new GridLayout(2, 1));
        panelEliminacion.add(createEliminarSocioPanel());
        panelEliminacion.add(createEliminarLibroPanel());
        tabbedPane.addTab("Eliminar", panelEliminacion);

        // Panel de visualización
        JPanel panelVisualizacion = new JPanel(new GridLayout(3, 1));
        panelVisualizacion.add(createVerSociosPanel());
        panelVisualizacion.add(createVerLibrosPanel());
        panelVisualizacion.add(createVerPrestamosPanel());
        tabbedPane.addTab("Ver", panelVisualizacion);

        // Añadir panel de pestañas al JFrame
        add(tabbedPane, BorderLayout.CENTER);
    }
    // Metodos para crear paneles de la GUI
    private JPanel createRegistroSocioPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnRegistrarSocio = new JButton("Registrar Socio");
        btnRegistrarSocio.addActionListener(e -> {
            String numero = JOptionPane.showInputDialog(this, "Ingrese DNI del socio:");
            if (numero == null) return; // Botón cancelar
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del socio:");
            if (nombre == null) return; // Botón cancelar
            String direccion = JOptionPane.showInputDialog(this, "Ingrese la dirección del socio:");
            if (direccion == null) return; // Botón cancelar
            servicioBiblioteca.registrarSocio(numero, nombre, direccion);
            JOptionPane.showMessageDialog(this, "Socio registrado correctamente");
        });
        panel.add(btnRegistrarSocio, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de registro de préstamos
    private JPanel createRegistroPrestamoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnRegistrarPrestamo = new JButton("Registrar Préstamo");
        btnRegistrarPrestamo.addActionListener(e -> {
            String numeroSocio = JOptionPane.showInputDialog(this, "Ingrese DNI del socio:");
            if (numeroSocio == null) return; // Botón cancelar
            String codigoLibro = JOptionPane.showInputDialog(this, "Ingrese el código del libro:");
            if (codigoLibro == null) return; // Botón cancelar
            String fechaPrestamo = JOptionPane.showInputDialog(this, "Ingrese la fecha del préstamo (YYYY-MM-DD):");
            if (fechaPrestamo == null) return; // Botón cancelar
            servicioBiblioteca.registrarPrestamo(numeroSocio, codigoLibro, fechaPrestamo);
            JOptionPane.showMessageDialog(this, "Préstamo registrado correctamente");
        });
        panel.add(btnRegistrarPrestamo, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de registro de libros
    private JPanel createRegistroLibroPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnRegistrarLibro = new JButton("Registrar Libro");
        btnRegistrarLibro.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del libro:");
            if (codigo == null) return; // Botón cancelar
            String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro:");
            if (titulo == null) return; // Botón cancelar
            String nombreAutor = JOptionPane.showInputDialog(this, "Ingrese el nombre del autor del libro:");
            if (nombreAutor == null) return; // Botón cancelar
            String nacionalidadAutor = JOptionPane.showInputDialog(this, "Ingrese la nacionalidad del autor del libro:");
            if (nacionalidadAutor == null) return; // Botón cancelar
            String localizacion = JOptionPane.showInputDialog(this, "Ingrese la localización del libro:");
            if (localizacion == null) return; // Botón cancelar
            String signatura = JOptionPane.showInputDialog(this, "Ingrese la signatura del libro:");
            if (signatura == null) return; // Botón cancelar
            boolean disponible = JOptionPane.showConfirmDialog(this, "¿Está disponible el libro?", "Disponibilidad", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            servicioBiblioteca.registrarLibro(codigo, titulo, nombreAutor, localizacion, signatura, disponible);
            JOptionPane.showMessageDialog(this, "Libro registrado correctamente");
        });
        panel.add(btnRegistrarLibro, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de eliminación de socios
    private JPanel createEliminarSocioPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnEliminarSocio = new JButton("Eliminar Socio");
        btnEliminarSocio.addActionListener(e -> {
            String numero = JOptionPane.showInputDialog(this, "Ingrese el DNI del socio a eliminar:");
            if (numero == null) return; // Botón cancelar
            servicioBiblioteca.eliminarSocio(numero);
            JOptionPane.showMessageDialog(this, "Socio eliminado correctamente");
        });
        panel.add(btnEliminarSocio, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de eliminación de libros
    private JPanel createEliminarLibroPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnEliminarLibro = new JButton("Eliminar Libro");
        btnEliminarLibro.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del libro a eliminar:");
            if (codigo == null) return; // Botón cancelar
            servicioBiblioteca.eliminarLibro(codigo);
            JOptionPane.showMessageDialog(this, "Libro eliminado correctamente");
        });
        panel.add(btnEliminarLibro, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de visualización de socios
    private JPanel createVerSociosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnVerSocios = new JButton("Ver Socios");
        btnVerSocios.addActionListener(e -> servicioBiblioteca.verSocios());
        panel.add(btnVerSocios, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de visualización de libros
    private JPanel createVerLibrosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnVerLibros = new JButton("Ver Libros");
        btnVerLibros.addActionListener(e -> servicioBiblioteca.verLibros());
        panel.add(btnVerLibros, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para crear el panel de visualización de préstamos
    private JPanel createVerPrestamosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton btnVerPrestamos = new JButton("Ver Préstamos");
        btnVerPrestamos.addActionListener(e -> servicioBiblioteca.verPrestamos());
        panel.add(btnVerPrestamos, BorderLayout.CENTER);
        return panel;
    }
    // Metodo para actualizar la GUI cuando hay cambios en los repositorios
    @Override
    public void actualizar() {
        // Patrón Observer: Actualiza la GUI cuando hay cambios en los repositorios observados.
        JOptionPane.showMessageDialog(this, "Datos actualizados en los repositorios");
    }
    // Metodo main para ejecutar la aplicación
    public static void main(String[] args) {
        // Utilizar inyección de dependencias mediante constructor.
        Repositorio<Libro> libroRepositorio = LibroRepositorio.getInstance();
        Repositorio<Socio> socioRepositorio = SocioRepositorio.getInstance();
        Repositorio<Prestamo> prestamoRepositorio = PrestamoRepositorio.getInstance();
        ServicioBiblioteca servicioBiblioteca = new ServicioBiblioteca(libroRepositorio, socioRepositorio, prestamoRepositorio);
        BibliotecaGUI bibliotecaGUI = new BibliotecaGUI(servicioBiblioteca);
        bibliotecaGUI.setVisible(true);
    }
}