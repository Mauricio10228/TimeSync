package modelo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DB_Backup {

    public static void realizarBackup() {
        String databaseName = "timesyncy";
        String user = "root";
        String password = ""; // vacío si no tienes
        String backupPath = "C:\\backups\\";

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String backupFile = backupPath + databaseName + "_backup_" + timeStamp + ".sql";

        String mysqldumpPath = "C:\\xampp\\mysql\\bin\\mysqldump.exe";

        try {
            // Construimos el comando
            ProcessBuilder pb;
            if (password.isEmpty()) {
                pb = new ProcessBuilder(
                        mysqldumpPath,
                        "-u" + user,
                        databaseName,
                        "-r", backupFile
                );
            } else {
                pb = new ProcessBuilder(
                        mysqldumpPath,
                        "-u" + user,
                        "-p" + password,
                        databaseName,
                        "-r", backupFile
                );
            }

            // Redirigir errores para depuración
            pb.redirectErrorStream(true);

            // Directorio de trabajo (opcional)
            pb.directory(new File("C:\\xampp\\mysql\\bin\\"));

            Process proceso = pb.start();
            int exitCode = proceso.waitFor();

            if (exitCode == 0) {
                System.out.println("✅ Backup completado correctamente: " + backupFile);
            } else {
                System.out.println("❌ Error al realizar el backup. Código: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        realizarBackup();
    }
}
