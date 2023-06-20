import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));
        stage.setTitle("Lab4");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
//        Scanner on = new Scanner(System.in);
//        int floors = on.nextInt();
//        Lift lift = new Lift();
//        lift.home_floors = floors;
//
//        for (; ;) {
//            Scanner in = new Scanner(System.in);
//            System.out.print("Введите номер этажа:");
//            int num = in.nextInt();
//            if (num == 0 || num > floors){
//                break;
//            }
//            lift.add_floor(num);
//            System.out.println(lift.floor);
//        }

    }
}