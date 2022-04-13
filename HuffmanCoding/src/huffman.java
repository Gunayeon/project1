import java.util.*;
import java.io.*;
class Node{
    public char character;
    public int frequency;
    public Node left,right;
}
class FrequencyComparater implements Comparator<Node>{
    public int compare(Node a,Node b){
        int frequencyA=a.frequency;
        int frequencyB=b.frequency;
        return frequencyA-frequencyB;
    }

}
public class huffman{
    public static PriorityQueue<Node>queue;
    public static HashMap<Character,String>charToCode=new HashMap<Character,String>();

    public static Node huffmanCoding(int n){
        for(int i=0;i<n-1;i++){
            Node z=new Node();
            z.right=queue.poll();
            z.left=queue.poll();
            z.frequency=z.right.frequency+z.left.frequency;
            queue.add(z);
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        String text=new Scanner(System.in).next();
        HashMap<Character,Integer>dictionary=new HashMap<Character,Integer>();

        for(int i=0;i<text.length();i++){
            char temp=text.charAt(i);

            if(dictionary.containsKey(temp))
                dictionary.put(temp,dictionary.get(temp)+1);
            else
                dictionary.put(temp,1);
        }
        queue=new PriorityQueue<Node>(100,new FrequencyComparater());
        int number=0;

        for(Character c:dictionary.keySet()){
            Node temp=new Node();
            temp.character=c;
            temp.frequency= dictionary.get(c);
            queue.add(temp);
            number++;
        }
        Node root=huffmanCoding(number);
        traversal(root,new String());

        String result=new String();
        for(int i=0;i<text.length();i++)
            result=result+charToCode.get(text.charAt(i))+" ";
        System.out.println(result);
    }
    public static void traversal(Node n,String s){
        if(n==null)
            return;
        traversal(n.left,s+"0");
        traversal(n.right,s+"1");
        if(n.character!='\0'){
            System.out.println(n.character+":"+s);
            charToCode.put(n.character,s);
        }
    }
}
