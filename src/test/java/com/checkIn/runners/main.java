package com.checkIn.runners;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class main {


        public static void runCollection(String collectionPath, String environmentPath, Map<String, String> vars) throws Exception {
            List<String> command = new ArrayList<>();
            command.add("newman");
            command.add("run");
            command.add(collectionPath);
            command.add("--environment");
            command.add(environmentPath);

            for (Map.Entry<String, String> entry : vars.entrySet()) {
                command.add("--env-var");
                command.add(entry.getKey() + "=" + entry.getValue());
            }

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);

        }
    }
