import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lift extends Thread{
    int floor;
    ArrayList<Integer> up_floors;
    ArrayList<Integer> down_floors;
    int direction;
    String condition;
    ArrayList<Integer> full_path;
    int next_n_floor;

Lift(){
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
    if (fl > this.floor && !this.up_floors.contains(fl)){
        this.up_floors.add(fl);
        Collections.sort(this.up_floors);
        }
    else if (fl < this.floor && !this.down_floors.contains(fl)){
        this.down_floors.add(fl);
        Collections.sort(this.down_floors, Collections.reverseOrder());
        }

    }
void stop_lift(int fl){
    System.out.println("stop");
    this.condition = "Ожидание";
    while (this.up_floors.isEmpty() && this.down_floors.isEmpty()){
        if (this.direction!=-1){ this.direction = -1;}
    }
    if (this.up_floors.isEmpty() && this.direction == -1){
        this.direction = 0;}
    else if (this.direction == -1){this.direction = 1;}
    try {
        Thread.sleep(3000);
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
    if (this.direction == 1 && !this.up_floors.isEmpty()){
        this.up_floors.removeIf(n -> n == fl);
        if (!up_floors.isEmpty()){
        this.floor = this.up_floors.get(0);}
        next_floor();
    }
    else if (this.direction == 0 && !this.down_floors.isEmpty()){
        this.down_floors.removeIf(n -> n == fl);
        if (!down_floors.isEmpty()){
            this.floor = this.down_floors.get(0);}
        next_floor();
    }}


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
    if (this.up_floors.isEmpty() && this.down_floors.isEmpty()){stop_lift(this.floor);}
    if (this.direction == 1 && this.up_floors.isEmpty()){
        change_direction();
        this.direction = 0;
        next_floor();
    }
    else if (this.direction == 0 && this.down_floors.isEmpty()){
        change_direction();
        this.direction = 1;
        next_floor();
    }
    if (this.direction == 1 && !this.up_floors.isEmpty()){
        this.full_path.add(this.floor);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        stop_lift(this.floor);
    }
    else if (this.direction == 0 && !this.down_floors.isEmpty()){
        this.full_path.add(this.floor);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        stop_lift(this.floor);
    }
    else {
        stop_lift(this.floor);
    }
}
void next_num_floor(){
    System.out.println("next num");
    if (this.direction == 1 && !this.up_floors.isEmpty()){
        this.next_n_floor = this.up_floors.get(0);
    }
    else if (this.direction == 0 && !this.down_floors.isEmpty()){
        this.next_n_floor = this.down_floors.get(0);
    }
    else this.next_n_floor = this.floor;
}
Integer up(int fl, int flag){
    ArrayList<Integer> up_list = new ArrayList<>(this.up_floors);
    up_list.add(fl);
    int index;
    if (flag == 1) {
        index = up_list.indexOf(fl) + 1;
        return index;
    }
    else {
        index = this.down_floors.size() + up_list.indexOf(fl);
        return index;
    }
}
Integer down(int fl, int flag){
    ArrayList<Integer> down_list = new ArrayList<>(this.down_floors) ;
    down_list.add(fl);
    int index;
    if (flag == 1) {
        index = down_list.indexOf(fl) + 1;
        return index;
    }
    else {
        index = this.up_floors.size() + down_list.indexOf(fl);
        return index;
    }
}
Integer floor_distance(int fl){
    if (fl == this.floor){
        return 0;
    }
    if (fl > this.floor && this.up_floors.isEmpty()){
        return 1;
    }
    if (fl < this.floor && this.down_floors.isEmpty()){
        return 1;
    }
    if (fl > this.floor && this.direction == 1){
        return up(fl, 1);
    }
    else if (fl > this.floor && this.direction == 0){
        return up(fl, 0);
    }
    else if (fl < this.floor && this.direction == 0){
        return down(fl, 1);
    }
    else {return down(fl, 0);}
    }
}

