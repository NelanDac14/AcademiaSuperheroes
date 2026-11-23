package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.EquipoRescate;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.Superheroe;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase encargada de gestionar los equipos de superhéroes registrados en la
 * academia
 *
 * @author Nelson Andrés Acuña Cambronero
 *
 * @version 1.0
 */
public class GestionEquipos {

    // Colección que almacena los equipos registrados
    private ArrayList<EquipoRescate> equipos;

    // Referencia al administrador de superhéroes
    private GestionSuperheroes gestionSuperheroes;

    /**
     * Constructor de la clase
     *
     * @param gestionSuperheroes Administrador de superheroes
     */
    public GestionEquipos(GestionSuperheroes gestionSuperheroes) {
        this.equipos = new ArrayList<>();
        this.gestionSuperheroes = gestionSuperheroes;
    }

    /**
     * Registra un nuevo equipo
     *
     * Busca lso superhéroes mediante sus identificadores, verifica que existan
     * y que no estén repetidos. Posteriormente crea el equipo y calcula su
     * costo operativo
     *
     * @param nombreEquipo Nombre del equipo
     *
     * @param tipoEmergencia Tipo de emergencia asignada
     *
     * @param nivelAmenaza Nivel de amenaza que combate
     *
     * @param ciudadAsignada Ciudad donde operará
     *
     * @param usoTecnologiaEspecial Indica si utiliza tecnología especial
     *
     * @param idsSuperheroes IDs de los superhéroes que lo componen
     *
     * @return true si el equipo fue registrado correctamente; false si ocurre
     * lo contrario
     */
    public boolean agregarEquipo(String nombreEquipo,
            String tipoEmergencia,
            String nivelAmenaza,
            String ciudadAsignada,
            String usoTecnologiaEspecial,
            List<String> idsSuperheroes) {

        // Validamos los datos generales del equipo
        if (!validarDatosEquipo(nombreEquipo, tipoEmergencia,
                nivelAmenaza, ciudadAsignada, usoTecnologiaEspecial)) {
            return false;
        }

        // Comprobamos que exista una lista de superhéroes
        if (idsSuperheroes == null || idsSuperheroes.isEmpty()
                || gestionSuperheroes == null) {
            return false;
        }

        // Lista temporal con los objetos que integraran el equipo
        ArrayList<Superheroe> integrantes = new ArrayList<>();

        // Evitamos asignar 2 veces el mismo superhéroe
        Set<String> identificadores = new HashSet<>();

        for (String id : idsSuperheroes) {
            if (id == null || id.trim().isEmpty()) {
                return false;
            }

            // Un vez validado se reasigna a otro string
            String idValidado = id.trim().toUpperCase();

            if (!identificadores.add(idValidado)) {
                return false;
            }

            // Buscamos el superhéroe en el sistema
            Superheroe heroe = gestionSuperheroes.buscarHeroePorId(idValidado);

            // Si no existe, no podemos registrar el equipo
            if (heroe == null) {
                return false;
            }

            // Se agrega el héroe a integrantes
            integrantes.add(heroe);
        } // Fin del for

        // Creamos el equipo con los datos proporcionados
        EquipoRescate nuevoEquipo = new EquipoRescate("",
                nombreEquipo.trim(),
                tipoEmergencia.trim(),
                nivelAmenaza.trim(),
                ciudadAsignada.trim(),
                usoTecnologiaEspecial.trim(),
                integrantes,
                0.0);

        //Generamos automáticamente el ID
        nuevoEquipo.setIdEquipo(nuevoEquipo.generadorIdEquipo());

        // Calculamos el costo operativo del equipo
        nuevoEquipo.calcularCostoOperativo();

        // Registramos el equipo en la colección de equipos
        equipos.add(nuevoEquipo);

        return true;
    }

