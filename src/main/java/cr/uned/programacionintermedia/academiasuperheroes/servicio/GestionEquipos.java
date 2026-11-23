package cr.uned.programacionintermedia.academiasuperheroes.servicio;

import cr.uned.programacionintermedia.academiasuperheroes.modelo.EquipoRescate;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.Superheroe;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        for (String id : identificadores) {
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
     * 
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

}