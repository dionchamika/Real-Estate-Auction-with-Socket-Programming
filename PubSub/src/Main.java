import java.io.BufferedReader; // line by line by readLine() method  // efficiently read
import java.io.FileNotFoundException; // Identifying a specific path
import java.io.FileReader; // CSV FILE READ
import java.io.IOException; // Managing those input,output streams
import java.text.SimpleDateFormat; // SHOW DATE FORMAT
import java.util.HashMap; 
import java.util.Map; // SHOW PROPERTY AND VALUE

public class Main {
	
	private static final Map<String, Topic> topics = new HashMap<>();

	public static void main(String[] args) throws IOException {
		readCSV();
		
		TimeHandler timeHandler = new TimeHandler(2 * 60 * 1000); // Convert into milli-seconds
		
		// pub-sub server
		Server2 server2 = new Server2(topics);
		server2.start();                                // PUB SUB SOCKET - START
		
		// client server
		Server1 server1 = new Server1(topics, timeHandler, server2);
		server1.start();                               

														// CLIENT SERVER SOCKET - START
		
		new Thread() {
			public void run() {
				while (true) {
					long s = timeHandler.getRemainingTime()/1000;
					String time = String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
					System.out.println("Remaining time " + time);                                              // TIME CONVERTER

					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
	}
	

	
	private static void readCSV() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        Topic t = new Topic();
				t.setSym(values[0]);
				t.setValue(Integer.parseInt(values[1]));
				topics.put(t.getSym(), t);                           // CSV FILE READ
		    }
		}
	}

}