    /**
     * Valida los datos generales del equipo de rescate
     *
     * @return true cuando todos los datos son válidos
     */
    private boolean validarDatosEquipo(String nombreEquipo,
            String tipoEmergencia,
            String nivelAmenaza,
            String ciudadAsignada,
            String usoTecnologiaEspecial) {
        return nombreEquipo != null
                && nombreEquipo.trim().length() >= 3
                && esOpcionValida(tipoEmergencia,
                        "Desastres naturales",
                        "Invasión alienígena",
                        "Amenaza nuclear",
                        "Villano de alto riesgo")
                && esOpcionValida(nivelAmenaza,
                        "Baja",
                        "Media",
                        "Alta")
                && ciudadAsignada != null
                && ciudadAsignada.trim().length() >= 3
                && esOpcionValida(usoTecnologiaEspecial,
                        "Sí", "No");
    }

    /**
     * Comprueba si un valor coincide con alguna de las opciones permitidas
     *
     * @param valor Dato que se va a validar
     *
     * @param opciones Opciones correctas
     *
     * @return true si el valor es válido; false si no es correcto
     */
    private boolean esOpcionValida(String valor, String... opciones) {
        if (valor == null) {
            return false;
        }

        // Se recorre las opciones y se compara con el valor elegido
        for (String opcion : opciones) {
            if (opcion.equalsIgnoreCase(valor.trim())) {

                // Se valida el dato
                return true;
            }
        }

        //Retorna false en caso de no cumplir con las validaciones
        return false;
    }

    /**
     * Busca un equipo de rescate mediante su ID
     *
     * Recorre la colección de equipos hasta encontrar un ID correspondiente a
     * un equipo
     *
     * @param idEquipo Identificador del equipo que se desea buscar
     *
     * @return El equipo encontrado o null si no existe
     */
    public EquipoRescate buscarEquipoPorId(String idEquipo) {
        // Validamos que el ID no esté vacío
        if (idEquipo == null || idEquipo.trim().isEmpty()) {
            return null;
        }

        // Transferimos el ID validado
        String idBuscado = idEquipo.trim();

        // Recorremos los equipos registrados
        for (EquipoRescate equipo : equipos) {
            // Comparamos ID de equipos
            if (equipo.getIdEquipo() != null
                    && equipo.getIdEquipo().equalsIgnoreCase(idBuscado)) {
                return equipo;
            }
        }

        // Si no se encontrara un equipo con el ID, devolvemos null
        return null;
    }

    /**
     * Actualiza la información de un equipo de rescate
     *
     * Busca el equipo por su ID, valida los nuevos datos y actualiza su lista
     * de integrantes
     *
     * @param idEquipo ID del equipo a actualizar
     *
     * @param nombreEquipo Nuevo nombre del equipo.
     *
     * @param tipoEmergencia Nuevo tipo de emergencia.
     *
     * @param nivelAmenaza Nuevo nivel de amenaza.
     *
     * @param ciudadAsignada Nueva ciudad asignada.
     *
     * @param usoTecnologiaEspecial Indica si utiliza tecnología especial.
     *
     * @param idsSuperheroes Identificadores de los nuevos integrantes.
     *
     * @return true si la actualización fue exitosa.
     */
    public boolean actualizarEquipo(String idEquipo,
            String nombreEquipo,
            String tipoEmergencia,
            String nivelAmenaza,
            String ciudadAsignada,
            String usoTecnologiaEspecial,
            List<String> idsSuperheroes) {
        // Buscamos el equipo que se desea actualizar
        EquipoRescate equipo = buscarEquipoPorId(idEquipo);

        // Validamos que el equipo no esté vacío
        if (equipo == null) {
            return false;
        }

        // Validamos los nuevos datos del equipo
        if (!validarDatosEquipo(nombreEquipo, tipoEmergencia,
                nivelAmenaza, ciudadAsignada, usoTecnologiaEspecial)) {
            return false;
        }

        // Se valida que se haya agregado integrantes
        if (idsSuperheroes == null || idsSuperheroes.isEmpty()
                || gestionSuperheroes == null) {
            return false;
        }

        //Creamos una lista temporal con los nuevos integrantes
        ArrayList<Superheroe> nuevosIntegrantes = new ArrayList<>();

        // Evitamos el duplicado de superhéroes dentro del equipo
        Set<String> identificadores = new HashSet<>();

        for (String id : idsSuperheroes) {
            if (id == null || id.trim().isEmpty()) {
                return false;
            }

            // Se transfiere el id una vez validado
            String idValidado = id.trim().toUpperCase();

            //Se validad si este no se agrega a identificadores
            if (!identificadores.add(idValidado)) {
                return false;
            }

            // Buscamos el superhéroe existente
            Superheroe heroe = gestionSuperheroes.buscarHeroePorId(idValidado);

            if (heroe == null) {
                return false;
            }

            //Agregamos el nuevo héroe
            nuevosIntegrantes.add(heroe);
        }

        // Actualizamos los datos del equipo
        equipo.setNombreEquipo(nombreEquipo.trim());
        equipo.setTipoEmergencia(tipoEmergencia.trim());
        equipo.setNivelAmenaza(nivelAmenaza.trim());
        equipo.setCiudadAsignada(ciudadAsignada.trim());
        equipo.setUsoTecnologiaEspecial(usoTecnologiaEspecial.trim());
        //Actualizamos la lista de superhéroes del equipo
        equipo.setListaSuperheroes(nuevosIntegrantes);
        //Recalculamos el costo operativo del equipo
        equipo.calcularCostoOperativo();

        //El equipo se actualiza correctamente
        return true;
    }

