import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/** program to find compression ratio using Huffman coding
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();
		String inputFileName = "large.txt";
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName),Charset.forName("ASCII")));
		int newlineCount = 0;
		
		/*
		This map will store all the unique standard ASCII characters
		and the number of times they occur in the given file
		 */
		Map<Integer, Integer> leafNodes = new HashMap<Integer, Integer>();
		int charcount = 0;
		int j;
		while ((j = in.read()) != -1)//Read the file char by char
		{
			if (j==10){//If newline character
				newlineCount++;//Increment newline counter to use later for pre-compressed file length
			}else
			{
				charcount++;
			}
			Integer freq = leafNodes.get(j);//Get the value associated with the character, c
			leafNodes.put(j, (freq==null) ? 1: freq + 1);//If the character isn't in the map, add it to the map 
														 //otherwise add one to the value
		}
		
		in.close();
		
		/*
		This array will store initially all the values of the leafNodes,
		values being the number of occurrences of each leafNodes key in the text file
		the array will the grow smaller as we sum the two smallest values,
		until we only have one value left, the value representing the root of a Huffman tree
		The sum of all sums performed will be the weighted path length 
		 */
		List<Integer> valueArray = new ArrayList<Integer>();
		
		for ( Integer value:leafNodes.values())	//iterate through all the leaf nodes 
		{
			valueArray.add(value);//add the value of each leaf node to an array
		}

		Integer min1 = 0;
		Integer min2 = 0;
		Integer WPL = 0;
		int minIndex = 0;
		
		/*
		This loop will iterate through the leaf nodes,
		finding the sum of the smallest two leaf nodes,
		then adding this sum to the weighted path length
		*/
		while (valueArray.size()>1)//Until we have reached the root of the 'Huffman tree'
		{
			min1 = Collections.min(valueArray);//Find the lowest value in the array
			minIndex = valueArray.indexOf(min1);//Find the index of the lowest value in the array
			valueArray.remove(minIndex);//Remove the minimum value from the array
			min2 = Collections.min(valueArray);//Find the lowest value in the array
			minIndex = valueArray.indexOf(min2);//Find the index of the lowest value in the array
			valueArray.set(minIndex, min1+min2);//Set minimum value in array to new calculated value
			WPL += (min1+min2);//Increment WPL counter
		}
		
		int uncompressedFile = (charcount+newlineCount)*8;//Number of characters in .txt including new line characters
														  //multiply by 8 to get the number of bits
		float result = ((float)WPL / (float)uncompressedFile);//Calculate compression as a float
		long end = System.currentTimeMillis();
		
		String outputFileName = "results.txt";
		FileWriter writer = new FileWriter(outputFileName);
		writer.write("Uncompressed file length of " + inputFileName + " in bits " + uncompressedFile);
		writer.write("Compressed file length of " + inputFileName + " in bits " + WPL);
		writer.write("Compression ratio = " + result);
		writer.write("\nElapsed time: " + (end - start) + " milliseconds");
		writer.close();
	}
	
}

