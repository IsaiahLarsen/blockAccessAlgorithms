import java.io.*;
import java.util.ArrayList;

public class assn6 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> blockTimes = new ArrayList<>();
        System.out.println("Assignment 6 by Isaiah Larsen");
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
        System.out.println(blockTimes.toString());
        firstComeFirstServe(blockTimes);
        shortestSeekFirst(blockTimes);

    }
    public static void printResults(int result){
        System.out.println(result);
    }
    public static void firstComeFirstServe(ArrayList<Integer> data){
        int totalTime = 0;
        for(int i = 0; i < data.size() - 1; i++){
            totalTime += Math.abs(data.get(i) - data.get(i + 1));
        }
        System.out.printf("FCFS Total Seek time: ");
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
        System.out.printf("SSTF Total seek: ");
        printResults(totalTime);

    }

    public void nonCircularLook(ArrayList data){

    }

    public void look(ArrayList data){

    }


}

