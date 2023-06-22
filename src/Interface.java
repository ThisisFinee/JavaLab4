import javafx.concurrent.Service;
import javafx.concurrent.Task;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Interface extends Thread{

    @FXML
    private TextField FloorLabel;

    @FXML
    private Button FullPuthButton;

    @FXML
    private Button GeneratorButton;

    @FXML
    private Button RefreshButton;

    @FXML
    private Button StartButton1;

    @FXML
    private Button StartButton2;

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
    private Text textCondition1;

    @FXML
    private Text textDirection;

    @FXML
    private Text textDirection1;

    @FXML
    private Text textFloor;

    @FXML
    private Text textFloor1;

    @FXML
    private Text textNextFloor;

    @FXML
    private Text textNextFloor1;

    @FXML
    private TextField timeGenerateLabel;
    Lift lift = new Lift();
    Lift lift1 = new Lift();
    int home_floors;
    ArrayList<Integer> Main_flow = new ArrayList<Integer>();
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

        StartButton1.setOnAction(actionEvent -> {Service<Void> service1 = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        start_first_lift();
                        return null;
                    }
                };
            }
        };
            service1.start();});
        StartButton2.setOnAction(actionEvent -> {Service<Void> service2 = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        start_second_lift();
                        return null;
                    }
                };
            }
        };
            service2.start();});
        addFloorButton.setOnAction(actionEvent -> {Service<Void> service3 = new Service<Void>() {
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
            service3.start();});
        GeneratorButton.setOnAction(actionEvent -> {Service<Void> service4 = new Service<Void>() {
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
            service4.start();});
        RefreshButton.setOnAction(actionEvent -> {Service<Void> service5 = new Service<Void>() {
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
            service5.start();});
        FullPuthButton.setOnAction(actionEvent -> {Service<Void> service6 = new Service<Void>() {
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
            service6.start();});
    }

    void Create_button(){
        if (numFloorsLabel.getText().isEmpty()) System.out.println("Не указано количество этажей");
        else {this.home_floors = Integer.parseInt(numFloorsLabel.getText());
        }
    }
    void start_first_lift(){
        lift.next_floor();
    }
    void start_second_lift(){
        lift1.next_floor();
    }
    void Add_button(){
        if (FloorLabel.getText().isEmpty()) System.out.println("Не указан этаж");
        else lift.add_floor(Integer.parseInt(FloorLabel.getText()));
    }

    void Generate_button(){
        if (numGenerateLabel.getText().isEmpty()) System.out.println("Не указано количество добавлений в поток");
        else {
            if (timeGenerateLabel.getText().isEmpty()) System.out.println(  "Не указан интервал добавления в поток");
            else {
                Generate_stream(Integer.parseInt(numGenerateLabel.getText()), Integer.parseInt(timeGenerateLabel.getText()));}
        }

    }
    void Add_main_flow(int fl){
        if (fl>0 && fl < this.home_floors){
            this.Main_flow.add(fl);
        }
    }
    void Distribute_lift() {
        for (int i = 0; i < Main_flow.size();i++){
            int fl = Main_flow.get(i);
            System.out.println(fl);
            System.out.println(this.lift.floor_distance(fl));
            System.out.println(this.lift1.floor_distance(fl));
            if (this.lift.floor_distance(fl) <= this.lift1.floor_distance(fl) && !this.lift1.up_floors.contains(fl) && !this.lift1.down_floors.contains(fl)){
                this.lift.add_floor(fl);

            }
            else if (this.lift.floor_distance(fl) > this.lift1.floor_distance(fl) && !this.lift.up_floors.contains(fl) && !this.lift.down_floors.contains(fl)){
                this.lift1.add_floor(fl);
            }
        }
    }
    void No_Duplicate(){
        Set<Integer> set = new LinkedHashSet<>(this.Main_flow);
        ArrayList<Integer> flow = new ArrayList<>(set);
        this.Main_flow = flow;
    }
    void Generate_stream(int numFloors, int timeGenerate){
        int interval = timeGenerate*1000;
        for (;;){
            for (int i = 0; i<numFloors;i++){
                int fl = (int) (Math.random()*this.home_floors+1);
                Add_main_flow(fl);
            }
            System.out.println(this.Main_flow);
            No_Duplicate();
            Distribute_lift();
            Main_flow.clear();
            System.out.println(lift.up_floors);
            System.out.println(lift.down_floors);
            System.out.println(lift1.up_floors);
            System.out.println(lift1.down_floors);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    void Refresh_Button(){
        for (;;){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        lift.next_num_floor();
        lift1.next_num_floor();
        textCondition.setText(lift.condition);
        textCondition1.setText(lift1.condition);
        textFloor.setText(String.valueOf(lift.floor));
        textFloor1.setText(String.valueOf(lift1.floor));
        textNextFloor.setText(String.valueOf(lift.next_n_floor));
        textNextFloor1.setText(String.valueOf(lift1.next_n_floor));
        if (lift.direction == 1){textDirection.setText("Вверх");}
        else if (lift.direction == 0){textDirection.setText("Вниз");}
        else {textDirection.setText("Ожидание");}
        if (lift1.direction == 1){textDirection1.setText("Вверх");}
        else if (lift1.direction == 0){textDirection1.setText("Вниз");}
        else {textDirection1.setText("Ожидание");}}
    }
    void Path_button(){
        for (int i = 0; i < lift.full_path.size();i++){
            System.out.print(lift.full_path.get(i));
            System.out.print(" ");
        }
    }

}
