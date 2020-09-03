package app.by.epamlab.gsu.managers;
import app.by.epamlab.gsu.constants.Constants;
import app.by.epamlab.gsu.model.beans.Task;
import app.by.epamlab.gsu.model.beans.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    private static final String mainPath = "C:\\Users\\Anton Emeljanov\\IdeaProjects\\webSecond\\Files";
    private static final String deliter = "\\";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;                                      // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40;                                     // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;

    private static String pathImp;

    public static void setGlobals(String strDAO) {
        if (strDAO.equals(Constants.DB_DAO)) {
            pathImp = Constants.DB_DAO;
        }
        else if (strDAO.equals(Constants.RAM_DAO)) {
            pathImp = Constants.RAM_DAO;
        }
    }

    public static String getFileName(User user, Task task) {
        String userPath = user.getLogin();
        int taskPath = task.getId();
        String path = mainPath + deliter + pathImp + deliter + userPath + deliter + taskPath;
        File filepath = new File(path);
        if(!filepath.exists()) {
            filepath.mkdirs();
        }
        File[] listFile = filepath.listFiles();
        if(listFile.length == 0) {
            return null;
        }
        else return listFile[0].getName();
    }

    public static void uploadTaskFile(HttpServletRequest request, User user, Task task) {
        String userPath = user.getLogin();
        int taskPath = task.getId();
        String pathCopy = mainPath + deliter + pathImp + deliter + userPath + deliter + taskPath;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        File uploadDirectore = new File(pathCopy);
        if(!uploadDirectore.exists()) {
            uploadDirectore.mkdirs();
        }

        try {
            List<FileItem> formItems = upload.parseRequest(request);
            if(formItems != null && formItems.size() > 0 ) {
                for(FileItem item : formItems) {
                    if(!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = pathCopy + deliter + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                    }
                }
            }

        }catch (Exception e ) {
            e.printStackTrace();
        }
    }


    public static void downloadTaskFile(HttpServletResponse response, User user, Task task) {
        String userPath = user.getLogin();
        int taskPath = task.getId();
        String filename = getFileName(user,task);
        String pathFile = mainPath + deliter + pathImp + deliter + userPath + deliter + taskPath + deliter + filename;
        File file = new File(pathFile);

        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader( "Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        try {
            OutputStream outputStream = response.getOutputStream();
            try (FileInputStream in = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];                                                         // 4 kB
                int length;
                while ((length = in.read(buffer)) > 0) {                                                // write bytes to the buffer
                    outputStream.write(buffer, 0, length);                                          // write buffer to te outputStream
                }
            }
            outputStream.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void removeTaskFile(User user, Task task) {
        String userPath = user.getLogin();
        int taskPath = task.getId();
        String filename = getFileName(user,task);
        String pathDelete = mainPath + deliter + pathImp + deliter + userPath + deliter + taskPath + deliter + filename;
        try {
            Files.delete(Paths.get(pathDelete));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
