package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMagico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMutante;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeTecnologico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.Superheroe;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 * Clase encargada de gestionar los superhéroes registrados en la academia
 *
 * @author Nelson Andrés Acuña Cambronero
 *
 * @version 1.0
 */
public class GestionSuperheroes {

    // Collecciones independientes según el tipo de héroe
    private ArrayList<HeroeTecnologico> heroesTecnologicos;
    private ArrayList<HeroeMutante> heroesMutantes;
    private ArrayList<HeroeMagico> heroesMagicos;

    /**
     * Constructor de la clase. Inicializa las colecciones de superhéroes
     *
     * @param heroesTecnologicos Colección de héroes tecnológicos
     * @param heroesMutantes Colección de héroes Mutantes
     * @param heroesMagicos Colección de héroes Mágicos
     */
    public GestionSuperheroes(ArrayList<HeroeTecnologico> heroesTecnologicos, ArrayList<HeroeMutante> heroesMutantes, ArrayList<HeroeMagico> heroesMagicos) {
        this.heroesTecnologicos = heroesTecnologicos;
        this.heroesMutantes = heroesMutantes;
        this.heroesMagicos = heroesMagicos;
    }
    // Constructor sobrecargado

    public GestionSuperheroes() {
        this.heroesTecnologicos = new ArrayList<>();
        this.heroesMutantes = new ArrayList<>();
        this.heroesMagicos = new ArrayList<>();
    }

    // Métodos adicionales
    /**
     * Registra un nuevo superhéroe tecnológico
     *
     * Valida los datos generales y específicos, genera automáticamente su
     * identificador y lo almacena en la colección correspondiente
     *
     * @param nombreHeroico Nombre del héroe (Alias)
     *
     * @param nombreReal Nombre real del superhéroe
     *
     * @param estado Estado actual del superhéroe
     *
     * @param salarioMensual Salario mensual del superhéroes
     *
     * @param nivelTecnologia Nivel tecnológico del superhéroe
     *
     * @param cantidadGadgets Cantidad de gadgets que utiliza
     *
     * @return True si el registro fue exitoso; False si los datos no cumplen
     * las validaciones
     */
    public boolean agregarHeroeTecnologico(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual,
            String nivelTecnologia,
            int cantidadGadgets) {
        // Validamos los datos generales del superhéroe
        if (!validarDatosGenerales(nombreHeroico, nombreReal, estado,
                salarioMensual)) {
            return false;
        }

        // Validamos los datos específicos del héroe tecnológico
        if (!esOpcionValida(nivelTecnologia, "Básico", "Intermedio", "Avanzado")
                || cantidadGadgets < 0) {
            return false;
        }

        // Creamos el objeto con los datos proporcionados
        HeroeTecnologico nuevoHeroe = new HeroeTecnologico(
                nivelTecnologia.trim(),
                cantidadGadgets,
                "",
                nombreHeroico.trim(),
                nombreReal.trim(),
                estado.trim(),
                salarioMensual
        );

        //Generamos y asignamos el identificador único
        nuevoHeroe.setIdHeroe(nuevoHeroe.generadorIdHeroe());

        // Agregamos el objeto a la colección correspondiente
        heroesTecnologicos.add(nuevoHeroe);

        //Retornamos True
        return true;
    }

    /**
     * Registra un nuevo superhéroe mutante.
     *
     * Valida los datos generales y específicos, genera automáticamente su
     * identificador y lo almacena en la colección correspondiente
     *
     * @param nombreHeroico Nombre heroico.
     *
     * @param nombreReal Nombre real.
     *
     * @param estado Estado actual.
     *
     * @param salarioMensual Salario mensual.
     *
     * @param tipoMutacion Tipo de mutación.
     *
     * @param controlPoder Nivel de control del poder.
     *
     * @return true si se registró correctamente; False si los datos no cumplen
     * las validaciones
     */
    public boolean agregarHeroeMutante(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual,
            String tipoMutacion,
            int controlPoder) {

        if (!validarDatosGenerales(nombreHeroico, nombreReal,
                estado, salarioMensual)) {
            return false;
        }

        if (!esOpcionValida(tipoMutacion,
                "Genética", "Radioactiva", "Experimental")
                || controlPoder < 1 || controlPoder > 100) {
            return false;
        }

        HeroeMutante nuevo = new HeroeMutante(
                tipoMutacion.trim(),
                controlPoder,
                "",
                nombreHeroico.trim(),
                nombreReal.trim(),
                estado.trim(),
                salarioMensual
        );

        nuevo.setIdHeroe(nuevo.generadorIdHeroe());

        heroesMutantes.add(nuevo);

        return true;
    }

