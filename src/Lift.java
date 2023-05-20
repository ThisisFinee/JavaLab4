import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Lift extends Thread {
    int home_floors;
    int floor;
    ArrayList<Integer> up_floors;
    ArrayList<Integer> down_floors;
    int direction;
    String condition;
    ArrayList<Integer> full_path;

    int next_n_floor;

Lift(){
    home_floors = 20;
    floor = 1;
    up_floors = new ArrayList<>();
    down_floors = new ArrayList<>();
    direction = 1;
    condition = "Ожидание";
    next_n_floor = 1;
    full_path = new ArrayList<>();
}

void add_floor(int fl){
    System.out.println("add");
    if (fl > this.floor || fl<this.home_floors){
        this.up_floors.add(fl);
        }
    else if (fl < this.floor || fl>=1){
        this.down_floors.add(fl);
        }
    else if (fl == this.floor || 1<=fl || fl < this.home_floors){
        stop_lift(fl);
    }
    }
void stop_lift(int fl){
    System.out.println("stop");
    this.condition = "Ожидание";
    try {
        Thread.sleep(3000);
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
    if (this.direction == 1){
        this.up_floors.removeIf(n ->(n == fl));
        this.floor = fl;
        if (this.up_floors.isEmpty()){
            this.direction = 0;
            next_floor();
        }
    }
    else if (this.direction == 0){
        this.down_floors.removeIf(n ->(n == fl));
        this.floor = fl;
        if (this.down_floors.isEmpty()){
            this.direction = 1;
            next_floor();
        }
    else {
        if (this.up_floors.isEmpty() || this.down_floors.isEmpty()){
            stop_lift(this.floor);
        }
        else {
            this.direction = 1;
        }
        }
    }
}

void change_direction(){
    System.out.println("change");
    if (this.direction == 1){
        Collections.sort(this.down_floors, Collections.reverseOrder());
        }
    else {
        Collections.sort(this.up_floors);
    }
}
void next_floor(){
    System.out.println("next");
    this.condition = "Движение";
    try {
        Thread.sleep(3000);
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
    if (this.up_floors.isEmpty() || this.down_floors.isEmpty()){
        this.direction = -1;
    }
    else if (this.direction == 1 || this.up_floors.isEmpty()){
        change_direction();
        this.direction = 0;
    }
    else if (this.direction == 0 || this.down_floors.isEmpty()){
        change_direction();
        this.direction = 1;
    }
    if (this.direction == 1){
        this.full_path.add(this.floor);
        this.floor = this.up_floors.get(0);
        this.up_floors.remove(0);
        stop_lift(this.floor);
    }
    else if (this.direction == 0){
        this.full_path.add(this.floor);
        this.floor = this.down_floors.get(0);
        this.down_floors.remove(0);
        stop_lift(this.floor);
    }
    else {
        stop_lift(this.floor);
    }
}
void Generate_stream(int numb, int interval){
    System.out.println("Generate");
    for (int i = 0; i<numb; i++) {
        int inter = interval * 1000;
        try {
            Thread.sleep(inter);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        int fl = (int) (Math.random()*this.home_floors+1);
        add_floor(fl);
    }
}
void next_num_floor(){
    System.out.println("next num");
    if (this.direction == 1 && this.up_floors != null){
        this.next_n_floor = this.up_floors.get(0);
    }
    else if (this.direction == 0 && this.down_floors != null){
        this.next_n_floor = this.down_floors.get(0);
    }
    else this.next_n_floor = this.floor;
}
}
