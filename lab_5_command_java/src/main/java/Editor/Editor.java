package Editor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class Editor {
    public final static String IMAGES_DIR_PATH = "images";
    private EditorMenu menu;
    private EditorDocument document;

    public Editor() {
        menu = new EditorMenu(this);
        document = new EditorDocument();
        addMenuItem("help", "Help", "showInstructions");
        addMenuItem("exit", "exit", "exit");
        addMenuItem("setDocumentTitle", "Changes value. Args: <new value>", "setDocumentTitle");
        addMenuItem("insertParagraph", "Insert paragraph. Args: <position>|end <paragrapg text>", "insertParagraph");
        addMenuItem("insertImage", "Insert image. Args: <position>|end <width> <height> <path>", "insertImage");
        addMenuItem("replaceText", "Replace text. Args: <position> <new text>", "replaceText");
        addMenuItem("resizeImage", "Resize image. Args: <position> <new width> <new height>", "resizeImage");
        addMenuItem("deleteItem", "Delete item. Args: <position>", "deleteItem");
        addMenuItem("save", "Save as Html document. Args: <path>", "save");
        addMenuItem("list", "Show document", "list");
        addMenuItem("undo", "undo command", "undo");
        addMenuItem("redo", "redo undone command", "redo");
    }

    public void start() {
        menu.run();
    }

    private void addMenuItem(String shortcut, String description, String methodName) {
        Method method;
        try {
            method = this.getClass().getDeclaredMethod(methodName, String.class);
            method.setAccessible(true);
            menu.addItem(shortcut, description, method);
        } catch (SecurityException e) {
            System.out.println("Security exception fired");
        } catch (NoSuchMethodException e) {
            System.out.println("No such method exception");
        }
    }

    private void insertParagraph(String methodArgument) {
        String commandParts[] = methodArgument.split(" ", 2);
        Integer position = initPosition(commandParts[0]);
        Paragraph paragraph = new Paragraph(commandParts[1]);
        try {
            document.insertParagraph(paragraph, position);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void replaceText(String methodArgument) {
        String commandParts[] = methodArgument.split(" ", 2);
        Integer position;
        try {
            position = Integer.parseInt(commandParts[0]) - 1;
        } catch (NumberFormatException e) {
            throw e;
        }
        document.replaceText(commandParts[1], position);
    }

    private void insertImage(String methodArgument) {
        String commandParts[] = methodArgument.split(" ");
        Integer position = initPosition(commandParts[0]);
        Integer width = Integer.parseInt(commandParts[1]);
        Integer height = Integer.parseInt(commandParts[2]);
        if (width > 10000 || width < 1 || height > 10000 || height < 1) {
            System.out.println("Image size must be between 1 and 10000");
            return;
        }
        Image image = new Image(commandParts[3], width, height);
        try {
            document.insertImage(image, position);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void resizeImage(String methodArgument) {
        String commandParts[] = methodArgument.split(" ", 3);
        Integer position;
        Integer width;
        Integer height;
        try {
            position = Integer.parseInt(commandParts[0]) - 1;
            width = Integer.parseInt(commandParts[1]);
            height = Integer.parseInt(commandParts[2]);
        } catch (NumberFormatException e) {
            throw e;
        }
        if (width > 10000 || width < 1 || height > 10000 || height < 1) {
            System.out.println("Image size must be between 1 and 10000");
            return;
        }
        document.resizeImage(position, width, height);
    }

    private void deleteItem(String methodArgument) {
        Integer position;
        try {
            position = Integer.parseInt(methodArgument);
        } catch (Exception e) {
            throw e;
        }
        document.deleteItem(position);
    }

    private void save(String methodArgument) {
        File destinationFolder = new File(methodArgument);
        if (!destinationFolder.exists()) {
            System.out.println("Folder doesnt exist");
            return;
        }

        File desinationImagesFolder = destinationFolder.toPath().resolve("images").toFile();
        if (!desinationImagesFolder.exists() && !desinationImagesFolder.mkdirs()) {
            System.out.println("Cant create images folder");
            return;
        }

        File imagesFolder = new File("images");
        if (imagesFolder.exists()) {
            Path destinationPath = desinationImagesFolder.toPath();
            for (File file : imagesFolder.listFiles()) {
                Path destinationImagePath = destinationPath.resolve(file.getName());
                try {
                    Files.copy(file.toPath(), destinationImagePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Cant copy image: " + file.getName());
                }
            }
        }

        File resultFile = destinationFolder.toPath().resolve("index.html").toFile();
        try {
            Files.write(resultFile.toPath(), document.getHtml().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Cant write html data");
        }
    }

    private void list(String methodArgument) {
        System.out.println("Title: " + document.getDocumentTitle());
        System.out.print(document.getItemsList());
    }

    private void setDocumentTitle(String methodArgument) {
        document.setDocumentTitle(methodArgument);
    }

    private void showInstructions(String methodArgument) {
        menu.showInstructions();
    }

    private void exit(String methodArgument) {
        menu.exit();
    }

    private void undo(String methodArgument) {
        document.undo();
    }

    private void redo(String methodArgument) {
        document.redo();
    }

    private int initPosition(String firstWord) {
        Integer position;
        if (firstWord.equalsIgnoreCase("end")) {
            position = -1;
        } else {
            try {
                position = Integer.parseInt(firstWord);
            } catch (NumberFormatException e) {
                throw e;
            }
        }

        return position;
    }
}
