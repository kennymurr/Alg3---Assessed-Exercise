import java.io.*;
import java.util.*;

/** program to find compression ratio using LZW compression
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = "small.txt";
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		int newlineCount = 0;
		char c;
		Trie trie = new Trie();
		Node node;
		Node prev;
		int i=0;
		c = (char) i;
		
		for (i=0;i<128;i++)//for all of the ascii character set
		{
			trie.insert(c,null);
		}
		
		while (in.hasNextLine())
		{
			c=in.nextLine();
			System.out.println(c);
			//if (!(trie.search(c)))
			//{
			//	trie.insert(c);
			//}
		}
		
		// read in the data and do the work here
        // read a line at a time to enable newlines to be detected and included in the compression

		reader.close();
		
		String outputFileName = args[1];
		FileWriter writer = new FileWriter(outputFileName);
		writer.write("Input file " + inputFileName + "  LZW algorithm\n\n");
		
		// write out the results here

		long end = System.currentTimeMillis();
		writer.write("\nElapsed time: " + (end - start) + " milliseconds");
		writer.close();
	}

}