    /**
     * Registra un nuevo superhéroe mágico.
     *
     * Valida los datos generales y específicos, genera automáticamente su
     * identificador y lo almacena en la colección correspondiente
     *
     * @param nombreHeroico Nombre heroico.
     *
     * @param nombreReal Nombre real.
     *
     * @param estado Estado actual.
     *
     * @param salarioMensual Salario mensual.
     *
     * @param tipoMagia Tipo de magia.
     *
     * @param cantidadHechizos Cantidad de hechizos.
     *
     * @return true si se registró correctamente; False si los datos no cumplen
     * las validaciones
     */
    public boolean agregarHeroeMagico(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual,
            String tipoMagia,
            int cantidadHechizos) {

        if (!validarDatosGenerales(nombreHeroico, nombreReal,
                estado, salarioMensual)) {
            return false;
        }

        if (!esOpcionValida(tipoMagia,
                "Elemental", "Oscura", "Sagrada")
                || cantidadHechizos < 0) {
            return false;
        }

        HeroeMagico nuevo = new HeroeMagico(
                tipoMagia.trim(),
                cantidadHechizos,
                "",
                nombreHeroico.trim(),
                nombreReal.trim(),
                estado.trim(),
                salarioMensual
        );

        nuevo.setIdHeroe(nuevo.generadorIdHeroe());

        heroesMagicos.add(nuevo);

        return true;
    }

    /**
     * Valida datos generales de superhéroe
     *
     * Comprueba que los datos generales del superhéroe cumpla con los
     * requisitos establecidos
     *
     * @return true si los datos son validos, false si estos son incorrectos
     */
    private boolean validarDatosGenerales(String nombreHeroico,
            String nombreReal,
            String estado,
            double salarioMensual) {

        return nombreHeroico != null
                && nombreHeroico.trim().length() >= 3
                && nombreReal != null
                && nombreReal.trim().length() >= 3
                && esOpcionValida(
                        estado, "Disponible", "En misión", "Recuperación")
                && Double.isFinite(salarioMensual)
                && salarioMensual >= 0;

    }

