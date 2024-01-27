package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageStegan extends Application {
    private static final int IMAGE_W = 600;
    private static final int IMAGE_H = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createContent()throws FileNotFoundException {
        VBox root = new VBox();        
        Image image1 = new Image(new FileInputStream("C:\\StrangerCodes\\ecpjava\\layerfx\\srcfx\\application\\image1.jpeg"));
        Image image2 = new Image(new FileInputStream("C:\\StrangerCodes\\ecpjava\\layerfx\\srcfx\\application\\image2.jpeg"));

        
        Image embedded = embed(image1, image2);
        Image extracted = extract(embedded);

        root.getChildren().addAll(
                new HBox(new ImageView(image1), new ImageView(image2)),
                new HBox(new ImageView(embedded), new ImageView(extracted))
        );

        return root;
    }

    public Image embed(Image target, Image source) {
//    	int IMAGE_W = target.getWidth();
//    	int IMAGE_H = target.getHeight();
        WritableImage result = new WritableImage(IMAGE_W, IMAGE_H);

        int[] targetBitIndices = new int[] { 0, 1, 2, 8, 9, 10, 16, 17, 18, 24, 25, 26 };
        int[] sourceBitIndices = new int[] { 4, 5, 6, 7, 12, 13, 14, 15, 20, 21, 22, 23 };

        for (int y = 0; y < IMAGE_H; y++) {
            for (int x = 0; x < IMAGE_W; x++) {
                int targetPixel = target.getPixelReader().getArgb(x, y);
                int sourcePixel = source.getPixelReader().getArgb(x, y);

                for (int i = 0; i < targetBitIndices.length; i++) {
                    int targetIndex = targetBitIndices[i];
                    int sourceIndex = sourceBitIndices[i];

                    // source bit at index is 1 (set)
                    if ((sourcePixel & (1 << sourceIndex)) != 0) {
                        targetPixel |= (1 << targetIndex);
                    } else {
                        targetPixel &= ~(1 << targetIndex);
                    }
                }

                result.getPixelWriter().setArgb(x, y, targetPixel);
            }
        }

        return result;
    }

    public Image extract(Image embedded) {
        WritableImage result = new WritableImage(IMAGE_W, IMAGE_H);

        int[] targetBitIndices = new int[] { 0, 1, 2, 8, 9, 10, 16, 17, 18, 24, 25, 26 };
        int[] sourceBitIndices = new int[] { 4, 5, 6, 7, 12, 13, 14, 15, 20, 21, 22, 23 };

        for (int y = 0; y < IMAGE_H; y++) {
            for (int x = 0; x < IMAGE_W; x++) {
                int targetPixel = embedded.getPixelReader().getArgb(x, y);

                int sourcePixel = 255 << 24;

                for (int i = 0; i < targetBitIndices.length; i++) {
                    int targetIndex = targetBitIndices[i];
                    int sourceIndex = sourceBitIndices[i];
                    
                    if ((targetPixel & (1 << targetIndex)) != 0) {
                        sourcePixel |= (1 << sourceIndex);
                    } else {
                        sourcePixel &= ~(1 << sourceIndex);
                    }
                }

                result.getPixelWriter().setArgb(x, y, sourcePixel);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}