    /**
     * Elimina un equipo de rescate mediante su identificador
     *
     * Busca el equipo registrado y se elimina de la colección si este existe
     * dentro de ella
     *
     * @param idEquipo ID del equipo que se desea eliminar
     *
     * @return true si se elimina el equipo correctamente; false si ocurre lo
     * contrario.
     */
    public boolean eliminarEquipo(String idEquipo) {
        // Validamos que el ID no esté vacío
        if (idEquipo == null || idEquipo.trim().isEmpty()) {
            return false;
        }

        // Buscamosel equipo que se desea eliminar
        EquipoRescate equipo = buscarEquipoPorId(idEquipo);

        // Comprobamos que el equipo exista
        if (equipo == null) {
            return false;
        }

        // Eliminamos el equipo de la coleccion
        return equipos.remove(equipo);
    }

    /**
     * Guarda todos los equipos registrados en el archivo Equipos.txt
     *
     * Almacena los datos del equipo y todos sus integrantes
     *
     * @return true si el archivo se crea y se guarda correctamente
     */
    public boolean guardarEquipos() {
        try (BufferedWriter escritor = Files.newBufferedWriter(Paths.get(
                "Equipos.txt"), StandardCharsets.UTF_8)) {

            // Recorremos todos los equipos registrados
            for (EquipoRescate equipo : equipos) {
                //Validamos que el equipo tenga su lista de integrantes
                if (equipo == null || equipo.getListaSuperheroes() == null) {
                    return false;
                }

                // Recuperamos los ID de los integrantes
                ArrayList<String> idsSuperheroes = new ArrayList<>();

                // Recorremos la lista de superhéroes del equipo para validar
                for (Superheroe heroe : equipo.getListaSuperheroes()) {
                    if (heroe == null || heroe.getIdHeroe() == null
                            || heroe.getIdHeroe().trim().isEmpty()) {
                        return false;
                    }
                    // Agregamos a la lista de IDs
                    idsSuperheroes.add(heroe.getIdHeroe());
                }

                //Construimos la línea con todos los datos del equipo
                String linea = String.join(";",
                        equipo.getIdEquipo(),
                        equipo.getNombreEquipo(),
                        equipo.getTipoEmergencia(),
                        equipo.getNivelAmenaza(),
                        equipo.getCiudadAsignada(),
                        equipo.getUsoTecnologiaEspecial(),
                        String.valueOf(equipo.getCostoOperativo()),
                        String.join(",", idsSuperheroes));

                // Escribimos los datos en el archivo Equipos.txt
                escritor.write(linea);
                escritor.newLine();
            }
            // Retorna true por agregar correctamente los datos
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar los equipos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carga los equipos de rescate almacenanos en Equipos.txt
     *
     * Reconstruye cada equipo y recupera sus integrantes mediante los IDs
     * almacenados
     *
     * Utiliza una colección temporal para evitar modificaciones erróneas a los
     * registros actuales
     *
     * @return true si la carga se realiza correctamente; en caso contrario
     * retorna false
     */
    public boolean cargarEquipos() {
        // Colección temporal para almacenar los equipos
        ArrayList<EquipoRescate> equiposCargados = new ArrayList<>();

        // Controlamos que no se dupliquen los ID de los equipos
        Set<String> IdEquipos = new HashSet<>();

        // Se conserva el consecutivo más alto
        int mayorId = 0;

        try (BufferedReader lector = Files.newBufferedReader(Paths.get("Equipos.txt"),
                StandardCharsets.UTF_8)) {
            String linea;
            int numeroLinea = 0;

            // Leemos el archivo línea por línea
            while ((linea = lector.readLine()) != null) {
                // Aumentamos el número de línea en 1
                numeroLinea++;

                // Validamos que la línea no esté vacía
                if (linea.trim().isEmpty()) {
                    // Continueamos con la siguiente si está vacía
                    continue;
                }

                // Separamos los 8 campos del equipo
                String[] datos = linea.split(";", -1);

                //Validamos los 8 datos del equipo
                if (datos.length != 8) {
                    //Lanzamos una exepción
                    throw new IllegalArgumentException(
                            "Cantidad incorrecta de campos en Equipos.txt, línea "
                            + numeroLinea);
                }

                // Recuperamos los datos del equipo
                String idEquipo = datos[0].trim().toUpperCase();
                String nombreEquipo = datos[1].trim();
                String tipoEmergencia = datos[2].trim();
                String nivelAmenaza = datos[3].trim();
                String ciudadAsignada = datos[4].trim();
                String usoTecnologia = datos[5].trim();

                // Recuperamos y validamos el costo operativo que se guardó
                double costoOperativo = Double.parseDouble(datos[6].trim());

                if (!Double.isFinite(costoOperativo) || costoOperativo < 0) {
                    // Se lanza una exepción
                    throw new IllegalArgumentException(
                            "Costo operativo inválido en la línea "
                            + numeroLinea);
                }

                // Validamos el ID y evitamos duplicados
                mayorId = Math.max(mayorId, validarIdentificadorEquipoCargado(idEquipo,
                        IdEquipos));

                // Validamos los datos del equipo cargado
                if (!validarDatosEquipo(nombreEquipo, tipoEmergencia,
                        nivelAmenaza, ciudadAsignada, usoTecnologia)) {
                    // Lanzamos la exepción
                    throw new IllegalArgumentException(
                            "Datos inválidos en Equipos.txt, línea "
                            + numeroLinea);
                }

                // Recuperamos los ID de los integrantes
                if (datos[7].trim().isEmpty()) {
                    // Lanzamos la exepción si está vacío
                    throw new IllegalArgumentException(
                            "El equipo no tiene integrantes en la línea "
                            + numeroLinea);
                }

                //Divimos el String obtenido del archivo
                String[] idsSuperheroes = datos[7].split(",", -1);

                // Lista temporal de superhéroes del equipo
                ArrayList<Superheroe> integrantes = new ArrayList<>();

                // Evitamos la repetición de superhéroes en el mismo equipo
                Set<String> idsIntegrantes = new HashSet<>();

                for (String idHeroe : idsSuperheroes) {
                    String idValidado = idHeroe.trim().toUpperCase();

                    if (idValidado.isEmpty() || !idsIntegrantes.add(idValidado)) {
                        // Lanzamos la exepción
                        throw new IllegalArgumentException(
                                "Identificador de héroe vacío o repetido "
                                + "en la línea " + numeroLinea);
                    }

                    // Recuperamos el héroe original desde Gestión de Superhéroes
                    Superheroe heroe = gestionSuperheroes.buscarHeroePorId(idValidado);

                    //Validamos que el heroe no sea nulo
                    if (heroe == null) {
                        // Lanzamos exepcion
                        throw new IllegalArgumentException(
                                "No existe el superhéroe " + idValidado
                                + " utilizado por el equipo de la línea "
                                + numeroLinea);
                    }

                    // Agregamos a la lista de integrantes los héroes no repetidos
                    integrantes.add(heroe);
                }

                // Reconstruimos el equipo conservando su identificador.
                EquipoRescate equipo = new EquipoRescate(
                        idEquipo,
                        nombreEquipo,
                        tipoEmergencia,
                        nivelAmenaza,
                        ciudadAsignada,
                        usoTecnologia,
                        integrantes,
                        costoOperativo);

                // Recalculamos el costo operativo de los datos actuales
                equipo.calcularCostoOperativo();

                //Agregamos el equipo a la colección temporal
                equiposCargados.add(equipo);
            }

            //Sustituimos los equipos actuales por los obtenidos del archivo
            equipos.clear();
            equipos.addAll(equiposCargados);

            //Actualizamos el contador ID para evitar repeticiones
            EquipoRescate.setContadorId(Math.max(EquipoRescate.getContadorId(),
                    mayorId));

            //Retorna true por exitosa recuperación de datos
            return true;

        } catch (IOException | IllegalArgumentException e) {

            // Lanzamos la exepción
            System.err.println(
                    "Error al cargar los equipos: " + e.getMessage());

            return false;
        }
    }

    /**
     * Obtiene todos los equipos registrados en la academia.
     *
     * Recorre la colección de equipos y devuelve una nueva lista con los
     * registros encontrados.
     *
     * @return Lista de todos los equipos registrados.
     */
    public ArrayList<EquipoRescate> obtenerTodosLosEquipos() {

        // Lista que contendrá los equipos encontrados.
        ArrayList<EquipoRescate> todosLosEquipos = new ArrayList<>();

        // Recorremos los equipos registrados.
        for (EquipoRescate equipo : equipos) {

            if (equipo != null) {
                todosLosEquipos.add(equipo);
            }
        }

        return todosLosEquipos;
    }

    /**
     * Busca el equipo que presenta el mayor costo operativo.
     *
     * Recorre todos los equipos registrados y compara sus respectivos costos
     * operativos.
     *
     * @return Equipo con mayor costo operativo o null si no existen equipos
     * registrados.
     */
    public EquipoRescate obtenerEquipoMayorCosto() {

        // Variable que conservará el equipo de mayor costo.
        EquipoRescate equipoMayorCosto = null;

        // Recorremos todos los equipos.
        for (EquipoRescate equipo : equipos) {

            if (equipo == null) {
                continue;
            }

            // Comparamos los costos operativos.
            if (equipoMayorCosto == null
                    || equipo.getCostoOperativo()
                    > equipoMayorCosto.getCostoOperativo()) {

                equipoMayorCosto = equipo;
            }
        }

        return equipoMayorCosto;
    }

    /**
     * Obtiene los equipos correspondientes a un tipo específico de emergencia.
     *
     * Recorre la colección y compara el tipo de emergencia de cada equipo con
     * el criterio proporcionado.
     *
     * @param tipoEmergencia Tipo de emergencia que se desea consultar.
     * @return Lista de equipos que coinciden con el criterio.
     */
    public ArrayList<EquipoRescate> obtenerEquiposPorTipoEmergencia(
            String tipoEmergencia) {

        // Lista que almacenará los resultados.
        ArrayList<EquipoRescate> equiposEncontrados = new ArrayList<>();

        // Validamos el criterio de búsqueda.
        if (tipoEmergencia == null
                || tipoEmergencia.trim().isEmpty()) {

            return equiposEncontrados;
        }

        // Normalizamos el criterio recibido.
        String tipoBuscado = tipoEmergencia.trim();

        // Recorremos todos los equipos registrados.
        for (EquipoRescate equipo : equipos) {

            if (equipo == null
                    || equipo.getTipoEmergencia() == null) {
                continue;
            }

            // Comparamos el tipo de emergencia.
            if (equipo.getTipoEmergencia()
                    .equalsIgnoreCase(tipoBuscado)) {

                equiposEncontrados.add(equipo);
            }
        }

        return equiposEncontrados;
    }

    /**
     *
     */
    private int validarIdentificadorEquipoCargado(String id,
            Set<String> identificadores) {
        // Validamos el formato y que no sea nulo
        if (id == null || !id.matches("EQU-\\d{2,}")) {
            // Se lanza la exepción
            throw new IllegalArgumentException(
                    "Identificador de equipo inválido: " + id);
        }

        // Si no se agrega es por que está duplicado
        if (!identificadores.add(id)) {
            // Se lanza la exepción
            throw new IllegalArgumentException(
                    "Identificador de equipo duplicado: " + id);
        }

        //Retornamos el número del ID
        return Integer.parseInt(id.substring(4));
    }

}
