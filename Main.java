import java.io.*;
import java.nio.charset.Charset;


/** program to find compression ratio using LZW compression
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = "large.txt";
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName),Charset.forName("ASCII")));
		Trie trie = new Trie();//Create a new trie object
		int charcount = 1;//Char count is 1 as we read first char of file before the loop begins, used to find uncompressed file size
		int trieSize = 1;//Size of the trie 
		int i=0;
		int codeword = 8;//Codeword is initially 0, to be incremented by 1 when trie is full
		double capacity = Math.pow(2,codeword);//Capacity of the trie
		
		for (i=0;i<128;i++)//For all of the ascii character set
		{
			trie.insert((char)i,null);//Initialise the trie with all single digit strings that will be in the txt file(standard ASCII)
			trieSize++;
		}
		long one = System.currentTimeMillis();
		System.out.println(one-start);
		
		int chr = in.read();//read the first char of the file
		char c = (char)chr;
		int compressedFile = 0;
		Node temp = null;
		Node node = trie.search(c,null);
		
		while ((chr = in.read()) != -1)
		{
			c = (char) chr;
			charcount++;
			temp = trie.search(c,node);
			if (temp==node)//If node hasn't changed after search function
			{
				trieSize++;
				if (trieSize>=capacity)//If the trie is at capacity or over
				{
					codeword++;//Increase the codeword length
					capacity=Math.pow(2,codeword);//therefore increasing the capacity by 2x
				}
				trie.insert(c, node);//Insert character c as child of Node node
				compressedFile += codeword;//Increment size of compressed file
				node = trie.search(c, null);/*
											Set node to use in next iterations search to one of the nodes which is a child node of the root
											or sibling node to the child etc, and node also represents character c, which is also the character
											the new node represents
											*/
			}else//As search will either return insert or iterate
			{
				node = temp;//This is the action for iterate, to set the node for the next search as the result of this search iteration
			}
		}
		
		in.close();
		long two = System.currentTimeMillis();
		System.out.println(two-start);
			
		// Write out the results here
		int uncompressedFile = (charcount*8);//Number of characters * 8(as each character is one byte)
		float result=(float)compressedFile/(float)uncompressedFile;//Calculate compression rate as a float
		long end = System.currentTimeMillis();
		System.out.println(end-start);

		String outputFileName = "results.txt";
		FileWriter writer = new FileWriter(outputFileName);
		writer.write("Uncompressed file length of " + inputFileName + " in bits " + uncompressedFile);
		writer.write("Compressed file length of " + inputFileName + " in bits " + compressedFile);
		writer.write("Compression ratio = " + result);
		writer.write("Elapsed time: " + (end - start) + " milliseconds");
		writer.close();
	}

}
