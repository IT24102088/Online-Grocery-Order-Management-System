package com.example.onlinegroceryordermanagementsystem;

import java.io.*;
import java.util.zip.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class backupManager {

    // Backup files to a ZIP archive
    public static void backupFiles(String[] filePaths, String backupDirPath) {

        try {
            File backupDir = new File(backupDirPath);
            if (!backupDir.exists()) {
                final boolean mkdir = backupDir.mkdirs();
            }

            String date = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            File zipFile = new File(backupDir, "backup-" + date + ".zip");

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String path : filePaths) {
                File file = new File(path);
                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    fis.close();
                } else {
                    System.out.println("⚠️ File not found: " + path);
                }
            }

            zos.close();
            fos.close();
            System.out.println("✅ Backup completed: " + zipFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Restore files from a ZIP archive
    public static void restoreBackup(String zipFilePath, String restoreToDirPath) {
        try {
            File destDir = new File(restoreToDirPath);
            if (!destDir.exists()) {
                final boolean mkdir = destDir.mkdirs();
            }

            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = new File(destDir, zipEntry.getName());
                final boolean mkdir = new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                zis.closeEntry();
            }

            zis.close();
            System.out.println("✅ Restore completed to: " + destDir.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

