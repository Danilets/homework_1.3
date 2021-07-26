import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringBuilder log = new StringBuilder();
        //каталоги (OS Linux)
        String path = "/home/tsemdaykindv/Desktop/Games";
        File srcDir = new File(path + "/src");
        File resDir = new File(path + "/res");
        File saveDir = new File(path + "/savegames");
        File tempDir = new File(path + "/temp");

        File mainDir = new File(srcDir + "/main");
        File testDir = new File(srcDir + "/test");
        File drawablesDir = new File(resDir + "/drawables");
        File vectorsDir = new File(resDir + "/vectors");
        File iconsDir = new File(resDir + "/icons");

        //файлы
        File mainFile = new File(mainDir, "Main.java");
        File utilsFile = new File(mainDir, "Utils.java");
        File tempFile = new File(tempDir, "temp.txt");

        ArrayList<File> dirList = new ArrayList<>();
        dirList.add(srcDir);
        dirList.add(resDir);
        dirList.add(saveDir);
        dirList.add(tempDir);
        dirList.add(mainDir);
        dirList.add(testDir);
        dirList.add(drawablesDir);
        dirList.add(vectorsDir);
        dirList.add(iconsDir);

        for(File item : dirList){
            if(item.mkdir()) log.append(" каталог " + item.getName() + " создан; \n");
        }

        try {
            if (mainFile.createNewFile()) log.append(" файл " + mainFile.getName() + " создан; \n");
            if (utilsFile.createNewFile()) log.append(" файл " + utilsFile.getName() + " создан; \n");
            if (tempFile.createNewFile()) log.append(" файл " + tempFile.getName() + " создан; \n");
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        String logInfo = new String(log);

        try (FileWriter writer = new FileWriter(tempFile, true)) {
                writer.write(logInfo);
                writer.flush();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