    /**
     * Valida si la opción es valida o no
     *
     * Comprueba si un valor pertenece a las opciones permitidas para un
     * determinado atributo
     *
     * @param valor Valor que se desea comprobar
     *
     * @param opciones Valores permitidos
     *
     * @return true si coincido con alguna opción; false caso contrario.
     */
    private boolean esOpcionValida(String valor, String... opciones) {
        // Validamos que el valor no sea nulo
        if (valor == null) {
            return false;
        }

        // Recorremos las opciones para comparar el valor seleccionado
        for (String opcion : opciones) {
            if (opcion.equals(valor.trim())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Busca un superhéroe por su ID
     *
     * Recorre las 3 colecciones para localizar el héroe
     *
     * @param idHeroe Identificador del superhéroe
     *
     * @return El Objeto encontrado como superhéroe; retorna null si no existe
     */
    public Superheroe buscarHeroePorId(String idHeroe) {
        // Validamos que se haya proporcionado un identificador
        if (idHeroe == null || idHeroe.trim().isEmpty()) {
            return null;
        }

        // Aceptamos el identificador recibido
        String idBuscado = idHeroe.trim();

        // Buscamos en la colección de héroes tecnológicos
        for (HeroeTecnologico heroeTecnologico : heroesTecnologicos) {
            /* En caso de cumplirse que no sea nulo
            y que también coincida se retorna como encontrado el 
            héroe
             */

            if (heroeTecnologico.getIdHeroe() != null
                    && heroeTecnologico.getIdHeroe().equalsIgnoreCase(idBuscado)) {
                return heroeTecnologico;
            }
        }

        // Buscamos en la colección de héroes mutantes
        for (HeroeMutante heroeMutante : heroesMutantes) {
            /* En caso de cumplirse que no sea nulo
            y que también coincida se retorna como encontrado el 
            héroe
             */

            if (heroeMutante.getIdHeroe() != null
                    && heroeMutante.getIdHeroe().equalsIgnoreCase(idBuscado)) {
                return heroeMutante;
            }
        }

        // Buscamos en la colección de héroes mágicos
        for (HeroeMagico heroeMagico : heroesMagicos) {
            /* En caso de cumplirse que no sea nulo
            y que también coincida se retorna como encontrado el 
            héroe
             */

            if (heroeMagico.getIdHeroe() != null
                    && heroeMagico.getIdHeroe().equalsIgnoreCase(idBuscado)) {
                return heroeMagico;
            }
        }

        /*Si termina todos los recorridos, significa que 
        no existe el ID buscado por lo que retorna null
         */
        return null;
    }

    /**
     * Obtiene todos los superhéroes registrados en la academia.
     *
     * Recorre las tres colecciones independientes y reúne los objetos en una
     * nueva lista de tipo Superheroe.
     *
     * @return Lista con todos los superhéroes registrados.
     */
    public ArrayList<Superheroe> obtenerTodosLosSuperheroes() {

        // Lista que contendrá todos los superhéroes.
        ArrayList<Superheroe> todosLosHeroes = new ArrayList<>();

        // Recorremos los héroes tecnológicos.
        for (HeroeTecnologico heroe : heroesTecnologicos) {
            todosLosHeroes.add(heroe);
        }

        // Recorremos los héroes mutantes.
        for (HeroeMutante heroe : heroesMutantes) {
            todosLosHeroes.add(heroe);
        }

        // Recorremos los héroes mágicos.
        for (HeroeMagico heroe : heroesMagicos) {
            todosLosHeroes.add(heroe);
        }

        return todosLosHeroes;
    }

    /**
     * Obtiene los superhéroes correspondientes a un tipo específico.
     *
     * Recorre la colección del tipo seleccionado y reúne los resultados en una
     * lista de tipo Superheroe.
     *
     * @param tipo Tipo de superhéroe que se desea consultar.
     * @return Lista de superhéroes del tipo seleccionado.
     */
    public ArrayList<Superheroe> obtenerSuperheroesPorTipo(String tipo) {

        // Lista que contendrá los resultados de la búsqueda.
        ArrayList<Superheroe> heroesEncontrados = new ArrayList<>();

        // Validamos que se haya seleccionado un tipo.
        if (tipo == null || tipo.trim().isEmpty()) {
            return heroesEncontrados;
        }

        // Comprobamos el tipo de superhéroe seleccionado.
        if (tipo.equalsIgnoreCase("Tecnológico")
                || tipo.equalsIgnoreCase("Tecnologico")) {

            // Recorremos los héroes tecnológicos.
            for (HeroeTecnologico heroe : heroesTecnologicos) {
                heroesEncontrados.add(heroe);
            }

        } else if (tipo.equalsIgnoreCase("Mutante")) {

            // Recorremos los héroes mutantes.
            for (HeroeMutante heroe : heroesMutantes) {
                heroesEncontrados.add(heroe);
            }

        } else if (tipo.equalsIgnoreCase("Mágico")
                || tipo.equalsIgnoreCase("Magico")) {

            // Recorremos los héroes mágicos.
            for (HeroeMagico heroe : heroesMagicos) {
                heroesEncontrados.add(heroe);
            }
        }

        return heroesEncontrados;
    }

    /**
     * Actualizar Superhéroe
     *
     * Busca el superhéroe mediante su ID y actualiza sus atributos generales y
     * específicos según su tipo
     *
     * @param idHeroe Identificador del superhéroe que se actualizará
     *
     * @param datosActualizados Objeto que contiene los nuevos datos
     *
     * @return true si la actualización fue exitosa; false en caso contrario
     */
    public boolean actualizarHeroe(String idHeroe, Superheroe datosActualizados) {
        // Comprobamos que los datos recibidos sean validos
        if (idHeroe == null || idHeroe.trim().isEmpty()
                || datosActualizados == null) {
            return false;
        }

        //Buscamos el superhéroe que se desea actualizar
        Superheroe heroeExistente = buscarHeroePorId(idHeroe);

        // Si no existe, no podemos actualizarlo
        if (heroeExistente == null) {
            return false;
        }

        //Validamos los atributos generales
        if (!validarDatosGenerales(datosActualizados.getNombreHeroico(),
                datosActualizados.getNombreReal(),
                datosActualizados.getEstado(),
                datosActualizados.getSalarioMensual())) {
            return false;
        }

        /*
        Verificamos que el objeto recibido sea del mismo
        ripo que el superhéroe que ya está registrado
         */
        if (heroeExistente instanceof HeroeTecnologico
                && datosActualizados instanceof HeroeTecnologico) {

            //Parceamos los datos actualizados a tipos héroe tecnológico
            HeroeTecnologico nuevosDatos = (HeroeTecnologico) datosActualizados;

            // Validamos los atributos específicos
            if (!esOpcionValida(nuevosDatos.getNivelTecnologia(),
                    "Básico", "Intermedio", "Avanzado")
                    || nuevosDatos.getCantidadGadgets() < 0) {

                // Retornamos falso por que no es valido los datos recibidos
                return false;

            }

        } else if (heroeExistente instanceof HeroeMutante
                && datosActualizados instanceof HeroeMutante) {
            //Parceamos los datos actualizados a tipo héroe mutante
            HeroeMutante nuevosDatos = (HeroeMutante) datosActualizados;

            // Validamos los atributos específicos
            if (!esOpcionValida(nuevosDatos.getTipoMutacion(),
                    "Genética", "Radioactiva", "Experimental")
                    || nuevosDatos.getControlPoder() < 1
                    || nuevosDatos.getControlPoder() > 100) {
                return false;
            }

        } else if (heroeExistente instanceof HeroeMagico
                && datosActualizados instanceof HeroeMagico) {
            //Parceamos los datos actualizados a tipo de héroe mágico
            HeroeMagico nuevosDatos = (HeroeMagico) datosActualizados;

            if (!esOpcionValida(nuevosDatos.getTipoMagia(),
                    "Elemental", "Oscura", "Sagrada")
                    || nuevosDatos.getCantHechizos() < 0) {
                return false;
            }
        } else {
            //No se permite cambiar el tipo de superhéroe
            return false;
        }

        // Actualizamos los atributos generales
        heroeExistente.setNombreHeroico(
                datosActualizados.getNombreHeroico().trim());
        heroeExistente.setNombreReal(
                datosActualizados.getNombreReal().trim());
        heroeExistente.setEstado(
                datosActualizados.getEstado().trim());
        heroeExistente.setSalarioMensual(
                datosActualizados.getSalarioMensual());

        //Actualizamos los atributos específicos
        if (heroeExistente instanceof HeroeTecnologico) {

            /* 
            Parcemos el héroe existente y el héroe con los datos actualizados
            al tipo de héroe correspondiente
             */
            HeroeTecnologico existente = (HeroeTecnologico) heroeExistente;

            HeroeTecnologico nuevos = (HeroeTecnologico) datosActualizados;

            //Se actualizan los atributos específicos según el tipos de héroe
            existente.setNivelTecnologia(nuevos.getNivelTecnologia());
            existente.setCantidadGadgets(nuevos.getCantidadGadgets());

        } else if (heroeExistente instanceof HeroeMutante) {

            /* 
            Parcemos el héroe existente y el héroe con los datos actualizados
            al tipo de héroe correspondiente
             */
            HeroeMutante existente = (HeroeMutante) heroeExistente;

            HeroeMutante nuevos = (HeroeMutante) datosActualizados;

            //Se actualizan los atributos específicos según el tipos de héroe
            existente.setTipoMutacion(nuevos.getTipoMutacion());
            existente.setControlPoder(nuevos.getControlPoder());

        } else if (heroeExistente instanceof HeroeMagico) {

            /* 
            Parcemos el héroe existente y el héroe con los datos actualizados
            al tipo de héroe correspondiente
             */
            HeroeMagico existente = (HeroeMagico) heroeExistente;

            HeroeMagico nuevos = (HeroeMagico) datosActualizados;

            //Se actualizan los atributos específicos según el tipos de héroe
            existente.setTipoMagia(nuevos.getTipoMagia());
            existente.setCantHechizos(nuevos.getCantHechizos());
        }

        //Retornamos True debido a que se actualizó correctamente
        return true;
    }

    /**
     * Cambiar el estado de un superhéroe registrado
     *
     * Busca el heroe mediante su identificador y verifica que el nuevo estado
     * corresponda a una opción permitida
     *
     * @param idHeroe ID del superhéroe
     *
     * @param nuevoEstado El nuevo estado del superhéroes
     *
     * @return true si el estado se cambió con éxito; false en caso contrario
     */
    public boolean cambiarEstadoHeroe(String idHeroe, String nuevoEstado) {
        // Validamos el nuevo estado
        if (!esOpcionValida(nuevoEstado,
                "Disponible", "En misión", "Recuperación")) {
            return false;
        }

        // Buscamos el superhéroe mediante su ID
        Superheroe heroe = buscarHeroePorId(idHeroe);

        // Comprobamos que el superhéroe exista
        if (heroe == null) {
            return false;
        }

        // Actualizamos únicamente el estado
        heroe.setEstado(nuevoEstado.trim());

        // Se actualiza el estado con éxito
        return true;
    }

    /**
     * Elimina un superhéroe de la colección correspondiente
     *
     * Busca el registro mediante su ID y determina a cual colección pertenece
     * antes de eliminarlo
     *
     * @param idHeroe ID del superhéroe que se eliminará
     *
     * @return true si se eliminó correctamente; false si no existe el ID
     * buscado
     */
    public boolean eliminarHeroe(String idHeroe) {
        // Validamos que el identificador no esté vacío
        if (idHeroe == null || idHeroe.trim().isEmpty()) {
            return false;
        }

        //Buscamos el superhéroe en las 3 colecciones
        Superheroe heroe = buscarHeroePorId(idHeroe);

        // Comprobamos que el registro existe
        if (heroe == null) {
            return false;
        }

        // Identificamos la colección correspondiente y eliminamos el superhéroe
        if (heroe instanceof HeroeTecnologico) {
            //Retorna true una vez eliminado el superhéroe parceado
            return heroesTecnologicos.remove((HeroeTecnologico) heroe);
        } else if (heroe instanceof HeroeMutante) {
            //Retorna true una vez eliminado el superhéroe parceado
            return heroesMutantes.remove((HeroeMutante) heroe);
        } else if (heroe instanceof HeroeMagico) {
            //Retorna true una vez eliminado el superhéroe parceado
            return heroesMagicos.remove((HeroeMagico) heroe);
        }

        return false;
    }

    /**
     * Guarda los superhéroes registrados en 3 archivos independientes según su
     * tipo
     *
     * Los archivos conservan los identificadores y todos los atributos
     * generales y específicos de cada héroe
     *
     * @return true si los 3 archivos se guardaron correctamente
     */
    public boolean guardarSuperheroes() {
        // Intentamos crear los 3 archivos .txt
        try (
                // Archivo HeroeTecnologico.txt
                BufferedWriter escritorTecnológico = Files.newBufferedWriter(
                        Paths.get("HeroeTecnologico.txt"),
                        StandardCharsets.UTF_8); // Archivo HeroeMutante.txt
                 BufferedWriter escritorMutante = Files.newBufferedWriter(
                        Paths.get("HeroeMutante.txt"),
                        StandardCharsets.UTF_8); // Archivo HeroeMagico.txt
                 BufferedWriter escritorMagico = Files.newBufferedWriter(
                        Paths.get("HeroeMagico.txt"),
                        StandardCharsets.UTF_8);) {
            //Guardamos los héroes tecnológicos
            for (HeroeTecnologico heroe : heroesTecnologicos) {

                // Creamos el string linea con el formato correspondiente
                String linea = String.join(";", heroe.getIdHeroe(),
                        heroe.getNombreHeroico(),
                        heroe.getNombreReal(),
                        heroe.getEstado(),
                        String.valueOf(heroe.getSalarioMensual()),
                        heroe.getNivelTecnologia(),
                        String.valueOf(heroe.getCantidadGadgets())
                );

                // Agregamos al archivo .txt correspondiente
                escritorTecnológico.write(linea);
                escritorTecnológico.newLine();
            }

            //Guardamos los héroes Mutantes
            for (HeroeMutante heroe : heroesMutantes) {

                // Creamos el string linea con el formato correspondiente
                String linea = String.join(";",
                        heroe.getIdHeroe(),
                        heroe.getNombreHeroico(),
                        heroe.getNombreReal(),
                        heroe.getEstado(),
                        String.valueOf(heroe.getSalarioMensual()),
                        heroe.getTipoMutacion(),
                        String.valueOf(heroe.getControlPoder())
                );

                // Agregamos al archivo .txt correspondiente
                escritorMutante.write(linea);
                escritorMutante.newLine();
            }

            //Guardamos los héroes Mutantes
            for (HeroeMagico heroe : heroesMagicos) {

                // Creamos el string linea con el formato correspondiente
                String linea = String.join(";",
                        heroe.getIdHeroe(),
                        heroe.getNombreHeroico(),
                        heroe.getNombreReal(),
                        heroe.getEstado(),
                        String.valueOf(heroe.getSalarioMensual()),
                        heroe.getTipoMagia(),
                        String.valueOf(heroe.getCantHechizos())
                );

                // Agregamos al archivo .txt correspondiente
                escritorMagico.write(linea);
                escritorMagico.newLine();
            }

            //Se guarda correctamente dentro de sus archivos respectivos
            return true;

        } catch (IOException e) {
            // En caso de error se muestra el mensaje 
            System.err.println("Error al guardar los archivos de superhéroes: "
                    + e.getMessage());
            return false;
        }
    }

    /**
     * Carga los superhéroes desde los 3 archivos .txt
     *
     * Reconstruye cada objeto según su tipo y conserva el identificador
     * original
     *
     * Utiliza colecciones temporales para evitar modificar los registros
     * actuales en caso de error durante la carga
     *
     * @return true si la carga se realiza correctamente; false en caso
     * contrario
     */
    public boolean cargarSuperheroes() {
        // Colecciones remporales para recuperar registros
        ArrayList<HeroeTecnologico> tecnologicos = new ArrayList<>();
        ArrayList<HeroeMutante> mutantes = new ArrayList<>();
        ArrayList<HeroeMagico> magicos = new ArrayList<>();

        //Controlamos que no existan identificadores repetidos
        Set<String> identificadores = new HashSet<>();

        // Consevamos el número de ID más alto encontrado
        int mayoId = 0;
        try (
                BufferedReader lectorTecnologico = Files.newBufferedReader(
                        Paths.get("HeroeTecnologico.txt"),
                        StandardCharsets.UTF_8); BufferedReader lectorMutante = Files.newBufferedReader(
                        Paths.get("HeroeMutante.txt"),
                        StandardCharsets.UTF_8); BufferedReader lectorMagico = Files.newBufferedReader(
                        Paths.get("HeroeMagico.txt"),
                        StandardCharsets.UTF_8);) {

            String linea;
            int numeroLinea;

            // Cargar héroes tecnológicos
            numeroLinea = 0;
            while ((linea = lectorTecnologico.readLine()) != null) {
                // Aumentamos el número de linea en 1
                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    // Continuamos en caso de que la linea esté vacía
                    continue;
                }

                // Dividimos la linea por su separador ";", almacenamos en datos
                String[] datos = linea.split(";", -1);

                /* 
                Si es diferente a la cantidad de datos esperados
                lanza un error, para este caso 7
                 */
                if (datos.length != 7) {
                    throw new IllegalArgumentException(
                            "Error en HeroeTecnologico.txt, línea "
                            + numeroLinea + ": cantidad incorrecta de campos.");
                }

                //Datos generales
                String id = datos[0].trim().toUpperCase();
                String nombreHeroico = datos[1].trim();
                String nombreReal = datos[2].trim();
                String estado = datos[3].trim();
                double salario = Double.parseDouble(datos[4].trim());

                //Datos específicos
                String nivel = datos[5].trim();
                int gadgets = Integer.parseInt(datos[6].trim());

                // Validamos el identificador
                mayoId = Math.max(mayoId, validarIdentificadorCargado(
                        id, identificadores));

                // Validamos datos generales
                if (!validarDatosGenerales(
                        nombreHeroico, nombreReal, estado, salario)) {
                    // Lanzamos un error por datos generales inválidos
                    throw new IllegalArgumentException(
                            "Datos generales inválidos en HeroeTecnologico.txt, línea "
                            + numeroLinea);
                }

                // Validamos los datos específicos
                if (!esOpcionValida(nivel,
                        "Básico", "Intermedio", "Avanzado")
                        || gadgets < 0) {
                    // Lanzamos un error por datos específicos inválidos
                    throw new IllegalArgumentException(
                            "Datos tecnológicos inválidos en la línea "
                            + numeroLinea);
                }

                // Reconstruimos el objeto conservando su ID original
                HeroeTecnologico heroe = new HeroeTecnologico(
                        nivel,
                        gadgets,
                        id,
                        nombreHeroico,
                        nombreReal,
                        estado,
                        salario);

                tecnologicos.add(heroe);

            }

            // Cargar héroes mutantes
            numeroLinea = 0;
            while ((linea = lectorMutante.readLine()) != null) {
                // Aumentamos el número de linea en 1
                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    // Continuamos en caso de que la linea esté vacía
                    continue;
                }

                // Dividimos la linea por su separador ";", almacenamos en datos
                String[] datos = linea.split(";", -1);

                /* 
                Si es diferente a la cantidad de datos esperados
                lanza un error, para este caso 7
                 */
                if (datos.length != 7) {
                    throw new IllegalArgumentException(
                            "Error en HeroeMutante.txt, línea "
                            + numeroLinea + ": cantidad incorrecta de campos.");
                }

                //Datos generales
                String id = datos[0].trim().toUpperCase();
                String nombreHeroico = datos[1].trim();
                String nombreReal = datos[2].trim();
                String estado = datos[3].trim();
                double salario = Double.parseDouble(datos[4].trim());

                //Datos específicos
                String mutacion = datos[5].trim();
                int control = Integer.parseInt(datos[6].trim());

                // Validamos el identificador
                mayoId = Math.max(mayoId, validarIdentificadorCargado(
                        id, identificadores));

                // Validamos datos generales
                if (!validarDatosGenerales(
                        nombreHeroico, nombreReal, estado, salario)) {
                    // Lanzamos un error por datos generales inválidos
                    throw new IllegalArgumentException(
                            "Datos generales inválidos en HeroeMutante.txt, línea "
                            + numeroLinea);
                }

                // Validamos los datos específicos
                if (!esOpcionValida(mutacion,
                        "Genética", "Radioactiva", "Experimental")
                        || control < 1 || control > 100) {
                    // Lanzamos un error por datos específicos inválidos
                    throw new IllegalArgumentException(
                            "Datos de mutación inválidos en la línea "
                            + numeroLinea);
                }

                // Reconstruimos el objeto conservando su ID original
                HeroeMutante heroe = new HeroeMutante(
                        mutacion,
                        control,
                        id,
                        nombreHeroico,
                        nombreReal,
                        estado,
                        salario);

                mutantes.add(heroe);

            }

            // Cargar héroes mágicos
            numeroLinea = 0;
            while ((linea = lectorMagico.readLine()) != null) {
                // Aumentamos el número de linea en 1
                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    // Continuamos en caso de que la linea esté vacía
                    continue;
                }

                // Dividimos la linea por su separador ";", almacenamos en datos
                String[] datos = linea.split(";", -1);

                /* 
                Si es diferente a la cantidad de datos esperados
                lanza un error, para este caso 7
                 */
                if (datos.length != 7) {
                    throw new IllegalArgumentException(
                            "Error en HeroeMagico.txt, línea "
                            + numeroLinea + ": cantidad incorrecta de campos.");
                }

                //Datos generales
                String id = datos[0].trim().toUpperCase();
                String nombreHeroico = datos[1].trim();
                String nombreReal = datos[2].trim();
                String estado = datos[3].trim();
                double salario = Double.parseDouble(datos[4].trim());

                //Datos específicos
                String magia = datos[5].trim();
                int hechizos = Integer.parseInt(datos[6].trim());

                // Validamos el identificador
                mayoId = Math.max(mayoId, validarIdentificadorCargado(
                        id, identificadores));

                // Validamos datos generales
                if (!validarDatosGenerales(
                        nombreHeroico, nombreReal, estado, salario)) {
                    // Lanzamos un error por datos generales inválidos
                    throw new IllegalArgumentException(
                            "Datos generales inválidos en HeroeMagico.txt, línea "
                            + numeroLinea);
                }

                // Validamos los datos específicos
                if (!esOpcionValida(magia,
                        "Elemental", "Oscura", "Sagrada")
                        || hechizos < 0) {
                    // Lanzamos un error por datos específicos inválidos
                    throw new IllegalArgumentException(
                            "Datos mágicos inválidos en la línea "
                            + numeroLinea);
                }

                // Reconstruimos el objeto conservando su ID original
                HeroeMagico heroe = new HeroeMagico(
                        magia,
                        hechizos,
                        id,
                        nombreHeroico,
                        nombreReal,
                        estado,
                        salario);

                magicos.add(heroe);

            }

            //Actualizamos las colecciones
            heroesTecnologicos.clear();
            heroesTecnologicos.addAll(tecnologicos);

            heroesMutantes.clear();
            heroesMutantes.addAll(mutantes);

            heroesMagicos.clear();
            heroesMagicos.addAll(magicos);

            //Actualizamos el contador para evitar duplicar ID en héroes
            Superheroe.setContadorId(Math.max(Superheroe.getContadorId(), mayoId));

            //Se realiza la carga de manera exitosa
            return true;

        } catch (Exception e) {
            System.err.println("Error al cargar los archivos de superhéroes: "
                    + e.getMessage());

            return false;
        }
    }

    /**
     * Valida el formato y la unicidad de un identificador recuperado
     *
     * @param id ID del héroe leído desde el archivo
     *
     * @param identificadores colección de IDs ya procesados
     *
     * @return Número consecutivo correspondiente al identificador
     */
    private int validarIdentificadorCargado(String id,
            Set<String> identificadores) {
        if (id == null || !id.matches("HER-\\d{2,}")) {
            throw new IllegalArgumentException("Indentificador inválido: " + id);
        }

        if (!identificadores.add(id)) {
            throw new IllegalArgumentException("Identificador dublicado: " + id);
        }

        //Retornamos el número consecutivo del identificador
        return Integer.parseInt(id.substring(4));
    }

}
