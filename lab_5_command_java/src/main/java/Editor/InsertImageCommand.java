package Editor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class InsertImageCommand extends CommonDocumentCommand {
    private final InsertDocumentItemCommand insertDocumentItemCommand;
    private final Image image;
    private File imageFile;

    public InsertImageCommand(ArrayList<DocumentItem> items, Image image, Integer position) {
        this.insertDocumentItemCommand = new InsertDocumentItemCommand(items, new DocumentItem(image));
        this.image = image;
    }

    @Override
    public void execute() {
        if (imageFile == null) {
            copyImage();
        }
        insertDocumentItemCommand.execute();
    }

    @Override
    public void unexecute() {
        insertDocumentItemCommand.unexecute();
    }

    @Override
    public void destroy() {
        deleteImage();
    }

    private void copyImage() {
        File imageDir = new File(Editor.IMAGES_DIR_PATH);
        imageDir.mkdirs();

        File sourceFile = new File(image.getOriginPath());
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("Image not exist");
        }

        String imageFileName = getNextImageName(sourceFile);
        Path destinationPath = imageDir.toPath().resolve(Paths.get(imageFileName));
        imageFile = destinationPath.toFile();
        try {
            Files.copy(sourceFile.toPath(), destinationPath);
            image.setActualPath(destinationPath.toString());
        } catch (IOException e) {
            System.out.println("Cant copy image: " + image.getOriginPath());
        }
    }

    private void deleteImage() {
        if (imageFile.exists()) {
            if (!imageFile.delete()) {
                System.out.println("Image was not deleted: " + imageFile.getPath());
            }
        }
    }

    private String getNextImageName(File source) {
        File imageDir = new File(Editor.IMAGES_DIR_PATH);
        ArrayList<Integer> imageNumbers = new ArrayList<>(10);
        String ext = "";
        if (source.getName().lastIndexOf(".") != -1 && source.getName().lastIndexOf(".") != 0) {
            ext = source.getName().substring(source.getName().lastIndexOf("."));
        }
        for (File file : imageDir.listFiles()) {
            String fileName = file.getName();
            String name = fileName;
            if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                ext = fileName.substring(fileName.lastIndexOf("."));
                name = fileName.substring(0, fileName.lastIndexOf("."));
            }
            imageNumbers.add(Integer.parseInt(name.split("_")[1]));
        }
        Collections.sort(imageNumbers);
        Integer imageIndex = imageNumbers.size() > 0 ? imageNumbers.get(imageNumbers.size() - 1) + 1 : 1;
        return "image_" + imageIndex + ext;
    }
}
