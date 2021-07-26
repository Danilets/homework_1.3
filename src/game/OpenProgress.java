package game;

import java.io.*;
import java.io.File;
import java.util.zip.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class OpenProgress {

    public static void main(String[] args) {
        String zipPath = "/home/tsemdaykindv/Desktop/Games/savegames/zip_progress.zip";
        String unZipPath = "/home/tsemdaykindv/Desktop/Games/savegames/";
        File unZipFilesPath = new File(unZipPath);

        //распаковка
        openZip(zipPath, unZipPath);

        //заполняем список файлов для десериализации
        List<String> listFilesPath = new ArrayList<>();
        for (File item : unZipFilesPath.listFiles()) {
            if (!item.getName().endsWith(".zip")) listFilesPath.add(item.toString());
        }

        //выбираем любой файл кроме .zip
        String filePath = listFilesPath.get(new Random().nextInt(listFilesPath.size()));

        //выполнение десериализации
        GameProgress gameProgress = openProgress(filePath);
        System.out.println(gameProgress);
    }

    public static void openZip(String zipPath, String unpackagePath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();

                // распаковка
                FileOutputStream fout = new FileOutputStream(unpackagePath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String filePath) {
        try (FileInputStream fin = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fin)) {
            return (GameProgress) ois.readObject();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
