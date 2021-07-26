package game;

import java.io.*;
import java.io.File;
import java.util.zip.*;
import java.util.List;
import java.util.ArrayList;

public class SaveProgress {

    public static void main(String[] args) {
        GameProgress gameFirst = new GameProgress(197, 5, 3, 20);
        GameProgress gameSecond = new GameProgress(83, 3, 5, 47);
        GameProgress gameThird = new GameProgress(19, 9, 7, 200);

        File saveDir = new File("/home/tsemdaykindv/Desktop/Games/savegames");
        String zipPath = "/home/tsemdaykindv/Desktop/Games/savegames/zip_progress.zip";

        String saveFile1 =  "/home/tsemdaykindv/Desktop/Games/savegames/save1.dat";
        String saveFile2 =  "/home/tsemdaykindv/Desktop/Games/savegames/save2.dat";
        String saveFile3 =  "/home/tsemdaykindv/Desktop/Games/savegames/save3.dat";

        List<String> filePathList = new ArrayList<>();
        filePathList.add(saveFile1);
        filePathList.add(saveFile2);
        filePathList.add(saveFile3);

        saveGame(saveFile1, gameFirst);
        saveGame(saveFile2, gameSecond);
        saveGame(saveFile3, gameThird);

        zipFiles(zipPath, filePathList);

        for (File item : saveDir.listFiles()) {
            if (!item.getName().trim().endsWith(".zip")) item.delete();
        }
    }

    public static void saveGame(String pathFile, GameProgress gameProgress){
        try (FileOutputStream out = new FileOutputStream(pathFile);
             ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(gameProgress);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipPath, List<String> filePathList){
        //архивация
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath))) {
            int i = 1;
            for (String filePath : filePathList) {
                FileInputStream fis = new FileInputStream(filePath);

                ZipEntry entry = new ZipEntry("save" + i + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                i++;
            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
