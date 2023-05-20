import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.concurrent.Task;
import javafx.concurrent.Service;
public class Interface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField FloorLabel;

    @FXML
    private Button FullPuthButton;

    @FXML
    private Button GeneratorButton;

    @FXML
    private Button RefreshButton;

    @FXML
    private Button addFloorButton;

    @FXML
    private Button homeCreateButton;

    @FXML
    private TextField numFloorsLabel;

    @FXML
    private TextField numGenerateLabel;

    @FXML
    private Text textCondition;

    @FXML
    private Text textDirection;

    @FXML
    private Text textFloor;

    @FXML
    private Text textNextFloor;

    @FXML
    private TextField timeGenerateLabel;
    Lift lift = new Lift();
    @FXML
    void initialize() {
        homeCreateButton.setOnAction(actionEvent -> {
            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            Create_button();
                            return null;
                        }
                    };
                }
            };
            service.start();
        });
        addFloorButton.setOnAction(actionEvent -> {Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Add_button();
                        return null;
                    }
                };
            }
        };
            service.start();});
        GeneratorButton.setOnAction(actionEvent -> {Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Generate_button();
                        return null;
                    }
                };
            }
        };
            service.start();});
        RefreshButton.setOnAction(actionEvent -> {Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Refresh_Button();
                        return null;
                    }
                };
            }
        };
            service.start();});
        FullPuthButton.setOnAction(actionEvent -> {Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Path_button();
                        return null;
                    }
                };
            }
        };
            service.start();});
    }

    void Create_button(){
        if (numFloorsLabel.getText().isEmpty()) System.out.println("Не указано количество этажей");
        else {lift.home_floors = Integer.parseInt(numFloorsLabel.getText());
        lift.start();
        lift.next_floor();}
    }
    void Add_button(){
        if (FloorLabel.getText().isEmpty()) System.out.println("Не указан этаж");
        else lift.add_floor(Integer.parseInt(FloorLabel.getText()));
    }

    void Generate_button(){
        if (numFloorsLabel.getText().isEmpty()) System.out.println("Не указано количество добавлений в поток");
        else {
            if (timeGenerateLabel.getText().isEmpty()) System.out.println("Не указан интервал добавления в поток");
            else lift.Generate_stream(Integer.parseInt(numFloorsLabel.getText()), Integer.parseInt(timeGenerateLabel.getText()));
        }
    }
    void Refresh_Button(){
        lift.next_num_floor();
        textCondition.setText(lift.condition);
        textFloor.setText(String.valueOf(lift.floor));
        textNextFloor.setText(String.valueOf(lift.next_n_floor));
        if (lift.direction == 1){textDirection.setText("Вверх");}
        else if (lift.direction == 0){textDirection.setText("Вниз");}
        else {textDirection.setText("Ожидание");}
    }
    void Path_button(){
        for (int i = 0; i < lift.full_path.size();i++){
            System.out.print(lift.full_path.get(i));
            System.out.print("1");
        }
    }

}
