package authoring.editorview.weapon.subviews;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.weapon.WeaponEditorViewDelegate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class WeaponEffectView extends PhotoFileChooser {

    private String imagePath;

    private VBox vboxView;
    private ScrollPane completeView;
    private WeaponEditorViewDelegate delegate;
    private TextField fireRateField;
    private TextField speedField;
    private TextField rangeField;
    private TextField nameField;
    private TextField damageField;
    private ComboBox<String> weaponEffectBox;
    private ComboBox<String> weaponPathBox;
    private ResourceBundle labelsResource;
    private File chosenFile;

    private final String WEAPON_EFFECT_RESOURCE_PATH = "resources/GameAuthoringWeapon";

    public WeaponEffectView () throws IOException {
        labelsResource = ResourceBundle.getBundle(WEAPON_EFFECT_RESOURCE_PATH);
        vboxView = new VBox(10);
        completeView = new ScrollPane();
        completeView.setContent(vboxView);

        buildViewComponents();
        placeInVBox();
    }

    public void setDelegate (WeaponEditorViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void buildViewComponents () throws IOException {
        makeTextFields();

        ImageView myImageView = loadWeaponImage();
        vboxView.getChildren().add(myImageView);
        vboxView.getChildren().add(makeButton(labelsResource.getString("Image"),
                                              e -> selectFile("text", "text")));
        vboxView.getChildren().add(createHBox(labelsResource.getString("Name"), nameField));
        vboxView.getChildren().add(createHBox(labelsResource.getString("Rate"), fireRateField));
        vboxView.getChildren().add(createHBox(labelsResource.getString("Speed"), speedField));
        vboxView.getChildren().add(createHBox(labelsResource.getString("Range"), rangeField));
        vboxView.getChildren().add(createHBox(labelsResource.getString("Damage"), damageField));
    }

    private Button makeButton (String buttonText, EventHandler<ActionEvent> event) {
        Button button = new Button(buttonText);
        button.setOnAction(event);
        return button;
    }

    private void makeTextFields () {
        rangeField = makeTextField(labelsResource.getString("EnterInt"),
                                   e -> delegate.onUserEnteredWeaponRange(rangeField.getText()));
        speedField = makeTextField(labelsResource.getString("EnterInt"),
                                   e -> delegate
                                           .onUserEnteredProjectileSpeed(speedField.getText()));
        fireRateField = makeTextField(labelsResource.getString("EnterInt"),
                                      e -> delegate.onUserEnteredWeaponFireRate(fireRateField
                                              .getText()));
        nameField = makeTextField(labelsResource.getString("EnterString"),
                                  e -> delegate.onUserEnteredWeaponName(nameField.getText()));
        damageField = makeTextField(labelsResource.getString("EnterInt"),
                                    e -> delegate.onUserEnteredWeaponDamage(damageField.getText()));
    }

    private HBox createHBox (String labelString, TextField textField) {
        HBox box = new HBox(5);
        Label label = new Label(labelString);
        box.getChildren().addAll(label, textField);
        return box;
    }

    public ScrollPane getInstanceAsNode () {
        return completeView;
    }

    private void placeInVBox () throws IOException {
        ObservableList<String> effectOptions =
                FXCollections.observableArrayList("IDK", "Sorry");
        weaponEffectBox = makeComboBox(labelsResource.getString("Effect"),
                                       e -> delegate.onUserEnteredWeaponEffect(weaponEffectBox
                                               .getValue()),
                                       effectOptions);
        vboxView.getChildren().add(weaponEffectBox);
        ObservableList<String> pathOptions =
                FXCollections.observableArrayList("I still don't know", "Sorry");
        weaponPathBox =
                makeComboBox(labelsResource.getString("Path"),
                             e -> delegate.onUserEnteredWeaponPath(weaponPathBox.getValue()),
                             pathOptions);
        vboxView.getChildren().add(weaponPathBox);
    }

    private ImageView loadWeaponImage () throws IOException {
        BufferedImage imageRead;
        ImageView myImageView = new ImageView();
        try {
            imageRead = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath));
            Image image2 = SwingFXUtils.toFXImage(imageRead, null);
            myImageView.setImage(image2);
            delegate.onUserEnteredWeaponImage(imagePath);
        }
        catch (Exception e) {
            imageRead =
                    ImageIO.read(getClass().getClassLoader()
                            .getResourceAsStream(labelsResource.getString("DefaultImagePath")));
            Image image2 = SwingFXUtils.toFXImage(imageRead, null);
            myImageView.setImage(image2);
            System.out.println("Unable to find picture in files");
        }
        return myImageView;
    }

    private TextField makeTextField (String promptText, EventHandler<ActionEvent> event) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setOnAction(event);
        return textField;
    }

    private ComboBox<String> makeComboBox (String name,
                                           EventHandler<ActionEvent> event,
                                           ObservableList<String> options) {
        ComboBox<String> combobox = new ComboBox<String>(options);
        combobox.setOnAction(event);
        combobox.setPromptText(name);
        return combobox;
    }

    /**
     * Update fields methods
     */

    public void updateFireRateDisplay (int fireRate) {
        // TODO: Watch for integer to string and error check in controller
        fireRateField.setText(Integer.toString(fireRate));
    }

    public void updateSpeedDisplay (int speed) {
        speedField.setText(Integer.toString(speed));
    }

    public void updateRangeDisplay (int range) {
        rangeField.setText(Integer.toString(range));
    }

    public void updateWeaponEffectDisplay (String effect) {
        weaponEffectBox.setValue(effect);
    }

    public void updateWeaponImagePath (String imagePath) {
        this.imagePath = imagePath;
    }

    public void updateWeaponName (String name) {
        nameField.setText(name);
    }

    public void updateWeaponDamage (int damage) {
        damageField.setText(Integer.toString(damage));
    }

    public void updateCreateWeapon () {
        // Call all of the other update methods to get the default values
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) {
        chosenFile = chooseFile.showOpenDialog(new Stage());
        // if not null -> get imageFilePath and update the instance variable
        // then loadImage through the created method above
    }

}
