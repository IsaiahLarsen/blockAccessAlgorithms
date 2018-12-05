import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class assn6 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> blockTimes = new ArrayList<>();
        System.out.println("Assignment 6: Block Access Algorithms\nBy: Isaiah Larsen\n");
        String[] dataArray;
        String data;
        if(args.length > 0) {
            //read from file
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((data = br.readLine()) != null){
                dataArray = data.split(" ");
                for(int i = 0; i < dataArray.length; i++){
                    blockTimes.add(Integer.parseInt(dataArray[i]));
                }

            }
        }else{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while((data = in.readLine()) != null){
                dataArray = data.split(" ");
                for(int i = 0; i < dataArray.length; i++){
                    blockTimes.add(Integer.parseInt(dataArray[i]));
                }

            }

        }

        System.out.printf("FCFS Total Seek: ");
        firstComeFirstServe(blockTimes);

        System.out.printf("SSTF Total Seek: ");
        shortestSeekFirst(blockTimes);

        System.out.printf("LOOK Total Seek: ");
        nonCircularLook(blockTimes);

        System.out.printf("C-LOOK Total Seek: ");
        cLook(blockTimes);

    }
    public static void printResults(int result){
        System.out.println(result);
    }
    public static void firstComeFirstServe(ArrayList<Integer> data){
        int totalTime = 0;
        for(int i = 0; i < data.size() - 1; i++){
            totalTime += Math.abs(data.get(i) - data.get(i + 1));
        }
        printResults(totalTime);
    }

    public static void shortestSeekFirst(ArrayList<Integer> data){
        ArrayList<Integer> d = new ArrayList<>();
        d.addAll(data);
        int difference = 1000000;
        int totalTime = 0;
        int nextIndex = 0;
        int blockReq = 0;
        while(d.size() > 1){
            for(int j = d.size() - 1; j >= 0; j--){
                if(Math.abs(d.get(blockReq) - d.get(j)) <= difference && Math.abs(d.get(blockReq) - d.get(j)) > 0){
                    difference = Math.abs(d.get(blockReq) - d.get(j));
                    nextIndex = j;
                }
            }
            totalTime += Math.abs(d.get(blockReq) - d.get(nextIndex));
            d.remove(blockReq);
            if(nextIndex < blockReq){
                blockReq = nextIndex;
            }else {
                blockReq = nextIndex - 1;
            }//reset difference
            difference = 1000000;
        }
        printResults(totalTime);

    }

    public static void nonCircularLook(ArrayList<Integer> data){
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> shortList = new ArrayList<>();
        d.addAll(data);
        int temp = 0;
        int currentTime = d.get(0);

        //trim anything smaller than starting point
        for(int i = 0; i < d.size(); i++){
            if(d.get(i) < currentTime){
                temp = d.get(i);
                d.remove(i);
                i--;
                shortList.add(temp);
            }
        }
        //sort Arrays
        Collections.sort(d);
        Collections.sort(shortList);
        //reverse the lower nums
        Collections.reverse(shortList);
        //add short nums back on original array in descending order
        d.addAll(shortList);
        //then just FCFS
        firstComeFirstServe(d);


    }

    public static void cLook(ArrayList data){
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> shortList = new ArrayList<>();
        d.addAll(data);
        int temp = 0;
        int currentTime = d.get(0);

        //trim anything smaller than starting point
        for(int i = 0; i < d.size(); i++){
            if(d.get(i) < currentTime){
                temp = d.get(i);
                d.remove(i);
                i--;
                shortList.add(temp);
            }
        }
        //sort Arrays
        Collections.sort(d);
        Collections.sort(shortList);
        //add short nums back on original array in descending order
        d.addAll(shortList);
        //then just FCFS
        firstComeFirstServe(d);
    }


}

