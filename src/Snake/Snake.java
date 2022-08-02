package Snake;

import java.util.ArrayList;

public class Snake {
    int[]headPosition;
    int length;
    ArrayList<int[]> snakePosition;
    char direction;

    public Snake(int x, int y, int length, char direction){
        headPosition = new int[2];
        headPosition[0] = x;
        headPosition[1] = y;
        this.length = length;
        snakePosition =  new ArrayList<int[]>();
        this.direction = direction;
        for (int i = 0; i < length; i++){
            snakePosition.add(new int[2]);
            snakePosition.get(i)[0] = headPosition[0];
            snakePosition.get(i)[1] = headPosition[1] + 25 * i;
        }
    }

    public void move(){
        if(direction == 'R'){
            headPosition[0]+= 25;
        } else if(direction == 'L'){
            headPosition[0]-= 25;
        } else if(direction == 'U'){
            headPosition[1]-= 25;
        } else if(direction == 'D'){
            headPosition[1]+= 25;
        }

        for (int i = length - 1; i >= 1; i--){
            snakePosition.get(i)[0] = snakePosition.get(i - 1)[0];
            snakePosition.get(i)[1] = snakePosition.get(i - 1)[1];
        }
        snakePosition.get(0)[0] = headPosition[0];
        snakePosition.get(0)[1] = headPosition[1];
    }

    public void changeDirection(char direction){
        if((direction == 'R' && this.direction == 'L') || (direction == 'L' && this.direction == 'R') || (direction == 'U' && this.direction == 'D') || (direction == 'D' && this.direction == 'U')){
            return;
        } else {
            this.direction = direction;
        }

    }

    public void grow(){
        length++;
        int[] newTail = new int[2];
        newTail[0] = snakePosition.get(length-2)[0] + 1;
        newTail[1] = snakePosition.get(length-2)[1];
        snakePosition.add(newTail);
    }

}
