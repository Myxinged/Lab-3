import java.util.*;
import java.io.*;

public class Lab3{
public static void main(String[] args) throws Exception {

	int row = 109;
	int col = 2;
	String[][] StreamList = new String[row][col];
	read(StreamList);

	//adds elements from the 2D array into the linked list
	TopStreamingArtists streams = new TopStreamingArtists();
	for(int r = 0; r < row; r++) {
		streams.insertFirst(StreamList[r][0], StreamList[r][1]);
	}
	streams.AscendingSort();
	streams.displayList();
}

	//Reads the data from the SpotifyData.csv file
	public static void read(String[][]arr) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("C:\\Java\\Lab-3\\SpotifyData.csv"));
		String data = br.readLine();

		//splits the commas.
		String[] line = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		arr[0][0] = line[2].trim();
		arr[0][1] = Integer.toString(1);

		int rowCount = 1;
		for(int r = 1; r < arr.length+1; r++) {
			data = br.readLine();
			line = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			if(search(arr, line[2].trim(), r) == 0) {
				arr[rowCount][0] = line[2].trim();
				arr[rowCount][1] = Integer.toString(1);
				rowCount++;
		}
			else {
				int pos = search(arr, line[2].trim(), r);
				int count = Integer.parseInt(arr[pos][1])+1;
				String c = Integer.toString(count);
				arr[pos][1] = c;
				r = rowCount;
			}
		}
		br.close();
	}

	//searches the artists that have the same name if artist not found return 0
	public static int search(String[][] arr, String name, int pos) {
		for(int i = 0; i < pos; i++) {
			if(name.equals(arr[i][0])) {
				return i;
			}
		}
		return 0;
	}
	}

	class Artist {
		private String artist;
		private String artistsCount;
		public Artist next;

		Artist(String artist, String artistsCount) {
			this.artist = artist;
			this.artistsCount = artistsCount;
		}

		public void setArtist(String x) {
			artist = x;
		}

		public void SetArtistsCount(String y) {
			artistsCount = y;
		}

		public String getArtist() {
			return artist;
		}

		public String getArtistsCount() {
			return artistsCount;
		}

		public String displayArtist(){
			return(artist + ", " + artistsCount);
		}
	}


	class TopStreamingArtists {
		private Artist first;

		public TopStreamingArtists() {
			first = null;
		}

		public boolean isEmpty() {
			return (first == null);
		}

		//inserts each element as the first in the linkedlist
		public void insertFirst(String artistName, String numAppear) {
			Artist a = new Artist(artistName, numAppear);
			a.next = first;
			first = a;
		}

		//sorts as ascending alphabetical order using Artist names.
		public void AscendingSort() {
			Artist current = first;
			Artist next = null;
			String NameTemp;
			String CountTemp;
			if(first == null) {
				return;
			}
				else {
					while(current != null) {
						next = current.next;
						while(next != null) {
							if(current.getArtist().compareToIgnoreCase(next.getArtist()) > 0) {
								NameTemp = current.getArtist();
								CountTemp = current.getArtistsCount();

								current.setArtist(next.getArtist());
								current.SetArtistsCount(next.getArtistsCount());

								next.setArtist(NameTemp);
								next.SetArtistsCount(CountTemp);
							}
							next = next.next;
						}
						current = current.next;
					}
				}
		}

		public void displayList() throws Exception{
			PrintWriter pw = new PrintWriter("C:\\Java\\Lab-3\\Artist.txt");
			Artist current = first;
			while(current != null){
	current.displayArtist();
	pw.println(current.displayArtist());
	current = current.next;
			}
			pw.close();
		}
}
