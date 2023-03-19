import java.io.*;
import java.util.*;
public class SuperListRunner {
    public static void main(String[] args){
        int nums = 30;
        SuperList<Integer> arr = new SuperList<Integer>();
        SuperList<Integer> st = new SuperList<Integer>();
        SuperList<Integer> q = new SuperList<Integer>();
        SuperList<String> words = new SuperList<String>();

    // fill array list
        for(int i=0; i<nums; i++){
            arr.add((int)(Math.random()*1000)+1);
        }
        System.out.println("ArrayList: " + arr);

    // fill stack
        for(int i=0; i<nums; i++){
            st.push(arr.remove(0));
        }
        System.out.println("Stack: " + st);

    //fill queue
        for(int i=0; i<nums; i++){
            q.add(st.pop());
        }
        System.out.println("Queue: " + q);

    //refill array list
        for(int i=0; i<nums; i++){
            if(i == 0)
                arr.add(q.poll());
            else{
                int index = (int)(Math.random()*arr.size()+1);
                arr.add(index, q.poll());
            }
        }
        System.out.println("Refilled ArrayList: " + arr);

    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    //sums of array list
        int sum = 0, evenSum = 0, oddSum = 0;
        for(int i=0; i<nums; i++){
            sum += arr.get(i);
            if(i%2 == 0)
                evenSum += arr.get(i);
            else 
                oddSum += arr.get(i);
        }
        System.out.println("Sum of all values in list: " + sum);
        System.out.println("Sum of even-indexed values in list: " + evenSum);
        System.out.println("Sum of odd-indexed values in list: " + oddSum);
    
    // duplicates of evens
        int ogSize = arr.size();
        for(int i=0; i<ogSize; i++){
            if(arr.get(i)%2 == 0){
                arr.add(arr.get(i));
            }
        }
        System.out.println("ArrayList with Duplicated Even Numbers: " + arr);

    // remove multiples of three
        //ogSize = arr.size();
        for(int i=arr.size()-1; i>=0; i--){
            if(arr.get(i)%3 == 0){
                arr.remove(i);
            }
        }
        System.out.println("ArrayList without Multiples of Three: " + arr);

    // add 5555
        arr.add(3, 55555);
        System.out.println("ArrayList with '55555' in the Fourth Spot: " + arr);

    // descending sort     
        for (int i = 0; i < arr.size(); i++) {
            int max = arr.get(i);
            int ind = i;
            for(int j=i+1; j<arr.size(); j++){
                if(arr.get(j) >= max){
                    max = arr.get(j);
                    ind = j;
                }
            }
            arr.add(i,arr.remove(ind));
        }
        System.out.println("ArrayList Sorted in Descending Order: " + arr);

    // get median of list
        double med = 0, medIndex = 0;
        if(arr.size()%2 == 0){
            med = (arr.get(arr.size()/2)+arr.get((arr.size()/2)-1))/2;
            medIndex = ((arr.size()/2)+((arr.size()/2)-1))/2;
        }
        else{
            med = arr.get((arr.size()/2));
            medIndex = arr.size()/2;
        }

        SuperList<Integer> beforeMed = new SuperList<Integer>();
        SuperList<Integer> afterMed = new SuperList<Integer>();
        for(int i=0; i<arr.size(); i++){ 
            if(i > medIndex)
                beforeMed.add(arr.get(i));
            else if(i < medIndex)
                afterMed.add(arr.get(i));
        }
        System.out.println("Median of ArrayList: " + med);
        System.out.println("Values Less Than Median of ArrayList: " + beforeMed);
        System.out.println("Values Greater Than Median of ArrayList: " + afterMed);

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

    // split sentence into list
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Please enter a sentence: ");
        // String sentence = sc.nextLine();
        String sentence = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        System.out.println("Sentence: " + sentence);
        String[] split = sentence.split("[ ,.]");
        for(int i=0; i<split.length; i++){
            words.add(split[i]);
        }
        System.out.println("Words in Sentence: " + words);
    
    // remove words with three letters or less
        for(int i=words.size()-1; i>=0; i--){
            if(words.get(i).length() <= 3)
                words.remove(i);
        }
        System.out.println("Words in Sentence with More than Three Letters: " + words);

    // sort words in ascending order with insertion sort
        for(int i=1; i<words.size(); i++){
            String word = words.get(i);
            int j = i-1;
            while(j >= 0 && word.toLowerCase().compareTo(words.get(j).toLowerCase()) < 0){
                words.add(j,words.remove(j+1));
                j--;    
            }
        }
        System.out.println("Words in Sentence Sorted in Alphabetical Order: " + words);

    // average word length of words
        int wordSum = 0;
        for(int i=0; i<words.size(); i++){
            wordSum += words.get(i).length();
        }
        System.out.println("Average Word Length in Sentence: " + wordSum/words.size());
    }
}
