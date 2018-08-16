package com.moha.demo.util;


import java.util.Scanner;

/**
 * <p class="detail">
 * 功能:
 * </p>
 *
 * @author Moha
 * @ClassName Small require.
 * @Version V1.0.
 * @date 2018.08.16 15:57:08
 */
public class SmallRequire {

    private static String[] directions = {"E","S","W","N"};


    /**
     * 主程序入口.
     *
     * @param args :
     * @author Moha
     * @date 2018.08.16 15:57:08
     */
    public static void main(String[] args) {
        int direct = 0,x = 0,y= 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("输入：");
        String rightUpperCorner = sc.nextLine();
        while (true) {
            String initialCoordinate = sc.nextLine();
            String command = sc.nextLine();

            int xmax ,ymax;
            String[] xy = rightUpperCorner.split(" ");
            xmax = Integer.parseInt(xy[0]);
            ymax = Integer.parseInt(xy[1]);

            String[] init = initialCoordinate.split(" ");
            direct = directionValue(init[2]);
            x = Integer.parseInt(init[0]);
            y = Integer.parseInt(init[1]);
            if(!protectCar(xmax,ymax,x,y)){
                System.out.println("车子已经在规定范围外");
                break;
            }

            char[] commands = command.toCharArray();

            for(char c : commands){
                switch (c) {
                    case 'L':
                        direct++;
                        direct = direct == 4 ? 0:direct;
                        break;
                    case 'R':
                        direct--;
                        direct = direct == -1 ? 3:direct;
                        break;
                    case 'M':
                        switch (direct){
                            case 0:
                                if(protectCar(xmax,ymax,x,y)){
                                    x--;
                                }
                                break;
                            case 1:
                                if(protectCar(xmax,ymax,x,y)){
                                    y--;
                                }
                                break;
                            case 2:
                                if(protectCar(xmax,ymax,x,y)) {
                                    x++;
                                }
                                break;
                            case 3:
                                if(protectCar(xmax,ymax,x,y)) {
                                    y++;
                                }
                                break;
                            default:
                        }
                        break;
                    default:
                }
            }
            System.out.println("预期产出：");
            System.out.println(x+" "+y+" "+directions[direct]);
        }
    }

    private static int directionValue(String direct){
        for(int i=0;i<directions.length;i++){
            if(directions[i].equals(direct)){
                return i;
            }
        }
        return 0;
    }

    private static boolean protectCar(int xmax ,int ymax,int x,int y){
        if (x>xmax){
            return false;
        }
        if(y>ymax){
            return false;
        }
        return true;
    }
}
