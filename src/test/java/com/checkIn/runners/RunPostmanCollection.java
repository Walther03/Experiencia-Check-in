package com.checkIn.runners;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RunPostmanCollection {

    /**
     * Ejecuta la colección de Postman con Newman y exporta el entorno actualizado
     * para capturar variables como reloc y lastName.
     */
    public static JSONObject runCollectionAndCaptureVariables(String collectionPath, String environmentPath) throws Exception {
        String newmanCmd = System.getenv("NEWMAN_PATH");
        if (newmanCmd == null || newmanCmd.isEmpty()) {
            newmanCmd = "newman"; // confía en el PATH
        }
        List<String> command = new ArrayList<>();
        command.add(newmanCmd);
        command.add("run");
        command.add(collectionPath);

        if (environmentPath != null && !environmentPath.isEmpty()) {
            command.add("--environment");
            command.add(environmentPath);
        }

        // Exportar entorno actualizado a env_out.json
        String exportedEnv = "env_out.json";
        command.add("--export-environment");
        command.add(exportedEnv);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Imprimir salida de Newman en consola
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        System.out.println("Exit code: " + exitCode);

        // Leer el archivo de entorno exportado
        String content = new String(Files.readAllBytes(Paths.get(exportedEnv)));
        JSONObject envJson = new JSONObject(content);
        JSONArray values = envJson.getJSONArray("values");

        // Capturar las variables que nos interesan
        String reloc = null;
        String lastName = null;
        for (int i = 0; i < values.length(); i++) {
            JSONObject obj = values.getJSONObject(i);
            String key = obj.getString("key");
            String value = obj.optString("value", null);
            if ("reloc".equals(key)) reloc = value;
            if ("lastName".equals(key)) lastName = value;
        }

        // Guardar en un JSON para devolverlo
        JSONObject result = new JSONObject();
        result.put("reloc", reloc);
        result.put("lastName", lastName);

        return result;
    }

    public static void EjecutarColeccion(String[] args) throws Exception {
        String collectionPath = "/Users/walther/IdeaProjects/Operaciones-Kiosko/src/test/resources/Postman/Reservas-Dom.json";
        String environmentPath = "/Users/walther/IdeaProjects/Operaciones-Kiosko/src/test/resources/Postman/QA_environment.json";

        JSONObject capturedVariables = runCollectionAndCaptureVariables(collectionPath, environmentPath);
        System.out.println("Variables capturadas: " + capturedVariables.toString(4));

        // Ahora puedes usar capturedVariables.getString("reloc") o capturedVariables.getString("lastName")
        String reloc = capturedVariables.getString("reloc");
        String lastName = capturedVariables.getString("lastName");

        System.out.println("Reloc: " + reloc);
        System.out.println("LastName: " + lastName);
    }
}
