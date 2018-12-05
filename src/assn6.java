import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* Promise of Originality
I promise that this source code file has, in its entirety, been
written by myself and by no other person or persons. If at any time an
exact copy of this source code is found to be used by another person in
this term, I understand that both myself and the student that submitted
the copy will receive a zero on this assignment.
*/

public class assn6 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> blockTimes = new ArrayList<>();
        System.out.println("Assignment 6: Block Access Algorithms\nBy: Isaiah Larsen\n");
        String[] dataArray;
        String data;
        //read from file
        if(args.length > 0) {
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((data = br.readLine()) != null){
                dataArray = data.split(" ");
                for(int i = 0; i < dataArray.length; i++){
                    blockTimes.add(Integer.parseInt(dataArray[i]));
                }
            }
            //read from stdin
        }else{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while((data = in.readLine()) != null){
                dataArray = data.split(" ");
                for(int i = 0; i < dataArray.length; i++){
                    blockTimes.add(Integer.parseInt(dataArray[i]));
                }

            }

        }

        //Call Block Access Algorithms
        System.out.printf("FCFS Total Seek: ");
        firstComeFirstServe(blockTimes);

        System.out.printf("SSTF Total Seek: ");
        shortestSeekFirst(blockTimes);

        System.out.printf("LOOK Total Seek: ");
        nonCircularLook(blockTimes);

        System.out.printf("C-LOOK Total Seek: ");
        cLook(blockTimes);

    }

    /**
     * Method to print the Total Seek Time
     * @param result the total seek time
     */
    public static void printResults(int result){
        System.out.println(result);
    }

    /**
     * First come first served algorithm
     * Takes them as they come
     * add all differences and print result
     * @param data array list of block times
     */
    public static void firstComeFirstServe(ArrayList<Integer> data){
        int totalTime = 0;
        //loop through add the differences
        for(int i = 0; i < data.size() - 1; i++){
            totalTime += Math.abs(data.get(i) - data.get(i + 1));
        }
        printResults(totalTime);
    }

    /**
     * Shortest seek time first
     * Take the shortest difference between current time and the next
     * @param data array list of block times
     */
    public static void shortestSeekFirst(ArrayList<Integer> data){
        ArrayList<Integer> d = new ArrayList<>();
        d.addAll(data);
        int difference = 1000000;
        int totalTime = 0;
        int nextIndex = 0;
        int blockReq = 0;

        while(d.size() > 1){
            //loop to find smallest difference between current block time and the possible next time
            for(int j = d.size() - 1; j >= 0; j--){
                if(Math.abs(d.get(blockReq) - d.get(j)) <= difference && Math.abs(d.get(blockReq) - d.get(j)) > 0){
                    difference = Math.abs(d.get(blockReq) - d.get(j));
                    nextIndex = j;
                }
            }
            //add time
            totalTime += Math.abs(d.get(blockReq) - d.get(nextIndex));
            //remove that index so it doesn't get repeated
            d.remove(blockReq);

            //keep indexes correct after deleting an item
            if(nextIndex < blockReq){
                blockReq = nextIndex;
            }else {
                blockReq = nextIndex - 1;
            }
            //reset difference
            difference = 1000000;
        }
        printResults(totalTime);

    }

    /**
     * LOOK algorithm goes like an elevator
     * start at first time and then ascend then go back up.
     * @param data array list of block times
     */
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

    /**
     * CLOOK algorithm
     * Start at first time ascend then start back at the top
     * @param data array list of block times
     */
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

