import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Test AGAIN AGAIN

public class Converter extends Application {
  private TextField tfConversionType = new TextField();
  private TextField tfAmountToConvert = new TextField();
  private TextField tfConvertedResult = new TextField();
  private Button btHelp = new Button("Help");
  private Button btCalculate = new Button("Calculate");
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) 
  {  
    GridPane gridPane = new GridPane();

    // Create text
    Label label = new Label("Directions:\n- In the first box, enter the type of conversion you wish to commit. \n- For example, \"km to m\". \n- Then, enter the amount of the desired unit you wish to convert.\n- Click the \"Calculate\" button to execute. \n- The converted amount will appear in the box labeled, \"Result:\" at the bottom. \n- Click the \"Help\" button for a list of conversions.");        
    gridPane.add(label, 0, 0);
    GridPane.setHalignment(label, HPos.LEFT);

    // Create UI
    gridPane.setHgap(5);
    gridPane.setVgap(30);
    gridPane.add(new Label("Unit Conversion Type:"), 0, 1);
    gridPane.add(tfConversionType, 1, 1);
    gridPane.add(new Label("Amount to Convert:"), 0, 2);
    gridPane.add(tfAmountToConvert, 1, 2);
    gridPane.add(new Label("Result:"), 0, 3);
    gridPane.add(tfConvertedResult, 1, 3);
    tfConvertedResult.setEditable(false);
    gridPane.add(btHelp, 0, 4);
    gridPane.add(btCalculate, 1, 4);

    // Set properties for UI
    gridPane.setAlignment(Pos.CENTER);
    GridPane.setHalignment(btCalculate, HPos.CENTER);

    // Process events
    btCalculate.setOnAction(e -> calculateConversion());
    btHelp.setOnAction(e -> displayHelp());

    // Create a scene and place it in the stage
    Scene scene = new Scene(gridPane, 600, 450);
    primaryStage.setTitle("Measurement Converter"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /**
   * Handles event when "Convert" button is clicked
   */
  private void calculateConversion() 
  {
    // Get values from text fields
    String type = tfConversionType.getText().toLowerCase();
    Double numToConvert = 0.00;

    // If anything besides a number is input, tell user to try again.
    String errorMsg = "Your input is not currently handled by this app, please\ninput another query, for example, kg to lb.";
    
    // Set up a separate pop-up pane for handling user error.
    GridPane errorPane = new GridPane();
    Label label = new Label(errorMsg);        
    errorPane.add(label, 0, 0);
    GridPane.setHalignment(label, HPos.LEFT);

    // Create a scene and place it in the stage
    Scene scene = new Scene(errorPane, 300, 100);
    Stage errorStage = new Stage();
    errorStage.setTitle("Error"); // Set title
    errorStage.setScene(scene); // Place the scene in the stage

    // Check if user entered a number.
    try
    {
    numToConvert = Double.parseDouble(tfAmountToConvert.getText());
    }
    catch (NumberFormatException except) 
    {
        errorStage.show(); // Display the stage
    }
    
    Double converted = 0.00;

    // Handle input based on choice user selects.
    switch (type) 
    {
        case "km to m":  

            converted = numToConvert * 1000;
            break; 
        
        case "m to km":  

            converted = numToConvert / 1000;
            break; 

        case "kg to lb":

            converted = numToConvert * 2.2;
            break; 
        
        case "lb to kg":
            
            converted = numToConvert / 2.2;
            break; 

        case "m to ft":

            converted = numToConvert * 3.28;
            break; 
        
        case "ft to m":

            converted = numToConvert / 3.28;
            break;

        case "km to mi":
    
            converted = numToConvert * 0.62;
            break;

        case "mi to km":
            
            converted = numToConvert * 1.609;
            break; 
        
        case "l to ml":

            converted = numToConvert * 1000;
            break; 
        
        case "ml to l":
            
            converted = numToConvert / 1000;
            break; 

        // If no case passes, present the error pop-up.
        default: 
            errorStage.show();
            break;
    }

    tfConvertedResult.setText(String.format("%.3f", converted));
  }

  /**
   * Handles event where "Help" button is clicked.
   */
  public void displayHelp()
  {
    // Set up a separate pop-up pane for "Help" section.
    GridPane helpPane = new GridPane();
    Label label = new Label("List of conversions: \nkm to m \t m to km \nkg to lb \t lb to kg \nm to ft \t ft to m" +
    "\nkm to mi \t mi to km \nL to mL \t mL to L");        
    helpPane.add(label, 0, 0);
    GridPane.setHalignment(label, HPos.LEFT);

    // Create a scene and place it in the stage
    Scene scene = new Scene(helpPane, 250, 150);
    Stage helpStage = new Stage();
    helpStage.setTitle("Help"); // Set title
    helpStage.setScene(scene); // Place the scene in the stage
    helpStage.show();
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) 
  {
    launch(args);
  }
}