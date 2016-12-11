package authoring.editorview.path.subviews;

import java.util.ResourceBundle;

import authoring.editorview.path.IPathSetView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.editorview.path.subviews.editorfields.PathDimensionsView;
import authoring.editorview.path.subviews.editorfields.PathImageView;
import authoring.editorview.path.subviews.editorfields.PathNameView;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PathEditorView implements IPathSetView {

	private VBox root;
	private AnchorPane rootBuffer;
	private static final double BUFFER = 10.0;
	
	private PathImageView pathImageView;
	private NewPathView newPathView;
	private PathNameView pathNameView;
	private PathChooser pathChooser;   
    private PathDimensionsView pathDimensionsView;
	
    public PathEditorView (int size, ResourceBundle pathResource) {
    	rootBuffer = new AnchorPane();
    	root = new VBox(10);
    	
    	this.pathImageView = new PathImageView();
    	this.newPathView = new NewPathView();
    	this.pathNameView = new PathNameView(pathResource);
    	this.pathChooser = new PathChooser();
        this.pathDimensionsView = new PathDimensionsView();
    	
    	buildView();

    }

	@Override
	public Node getInstanceAsNode() {
		return rootBuffer;
	}

	@Override
	public void setDelegate(PathAuthoringViewDelegate delegate) {
		pathImageView.setDelegate(delegate);
		newPathView.setDelegate(delegate);
		pathNameView.setDelegate(delegate);
		pathChooser.setDelegate(delegate);
		pathDimensionsView.setDelegate(delegate);
		
	}
	
	private void buildView() {
		rootBuffer.getChildren().add(root);
    	AnchorPane.setLeftAnchor(root, BUFFER);
    	AnchorPane.setTopAnchor(root, BUFFER);
    	
    	rootBuffer.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.5))));
    	rootBuffer.setBackground(new Background(new BackgroundFill(Color.rgb(235, 235, 235), CornerRadii.EMPTY, Insets.EMPTY)));	
    	
    	root.getChildren().addAll(
    			newPathView.getInstanceAsNode(),
    			pathChooser.getInstanceAsNode(),
    			pathNameView.getInstanceAsNode(),
    			pathDimensionsView.getInstanceAsNode()
    	);
		
	}
    
	public void updatePathName(String pathName){
		pathNameView.updateName(pathName);
		pathChooser.updatePathComboBox(pathName);
	}
	
	public void updateActiveID(int pathID){
		pathChooser.setActivePathId(pathID);
	}
	
	public void updateGridDimensions (int dimensions) {
        pathDimensionsView.setGridDimensions(dimensions);

    }
	
	public void updatePathImagePath (String imagePath) {
	    pathImageView.setPathImagePath(imagePath);
	}
	
	

}
